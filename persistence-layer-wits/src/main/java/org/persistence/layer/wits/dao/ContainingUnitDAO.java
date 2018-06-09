package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.form.ContainingUnit;

public interface ContainingUnitDAO {
	public int addContainingUnit(ContainingUnit cu );
	public List<ContainingUnit> listContainingUnit();
	public void removeContainingUnit(int id);
	public ContainingUnit getContainingUnit(int id);
	public void updateContainingUnit(ContainingUnit cu );
    public List<ContainingUnit> listContainingUnit( Boolean immovable, Boolean active );
    public List<ContainingUnit> listContainingUnit( Boolean immovable, Boolean active, ContainingUnit father );
    public ContainingUnit getContainingUnit(String cuName );
	public ContainingUnit getContainingUnit(ContainingUnit containingUnit, Short positionX,
			Short positionY, Short positionZ);
	public List<ContainingUnit> hasChildren(ContainingUnit father);
	public List<ContainingUnit> listContainingUnitMovable( Boolean immovable, Boolean active, 
			List<Integer> listCUByFkCuFirstImmobile );    
	public String getPositionContainingUnit(int id);

}
