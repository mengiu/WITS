package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.Wac2ExtFieldTypeSt;
import org.persistence.layer.wits.form.WacSt;

public interface Wac2ExtFieldTypeStDAO {
	public int addWac2ExtFieldTypeSt(Wac2ExtFieldTypeSt w2eft );
	public List<Wac2ExtFieldTypeSt> listWac2ExtFieldTypeSt();
	public void removeWac2ExtFieldTypeSt(int id);
	public Wac2ExtFieldTypeSt getWac2ExtFieldTypeSt(int id);
	public void updateWac2ExtFieldTypeSt(Wac2ExtFieldTypeSt w2eft);
	public List<Wac2ExtFieldTypeSt> listWac2ExtFieldTypeSt(WacSt wacSt);

}
