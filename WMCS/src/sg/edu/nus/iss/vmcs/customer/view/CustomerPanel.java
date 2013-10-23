package sg.edu.nus.iss.vmcs.customer.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Font;
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
		
		// north part
		Panel p1 = new Panel();
		Label lb = new Label(TITLE);
		lb.setFont(new Font("Helvetica", Font.BOLD, 24));
		p1.add(lb);
		
		Panel container = new Panel();
		container.setLayout(new BorderLayout());
		Label coinLbl = new Label("Enter Coins Here");
		container.add("North", coinLbl);
		
		Panel totalPanel = new Panel();
		totalPanel.setLayout(new BorderLayout());
		Label totalLbl = new Label("Total Money Inserted:");
		totalPanel.add("West", totalLbl);
		container.add("South", totalPanel);
		
		Panel tpc = new Panel();
		container.add("Center", tpc);
		final Label l1 = new Label("0");
		ActionListener l = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] c = e.getActionCommand().split(" ");
				if (c.length == 1)
					l1.setText("Invalid coin!");
				else {
					int amt = Integer.parseInt(c[0]);
					totalCash += amt; 
					l1.setText(totalCash + " C");
				}
			}
		};
		
		tpc.setLayout(new GridLayout(1, 0));
		Button b1 = new Button("5 C");
		Button b2 = new Button("10 C");
		Button b3 = new Button("20 C");
		Button b4 = new Button("50 C");
		Button b5 = new Button("100 C");
		Button b6 = new Button("Invalid");
		b1.addActionListener(l);
		b2.addActionListener(l);
		b3.addActionListener(l);
		b4.addActionListener(l);
		b5.addActionListener(l);
		b6.addActionListener(l);
		
		tpc.add(b1);
		tpc.add(b2);
		tpc.add(b3);
		tpc.add(b4);
		tpc.add(b5);
		tpc.add(b6);
		
		this.setLayout(new BorderLayout());
		this.add("North", p1);
		this.add("Center", container);
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
