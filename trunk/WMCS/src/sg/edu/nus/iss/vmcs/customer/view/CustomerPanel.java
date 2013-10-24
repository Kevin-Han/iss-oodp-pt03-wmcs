package sg.edu.nus.iss.vmcs.customer.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import sg.edu.nus.iss.vmcs.customer.controller.CoinReceiver;
import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;
import sg.edu.nus.iss.vmcs.maintenance.DrinkDisplay;
import sg.edu.nus.iss.vmcs.util.LabelledDisplay;
import sg.edu.nus.iss.vmcs.util.WarningDisplay;

public class CustomerPanel extends Dialog {

	private static final String TITLE = "VIMTO Soft Drink Dispenser";
	private TransactionController transactionControl;
	
	private CoinInputBox coinInputBox;
	private DrinkSelectionBox drinkSelectionBox;
	private WarningDisplay noChange;
	private LabelledDisplay refundBox;
	private LabelledDisplay canCollectionBox;
	
	public CustomerPanel(Frame fr, TransactionController tc) {
		super(fr, TITLE, false);
		transactionControl = tc;
		
		Panel titlePanel = new Panel();
		Label title = new Label(TITLE);
		title.setFont(new Font("Helvetica", Font.BOLD, 24));
		titlePanel.add("Center", title);
		
		Panel topPanel = new Panel();
		topPanel.setLayout(new BorderLayout());
		coinInputBox = new CoinInputBox(tc.getCoinReceiver(), tc);
		drinkSelectionBox = new DrinkSelectionBox(tc);
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
		
		refundBox = new LabelledDisplay("Collect Coins:", 10, LabelledDisplay.DEFAULT);
		refundBox.setEnabled(false);
		canCollectionBox = new LabelledDisplay("Collect Can Here:", 10, LabelledDisplay.DEFAULT);
		canCollectionBox.setEnabled(false);
		bottomPanel.add(refundBox);
		bottomPanel.add(canCollectionBox);
		
		Panel customerPanel = new Panel();
		customerPanel.setLayout(new BorderLayout());
		customerPanel.add("Center", topPanel);
		customerPanel.add("South", bottomPanel);
		this.add("North", titlePanel);
		this.add("Center", customerPanel);
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
	
	public void setActive(boolean isActive, Component component) {
		component.setEnabled(isActive);
	}

	public DrinkSelectionBox getDrinkSelectionBox() {
		return drinkSelectionBox;
	}
	
	public CoinInputBox getCoinInputBox() {
		return coinInputBox;
	}
	
	public LabelledDisplay getRefundBox() {
		return refundBox;
	}
	
	public LabelledDisplay getCanCollectionBox() {
		return canCollectionBox;
	}
}
