package sg.edu.nus.iss.vmcs.customer.controller;

import sg.edu.nus.iss.vmcs.store.DrinksBrand;
import sg.edu.nus.iss.vmcs.store.DrinksStoreItem;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.util.VMCSException;

public class DispenseController {

	private TransactionController transactionController;

	public DispenseController(TransactionController transactionController) {
		this.transactionController = transactionController;
	}

	public void updateDrinkPanel() {
		
	}
	
	public void allowSelection(boolean b) {
		transactionController.getDrinkSelectionBox().setActive(b);
		updateDrinkPanel();
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
		
		DrinksBrand drink = (DrinksBrand) transactionController.getMainController()
				.getStoreController().getStoreItem(Store.DRINK, selectedBrand).getContent();
		transactionController.getCanCollectionBox().setValue(drink.getName());
		
		updateDrinkSelection(selectedBrand);
		return true;
	}
	
	private void updateDrinkSelection(int index) {
		DrinksStoreItem drinkItem = (DrinksStoreItem) transactionController.getMainController()
				.getStoreController().getStoreItem(Store.DRINK, index);
		DrinksBrand drink = (DrinksBrand) drinkItem.getContent();
		transactionController.getDrinkSelectionBox().update(index, drinkItem.getQuantity(), drink.getPrice(), drink.getName());
	}

	public void resetCan() {
		transactionController.getCanCollectionBox().setValue("No Can");
	}
}
