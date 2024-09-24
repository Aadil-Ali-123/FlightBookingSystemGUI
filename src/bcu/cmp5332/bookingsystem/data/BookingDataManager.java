package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class BookingDataManager implements DataManager {
    
    public final String RESOURCE = "./resources/data/bookings.txt";

    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        // TODO: implementation here
    	 try (Scanner sc = new Scanner(new File(RESOURCE))) {
             int line_idx = 1;
             while (sc.hasNextLine()) {
                 String line = sc.nextLine();
                 String[] properties = line.split(SEPARATOR, -1);
                 try {
                	 int idcus = Integer.parseInt(properties[0]);
                     String name = properties[1];
                     String phone = properties[2];
                     String email = properties[3];
                     Customer customer = new Customer(idcus,name,phone,email,false);
                     
                     int idfly = Integer.parseInt(properties[4]);
                     String flightNumber = properties[5];
                     String origin = properties[6];
                     String destination = properties[7];
                     LocalDate departureDate = LocalDate.parse(properties[8]);
                     int price = Integer.parseInt(properties[9]);
                     int seats = Integer.parseInt(properties[10]);
                     Flight flight = new Flight(idfly, flightNumber, origin, destination, departureDate,seats,price,false);
                     LocalDate bookingdate = LocalDate.parse(properties[11]);
                     int fee = Integer.parseInt(properties[12]);
                     
                     Booking booking = new Booking(customer, flight, bookingdate,fee);
                     fbs.getCustomerByID(idcus).addBooking(booking);
                     customer.addBooking(booking);
                     fbs.getFlightByID(idfly).addPassenger(customer);
                     flight.addPassenger(customer);
                     
                     
                 } catch (NumberFormatException ex) {
                     throw new FlightBookingSystemException("Unable to parse book id " + properties[0] + " on line " + line_idx
                         + "\nError: " + ex);
                 }
                 line_idx++;
             }
         }
    }
    
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Flight flight : fbs.getFlights()) { 
            	for (Customer customer : fbs.getCustomers()) {
            		for(int x=0; x < customer.getBookings().size(); x++) {
            			if(customer.getBookings().get(x).getFlight() == flight) {
            				
            				out.print(customer.getID() + SEPARATOR);
                            out.print(customer.getName() + SEPARATOR);
                            out.print(customer.getPhone() + SEPARATOR);
                            out.print(customer.getEmail() + SEPARATOR);
                            out.print(flight.getId() + SEPARATOR);
                            out.print(flight.getFlightNumber() + SEPARATOR);
                            out.print(flight.getOrigin() + SEPARATOR);
                            out.print(flight.getDestination() + SEPARATOR);
                            out.print(flight.getDepartureDate() + SEPARATOR);
                            out.print(flight.getSeats() + SEPARATOR);
                            out.print(flight.getPrice() + SEPARATOR);
                            out.print(customer.getBookings().get(x).getBookingDate() + SEPARATOR);
                            out.print(customer.getBookings().get(x).getFee() + SEPARATOR);
                            out.println();
            			}
            		}


            }
            }
            
            
            
    }
    }
    
    
}
