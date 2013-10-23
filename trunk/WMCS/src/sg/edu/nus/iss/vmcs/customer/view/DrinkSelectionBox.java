package sg.edu.nus.iss.vmcs.customer.view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;

import com.sun.org.apache.bcel.internal.generic.F2D;

import sg.edu.nus.iss.vmcs.customer.controller.TransactionController;

public class DrinkSelectionBox extends Panel {

	private TransactionController transactionController;
	private DrinkSelectionItem[] items;
	
	public DrinkSelectionBox(Component container, TransactionController tc) {
		transactionController = tc;
		
		// dummy data
		items = new DrinkSelectionItem[5];
		DrinkSelectionItem item1 = new DrinkSelectionItem("Coca-Cola", 75, false);
		DrinkSelectionItem item2 = new DrinkSelectionItem("Fanta", 85, false);
		DrinkSelectionItem item3 = new DrinkSelectionItem("Sarsi", 70, false);
		DrinkSelectionItem item4 = new DrinkSelectionItem("Soya Bean", 60, false);
		DrinkSelectionItem item5 = new DrinkSelectionItem("Coca-Cola", 75, false);
		
		setLayout(new GridLayout(0, 1));
		add(item1);
		add(item2);
		add(item3);
		add(item4);
		add(item5);
	}
}
