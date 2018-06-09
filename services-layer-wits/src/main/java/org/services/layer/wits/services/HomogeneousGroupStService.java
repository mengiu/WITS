package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.form.HomogeneousGroupSt;

public interface HomogeneousGroupStService {
	public int addHomogeneousGroupSt(HomogeneousGroupSt hgSt );
	public List<HomogeneousGroupSt> listHomogeneousGroupSt();
	public void removeHomogeneousGroupSt(int id);
	public HomogeneousGroupSt getHomogeneousGroupSt(int id);
	public void updateHomogeneousGroupSt(HomogeneousGroupSt hgSt );

}
