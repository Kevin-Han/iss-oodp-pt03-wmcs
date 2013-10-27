package sg.edu.nus.iss.vmcs.customer.controller;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.vmcs.customer.view.CoinInputBox;
import sg.edu.nus.iss.vmcs.store.CashStore;
import sg.edu.nus.iss.vmcs.store.Coin;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.util.VMCSException;

public class CoinReceiver {

	private TransactionController transactionController;
	private List<Coin> coins;
	private int totalCash;
	
	private CoinInputBox coinInputBox;
	
	public CoinReceiver(TransactionController transactionController) {
		this.transactionController = transactionController;
	}

	public int getTotalCash() {
		return totalCash;
	}
	
	public void startReceive() {
		coinInputBox = transactionController.getCoinInputBox();
		coins = new ArrayList<Coin>();
		totalCash = 0;
		coinInputBox.setActive(true);
	}
	
	public void receiveCoin(double weight) {
		StoreController storeController = transactionController.getMainController().getStoreController();
		CashStore cashStore = (CashStore) storeController.getStore(Store.CASH);
		int coinIndex = cashStore.findCashStoreIndex(new Coin(0, weight));
		
		if (coinIndex >= 0) { // valid coin
			Coin coin = (Coin) storeController.getStoreItem(Store.CASH, coinIndex).getContent();
			coins.add(coin);
			
			int value = coin.getValue();
			totalCash += value;
			coinInputBox.getTotalDisplay().setValue(totalCash + "C");
			
			transactionController.processMoneyReceived(totalCash);
		} else { // invalid coin
			coinInputBox.getInvalidCoinDisplay().setState(true);
			transactionController.getRefundBox().setValue("Invalid Coin");
			coinInputBox.setActive(false);
		}
	}
	
	public void continueReceive() {
		coinInputBox.setActive(true);
	}
	
	public boolean storeCash() {
		for (Coin coin : coins)
			try {
				transactionController.getMainController().getMachineryController().storeCoin(coin);
			} catch (VMCSException e) {
				e.printStackTrace();
			}
		coinInputBox.getTotalDisplay().setValue("0C");
		coins = null;
		totalCash = 0;
		coinInputBox.setActive(false);
		return true;
	}
	
	public void stopReceive() {
		setActive(false);
	}
	
	public void refundCash() {
		transactionController.getRefundBox().setValue(totalCash + "C");
		totalCash = 0;
		coins = null;
		coinInputBox.getTotalDisplay().setValue(totalCash + "C");
		coinInputBox.getInvalidCoinDisplay().setState(false);
	}
	
	public void setActive(boolean isActive) {
		coinInputBox.setActive(false);
	}
}
