package Testing;
import org.junit.Test;

import java.time.LocalDate;  
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;

import static org.junit.Assert.assertEquals;


public class test {
	@Test
	public void testGetCustomerEmail() {
		Customer customer = new Customer(1,"Aadil","074635123421","aadil@hotmail.com",false);
		assertEquals("aadil@hotmail.com", customer.getEmail());
	}
	
	@Test
	public void testSetCustomerEmail() {
		Customer customer = new Customer(1,"Aadil","074635123421","aadil@hotmail.com",false);
		customer.setEmail("new@hotmail.com");
		assertEquals("new@hotmail.com", customer.getEmail());
	}
	
	@Test
	public void testGetSeats() {
		LocalDate date = LocalDate.now(); 
		Flight flight = new Flight(1,"H2102","Birmingham","London",date,100,1200,false);
		assertEquals(100, flight.getSeats());
	}
	
	@Test
	public void testSetSeats() {
		LocalDate date = LocalDate.now(); 
		Flight flight = new Flight(1,"H2102","Birmingham","London",date,100,1200,false);
		flight.setSeats(120);
		assertEquals(120, flight.getSeats());
	}
	
	@Test
	public void testGetPrice() {
		LocalDate date = LocalDate.now(); 
		Flight flight = new Flight(1,"H2102","Birmingham","London",date,100,1200,false);
		assertEquals(1200, flight.getPrice());
	}
	
	@Test
	public void testSetPrice() {
		LocalDate date = LocalDate.now(); 
		Flight flight = new Flight(1,"H2102","Birmingham","London",date,100,1200,false);
		flight.setPrice(1500);
		assertEquals(1500, flight.getPrice());
	}
}
