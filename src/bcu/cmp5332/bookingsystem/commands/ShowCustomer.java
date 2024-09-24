package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ShowCustomer implements Command {
	private final int customerid;
	
	public ShowCustomer(int customerid) {
    	this.customerid = customerid;
    }
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		Customer customer = flightBookingSystem.getCustomerByID(customerid);
		
		if(customer.getDeleted() == false) {
			System.out.println(customer.getDetailsLong());
		}else {
			System.out.println("customer has been Removed");
		}
		
	}

}
