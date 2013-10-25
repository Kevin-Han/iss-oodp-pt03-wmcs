package sg.edu.nus.iss.vmcs.customer.view;

import java.awt.GridLayout;
import java.awt.Panel;

import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;
import sg.edu.nus.iss.vmcs.store.DrinksBrand;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;

public class DrinkSelectionBox extends Panel {

	private TransactionController transactionController;
	private StoreController storeController;
	private DrinkSelectionItem[] items;
	
	public DrinkSelectionBox(TransactionController tc) {
		setLayout(new GridLayout(0, 1));
		
		transactionController = tc;
		storeController = transactionController.getMainController().getStoreController();
		
		StoreItem[] storeItems = storeController.getStoreItems(Store.DRINK);
		items = new DrinkSelectionItem[storeItems.length];
		int i = 0;
		for (StoreItem storeItem : storeItems) {
			DrinksBrand drink = (DrinksBrand) storeItem.getContent();
			DrinkSelectionItem drinkItem = new DrinkSelectionItem(i, drink.getName(), 
					drink.getPrice(), storeItem.getQuantity() <= 0, new DrinkSelectionListener(tc));
			add(drinkItem);
			items[i++] = drinkItem;
		}
		
	}
	
	public void update(int brand, int quantity, int price, String name) {
		if (quantity <= 0) {
			items[brand].setItemState(true);
		}
	}
	
	public void setState(int index, boolean isActive) {
		items[index].setEnabled(isActive);
	}
	
	public void setActive(boolean isActive) {
		setEnabled(isActive);
	}
	
	public void setItemState(int index, boolean isActive) {
		items[index].setItemState(isActive);
	}
	
}
