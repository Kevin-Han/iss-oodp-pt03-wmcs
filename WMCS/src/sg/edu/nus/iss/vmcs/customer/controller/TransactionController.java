package sg.edu.nus.iss.vmcs.customer.controller;

import java.awt.Frame;

import sg.edu.nus.iss.vmcs.customer.view.CustomerPanel;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.system.SimulatorControlPanel;

public class TransactionController {

	private MainController mainController;
	private CustomerPanel customerPanel;
	private DispenseController dispenseController;
	private CoinReceiver coinReceiver;
	private ChangeGiver changeGiver;
	
	private boolean changeGiven;
	private boolean drinkDispensed;
	private int price;
	private int selection;
	
	public TransactionController(MainController mc) {
		mainController = mc;
		dispenseController = new DispenseController(this);
		coinReceiver = new CoinReceiver(this);
		changeGiver = new ChangeGiver(this);
	}
	
	public MainController getMainController() {
		return mainController;
	}
	
	public void closeDown() {
		if (customerPanel != null)
			customerPanel.dispose();
	}
	
	public void displayCustomerPanel() {
		SimulatorControlPanel scp = mainController.getSimulatorControlPanel();
		if (customerPanel == null)
			customerPanel = new CustomerPanel((Frame) scp, this);
		customerPanel.display();
		
		dispenseController.updateDrinkPanel();
		dispenseController.allowSelection(true);
		changeGiver.displayChangeStatus();
		
		// redundant, kept to align with design doc
		scp.setActive(SimulatorControlPanel.ACT_CUSTOMER, false);
	}

	public void refreshCustomerPanel() {
		//TODO
	}
	
	public void closeCustomerPanel() {
		if (customerPanel == null)
			return;
		
		customerPanel.dispose();
		SimulatorControlPanel scp = mainController.getSimulatorControlPanel();
		scp.setActive(SimulatorControlPanel.ACT_CUSTOMER, true);
	}

}
