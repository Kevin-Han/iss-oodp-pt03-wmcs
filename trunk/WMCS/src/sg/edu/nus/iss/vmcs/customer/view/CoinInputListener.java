package sg.edu.nus.iss.vmcs.customer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.vmcs.customer.controller.CoinReceiver;
import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;

public class CoinInputListener implements ActionListener {

	private TransactionController tc;
	
	public CoinInputListener(TransactionController tc) {
		this.tc = tc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		double weight = Double.parseDouble(e.getActionCommand());
		tc.receiveCoin(weight);
	}

}
