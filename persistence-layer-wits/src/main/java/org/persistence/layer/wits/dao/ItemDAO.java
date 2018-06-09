package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.enumusertypes.OrdersFieldsTableItems;
import org.persistence.layer.wits.form.GenerationProcessInstance;
import org.persistence.layer.wits.form.Item;
import org.persistence.layer.wits.form.WamatObject;

public interface ItemDAO {
	public int addItem(Item item );
	public List<Item> listItem();
	public void removeItem(int id);
	public Item getItem(int id);
	public void updateItem(Item item );
	public List<Item> listItem(WamatObject wamatObject,OrdersFieldsTableItems ordersFieldsTableItems , 
			Boolean ascending);
	public List<Item> listItem(GenerationProcessInstance generationProcessInstance);
    public Short getMaxItemForWamatObject(WamatObject wamatObject);
}
