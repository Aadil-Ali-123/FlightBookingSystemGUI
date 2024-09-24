package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private String email;
    private boolean deleted;
    private final List<Booking> bookings = new ArrayList<>();
    
    // TODO: implement constructor here
    public Customer(int id, String name, String phone,String email,boolean deleted) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.deleted = deleted;
        
        
    }
    // TODO: implementation of Getter and Setter methods
    public int getID() {
		// TODO Auto-generated method stub
		return this.id;
	}
    
    public boolean getDeleted() {
    	return deleted;
    }
    
    public void setDeleted(boolean deleted) {
    	this.deleted = deleted;
    }
    
    public void setID(int id) {
		// TODO Auto-generated method stub
    	this.id = id;
	}
    
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}
	
	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}
	
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		this.email = email;
	}
	
	public String getPhone() {
		// TODO Auto-generated method stub
		return this.phone;
	}
	
	public void setPhone(String phone) {
		// TODO Auto-generated method stub
		this.phone = phone;
	}
	
	public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }
	
	public String getDetailsShort() {
        return "Customer #" + id + " - " + name + " - " + phone + " - " + email;
    }
	
	 public String getDetailsLong() {
	        // TODO: implementation here
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-YYYY");
	    	String y = "";
	    	
	    	for(Booking x : this.bookings) {
	    		y += "\n * " + x.getBookingDate() + " for " + (x.getFlight()).getDetailsShort();
	    	}
	    	return "Customer #" + id + '\n' + "Name: " + name + '\n' + "Phone: " + phone + '\n' + "Email: " + email + '\n' +
	        "---------------------------" + '\n' + "Bookings:" + '\n' + y + "\n" + this.bookings.size() + "booking(s)";
	    }
    
    public void addBooking(Booking booking) throws FlightBookingSystemException{
        // TODO: implementation here
    	for(int x=0; x < bookings.size(); x++) {
    		if(bookings.get(x).getFlight() == booking.getFlight()) {
    			throw new FlightBookingSystemException("Booking already exists");
    		}
    	}bookings.add(booking);
    	
    	
    }
    
    public void cancelBookingForFlight(Flight flight) throws FlightBookingSystemException{
        // TODO: implementation here
    	if (bookings.size() <= 0) {
    		throw new FlightBookingSystemException("Booking doesnt exists");
    	}else {
    		for(int x=0; x < bookings.size(); x++) {
    			if(bookings.get(x).getFlight() != flight) {
    				throw new FlightBookingSystemException("Booking doesnt exists");
    				}
    	}
    	
    		for(int x=0; x < bookings.size(); x++) {
    			if(bookings.get(x).getFlight() == flight) {
    			
    				bookings.remove(x);
    				}
    	}
    	
    	}
    	
    	
    	
    }
    
    


	
}
