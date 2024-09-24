package bcu.cmp5332.bookingsystem.commands;

import java.time.LocalDate;
import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ShowFlight implements Command {
	
	private final int flightid;
    
    public ShowFlight(int flightid) {
    	this.flightid = flightid;
    }
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		Flight flight = flightBookingSystem.getFlightByID(flightid);
		if(flight.getDeleted() == false) {
			System.out.println(flight.getDetailsLong());
		}else {
			System.out.println("Flight has been Removed");
		}
		
		
	}

}
