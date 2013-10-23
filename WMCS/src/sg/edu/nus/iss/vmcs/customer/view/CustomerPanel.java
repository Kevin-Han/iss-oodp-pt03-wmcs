package sg.edu.nus.iss.vmcs.customer.view;

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

import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;

public class CustomerPanel extends Dialog {

	private static final String TITLE = "Customer Panel";
	private TransactionController cControl;
	private int totalCash = 0;
	
	public CustomerPanel(Frame fr, TransactionController cc) {
		super(fr, TITLE, false);
		cControl = cc;
		
		Panel tpc = new Panel();
		final Label l1 = new Label("Total Cash");
		ActionListener l = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String coin = e.getActionCommand();
				int amt = Integer.parseInt(coin.split(" ")[0]);
				totalCash += amt; 
				l1.setText("Total Cash: " + totalCash + " C");
			}
		};
		
		tpc.setLayout(new GridLayout(1, 0));
		Button b1 = new Button("5 C");
		Button b2 = new Button("10 C");
		Button b3 = new Button("20 C");
		Button b4 = new Button("50 C");
		Button b5 = new Button("100 C");
		b1.addActionListener(l);
		b2.addActionListener(l);
		b3.addActionListener(l);
		b4.addActionListener(l);
		b5.addActionListener(l);
		
		tpc.add(b1);
		tpc.add(b2);
		tpc.add(b3);
		tpc.add(b4);
		tpc.add(b5);
		
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
