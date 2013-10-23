package sg.edu.nus.iss.vmcs.customer.controller;

import java.awt.Frame;

import sg.edu.nus.iss.vmcs.customer.view.CustomerPanel;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.system.SimulatorControlPanel;

public class TransactionController {

	private MainController mCtrl;
	private CustomerPanel cpanel;
	
	private boolean changeGiven;
	private boolean drinkDispensed;
	private int price;
	private int selection;
	
	public TransactionController(MainController mc) {
		mCtrl = mc;
	}
	
	public MainController getMainController() {
		return mCtrl;
	}
	
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	public void closeDown() {
		if (cpanel != null)
			cpanel.dispose();
	}
	
	public void displayCustomerPanel() {
		SimulatorControlPanel scp = mCtrl.getSimulatorControlPanel();
		if (cpanel == null)
			cpanel = new CustomerPanel((Frame) scp, this);
		cpanel.display();
		//scp.setActive(SimulatorControlPanel.ACT_CUSTOMER, false);
	}

	public void refreshCustomerPanel() {
		//TODO
	}
	
	public void closeCustomerPanel() {
		if (cpanel == null)
			return;
		
		cpanel.dispose();
		SimulatorControlPanel scp = mCtrl.getSimulatorControlPanel();
		scp.setActive(SimulatorControlPanel.ACT_CUSTOMER, true);
	}

}
