package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.form.ContainingUnitTypeSt;

public interface ContainingUnitTypeStService {
	public int addContainingUnitTypeSt(ContainingUnitTypeSt gst);
	public List<ContainingUnitTypeSt> listContainingUnitTypeSt();
	public void removeContainingUnitTypeSt(int id);
	public ContainingUnitTypeSt getContainingUnitTypeSt(int id);
	public void updateContainingUnitTypeSt(ContainingUnitTypeSt gst);
	public List<ContainingUnitTypeSt> listContainingUnitTypeSt(Boolean immovable,Boolean active);
}
