package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;


/**
 * Represents an implementation of  Command.
 * 
 * @Command DeleteCustomer
 * 
 * @see Command
 */

public class DeleteCustomer implements Command {
	private int customerid;
	  

    
    public DeleteCustomer(int customerid) {
    	
        this.customerid = customerid;
        
    }
    /**
     * Executes the Command and sets the deleted value of the customer to true
     * 
     * @return if it was removed succefully
     */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		
		Customer customer = flightBookingSystem.getCustomerByID(customerid);
		customer.setDeleted(true);
		
		System.out.println("Customer Removed Successfully");
		
	}
}
