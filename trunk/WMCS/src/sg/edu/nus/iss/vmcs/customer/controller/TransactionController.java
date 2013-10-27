package sg.edu.nus.iss.vmcs.customer.controller;

import java.util.Observable;
import java.util.Observer;

import sg.edu.nus.iss.vmcs.customer.view.CoinInputBox;
import sg.edu.nus.iss.vmcs.customer.view.CustomerPanel;
import sg.edu.nus.iss.vmcs.customer.view.DrinkSelectionBox;
import sg.edu.nus.iss.vmcs.store.DrinksBrand;
import sg.edu.nus.iss.vmcs.store.DrinksStoreItem;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.system.SimulatorControlPanel;
import sg.edu.nus.iss.vmcs.util.LabelledDisplay;
import sg.edu.nus.iss.vmcs.util.WarningDisplay;

public class TransactionController implements Observer, Controller {

	private MainController mainController;
	private StoreController storeController;
	private CustomerPanel customerPanel;
	
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
		storeController.getStore(Store.DRINK).addObserver(this);
		
		dispenseController = new DispenseController(this);
		coinReceiver = new CoinReceiver(this);
		changeGiver = new ChangeGiver(this);
		
		selectedDrinkIndex = -1;
	}
	
	// model getters
	public CoinReceiver getCoinReceiver() {
		return coinReceiver;
	}
	public ChangeGiver getChangeGiver() {
		return changeGiver;
	}
	public MainController getMainController() {
		return mainController;
	}
	
	// ui getters
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
			customerPanel = new CustomerPanel(scp, this);
	//		CustomerPanelBuilder b = new CustomerPanelBuilder();
	//		b.buildContainer(this, customerPanel);
	//		b.buildCoinPanel(null);
	//		b.buildDrinkPanel(null);
	//		b.buildOtherPanel();
	//		customerPanel = b.getUI();
		customerPanel.display();
		
		dispenseController.updateDrinkPanel();
		dispenseController.allowSelection(true);
		changeGiver.displayChangeStatus();
		
		// redundant, kept to align with design doc
		scp.setActive(SimulatorControlPanel.ACT_CUSTOMER, false);
	}

	public void refreshCustomerPanel() {
		dispenseController.updateDrinkPanel();
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
		changeGiven = false;
		drinkDispensed = false;
		
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
		drinkDispensed = dispenseController.dispenseDrink(selectedDrinkIndex);
		selectedDrinkIndex = -1;
		
		int change = getCoinReceiver().getTotalCash() - selectedDrinkPrice;
		changeGiven = changeGiver.giveChange(change);
		
		coinReceiver.storeCash();
		dispenseController.allowSelection(true);
		
	}
	
	public void terminateFault() {
		// TODO
	}
	
	public void terminateTransaction() {
		// TODO
	}
	
	public void cancelTransaction() {
		coinReceiver.stopReceive();
		coinReceiver.refundCash();
		dispenseController.allowSelection(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		refreshCustomerPanel();
	}
	
}
