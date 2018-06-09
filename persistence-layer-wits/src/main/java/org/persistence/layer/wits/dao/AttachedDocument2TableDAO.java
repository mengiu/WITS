package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.enumusertypes.OrdersFieldsTableAttached;
import org.persistence.layer.wits.form.AttachedDocument2Table;
import org.persistence.layer.wits.form.AttachedDocument2TableId;
import org.persistence.layer.wits.form.utility.AttachmentWits;
import org.persistence.layer.wits.util.AttachToGenericTable;

public interface AttachedDocument2TableDAO {
	public AttachedDocument2TableId addAttachedDocument2Table(AttachedDocument2Table ad2t);
	public List<AttachedDocument2Table> listAttachedDocument2Table();
	public void removeAttachedDocument2Table(AttachedDocument2TableId id);
	public AttachedDocument2Table getAttachedDocument2Table(AttachedDocument2TableId id);
	public void updateAttachedDocument2Table(AttachedDocument2Table ad2t);
	public List<AttachmentWits> listAttachedDocument2Table(AttachToGenericTable attachToGenericTable, Boolean active);
	public List<AttachedDocument2Table> listAttachedDocument2Table(String tableName , List<Integer> listIdTableRelated, 
			String documentType, Boolean active);
	public List<AttachedDocument2Table> listAttachedDocument2Table(List<AttachedDocument2Table> listIdAttachedDocument2Table , 
			String documentType ,  Boolean active, 
			OrdersFieldsTableAttached ordersFieldsTableAttached , 
			Boolean ascending );
	public List<AttachmentWits> listAttachedDocument2Table(
			String tableName, Integer idTableRelated, Boolean active );

}
