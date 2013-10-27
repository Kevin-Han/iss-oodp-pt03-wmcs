package sg.edu.nus.iss.vmcs.customer.controller.state;

import sg.edu.nus.iss.vmcs.customer.controller.ChangeGiver;
import sg.edu.nus.iss.vmcs.customer.controller.CoinReceiver;
import sg.edu.nus.iss.vmcs.customer.controller.DispenseController;
import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;

public class CompleteTransactionState implements TransactionState {

	private TransactionController controller;
	
	private DispenseController dispenseController;
	private CoinReceiver coinReceiver;
	private ChangeGiver changeGiver;
	
	public CompleteTransactionState(TransactionController controller) {
		this.controller = controller;
		dispenseController = controller.getDispenseController();
		coinReceiver = controller.getCoinReceiver();
		changeGiver = controller.getChangeGiver();
	}
	
	@Override
	public void startTransaction(int drinkIndex) {
		System.out.println("Invalid operation..");
	}

	@Override
	public void processMoneyReceived(int total) {
		System.out.println("Invalid operation..");
	}

	@Override
	public void completeTransaction() {
		System.out.println("Completing transaction...");
		boolean drinkDispensed = dispenseController.dispenseDrink(controller.getSelectedDrinkIndex());
		controller.setDrinkDispensed(drinkDispensed);
		controller.setSelectedDrinkIndex(-1);
		
		int change = coinReceiver.getTotalCash() - controller.getSelectedDrinkPrice();
		boolean changeGiven = changeGiver.giveChange(change);
		controller.setChangeGiven(changeGiven);
		
		coinReceiver.storeCash();
		dispenseController.allowSelection(true);
		
		// state transition
		controller.setTransactionState(controller.getSelectDrinkState());
	}

	@Override
	public void cancelTransaction() {
		System.out.println("Invalid operation..");
	}

	@Override
	public void terminateFault() {
		
	}

	@Override
	public void terminateTransaction() {
		
	}

}
