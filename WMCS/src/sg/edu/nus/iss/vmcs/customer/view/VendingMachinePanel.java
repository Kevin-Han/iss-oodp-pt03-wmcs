package sg.edu.nus.iss.vmcs.customer.view;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Panel;

public abstract class VendingMachinePanel extends Dialog {
	
	protected Panel coinPanel;
	protected Panel drinkPanel;
	
	public VendingMachinePanel(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
	}
	
	abstract protected Panel setUpCoinPanel();
	abstract protected Panel setUpDrinkPanel();
	
	public void display() {
		this.setVisible(true);
	}
	
	public void closeDown() {
		this.dispose();
	}
	
}
