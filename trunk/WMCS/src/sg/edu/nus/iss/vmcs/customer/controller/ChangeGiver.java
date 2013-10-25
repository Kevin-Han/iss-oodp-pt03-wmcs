package sg.edu.nus.iss.vmcs.customer.controller;

import sg.edu.nus.iss.vmcs.machinery.MachineryController;
import sg.edu.nus.iss.vmcs.store.CashStore;
import sg.edu.nus.iss.vmcs.store.CashStoreItem;
import sg.edu.nus.iss.vmcs.store.Coin;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.util.VMCSException;

public class ChangeGiver {

	private TransactionController transactionController;
	
	public ChangeGiver(TransactionController tc) {
		transactionController = tc;
	}

	public void displayChangeStatus() {
		// TODO Auto-generated method stub
		
	}

	public void resetChange() {
		transactionController.getRefundBox().setValue("0C");
	}
	
	public boolean giveChange(int change) {
		System.out.println("Change to be given: " + change + "C");
		int changeGiven = 0;
		MachineryController machineryController = transactionController.getMainController().getMachineryController();
		CashStore cashStore = (CashStore) transactionController.getMainController().getStoreController().getStore(Store.CASH);
		int size = cashStore.getStoreSize();
		for (int i = size-1; i >= 0; i--) {
			CashStoreItem item = (CashStoreItem)cashStore.getStoreItem(i);
			int qtyAvail = item.getQuantity();
			int value = ((Coin)item.getContent()).getValue();
			System.out.println(value + "C, qty available: " + qtyAvail);
			int numOfCoins = 0;
			while (changeGiven < change && (change - changeGiven) >= value && qtyAvail > 0) {
				numOfCoins++;
				qtyAvail--;
				changeGiven += value;
			}
			System.out.println("Giving change: " + value + "C, " + numOfCoins);
			try {
				machineryController.giveChange(i, numOfCoins);
			} catch (VMCSException e) {
				e.printStackTrace();
			}
		}
		transactionController.getRefundBox().setValue(changeGiven + "C");
		
		if (change > changeGiven) {
			System.out.println("Short of " + (change-changeGiven) + "C");
			transactionController.getNoChangeDisplay().setState(true);
		}
			
		return true;
	}
}
