package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.enumusertypes.OrdersFieldsTableOtherInformation;
import org.persistence.layer.wits.form.WamatExtendedField;
import org.persistence.layer.wits.form.WamatExtendedFieldTypeSt;
import org.persistence.layer.wits.form.WamatObject;

public interface WamatExtendedFieldDAO {
	public int addWamatExtendedField(WamatExtendedField wef );
	public List<WamatExtendedField> listWamatExtendedField(WamatObject wamatObject,
			Boolean confirmed,
			OrdersFieldsTableOtherInformation ordersFieldsTableOtherInformation,
			Boolean ascending);
	public void removeWamatExtendedField(int id);
	public WamatExtendedField getWamatExtendedField(int id);
	public void updateWamatExtendedField(WamatExtendedField wef);
	public WamatExtendedField getWamatExtendedField(WamatObject wamatObject, 
			WamatExtendedFieldTypeSt wamatExtendedFieldTypeSt);
}
