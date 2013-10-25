package sg.edu.nus.iss.vmcs.customer.controller;

import java.awt.Frame;

import sg.edu.nus.iss.vmcs.customer.view.CoinInputBox;
import sg.edu.nus.iss.vmcs.customer.view.CustomerPanel;
import sg.edu.nus.iss.vmcs.customer.view.DrinkSelectionBox;
import sg.edu.nus.iss.vmcs.store.DrinksBrand;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.system.SimulatorControlPanel;
import sg.edu.nus.iss.vmcs.util.LabelledDisplay;
import sg.edu.nus.iss.vmcs.util.WarningDisplay;

public class TransactionController {

	private MainController mainController;
	private CustomerPanel customerPanel;
	private StoreController storeController;
	private DispenseController dispenseController;
	private CoinReceiver coinReceiver;
	private ChangeGiver changeGiver;
	
	private boolean changeGiven;
	private boolean drinkDispensed;
	private int selectedDrinkPrice;
	private int selectedDrinkIndex;
	
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
	
	public DrinkSelectionBox getDrinkSelectionBox() {
		return customerPanel.getDrinkSelectionBox();
	}
	
	public CoinInputBox getCoinInputBox() {
		return customerPanel.getCoinInputBox();
	}
	
	public LabelledDisplay getRefundBox() {
		return customerPanel.getRefundBox();
	}
	
	public LabelledDisplay getCanCollectionBox() {
		return customerPanel.getCanCollectionBox();
	}
	
	public WarningDisplay getNoChangeDisplay() {
		return customerPanel.getNoChangeDisplay();
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

	public void startTransaction(int drinkIndex) {
		selectedDrinkIndex = drinkIndex;
		StoreItem storeItem = storeController.getStoreItem(Store.DRINK, drinkIndex);
		DrinksBrand drink = (DrinksBrand) storeItem.getContent();
		selectedDrinkPrice = drink.getPrice();
		System.out.println(drink.getName() + " (" + selectedDrinkPrice + "C) selected");
		
		changeGiver.resetChange();
		dispenseController.resetCan();
		changeGiver.displayChangeStatus();
		
		
		dispenseController.allowSelection(false);
		
		coinReceiver.startReceive();
		
	}

	public void processMoneyReceived(int total) {
		if (total >= selectedDrinkPrice) {
			completeTransaction();
		}
		else
			coinReceiver.continueReceive();
	}
	
	public void completeTransaction() {
		System.out.println("Completing transaction...");
		dispenseController.dispenseDrink(selectedDrinkIndex);
		
		int change = getCoinReceiver().getTotalCash() - selectedDrinkPrice;
		changeGiven = changeGiver.giveChange(change);
		
		coinReceiver.storeCash();
		dispenseController.allowSelection(true);
	}
	
	public void terminateFault() {
		
	}
	
	public void terminateTransaction() {
		
	}
	
	public void cancelTransaction() {
		
	}
	
}
