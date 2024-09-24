package bcu.cmp5332.bookingsystem.commands;

import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
/**
 * Represents an implementation of  Command.
 * 
 * @Command AddBooking
 * 
 * @see Command
 */
public class AddBooking implements Command {
	
	private int customerid;
    private int flightid;
    

    public AddBooking(int customerid, int flightid) {
    	this.customerid = customerid;
        this.flightid = flightid;
        
    }
    /**
     * Executes the Command and checks if there is enough seats available on the flight
     * 
     * @return if the booking was succefully added or not
     */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		LocalDate now = LocalDate.now();
		Customer customer = flightBookingSystem.getCustomerByID(customerid);
		Flight flight = flightBookingSystem.getFlightByID(flightid);
		if (flight.getPassengers().size() < flight.getSeats()) {
			if(flight.getCancellations().contains(customer)) {
				int fee = 50;
				Booking booking = new Booking(customer,flight, now,fee);
				customer.addBooking(booking);
				flight.addPassenger(customer);
			}else {
				Booking booking = new Booking(customer,flight, now,0);
				customer.addBooking(booking);
				flight.addPassenger(customer);
			}
			System.out.println(" Booking was issued successfully to the customer");
		}else {
			System.out.println("There are no more seats available for this flight");
		}
		
		
	}

}
