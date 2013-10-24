package sg.edu.nus.iss.vmcs.customer.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sg.edu.nus.iss.vmcs.store.CashStore;
import sg.edu.nus.iss.vmcs.store.Coin;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;

public class CoinReceiver {

	private TransactionController transactionController;
	private List<Coin> coins;
	private int totalCash;
	
	public CoinReceiver(TransactionController transactionController) {
		this.transactionController = transactionController;
	}

	public int getTotalCash() {
		return totalCash;
	}
	
	public void startReceive() {
		coins = new ArrayList<Coin>();
		totalCash = 0;
		transactionController.getCoinInputBox().setActive(true);
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
			transactionController.getCoinInputBox().getTotalDisplay().setValue(totalCash + "C");
			
			transactionController.processMoneyReceived(totalCash);
		} else { // invalid coin
			transactionController.getCoinInputBox().getInvalidCoinDisplay().setState(true);
			transactionController.getRefundBox().setValue("Invalid Coin");
			transactionController.getCoinInputBox().setActive(false);
		}
	}
	
	public void continueReceive() {
		transactionController.getCoinInputBox().setActive(true);
	}
	
	public boolean storeCash() {
		return false;
	}
	
	public void stopReceive() {
		
	}
	
	public void refundCash() {
		
	}
	
	public void setActive(boolean isActive) {
		
	}
}
