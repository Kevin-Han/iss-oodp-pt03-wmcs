package sg.edu.nus.iss.vmcs.customer.controller;

import java.awt.Frame;

import sg.edu.nus.iss.vmcs.customer.view.CustomerPanel;
import sg.edu.nus.iss.vmcs.store.DrinksBrand;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.system.SimulatorControlPanel;

public class TransactionController {

	private MainController mainController;
	private CustomerPanel customerPanel;
	private StoreController storeController;
	private DispenseController dispenseController;
	private CoinReceiver coinReceiver;
	private ChangeGiver changeGiver;
	
	private boolean changeGiven;
	private boolean drinkDispensed;
	private int price;
	private int selection;
	
	public TransactionController(MainController mc) {
		mainController = mc;
		storeController = mc.getStoreController();
		dispenseController = new DispenseController(this);
		coinReceiver = new CoinReceiver(this);
		changeGiver = new ChangeGiver(this);
	}
	
	public CoinReceiver getCoinReceiver() {
		return coinReceiver;
	}
	
	public ChangeGiver getChangeGiver() {
		return changeGiver;
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

	public void startTransaction(int index) {
		StoreItem storeItem = storeController.getStoreItem(Store.DRINK, index);
		DrinksBrand drink = (DrinksBrand) storeItem.getContent();
		int price = drink.getPrice();
		System.out.println(drink.getName() + " (" + price + "C) selected");
		
		changeGiver.resetChange();
		dispenseController.resetCan();
		changeGiver.displayChangeStatus();
		
		dispenseController.allowSelection(true);
		
		coinReceiver.startReceive();
		
	}

}
