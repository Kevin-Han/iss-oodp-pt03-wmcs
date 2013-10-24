package sg.edu.nus.iss.vmcs.customer.view;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;

public class DrinkSelectionListener implements ActionListener {

	private TransactionController tc;
	
	public DrinkSelectionListener(TransactionController tc) {
		this.tc = tc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Button btn;
		String cmd;
		int idx;

		btn = (Button) e.getSource();
		cmd = btn.getActionCommand();
		idx = Integer.parseInt(cmd);
		tc.startTransaction(idx);
	}

}
