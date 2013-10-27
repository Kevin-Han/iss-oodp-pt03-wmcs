package sg.edu.nus.iss.vmcs.builder;

import java.awt.Dialog;

import sg.edu.nus.iss.vmcs.customer.controller.Controller;
import sg.edu.nus.iss.vmcs.store.CashStoreItem;
import sg.edu.nus.iss.vmcs.store.DrinksStoreItem;

public interface UIBuilder {
	void buildContainer(Controller controller, Dialog container);
	void buildCoinPanel(CashStoreItem[] cashItems);
	void buildDrinkPanel(DrinksStoreItem[] drinksItems);
	void buildOtherPanel();
	Dialog getUI();
}
