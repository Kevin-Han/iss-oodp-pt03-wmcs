package sg.edu.nus.iss.vmcs.system;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.store.DrinksStoreItem;
import sg.edu.nus.iss.vmcs.store.PropertyLoader;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.store.StoreLoader;
import sg.edu.nus.iss.vmcs.store.StoreObject;

public class DrinkStoreLoader implements StoreLoader {
	
	private PropertyLoader loader;
	
	public DrinkStoreLoader(PropertyLoader loader){
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
		int numOfItems = loader.getNumOfItems();
		store.setStoreSize(numOfItems);

		for (int i = 0; i < numOfItems; i++) {
            DrinksStoreItem item = (DrinksStoreItem) loader.getItem(i);
			StoreObject brand = item.getContent();
			StoreObject existingBrand = store.findObject(brand.getName());
			if (existingBrand != null) {
			    item.setContent(existingBrand);
			}
			store.addItem(i, item);
		}

	}

	@Override
	public void saveAll(Store store) {
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


	public PropertyLoader getLoader() {
		return loader;
	}

	public void setLoader(PropertyLoader loader) {
		this.loader = loader;
	}

}
