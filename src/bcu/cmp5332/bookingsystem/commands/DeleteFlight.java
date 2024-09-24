package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.time.LocalDate;
/**
 * Represents an implementation of  Command.
 * 
 * @Command DeleteFlight
 * 
 * @see Command
 */
public class DeleteFlight implements  Command {
	
    	private int flightid;
  

	    
	    public DeleteFlight(int flightid) {
	    	
	        this.flightid = flightid;
	        
	    }
	    
	    /**
	     * Executes the Command and sets the deleted value of the flight to true
	     * 
	     * @return if it was removed succefully
	     */
	    
		@Override
		public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
			
			Flight flight = flightBookingSystem.getFlightByID(flightid);
			flight.setDeleted(true);
			
			System.out.println("Flight Removed Successfully");
			
		}

	}


