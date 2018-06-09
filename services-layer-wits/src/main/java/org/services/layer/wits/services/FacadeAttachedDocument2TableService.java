package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.enumusertypes.OrdersFieldsTableAttached;
import org.persistence.layer.wits.form.AttachedDocument2Table;
import org.persistence.layer.wits.form.WamatObject;

public interface FacadeAttachedDocument2TableService {
 public List<AttachedDocument2Table> listAttachedDocument2Table( WamatObject wamatObject ,
		 String documentType , Boolean active, OrdersFieldsTableAttached ordersFieldsTableAttached ,
		 Boolean ascending );
}
