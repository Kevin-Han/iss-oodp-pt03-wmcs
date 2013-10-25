package sg.edu.nus.iss.vmcs.customer.controller;

import sg.edu.nus.iss.vmcs.customer.view.DrinkSelectionBox;
import sg.edu.nus.iss.vmcs.store.DrinksBrand;
import sg.edu.nus.iss.vmcs.store.DrinksStoreItem;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.util.LabelledDisplay;
import sg.edu.nus.iss.vmcs.util.VMCSException;

public class DispenseController {

	private TransactionController transactionController;
	private StoreController drinkStoreController;
	private DrinkSelectionBox drinkSelectionBox;
	private LabelledDisplay canCollectionBox;

	public DispenseController(TransactionController transactionController) {
		this.transactionController = transactionController;
		drinkStoreController = transactionController
				.getMainController().getStoreController();
	}

	public void updateDrinkPanel() {
		drinkSelectionBox = transactionController.getDrinkSelectionBox();
		canCollectionBox = transactionController.getCanCollectionBox();
		int size = drinkStoreController.getStoreSize(Store.DRINK);
		for (int i = 0; i < size; i++)
			updateDrinkSelection(i);
	}
	
	public void allowSelection(boolean b) {
		drinkSelectionBox.setActive(b);
	}

	public boolean dispenseDrink(int selectedBrand) {
		System.out.println("Dispensing drink...");
		try {
			transactionController.getMainController().getMachineryController().dispenseDrink(selectedBrand);
		} catch (VMCSException e) {
			e.printStackTrace();
			return false;
		}
		
		// update drink store display
		DrinksBrand drink = (DrinksBrand) drinkStoreController
				.getStoreItem(Store.DRINK, selectedBrand).getContent();
		canCollectionBox.setValue(drink.getName());
		
		updateDrinkSelection(selectedBrand);
		return true;
	}
	
	private void updateDrinkSelection(int index) {
		DrinksStoreItem drinkItem = (DrinksStoreItem) drinkStoreController
				.getStoreItem(Store.DRINK, index);
		DrinksBrand drink = (DrinksBrand) drinkItem.getContent();
		
		drinkSelectionBox.update(index, drinkItem.getQuantity(), 
				drink.getPrice(), drink.getName());
	}

	public void resetCan() {
		canCollectionBox.setValue("No Can");
	}
}
