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

import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;
import sg.edu.nus.iss.vmcs.util.LabelledDisplay;
import sg.edu.nus.iss.vmcs.util.WarningDisplay;

public class CustomerPanel extends VendingMachinePanel {

	public static final String TITLE = "VIMTO Soft Drink Dispenser";
	private TransactionController transactionControl;
	
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
		
		// use factory method
		coinPanel = setUpCoinPanel();
		drinkPanel = setUpDrinkPanel();
		
		topPanel.add("North", coinPanel);
		topPanel.add("South", drinkPanel);
		
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
				transactionControl.closePanel();
			}
		});
	}

//	public void display() {
//		this.setVisible(true);
//	}
//	
//	public void closeDown() {
//		this.dispose();
//	}
	
	public void setActive(boolean isActive, Component component) {
		component.setEnabled(isActive);
	}

	public DrinkSelectionBox getDrinkSelectionBox() {
		return (DrinkSelectionBox)drinkPanel;
	}
	
	public CoinInputBox getCoinInputBox() {
		return (CoinInputBox) coinPanel;
	}
	
	public LabelledDisplay getRefundBox() {
		return refundBox;
	}
	
	public LabelledDisplay getCanCollectionBox() {
		return canCollectionBox;
	}
	
	public WarningDisplay getNoChangeDisplay() {
		return noChange;
	}

	public void setCoinInputBox(CoinInputBox coinInputBox) {
		this.coinPanel = coinInputBox;
	}

	public void setDrinkSelectionBox(DrinkSelectionBox drinkSelectionBox) {
		this.drinkPanel = drinkSelectionBox;
	}

	public void setNoChange(WarningDisplay noChange) {
		this.noChange = noChange;
	}

	public void setRefundBox(LabelledDisplay refundBox) {
		this.refundBox = refundBox;
	}

	public void setCanCollectionBox(LabelledDisplay canCollectionBox) {
		this.canCollectionBox = canCollectionBox;
	}

	@Override
	protected Panel setUpCoinPanel() {
		return new CoinInputBox(transactionControl);
	}

	@Override
	protected Panel setUpDrinkPanel() {
		return new DrinkSelectionBox(transactionControl);
	}

}
