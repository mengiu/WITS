package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.form.GenerationProcessInstance;


public interface GenerationProcessInstanceService {
	public int addGenerationProcessInstance(GenerationProcessInstance wpi );
	public List<GenerationProcessInstance> listGenerationProcessInstance();
	public void removeGenerationProcessInstance(int id);
	public GenerationProcessInstance getGenerationProcessInstance(int id);
	public void updateGenerationProcessInstance(GenerationProcessInstance wpi);
	public GenerationProcessInstance getGenerationProcessInstance(String processNameInstance);

}
