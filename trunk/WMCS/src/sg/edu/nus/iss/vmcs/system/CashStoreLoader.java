package sg.edu.nus.iss.vmcs.system;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.store.CashStore;
import sg.edu.nus.iss.vmcs.store.CashStoreItem;
import sg.edu.nus.iss.vmcs.store.PropertyLoader;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.store.StoreLoader;

public class CashStoreLoader implements StoreLoader{
	//refined abstraction
	
	//implementor
	private PropertyLoader loader;
	
	public CashStoreLoader(PropertyLoader loader)
	{
		this.loader = loader;
		try {
			loader.initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void loadAll(Store store) {
		if(store instanceof CashStore){
			int numOfItems = loader.getNumOfItems();
			store.setStoreSize(numOfItems);
	
			for (int i = 0; i < numOfItems; i++) {
			    CashStoreItem item = (CashStoreItem) loader.getItem(i);
			    store.addItem(i, item);
			}
		}
	}

	@Override
	public void saveAll(Store store) {
		// TODO Auto-generated method stub
		try {
			int size = store.getStoreSize();
			loader.setNumOfItems(size);
			for (int i = 0; i < size; i++) {
				loader.setItem(i, store.getStoreItem(i));
			}
			loader.saveProperty();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
}
