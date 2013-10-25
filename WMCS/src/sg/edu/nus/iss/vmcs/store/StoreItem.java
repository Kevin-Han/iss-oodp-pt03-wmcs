package sg.edu.nus.iss.vmcs.store;

import java.util.Observable;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

/**
 *
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class StoreItem extends Observable {

	private StoreObject content;
	private int quantity;

	public StoreItem(StoreObject content, int quantity) {
		this.content = content;
		this.quantity = quantity;
	}

	public StoreObject getContent() {
		return content;
	}

	public void setContent(StoreObject ct) {
		content = ct;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		notifyQuantityChange();
	}

	public int getQuantity() {
		return quantity;
	}

	public void store() {
		quantity++;
		notifyQuantityChange();
	}
	
	public void decrement() {
		quantity--;
		if (quantity < 0)
			quantity = 0;
		notifyQuantityChange();
	}

	public void increment() {
		quantity++;
		notifyQuantityChange();
	}
	
	private void notifyQuantityChange() {
		setChanged();
		notifyObservers();
	}
}