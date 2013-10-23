package sg.edu.nus.iss.vmcs.customer.controller;

public class DispenseController {

	private TransactionController transactionController;

	public DispenseController(TransactionController transactionController) {
		this.transactionController = transactionController;
	}

	public void updateDrinkPanel() {
		// TODO Auto-generated method stub
		updateDrinkSelection(0);
	}
	
	public void allowSelection(boolean b) {
		// TODO Auto-generated method stub
		
	}

	private void updateDrinkSelection(int selection) {
		
	}
}
