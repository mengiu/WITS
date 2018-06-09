package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.WacSt;

public interface WacStDAO {
	public int addWacSt(WacSt wacSt );
	public List<WacSt> listWacSt();
	public void removeWacSt(int id);
	public WacSt getWacSt(int id);
	public void updateWacSt(WacSt wacSt);

}
