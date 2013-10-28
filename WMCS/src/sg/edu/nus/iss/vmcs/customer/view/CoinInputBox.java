package sg.edu.nus.iss.vmcs.customer.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;
import sg.edu.nus.iss.vmcs.store.CashStore;
import sg.edu.nus.iss.vmcs.store.Coin;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.util.LabelledDisplay;
import sg.edu.nus.iss.vmcs.util.WarningDisplay;
import sg.edu.nus.iss.vmcs.util.uifactory.AWTPanel;
import sg.edu.nus.iss.vmcs.util.uifactory.AWTUIFactory;
import sg.edu.nus.iss.vmcs.util.uifactory.VMCSButton;
import sg.edu.nus.iss.vmcs.util.uifactory.VMCSComponentFactory;
import sg.edu.nus.iss.vmcs.util.uifactory.VMCSPanel;

public class CoinInputBox extends AWTPanel {

	private LabelledDisplay totalDisplay;
	private WarningDisplay invalidCoin;
	
	public CoinInputBox(TransactionController transactionController) {
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
		
		
		CoinInputListener l = new CoinInputListener(transactionController.getCoinReceiver());
		
		StoreController storeController = transactionController.getMainController().getStoreController();
		StoreItem[] cashItems = storeController.getStoreItems(Store.CASH);
		
//		Panel coinInputPanel = new Panel();
//		add("Center", coinInputPanel);
//		coinInputPanel.setLayout(new GridLayout(1, 0));
//		
//		for (StoreItem storeItem : cashItems) {
//			Coin coin = (Coin) storeItem.getContent();
//			
//			Button b = new Button(coin.getName());
//			b.addActionListener(l);
//			b.setActionCommand(String.valueOf(coin.getWeight()));
//			coinInputPanel.add(b);
//		}
//		Button invalid = new Button("Invalid");
//		invalid.addActionListener(l);		
//		invalid.setActionCommand(String.valueOf(CashStore.INVALID_COIN_WEIGHT));
//		coinInputPanel.add(invalid);
		
		VMCSComponentFactory factory = transactionController.getMainController().getUIFactory();
		
		VMCSPanel coinInputPanel = factory.createPanel();
		add("Center", coinInputPanel);
		coinInputPanel.setLayout(new GridLayout(1, 0));
		
		for (StoreItem storeItem : cashItems) {
			Coin coin = (Coin) storeItem.getContent();
			
			VMCSButton b = factory.createButton(coin.getName());
			b.addActionListener(l);
			b.setActionCommand(String.valueOf(coin.getWeight()));
			coinInputPanel.add(b);
		}
		VMCSButton invalid = factory.createButton("Invalid");
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
