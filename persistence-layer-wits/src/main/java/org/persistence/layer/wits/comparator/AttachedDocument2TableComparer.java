package org.persistence.layer.wits.comparator;

import java.util.Comparator;

import org.persistence.layer.wits.enumusertypes.OrdersFieldsTableAttached;
import org.persistence.layer.wits.form.AttachedDocument2Table;

public class AttachedDocument2TableComparer implements Comparator<AttachedDocument2Table> {
	protected OrdersFieldsTableAttached ordersFieldsTableAttached;
	protected Boolean ascending;
	
	public AttachedDocument2TableComparer(OrdersFieldsTableAttached ordersFieldsTableAttached ,
			Boolean ascending )
	{
		this.ordersFieldsTableAttached = ordersFieldsTableAttached;
		this.ascending = ascending;
	}
	@Override
	public int compare(AttachedDocument2Table o1, AttachedDocument2Table o2) {
		int resultRet = -1;
		if (ordersFieldsTableAttached!=null)
		{
			switch (ordersFieldsTableAttached)
			{
			case REFER :
			{
				if (ascending)
				{
					resultRet = o1.getId().getFkTableName().compareTo(o2.getId().getFkTableName());
				}
				else
				{
					resultRet = o2.getId().getFkTableName().compareTo(o1.getId().getFkTableName());
				}
				break;
			}
			case TYPE :
			{
				if (ascending)
				{
					resultRet = o1.getAttachedDocument().getDocumentTypeSt().getNameDocumentTypeSt().compareTo(o2.getAttachedDocument().getDocumentTypeSt().getNameDocumentTypeSt());
				}
				else
				{
					resultRet = o2.getAttachedDocument().getDocumentTypeSt().getNameDocumentTypeSt().compareTo(o1.getAttachedDocument().getDocumentTypeSt().getNameDocumentTypeSt());
				}
				break;
			}
			case ATTACHED :
			{
				if (ascending)
				{
					resultRet = o1.getNameAttachedDocument().compareTo(o2.getNameAttachedDocument());
				}
				else
				{
					resultRet = o2.getNameAttachedDocument().compareTo(o1.getNameAttachedDocument());
				}
				break;
			}
			default :
			{
				if (ascending)
				{
					resultRet = o1.getId().getFkTableName().compareTo(o2.getId().getFkTableName());
				}
				else
				{
					resultRet = o2.getId().getFkTableName().compareTo(o1.getId().getFkTableName());
				}
				break;
			}
			}
		}
		return resultRet;
	}

}
