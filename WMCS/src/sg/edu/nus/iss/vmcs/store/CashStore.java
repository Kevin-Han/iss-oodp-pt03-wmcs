package sg.edu.nus.iss.vmcs.store;

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

public class CashStore extends Store {

	public final static int INVALID_COIN_WEIGHT = 9999;
	private volatile static CashStore cashStore;
	
	private CashStore() {
	}
	
	public static CashStore getInstance() {
		if (cashStore == null) {
			synchronized (CashStore.class) {
				if (cashStore == null)
					cashStore = new CashStore();
			}
		}
		return cashStore;
	}
	
	public int findCashStoreIndex (Coin c) {
		int size = getStoreSize();
		for (int i = 0; i < size; i++) {
			StoreItem item = (CashStoreItem) getStoreItem(i);
			Coin current = (Coin) item.getContent();
			if (current.getWeight() == c.getWeight())
				return i;
		}
		return -1;
	}


}

