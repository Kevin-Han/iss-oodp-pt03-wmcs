package sg.edu.nus.iss.vmcs.customer.view;

import java.awt.Frame;

import sg.edu.nus.iss.vmcs.customer.controller.Controller;
import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;

public class VMCSPanelFactory {
	public VendingMachinePanel createPanel(int type, Frame parent, Controller controller) {
		VendingMachinePanel panel = null;
		switch(type) {
		case 1:
			panel = new CustomerPanel(parent, (TransactionController)controller);
			break;
		case 2:
			break;
		case 3:
			break;
		default:
				
		}
		return panel;
	}
}
