package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.commands.LoadGUI;
import bcu.cmp5332.bookingsystem.commands.ListFlights;
import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.DeleteCustomer;
import bcu.cmp5332.bookingsystem.commands.DeleteFlight;
import bcu.cmp5332.bookingsystem.commands.EditBooking;
import bcu.cmp5332.bookingsystem.commands.Help;
import bcu.cmp5332.bookingsystem.commands.AddBooking;
import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.commands.CancelBooking;
import bcu.cmp5332.bookingsystem.commands.ListCustomers;
import bcu.cmp5332.bookingsystem.commands.ShowCustomer;
import bcu.cmp5332.bookingsystem.commands.ShowFlight;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CommandParser {
    
    public static Command parse(String line) throws IOException, FlightBookingSystemException {
        try {
            String[] parts = line.split(" ", 3);
            String cmd = parts[0];

            
            if (cmd.equals("addflight")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Flight Number: ");
                String flighNumber = reader.readLine();
                System.out.print("Origin: ");
                String origin = reader.readLine();
                System.out.print("Destination: ");
                String destination = reader.readLine();
                System.out.print("Price: ");
                String price = reader.readLine();
                System.out.print("Max Seats: ");
                String seats = reader.readLine();
                int priceint = Integer.parseInt(price);
                int seatsint = Integer.parseInt(seats);
                LocalDate departureDate = parseDateWithAttempts(reader);

                return new AddFlight(flighNumber, origin, destination, departureDate,seatsint,priceint,false);
                
            } else if (cmd.equals("addcustomer")) {
            	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Customer Name: ");
                String custName = reader.readLine();
                System.out.print("Phone Number: ");
                String phone = reader.readLine();
                System.out.print("Email: ");
                String email = reader.readLine();
                return new AddCustomer(custName, phone, email);
                
            } else if (cmd.equals("loadgui")) {
                return new LoadGUI();
                
            } else if (parts.length == 1) {
                if (line.equals("listflights")) {
                    return new ListFlights();
                } else if (line.equals("listcustomers")) {
                    return new ListCustomers();
                } else if (line.equals("help")) {
                    return new Help();
                }
            } else if (parts.length == 2) {

                if (cmd.equals("showflight")) {
                    return new ShowFlight(Integer.parseInt(parts[1]));
                    
                } else if (cmd.equals("showcustomer")) {
                	
                    return new ShowCustomer(Integer.parseInt(parts[1]));
                }else if (cmd.equals("deleteflight")) { 
                	return new DeleteFlight(Integer.parseInt(parts[1]));
                }else if (cmd.equals("deletecustomer")) { 
                	return new DeleteCustomer(Integer.parseInt(parts[1]));
                }
            } else if (parts.length == 3) {
                

                if (cmd.equals("addbooking")) {
                    return new AddBooking(Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
                    
                } else if (cmd.equals("editbooking")) {
                    return new EditBooking();
                } else if (cmd.equals("cancelbooking")) {
                    return new CancelBooking(Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
                    
                }
            }
        } catch (NumberFormatException ex) {

        }

        throw new FlightBookingSystemException("Invalid command.");
    }
    
    private static LocalDate parseDateWithAttempts(BufferedReader br, int attempts) throws IOException, FlightBookingSystemException {
        if (attempts < 1) {
            throw new IllegalArgumentException("Number of attempts should be higher that 0");
        }
        while (attempts > 0) {
            attempts--;
            System.out.print("Departure Date (\"YYYY-MM-DD\" format): ");
            try {
                LocalDate departureDate = LocalDate.parse(br.readLine());
                return departureDate;
            } catch (DateTimeParseException dtpe) {
                System.out.println("Date must be in YYYY-MM-DD format. " + attempts + " attempts remaining...");
            }
        }
        
        throw new FlightBookingSystemException("Incorrect departure date provided. Cannot create flight.");
    }
    
    private static LocalDate parseDateWithAttempts(BufferedReader br) throws IOException, FlightBookingSystemException {
        return parseDateWithAttempts(br, 3);
    }
}
