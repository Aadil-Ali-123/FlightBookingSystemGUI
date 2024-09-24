package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.ShowFlight;
import bcu.cmp5332.bookingsystem.commands.Command;
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

public class CustBookings extends JFrame implements ActionListener{

	private MainWindow mw;
    private JTextField custID = new JTextField();
    

    private JButton viewbtn = new JButton("View Bookings");
    private JButton cancelBtn = new JButton("Cancel");

    private FlightBookingSystem fbs;
    public CustBookings(MainWindow mw, FlightBookingSystem fbs) {
        this.mw = mw;
        this.fbs = fbs;
        initialize();
    }
    
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("View Customers Booking");

        setSize(300, 100);
        JPanel topPanel = new JPanel();
        List<Customer> custList = fbs.getCustomers();
        // headers for the table
        String[] columns = new String[]{"Name", "Phone", "Email", "Number of Booking"};

        Object[][] data = new Object[custList.size()][6];
        for (int i = 0; i < custList.size(); i++) {
            Customer customer = custList.get(i);
            data[i][0] = customer.getName();
            data[i][1] = customer.getPhone();
            data[i][2] = customer.getEmail();
            data[i][3] = customer.getBookings().size();
        }

        JTable table = new JTable(data, columns);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("Enter Customer ID"));
        bottomPanel.add(custID);
        bottomPanel.add(viewbtn);
        

        viewbtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource() == viewbtn) {
            viewPass();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }
	}
	
	
	private void viewPass() {
		try {
            int customerNumber = Integer.parseInt(custID.getText());
            // create and execute the ShowFlight Command
            
            List<Booking> pass = fbs.getCustomerByID(customerNumber).getBookings();
            StringBuilder text = new StringBuilder();
            
            for (int i=0; i<pass.size();i++){
                text.append("\n"+pass.get(i).getFlight().getDetailsShort() );
            }

            JOptionPane.showMessageDialog(null,"Bookings:"+text.toString());
            // hide (close) the viewPassWindow
            this.setVisible(false);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
