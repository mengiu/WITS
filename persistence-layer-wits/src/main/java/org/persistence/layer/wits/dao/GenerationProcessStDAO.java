package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.GenerationProcessSt;

public interface GenerationProcessStDAO {
	public int addGenerationProcessSt(GenerationProcessSt wp );
	public List<GenerationProcessSt> listGenerationProcessSt();
	public void removeGenerationProcessSt(int id);
	public GenerationProcessSt getGenerationProcessSt(int id);
	public GenerationProcessSt getGenerationProcessSt(String nameProcess);
	public void updateGenerationProcessSt(GenerationProcessSt wp);
	public List<GenerationProcessSt> listGenerationProcessSt(Boolean active);

}
