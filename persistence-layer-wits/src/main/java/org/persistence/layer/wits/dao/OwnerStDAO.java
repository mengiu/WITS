package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.OwnerSt;

public interface OwnerStDAO {
	public int addOwnerSt(OwnerSt ow );
	public List<OwnerSt> listOwnerSt();
	public void removeOwnerSt(int id);
	public OwnerSt getOwnerSt(int id);
	public void updateOwnerSt(OwnerSt ow );

}
