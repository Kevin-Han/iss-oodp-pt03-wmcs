package sg.edu.nus.iss.vmcs.customer.controller.state;

public interface TransactionState {
	public void startTransaction(int drinkIndex);
	public void processMoneyReceived(int total);
	public void completeTransaction();
	public void cancelTransaction();
	public void terminateFault();
	public void terminateTransaction();
}
