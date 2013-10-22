package sg.edu.nus.iss.vmcs.customer;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomerPanel extends Dialog {

	private static final String TITLE = "Customer Panel";
	private CustomerController cControl;
	
	public CustomerPanel(Frame fr, CustomerController cc) {
		super(fr, TITLE, false);
		cControl = cc;
		
		Panel tpc = new Panel();
		tpc.setLayout(new GridLayout(0, 1));
		Button c1 = new Button("Coin");
		final Label l1 = new Label("Total Cash");
		c1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				l1.setText("Coin inserted!");
			}
		});
		tpc.add(c1);
		
		this.setLayout(new BorderLayout());
		this.add("Center", tpc);
		this.add("South", l1);
		
		pack();
		setLocation(100, 100);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cControl.closeCustomerPanel();
			}
		});
	}

	public void display() {
		this.setVisible(true);
	}
}
