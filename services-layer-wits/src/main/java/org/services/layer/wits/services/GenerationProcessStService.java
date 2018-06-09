package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.form.GenerationProcessSt;
import org.persistence.layer.wits.form.WasteManagementPlanSt;

public interface GenerationProcessStService {
	public int addGenerationProcessSt(GenerationProcessSt wp );
	public List<GenerationProcessSt> listGenerationProcessSt();
	public void removeGenerationProcessSt(int id);
	public GenerationProcessSt getGenerationProcessSt(int id);
	public GenerationProcessSt getGenerationProcessSt(String nameProcess);
	public void updateGenerationProcessSt(GenerationProcessSt wp);
	public List<GenerationProcessSt> listGenerationProcessSt(Boolean active);

}
