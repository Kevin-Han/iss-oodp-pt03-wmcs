package sg.edu.nus.iss.vmcs.customer.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;
import sg.edu.nus.iss.vmcs.util.LabelledDisplay;
import sg.edu.nus.iss.vmcs.util.WarningDisplay;

public class CustomerPanel extends Dialog {

	private static final String TITLE = "VIMTO Soft Drink Dispenser";
	private TransactionController transactionControl;
	
	private CoinInputBox coinInputBox;
	private DrinkSelectionBox drinkSelectionBox;
	private WarningDisplay noChange;
	
	public CustomerPanel(Frame fr, TransactionController cc) {
		super(fr, TITLE, false);
		transactionControl = cc;
		
		Panel titlePanel = new Panel();
		Label title = new Label(TITLE);
		title.setFont(new Font("Helvetica", Font.BOLD, 24));
		titlePanel.add("Center", title);
		
		Panel topPanel = new Panel();
		topPanel.setLayout(new BorderLayout());
		coinInputBox = new CoinInputBox(null, cc);
		drinkSelectionBox = new DrinkSelectionBox(cc);
		topPanel.add("North", coinInputBox);
		topPanel.add("South", drinkSelectionBox);
		
		Panel bottomPanel = new Panel();
		bottomPanel.setLayout(new GridLayout(0, 1));
		noChange = new WarningDisplay("No Change Available");
		bottomPanel.add(noChange);
		
		Panel terminatePanel = new Panel();
		terminatePanel.setLayout(new FlowLayout());
		Button terminateButton = new Button("Terminate and Return Cash");
		terminateButton.addActionListener(new TerminateButtonListener(transactionControl));
		terminatePanel.add(terminateButton);
		bottomPanel.add(terminatePanel);
		
		LabelledDisplay collectCoin = new LabelledDisplay("Collect Coins:", 5, LabelledDisplay.DEFAULT);
		collectCoin.setEnabled(false);
		collectCoin.setValue("0C");
		LabelledDisplay collectCan = new LabelledDisplay("Collect Can Here:", 5, LabelledDisplay.DEFAULT);
		collectCan.setEnabled(false);
		collectCan.setValue("NO CAN");
		bottomPanel.add(collectCoin);
		bottomPanel.add(collectCan);
		
		//this.setLayout(new GridLayout(0, 1));
		Panel customerPanel = new Panel();
		customerPanel.setLayout(new BorderLayout());
		customerPanel.add("Center", topPanel);
		customerPanel.add("South", bottomPanel);
		this.add("North", titlePanel);
		this.add("Center", customerPanel);
		//this.add(coinInputBox);
		//this.add(drinkSelectionBox);
		//this.add(noChange);
		//this.add(bottomPanel);
		//this.add(collectCoin);
		//this.add(collectCan);
		pack();
		setLocation(350, 100);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				transactionControl.closeCustomerPanel();
			}
		});
	}

	public void display() {
		this.setVisible(true);
	}
	
	public void closeDown() {
		this.dispose();
	}
	
	public void setActive(boolean isActive) {
		this.setEnabled(isActive);
	}
}
