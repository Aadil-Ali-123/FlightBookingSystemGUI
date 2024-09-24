package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

public class ListFlights implements Command {

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
    	int size = 0;
    	List<Flight> flights = flightBookingSystem.getFlights();
        for (Flight flight : flights) {
        	if (flight.getDeleted() == false) {
        		size +=1;
        		System.out.println(flight.getDetailsShort());
        	}
            
        }
        System.out.println(size + " flight(s)");
    }
}
