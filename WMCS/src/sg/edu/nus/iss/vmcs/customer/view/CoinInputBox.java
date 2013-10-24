package sg.edu.nus.iss.vmcs.customer.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.vmcs.customer.controller.CoinReceiver;
import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;
import sg.edu.nus.iss.vmcs.store.CashStore;
import sg.edu.nus.iss.vmcs.store.Coin;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.util.LabelledDisplay;
import sg.edu.nus.iss.vmcs.util.WarningDisplay;

public class CoinInputBox extends Panel {

	private int[] coinWeights;
	private int totalCash = 0;
	private LabelledDisplay totalDisplay;
	private WarningDisplay invalidCoin;
	
	public CoinInputBox(CoinReceiver coinReceiver, TransactionController transactionController) {
		setLayout(new BorderLayout());
		
		Label coinLbl = new Label("Enter Coins Here");
		add("North", coinLbl);

		
		Panel totalPanel = new Panel();
		totalPanel.setLayout(new BorderLayout());
		invalidCoin = new WarningDisplay("Invalid Coin");
		totalPanel.add("North", invalidCoin);
		
		totalDisplay = new LabelledDisplay("Total Money Inserted:", 5, LabelledDisplay.DEFAULT);
		totalDisplay.setEnabled(false);
		totalDisplay.setValue("0C");
		totalPanel.add("South", totalDisplay);
		
		add("South", totalPanel);
		
		
		CoinInputListener l = new CoinInputListener(coinReceiver);
		
		Panel coinInputPanel = new Panel();
		add("Center", coinInputPanel);
		coinInputPanel.setLayout(new GridLayout(1, 0));
		
		StoreController storeController = transactionController.getMainController().getStoreController();
		StoreItem[] cashItems = storeController.getStoreItems(Store.CASH);
		for (StoreItem storeItem : cashItems) {
			Coin coin = (Coin) storeItem.getContent();
			
			Button b = new Button(coin.getName());
			b.addActionListener(l);
			b.setActionCommand(String.valueOf(coin.getWeight()));
			coinInputPanel.add(b);
		}
		Button invalid = new Button("Invalid");
		invalid.addActionListener(l);		
		invalid.setActionCommand(String.valueOf(CashStore.INVALID_COIN_WEIGHT));
		coinInputPanel.add(invalid);
		
		// coin input is disabled by default
		setEnabled(false);
	}
	
	public void setActive(boolean isActive) {
		setEnabled(isActive);
	}
	
	public LabelledDisplay getTotalDisplay() {
		return totalDisplay;
	}
	
	public WarningDisplay getInvalidCoinDisplay() {
		return invalidCoin;
	}
}
