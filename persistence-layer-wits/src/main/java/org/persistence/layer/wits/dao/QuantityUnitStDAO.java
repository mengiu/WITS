package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.QuantityUnitSt;

public interface QuantityUnitStDAO {
	public int addQuantityUnitSt(QuantityUnitSt qus );
	public List<QuantityUnitSt> listQuantityUnitSt();
	public void removeQuantityUnitSt(int id);
	public QuantityUnitSt getQuantityUnitSt(int id);
	public void updateQuantityUnitSt(QuantityUnitSt qus );

}
