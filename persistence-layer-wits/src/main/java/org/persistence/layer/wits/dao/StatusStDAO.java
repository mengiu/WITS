package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.StatusSt;

public interface StatusStDAO {
	public int addStatusSt(StatusSt statusSt );
	public List<StatusSt> listStatusSt();
	public void removeStatusSt(int id);
	public StatusSt getStatusSt(int id);
	public void updateStatusSt(StatusSt statusSt);
}
