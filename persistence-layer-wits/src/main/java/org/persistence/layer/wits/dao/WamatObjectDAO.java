package org.persistence.layer.wits.dao;

import java.util.List;

import org.persistence.layer.wits.enumusertypes.WamatObjectStatusType;
import org.persistence.layer.wits.form.ContainingUnit;
import org.persistence.layer.wits.form.GenerationProcessInstance;
import org.persistence.layer.wits.form.OwnerSt;
import org.persistence.layer.wits.form.StatusSt;
import org.persistence.layer.wits.form.WamatObject;

public interface WamatObjectDAO {
	public int addWamatObject(WamatObject wmo );
	public List<WamatObject> listWamatObject();
	public void removeWamatObject(int id);
	public WamatObject getWamatObject(int id);
	public void updateWamatObject(WamatObject wmo );
	public WamatObjectStatusType getWamatObjectStatusType(WamatObject wmo);
	public String getPositionWamatObject(int id);
	public List<WamatObject> listWamatObject(
			Integer idContainerType,
			Integer idOwner,
			Integer idWac,
			Integer idWMOState,
			List<Integer> listIdCU,
			Integer idGenerationProcessSt,
			List<Integer> listIdMaterialSt,
			String leftIntervalBound,
			String rihgtIntervalBound);
	public List<WamatObject> listWamatObject(String idProcessInstance );
	public List<WamatObject> listWamatObject(OwnerSt owner,StatusSt status);
	public List<WamatObject> listWamatObject(GenerationProcessInstance generationProcessInstance);
	public WamatObject getWamatObject(ContainingUnit containingUnit, Short positionX,
			Short positionY, Short positionZ);
	public WamatObject getWamatObject(String codeWamat);
	public List<WamatObject> listWamatObject(List<StatusSt> listStatus, OwnerSt ownerSt);
	
}
