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

	private static final String TITLE = "VIMTO Soft Drink Dispenser";
	private TransactionController cControl;
	
	private CoinInputBox coinInputBox;
	private DrinkSelectionBox drinkSelectionBox;
	
	public CustomerPanel(Frame fr, TransactionController cc) {
		super(fr, TITLE, false);
		cControl = cc;
		
		Panel title = new Panel();
		Label lb = new Label(TITLE);
		lb.setFont(new Font("Helvetica", Font.BOLD, 24));
		title.add("Center", lb);
		
		CoinInputBox coinInputBox = new CoinInputBox(this, null);
		
		
		this.setLayout(new GridLayout(0, 1));
		this.add(title);
		this.add(coinInputBox);
		
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
