package sg.edu.nus.iss.vmcs.customer;

import java.awt.Frame;

import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.system.SimulatorControlPanel;

public class CustomerController {

	private MainController mCtrl;
	private CustomerPanel cpanel;
	
	public CustomerController(MainController mc) {
		mCtrl = mc;
	}
	
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	public void displayCustomerPanel() {
		SimulatorControlPanel scp = mCtrl.getSimulatorControlPanel();
		if (cpanel == null)
			cpanel = new CustomerPanel((Frame) scp, this);
		cpanel.display();
	}

	public void closeCustomerPanel() {
		if (cpanel == null)
			return;
		
		cpanel.dispose();
		SimulatorControlPanel scp = mCtrl.getSimulatorControlPanel();
		scp.setActive(SimulatorControlPanel.ACT_CUSTOMER, true);
	}

}
