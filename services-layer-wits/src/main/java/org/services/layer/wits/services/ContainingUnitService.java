package org.services.layer.wits.services;

import java.util.List;

import org.persistence.layer.wits.enumusertypes.ContainingUnitType;
import org.persistence.layer.wits.form.ContainingUnit;

public interface ContainingUnitService {
	public int addContainingUnit(ContainingUnit cu );
	public List<ContainingUnit> listContainingUnit();
	public void removeContainingUnit(int id);
	public ContainingUnit getContainingUnit(int id);
	public void updateContainingUnit(ContainingUnit cu );
	public ContainingUnitType getTypeContainingUnit(int id );
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
