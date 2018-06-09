package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.WamatExtendedFieldTypeSt;
import org.persistence.layer.wits.form.WamatObject;

public interface WamatExtendedFieldTypeStDAO {
	public int addWamatExtendedFieldTypeSt(WamatExtendedFieldTypeSt weft );
	public List<WamatExtendedFieldTypeSt> listWamatExtendedFieldTypeSt();
	public void removeWamatExtendedFieldTypeSt(int id);
	public WamatExtendedFieldTypeSt getWamatExtendedFieldTypeSt(int id);
	public void updateWamatExtendedFieldTypeSt(WamatExtendedFieldTypeSt weft);
	public List<WamatExtendedFieldTypeSt> listWamatExtendedFieldTypeSt(String startWith );
	public List<WamatExtendedFieldTypeSt> listWamatExtendedFieldTypeSt(
			WamatObject wamatObject);
	public List<WamatExtendedFieldTypeSt> listWamatExtendedFieldTypeSt(
			List<Integer> listidWamatExtendedFieldTypeSt);
	
}
