package sg.edu.nus.iss.vmcs.customer.view;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.vmcs.util.WarningDisplay;

public class DrinkSelectionItem extends Panel {

	private Button drinkBrand;
	private TextField price;
	private WarningDisplay stockState;
	
	public DrinkSelectionItem(int index, String brand, int p, boolean inStock, ActionListener listener) {
		drinkBrand = new Button(brand);
		drinkBrand.setEnabled(!inStock);
		drinkBrand.addActionListener(listener);
		drinkBrand.setActionCommand(String.valueOf(index));
		
		price = new TextField(p + "C");
		price.setColumns(5);
		Panel pricePanel = new Panel();
		pricePanel.add(price);
		pricePanel.setEnabled(false);
		
		stockState = new WarningDisplay("Not in Stock");
		stockState.setState(inStock);
		
		setLayout(new GridLayout(1, 0));
		add(drinkBrand);
		add(pricePanel);
		add(stockState);
	}
	
	public void setItemState(boolean active) {
		stockState.setState(active);
		drinkBrand.setEnabled(!active);
	}
	
	public void setPrice(int price) {
		this.price.setText(price + "C");
	}
	
	public void setName(String name) {
		this.drinkBrand.setLabel(name);
	}
}
