package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.GenerationProcessSt;
import org.persistence.layer.wits.form.WasteManagementPlanSt;
import org.persistence.layer.wits.form.Wmp2GenerationProcess;

public interface Wmp2GenerationProcessDAO {
	public int addWmp2GenerationProcess(Wmp2GenerationProcess w2wp );
	public List<Wmp2GenerationProcess> listWmp2GenerationProcess();
	public void removeWmp2GenerationProcess(int id);
	public Wmp2GenerationProcess getWmp2GenerationProcess(int id);
	public void updateWmp2GenerationProcess(Wmp2GenerationProcess w2wp);
	public List<Wmp2GenerationProcess> listWmp2GenerationProcess(WasteManagementPlanSt wasteManagementPlanSt);
	public Wmp2GenerationProcess getWmp2GenerationProcess(WasteManagementPlanSt wasteManagementPlanSt,
			GenerationProcessSt generationProcessSt);

}
