package sg.edu.nus.iss.vmcs.customer.controller.state;

import sg.edu.nus.iss.vmcs.customer.controller.ChangeGiver;
import sg.edu.nus.iss.vmcs.customer.controller.CoinReceiver;
import sg.edu.nus.iss.vmcs.customer.controller.DispenseController;
import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;
import sg.edu.nus.iss.vmcs.store.DrinksBrand;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreItem;

public class SelectDrinkState implements TransactionState {

	private TransactionController controller;
	
	private DispenseController dispenseController;
	private CoinReceiver coinReceiver;
	private ChangeGiver changeGiver;
	
	public SelectDrinkState(TransactionController controller) {
		this.controller = controller;
		dispenseController = controller.getDispenseController();
		coinReceiver = controller.getCoinReceiver();
		changeGiver = controller.getChangeGiver();
	}
	
	@Override
	public void startTransaction(int drinkIndex) {
		controller.setSelectedDrinkIndex(drinkIndex);
		controller.setChangeGiven(false);
		controller.setDrinkDispensed(false);
		
		StoreItem storeItem = controller.getMainController()
				.getStoreController().getStoreItem(Store.DRINK, drinkIndex);
		DrinksBrand drink = (DrinksBrand) storeItem.getContent();
		controller.setSelectedDrinkPrice(drink.getPrice());
		System.out.println(drink.getName() + " (" + drink.getPrice() + "C) selected");
		
		changeGiver.resetChange();
		changeGiver.displayChangeStatus();
		
		dispenseController.resetCan();
		dispenseController.allowSelection(false);
		
		coinReceiver.startReceive();
		
		// state transition
		controller.setTransactionState(controller.getInsetCoinState());
	}

	@Override
	public void processMoneyReceived(int total) {
		System.out.println("Invalid operation..");
	}

	@Override
	public void completeTransaction() {
		System.out.println("Invalid operation..");
	}

	@Override
	public void cancelTransaction() {
		System.out.println("Invalid operation..");
	}

	@Override
	public void terminateFault() {
		// TODO Auto-generated method stub
	}

	@Override
	public void terminateTransaction() {
		// TODO Auto-generated method stub
	}

	@Override
	public void receiveCoin(double weight) {
		System.out.println("Invalid operation...");
	}

}
