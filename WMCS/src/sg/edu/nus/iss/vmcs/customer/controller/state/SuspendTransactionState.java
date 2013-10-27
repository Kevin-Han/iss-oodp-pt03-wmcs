package sg.edu.nus.iss.vmcs.customer.controller.state;

import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;

public class SuspendTransactionState implements TransactionState {

	private TransactionController controller;
	
	public SuspendTransactionState(TransactionController controller) {
		this.controller = controller;
	}

	@Override
	public void startTransaction(int drinkIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processMoneyReceived(int total) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void completeTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelTransaction() {
		// TODO Auto-generated method stub
		
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
