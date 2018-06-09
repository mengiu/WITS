package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.WitsUserSt;

public interface WitsUserStDAO {
	public int addWitsUserSt(WitsUserSt user);
	public List<WitsUserSt> listWitsUserSt();
	public void removeWitsUserSt(int id);
	public WitsUserSt getWitsUserSt(int id);
	public void updateWitsUserSt(WitsUserSt user);
	public WitsUserSt getWitsUserSt(String userId, boolean valid);

}
