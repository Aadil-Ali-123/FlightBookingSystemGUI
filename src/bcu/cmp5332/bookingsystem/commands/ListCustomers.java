package bcu.cmp5332.bookingsystem.commands;

import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ListCustomers implements Command {

	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		int size = 0;
		List<Customer> customers = flightBookingSystem.getCustomers();
        for (Customer customer : customers) {
        	if (customer.getDeleted() == false) {
        		size +=1;
        		System.out.println(customer.getDetailsShort());
        	}
           
        }
        System.out.println(size + " customer(s)");
		
	}

}
