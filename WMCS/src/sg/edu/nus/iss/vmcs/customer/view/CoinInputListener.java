package sg.edu.nus.iss.vmcs.customer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.vmcs.customer.controller.CoinReceiver;

public class CoinInputListener implements ActionListener {

	private CoinReceiver coinReceiver;
	
	public CoinInputListener(CoinReceiver cr) {
		coinReceiver = cr;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		double weight = Double.parseDouble(e.getActionCommand());
		coinReceiver.receiveCoin(weight);
	}

}
