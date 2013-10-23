package sg.edu.nus.iss.vmcs.system;

import sg.edu.nus.iss.vmcs.store.Store;

public interface StoreLoader {
	
	public void loadAll(Store store);
	public void saveAll(Store store);	
}
