package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.OwnerSt;
import org.persistence.layer.wits.form.WasteManagementPlanSt;

public interface WasteManagementPlanStDAO {
	public int addWasteManagementPlanSt(WasteManagementPlanSt wmp);
	public List<WasteManagementPlanSt> listWasteManagementPlanSt();
	public void removeWasteManagementPlanSt(int id);
	public WasteManagementPlanSt getWasteManagementPlanSt(int id);
	public void updateWasteManagementPlanSt(WasteManagementPlanSt wmp);
	public List<WasteManagementPlanSt> listWasteManagementPlanSt(OwnerSt ownerSt, Boolean active);
}
