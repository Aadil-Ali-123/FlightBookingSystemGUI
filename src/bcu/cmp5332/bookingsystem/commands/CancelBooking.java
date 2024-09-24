package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class CancelBooking implements Command {
	private int customerid;
    private int flightid;
    
    public CancelBooking(int customerid, int flightid) {
    	this.customerid = customerid;
        this.flightid = flightid;
        
    }
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		Customer customer = flightBookingSystem.getCustomerByID(customerid);
		Flight flight = flightBookingSystem.getFlightByID(flightid);
		customer.cancelBookingForFlight(flight);
		flight.removePassenger(customer);
		flight.addcancellation(customer);
		System.out.println("Booking was cancelled successfully and a fee of £50 has been assigned");
		
	}

}
