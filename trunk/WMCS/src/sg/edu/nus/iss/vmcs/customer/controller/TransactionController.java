package sg.edu.nus.iss.vmcs.customer.controller;

import java.util.Observable;
import java.util.Observer;

import sg.edu.nus.iss.vmcs.customer.controller.state.CompleteTransactionState;
import sg.edu.nus.iss.vmcs.customer.controller.state.InsertCoinState;
import sg.edu.nus.iss.vmcs.customer.controller.state.SelectDrinkState;
import sg.edu.nus.iss.vmcs.customer.controller.state.SuspendTransactionState;
import sg.edu.nus.iss.vmcs.customer.controller.state.TransactionState;
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

public class TransactionController implements Observer {

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
	
	private TransactionState selectDrinkState;
	private TransactionState insetCoinState;
	private TransactionState completeTxnState;
	private TransactionState suspendTxnState;
	private TransactionState currentState;
	
	public TransactionController(MainController mc) {
		mainController = mc;
		storeController = mc.getStoreController();
		storeController.getStore(Store.DRINK).addObserver(this);
		
		dispenseController = new DispenseController(this);
		coinReceiver = new CoinReceiver(this);
		changeGiver = new ChangeGiver(this);
		
		selectedDrinkIndex = -1;
		
		// initialize states
		selectDrinkState = new SelectDrinkState(this);
		insetCoinState = new InsertCoinState(this);
		completeTxnState = new CompleteTransactionState(this);
		suspendTxnState = new SuspendTransactionState(this);
		currentState = selectDrinkState;
	}
	
	// state setter & getters
	public void setTransactionState(TransactionState newState) {
		System.out.println("Trasition to " + newState.getClass().getName() + "......");
		currentState = newState;
	}
	public TransactionState getSelectDrinkState() {
		return selectDrinkState;
	}
	public TransactionState getInsetCoinState() {
		return insetCoinState;
	}
	public TransactionState getCompleteTxnState() {
		return completeTxnState;
	}
	public TransactionState getSuspendTxnState() {
		return suspendTxnState;
	}

	// model getters
	public CoinReceiver getCoinReceiver() {
		return coinReceiver;
	}
	public ChangeGiver getChangeGiver() {
		return changeGiver;
	}
	public DispenseController getDispenseController() {
		return dispenseController;
	}
	public MainController getMainController() {
		return mainController;
	}
	
	// attr setters & getters
	public boolean isChangeGiven() {
		return changeGiven;
	}
	public void setChangeGiven(boolean changeGiven) {
		this.changeGiven = changeGiven;
	}
	public boolean isDrinkDispensed() {
		return drinkDispensed;
	}
	public void setDrinkDispensed(boolean drinkDispensed) {
		this.drinkDispensed = drinkDispensed;
	}
	public int getSelectedDrinkPrice() {
		return selectedDrinkPrice;
	}
	public void setSelectedDrinkPrice(int selectedDrinkPrice) {
		this.selectedDrinkPrice = selectedDrinkPrice;
	}
	public int getSelectedDrinkIndex() {
		return selectedDrinkIndex;
	}
	public void setSelectedDrinkIndex(int selectedDrinkIndex) {
		this.selectedDrinkIndex = selectedDrinkIndex;
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
	
	// ui functions
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
	}

	public void refreshCustomerPanel() {
		dispenseController.updateDrinkPanel();
	}
	
	public void closePanel() {
		if (customerPanel == null)
			return;
		
		customerPanel.dispose();
		SimulatorControlPanel scp = mainController.getSimulatorControlPanel();
		scp.setActive(SimulatorControlPanel.ACT_CUSTOMER, true);
	}

	// transaction functions
	public void startTransaction(int drinkIndex) {
		currentState.startTransaction(drinkIndex);
	}

	public void processMoneyReceived(int total) {
		currentState.processMoneyReceived(total);
	}
	
	public void completeTransaction() {
		currentState.completeTransaction();
	}
	
	public void cancelTransaction() {
		currentState.cancelTransaction();
	}
	
	public void terminateFault() {
		currentState.terminateFault();
	}
	
	public void terminateTransaction() {
		currentState.terminateTransaction();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		refreshCustomerPanel();
	}
	
}
