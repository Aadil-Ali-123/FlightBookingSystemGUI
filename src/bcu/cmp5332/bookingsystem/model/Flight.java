package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Flight {
    
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private int seats;
    private int price;
    private boolean deleted;

    private final Set<Customer> passengers;
    private final Set<Customer> cancelations;
    
    public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate,int seats, int price,boolean deleted) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.seats = seats;
        this.price = price;
        this.deleted = deleted;
        passengers = new HashSet<>();
        cancelations = new HashSet<>();
    }
    public boolean getDeleted() {
    	return deleted;
    }
    
    public void setDeleted(boolean deleted) {
    	this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
    
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }
    
    public List<Customer> getCancellations() {
        return new ArrayList<>(cancelations);
    }
	
    public String getDetailsShort() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate.format(dtf) + " - £Price " + price + " - Max seats " + seats;
    }

    public String getDetailsLong() {
        // TODO: implementation here
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-YYYY");
    	String y = "";
    	
    	for(Customer x : this.passengers) {
    		y += "\n * " + x.getDetailsShort();
    	}
    	return "Flight #" + id + '\n' + "Flight No: " + flightNumber + '\n' + "Origin: " + origin + '\n' + "Destination: " 
        + destination + '\n' + "Depature Date: " + departureDate.format(dtf) + '\n' +  "Price: " + price + '\n' + "Max Seats: " + seats + '\n' +
        "---------------------------" + '\n' + "Passengers:" + '\n' + y + "\n" + this.passengers.size() + " passenger(s)";
    }
    
    public void addPassenger(Customer passenger) throws FlightBookingSystemException{
        if(this.passengers.contains(passenger)) {
        	throw new FlightBookingSystemException("Passenger is already present in the flights list of passengers");
        }else {
        	this.passengers.add(passenger);
        }
    }
    
    public void removePassenger(Customer passenger) throws FlightBookingSystemException{
        if(this.passengers.contains(passenger)) {
        	this.passengers.remove(passenger);
        }else {
        	throw new FlightBookingSystemException("Passenger is not present in the flights list of passengers");
        }
    }
    
    public void addcancellation(Customer passenger) throws FlightBookingSystemException{
        if(this.cancelations.contains(passenger)) {
        	throw new FlightBookingSystemException("Passenger is already present in the flights list of passengers");
        }else {
        	this.cancelations.add(passenger);
        }
    }
    
    public void removecancelation(Customer passenger) throws FlightBookingSystemException{
        if(this.cancelations.contains(passenger)) {
        	this.cancelations.remove(passenger);
        }else {
        	throw new FlightBookingSystemException("Passenger is not present in the flights list of passengers");
        }
    }
}
