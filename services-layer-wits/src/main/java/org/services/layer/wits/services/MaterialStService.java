package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.form.MaterialSt;

public interface MaterialStService {
	public int addMaterialSt(MaterialSt mat );
	public List<MaterialSt> listMaterialSt();
	public void removeMaterialSt(int id);
	public MaterialSt getMaterialSt(int id);
	public void updateMaterialSt(MaterialSt mat );
	public List<MaterialSt> listMaterialSt(MaterialSt father,Boolean active);
	public List<MaterialSt> listMaterialSt(Boolean active);	
}
