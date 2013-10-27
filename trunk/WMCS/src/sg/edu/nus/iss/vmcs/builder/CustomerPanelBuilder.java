package sg.edu.nus.iss.vmcs.builder;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import sg.edu.nus.iss.vmcs.customer.controller.Controller;
import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;
import sg.edu.nus.iss.vmcs.customer.view.CoinInputBox;
import sg.edu.nus.iss.vmcs.customer.view.CustomerPanel;
import sg.edu.nus.iss.vmcs.customer.view.DrinkSelectionBox;
import sg.edu.nus.iss.vmcs.customer.view.TerminateButtonListener;
import sg.edu.nus.iss.vmcs.store.CashStoreItem;
import sg.edu.nus.iss.vmcs.store.DrinksStoreItem;
import sg.edu.nus.iss.vmcs.util.LabelledDisplay;
import sg.edu.nus.iss.vmcs.util.WarningDisplay;

public class CustomerPanelBuilder implements UIBuilder {
	private TransactionController tc;
	private CustomerPanel customerPanel;
	private Panel placeHolder;
	
	@Override
	public void buildContainer(Controller controller, Dialog container) {
		tc = (TransactionController) controller;
		customerPanel = (CustomerPanel) container;
		
		customerPanel.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				tc.closePanel();
			}
		});
		
		Panel titlePanel = new Panel();
		Label title = new Label(CustomerPanel.TITLE);
		title.setFont(new Font("Helvetica", Font.BOLD, 24));
		titlePanel.add("Center", title);
		customerPanel.add("North", titlePanel);
	}

	@Override
	public void buildCoinPanel(CashStoreItem[] cashItems) {
		placeHolder = new Panel();
		placeHolder.setLayout(new BorderLayout());
		CoinInputBox coinInputBox = new CoinInputBox(tc);
		customerPanel.setCoinInputBox(coinInputBox);
		placeHolder.add("North", coinInputBox);
	}

	@Override
	public void buildDrinkPanel(DrinksStoreItem[] drinksItems) {
		DrinkSelectionBox drinkSelectionBox = new DrinkSelectionBox(tc);
		customerPanel.setDrinkSelectionBox(drinkSelectionBox);
		placeHolder.add("South", drinkSelectionBox);
	}

	@Override
	public void buildOtherPanel() {
		Panel bottomPanel = new Panel();
		bottomPanel.setLayout(new GridLayout(0, 1));
		WarningDisplay noChange = new WarningDisplay("No Change Available");
		customerPanel.setNoChange(noChange);
		bottomPanel.add(noChange);
		
		Panel terminatePanel = new Panel();
		terminatePanel.setLayout(new FlowLayout());
		Button terminateButton = new Button("Terminate and Return Cash");
		terminateButton.addActionListener(new TerminateButtonListener(tc));
		terminatePanel.add(terminateButton);
		bottomPanel.add(terminatePanel);
		
		LabelledDisplay refundBox = new LabelledDisplay("Collect Coins:", 10, LabelledDisplay.DEFAULT);
		customerPanel.setRefundBox(refundBox);
		refundBox.setEnabled(false);
		LabelledDisplay canCollectionBox = new LabelledDisplay("Collect Can Here:", 10, LabelledDisplay.DEFAULT);
		customerPanel.setCanCollectionBox(canCollectionBox);
		canCollectionBox.setEnabled(false);
		bottomPanel.add(refundBox);
		bottomPanel.add(canCollectionBox);
		
		customerPanel.add("South", bottomPanel);
	}

	@Override
	public CustomerPanel getUI() {
		customerPanel.add("Center", placeHolder);
		customerPanel.pack();
		customerPanel.setLocation(350, 100);
		return customerPanel;
	}
	
}
