package sg.edu.nus.iss.vmcs.customer.controller;

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
		transactionController.getRefundBox().setValue(change + "C");
		return true;
	}
}
