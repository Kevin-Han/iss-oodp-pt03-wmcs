package sg.edu.nus.iss.vmcs.customer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;

public class TerminateButtonListener implements ActionListener {

	private TransactionController transactionController;
	
	public TerminateButtonListener(TransactionController tc) {
		transactionController = tc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		transactionController.cancelTransaction();
	}

}
