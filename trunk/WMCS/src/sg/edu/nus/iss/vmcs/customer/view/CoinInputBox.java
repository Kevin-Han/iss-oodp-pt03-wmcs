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
import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;
import sg.edu.nus.iss.vmcs.util.LabelledDisplay;
import sg.edu.nus.iss.vmcs.util.WarningDisplay;

public class CoinInputBox extends Panel {

	private int[] coinWeights;
	private int totalCash = 0;
	
	public CoinInputBox(Component container, CoinReceiver coinReceiver, TransactionController transactionController) {
		setLayout(new BorderLayout());
		
		Label coinLbl = new Label("Enter Coins Here");
		add("North", coinLbl);

		
		Panel totalPanel = new Panel();
		totalPanel.setLayout(new BorderLayout());
		final WarningDisplay invalidCoin = new WarningDisplay("Invalid Coin");
		totalPanel.add("North", invalidCoin);
		
		final LabelledDisplay totalLabel = new LabelledDisplay("Total Money Inserted:", 5, LabelledDisplay.DEFAULT);
		totalLabel.setEnabled(false);
		totalLabel.setValue("0C");
		totalPanel.add("South", totalLabel);
		
		
		add("South", totalPanel);
		
		
		ActionListener l = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String label = e.getActionCommand();
				if (label.equals("Invalid"))
					invalidCoin.setState(true);
				else {
					invalidCoin.setState(false);
					if (label.contains("C")) {
						int amt = Integer.parseInt(label.substring(0, label.length()-1));
						totalCash += amt; 
						totalLabel.setValue(totalCash + "C");
					} else {
						totalCash += 100;
						totalLabel.setValue(totalCash + "C");
					}
				}
				
			}
		};
		
		Panel coinInputPanel = new Panel();
		add("Center", coinInputPanel);
		coinInputPanel.setLayout(new GridLayout(1, 0));
		
		Button b1 = new Button("5C");
		Button b2 = new Button("10C");
		Button b3 = new Button("20C");
		Button b4 = new Button("50C");
		Button b5 = new Button("$1");
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
