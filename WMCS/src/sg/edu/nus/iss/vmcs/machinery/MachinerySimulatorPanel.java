package sg.edu.nus.iss.vmcs.machinery;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import sg.edu.nus.iss.vmcs.customer.view.VendingMachinePanel;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class MachinerySimulatorPanel extends VendingMachinePanel {

	private static final String TITLE = "Machinery Panel";

	private Checkbox doorDisplay;
	private StoreController storeCtrl;
	private MachineryController machineryCtrl;

	public MachinerySimulatorPanel(Frame fr, MachineryController machCtrl) {
		super(fr, TITLE, false);

		machineryCtrl = machCtrl;
		storeCtrl = machineryCtrl.getMainController().getStoreController();

		Label lb = new Label(TITLE);
		lb.setFont(new Font("Helvetica", Font.BOLD, 24));
		lb.setAlignment(Label.CENTER);

		coinPanel = setUpCoinPanel();
		drinkPanel = setUpDrinkPanel();

		Panel tp = new Panel();
		tp.setLayout(new GridLayout(0, 1));
		tp.add(coinPanel);
		tp.add(drinkPanel);

		Panel dp = new Panel();
		doorDisplay = new Checkbox();
		doorDisplay.addItemListener(new DoorListener(machineryCtrl));
		doorDisplay.setLabel("Door Locked");
		dp.add(doorDisplay);

		this.setLayout(new BorderLayout());
		this.add("North", lb);
		this.add("Center", tp);
		this.add("South", dp);
		pack();
		setLocation(100, 320);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				machineryCtrl.closeMachineryPanel();
			}
		});
	}

	public void display() {
		this.setVisible(true);
	}

	public void closeDown() {
		dispose();
	}

	public StoreViewer getCashStoreDisplay() {
		return (StoreViewer) coinPanel;
	}

	public StoreViewer getDrinksStoreDisplay() {
		return (StoreViewer) drinkPanel;
	}

	public void setDoorState(boolean state) {
		doorDisplay.setState(state);
		this.setActive(!state);

	}

	public void setActive(boolean state) {
		getCashStoreDisplay().setActive(state);
		getDrinksStoreDisplay().setActive(state);
		doorDisplay.setEnabled(state);
	}

	@Override
	protected Panel setUpCoinPanel() {
		return new StoreViewer(Store.CASH, storeCtrl);
	}

	@Override
	protected Panel setUpDrinkPanel() {
		return new StoreViewer(Store.DRINK, storeCtrl);
	}

}
