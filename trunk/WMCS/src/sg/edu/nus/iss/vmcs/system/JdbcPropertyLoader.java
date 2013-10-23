package sg.edu.nus.iss.vmcs.system;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.store.PropertyLoader;
import sg.edu.nus.iss.vmcs.store.StoreItem;

public abstract class JdbcPropertyLoader implements PropertyLoader {
	
	protected String tblName="";
	protected String fieldName="";
	
	@Override
	public void initialize() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("SQL connection");
	}

	@Override
	public void saveProperty() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("SQL insert stmt");
	}

	@Override
	public int getNumOfItems() {
		// TODO Auto-generated method stub
		System.out.println("SQL count stmt");
		return 0;
	}

	@Override
	public void setNumOfItems(int numItems) {
		// TODO Auto-generated method stub

	}

	@Override
	public StoreItem getItem(int index) {
		// TODO Auto-generated method stub
		System.out.println("SQL select stmt");
		return null;
	}

	@Override
	public void setItem(int index, StoreItem item) {
		// TODO Auto-generated method stub
		System.out.println("SQL insert/update stmt");
	}

}
