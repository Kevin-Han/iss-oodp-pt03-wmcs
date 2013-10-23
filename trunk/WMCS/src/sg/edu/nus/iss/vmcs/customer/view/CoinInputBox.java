package sg.edu.nus.iss.vmcs.customer.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.vmcs.customer.controller.CoinReceiver;

public class CoinInputBox extends Panel {

	private int[] coinWeights;
	private int totalCash = 0;
	
	public CoinInputBox(Component container, CoinReceiver coinReceiver) {
		setLayout(new BorderLayout());
		
		Label coinLbl = new Label("Enter Coins Here");
		add("North", coinLbl);
		
		Panel totalPanel = new Panel();
		totalPanel.setLayout(new BorderLayout());
		Label totalLbl = new Label("Total Money Inserted:");
		totalPanel.add("West", totalLbl);
		add("South", totalPanel);
		
		
		final Label l1 = new Label("0");
		totalPanel.add("Center", l1);
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
		
		Panel coinInputPanel = new Panel();
		add("Center", coinInputPanel);
		coinInputPanel.setLayout(new GridLayout(1, 0));
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
		
		coinInputPanel.add(b1);
		coinInputPanel.add(b2);
		coinInputPanel.add(b3);
		coinInputPanel.add(b4);
		coinInputPanel.add(b5);
		coinInputPanel.add(b6);
	}
	
	public void setActive() {
		
	}
}
