package org.persistence.layer.wits.logic;

import org.junit.Test;
import org.persistence.layer.wits.enumusertypes.OrdersFieldsTableItems;

public class OrdersFieldsTableItemsTest {
 @Test
 public void enumTestOrdersFieldsTableItems()
 {
	 try {
		OrdersFieldsTableItems.valueOf("MATERIAL");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
}
