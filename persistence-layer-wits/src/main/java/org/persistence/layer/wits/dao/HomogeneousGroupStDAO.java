package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.HomogeneousGroupSt;

public interface HomogeneousGroupStDAO {
	public int addHomogeneousGroupSt(HomogeneousGroupSt hgSt );
	public List<HomogeneousGroupSt> listHomogeneousGroupSt();
	public void removeHomogeneousGroupSt(int id);
	public HomogeneousGroupSt getHomogeneousGroupSt(int id);
	public void updateHomogeneousGroupSt(HomogeneousGroupSt hgSt );

}
