package sg.edu.nus.iss.vmcs.customer.controller.state;

import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;

public class SuspendTransactionState implements TransactionState {

	private TransactionController controller;
	
	public SuspendTransactionState(TransactionController controller) {
		this.controller = controller;
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
		System.out.println("Invalid operation..");
	}

	@Override
	public void cancelTransaction() {
		System.out.println("Invalid operation..");
	}

	@Override
	public void terminateFault() {
		System.out.println("Invalid operation..");
	}

	@Override
	public void terminateTransaction() {
		System.out.println("Invalid operation..");
	}
	
}
