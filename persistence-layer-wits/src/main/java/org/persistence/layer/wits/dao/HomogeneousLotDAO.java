package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.HomogeneousGroupSt;
import org.persistence.layer.wits.form.HomogeneousLot;

public interface HomogeneousLotDAO {
	public int addHomogeneousLot(HomogeneousLot hl );
	public List<HomogeneousLot> listHomogeneousLot();
	public void removeHomogeneousLot(int id);
	public HomogeneousLot getHomogeneousLot(int id);
	public void updateHomogeneousLot(HomogeneousLot hl );
	public List<HomogeneousLot> listHomogeneousLot(HomogeneousGroupSt hgSt);
	public HomogeneousLot getHomogeneousLot(HomogeneousGroupSt hgSt, String nameHomogeneousLot);

}
