package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.ShowFlight;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.DeleteCustomer;
import bcu.cmp5332.bookingsystem.commands.DeleteFlight;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
/**
 * Represents an implementation of  ActionListnener and represents Jframe
 * 
 * @ActionListener DeleteCustomerWindow
 * 
 * @see ActionListener
 */
public class DeleteCustomerWindow extends JFrame implements ActionListener{

	private MainWindow mw;
    private JTextField custID = new JTextField();
    

    private JButton deletebtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");

    private FlightBookingSystem fbs;
    public DeleteCustomerWindow(MainWindow mw, FlightBookingSystem fbs) {
        this.mw = mw;
        this.fbs = fbs;
        initialize();
    }
    /**
     * Initalises the Jframe window and makes it visible to the user
     * 
     * @return null
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("View Customers Booking");

        setSize(300, 100);
        JPanel topPanel = new JPanel();
        

       

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(custID);
        bottomPanel.add(deletebtn);
        

        deletebtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }
	
    /**
     * When button is clicked it runs the function deleteCustomer
     * 
     * @return null
     */
    
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource() == deletebtn) {
            deleteCustomer();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }
	}
	
	/**
     * Deletes the customer and refreshes the list with an updated list
     * 
     * @return null
     */
	
	private void deleteCustomer() {
		try {
            int customerNumber = Integer.parseInt(custID.getText());
            Command deletecustomer = new DeleteCustomer(customerNumber);
            deletecustomer.execute(mw.getFlightBookingSystem());
            // refresh the view with the list of flights
            
            mw.displayCustomers();
            // hide (close) the AddFlightWindow
            this.setVisible(false);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
