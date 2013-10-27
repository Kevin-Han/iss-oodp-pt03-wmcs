package sg.edu.nus.iss.vmcs.customer.controller.state;

import sg.edu.nus.iss.vmcs.customer.controller.CoinReceiver;
import sg.edu.nus.iss.vmcs.customer.controller.DispenseController;
import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;

public class InsertCoinState implements TransactionState {

	private TransactionController controller;
	
	private DispenseController dispenseController;
	private CoinReceiver coinReceiver;
	
	public InsertCoinState(TransactionController controller) {
		this.controller = controller;
		dispenseController = controller.getDispenseController();
		coinReceiver = controller.getCoinReceiver();
	}
	
	@Override
	public void startTransaction(int drinkIndex) {
		System.out.println("Invalid operation..");
	}

	@Override
	public void processMoneyReceived(int total) {
		if (total >= controller.getSelectedDrinkPrice()) {
			controller.setTransactionState(controller.getCompleteTxnState());
			controller.completeTransaction();
		}
		else
			coinReceiver.continueReceive();
	}

	@Override
	public void completeTransaction() {
		System.out.println("Invalid operation..");
	}

	@Override
	public void cancelTransaction() {
		coinReceiver.stopReceive();
		coinReceiver.refundCash();
		dispenseController.allowSelection(true);
		
		// state transition
		controller.setTransactionState(controller.getSelectDrinkState());
	}

	@Override
	public void terminateFault() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminateTransaction() {
		// TODO Auto-generated method stub
		
	}

}
