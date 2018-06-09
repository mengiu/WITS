package org.services.layer.wits.services.model;

import java.io.Serializable;
import java.util.List;

import org.persistence.layer.wits.enumusertypes.WamatObjectStatusType;
import org.persistence.layer.wits.form.ContainingUnit;
import org.persistence.layer.wits.form.WamatObject;
import org.services.layer.wits.services.ContainingUnitService;
import org.services.layer.wits.services.WamatObjectService;
import org.services.layer.wits.services.parser.ReadingObjectCoordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecondLevelControlPosition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ContainingUnit containingUnitDest;
	protected ContainingUnit containingUnitMoved;
	protected WamatObject wamatObjectMoved;
	protected ReadingObjectCoordinate objectCoordinate;
	protected StatusReturnAnswer statusReturnAnswer;
	protected String messageErrorRet;
	protected static Logger logger = LoggerFactory.getLogger(SecondLevelControlPosition.class);
	protected boolean duplicateAdmitted;
	protected List<WamatObject> listWamatObjectMoved;
	protected List<ContainingUnit> listContainingUnitMoved;
	protected List<WamatObject> listWamatObjectToReadByGroup;
	protected WamatObject wamatObjectPackaged;
	protected List<ReadingObjectCoordinate> listObjectCoordinate;
	protected WamatObjectService wamatObjectService;
	protected ContainingUnitService containingUnitService;

	public SecondLevelControlPosition(WamatObjectService wamatObjectService,
			ContainingUnitService containingUnitService)
	{
		this.wamatObjectService = wamatObjectService;
		this.containingUnitService = containingUnitService;
	}
	public StatusReturnAnswer getStatusReturnAnswer() {
		return statusReturnAnswer;
	}
	public void setStatusReturnAnswer(StatusReturnAnswer statusReturnAnswer) {
		this.statusReturnAnswer = statusReturnAnswer;
	}
	public ContainingUnit getContainingUnitDest() {
		return containingUnitDest;
	}
	public void setContainingUnitDest(ContainingUnit containingUnitDest) {
		this.containingUnitDest = containingUnitDest;
	}
	public ContainingUnit getContainingUnitMoved() {
		return containingUnitMoved;
	}
	public void setContainingUnitMoved(ContainingUnit containingUnitMoved) {
		this.containingUnitMoved = containingUnitMoved;
	}
	public WamatObject getWamatObjectMoved() {
		return wamatObjectMoved;
	}
	public void setWamatObjectMoved(WamatObject wamatObjectMoved) {
		this.wamatObjectMoved = wamatObjectMoved;
	}
	public ReadingObjectCoordinate getObjectCoordinate() {
		return objectCoordinate;
	}
	public void setObjectCoordinate(ReadingObjectCoordinate objectCoordinate) {
		this.objectCoordinate = objectCoordinate;
	}
	public String getMessageErrorRet() {
		return messageErrorRet;
	}
	public void setMessageErrorRet(String messageErrorRet) {
		this.messageErrorRet = messageErrorRet;
	}
	public boolean isDuplicateCoordinate() {
		int cont = 0;
		boolean bRet = false;
		for (ReadingObjectCoordinate item  : listObjectCoordinate)
		{
			if (item.getCoordinateXYZ().get("X")==objectCoordinate.getCoordinateXYZ().get("X") &&
					item.getCoordinateXYZ().get("Y")==objectCoordinate.getCoordinateXYZ().get("Y") &&
					item.getCoordinateXYZ().get("Z")==objectCoordinate.getCoordinateXYZ().get("Z"))
			{
				cont++;
			}
		}
		bRet = (cont>1);
		if (!bRet)
		{
			bRet = (!((containingUnitService.getContainingUnit(containingUnitDest, 
					objectCoordinate.getCoordinateXYZ().get("X").shortValue(), 
					objectCoordinate.getCoordinateXYZ().get("Y").shortValue(), 
					objectCoordinate.getCoordinateXYZ().get("Z").shortValue())==null)
					&&
					(wamatObjectService.getWamatObject(containingUnitDest, 
							objectCoordinate.getCoordinateXYZ().get("X").shortValue(), 
							objectCoordinate.getCoordinateXYZ().get("Y").shortValue(), 
							objectCoordinate.getCoordinateXYZ().get("Z").shortValue())==null)));
		}
		return bRet;
	}
	public WamatObjectStatusType getWamatObjectMovedStatus() {
		if (wamatObjectMoved!=null)
			return WamatObjectStatusType.valueOf(wamatObjectMoved.getStatusSt().getNameStatusSt().toUpperCase());
		return null;
	}
	public List<WamatObject> getListWamatObjectMoved() {
		return listWamatObjectMoved;
	}
	public void setListWamatObjectMoved(List<WamatObject> listWamatObjectMoved) {
		this.listWamatObjectMoved = listWamatObjectMoved;
	}
	public boolean isDuplicateWamatObject() {
		int cont = 0;
		if (listWamatObjectMoved!=null)
		{
			for ( WamatObject item : listWamatObjectMoved)
			{
				if (wamatObjectMoved.getIdWamatObject()==item.getIdWamatObject())
				{
					cont++;
				}
			}
		}
		return (cont>1);
	}
	public boolean isDuplicateContainingUnit() {
		int cont = 0;
		if (listContainingUnitMoved!=null)
		{
			for ( ContainingUnit item : listContainingUnitMoved)
			{
				if (containingUnitMoved.getIdContainingUnit()==item.getIdContainingUnit())
				{
					cont++;
				}
			}
		}
		return (cont>1);
	}
	public boolean isContainerUnitImmovable() {
		if (containingUnitMoved!=null)
		{
			return containingUnitMoved.getContainingUnitTypeSt().getIsImmobile();
		}
		return false;
	}
	public boolean isObjectMovedAnContainerUnit() {
		if (containingUnitMoved!=null)
		{
			return true;
		}
		return false;
	}
	public List<ContainingUnit> getListContainingUnitMoved() {
		return listContainingUnitMoved;
	}
	public void setListContainingUnitMoved(
			List<ContainingUnit> listContainingUnitMoved) {
		this.listContainingUnitMoved = listContainingUnitMoved;
	}
	public List<WamatObject> getListWamatObjectToReadByGroup() {
		return listWamatObjectToReadByGroup;
	}
	public void setListWamatObjectToReadByGroup(
			List<WamatObject> listWamatObjectToReadByGroup) {
		this.listWamatObjectToReadByGroup = listWamatObjectToReadByGroup;
	}
	public boolean isObjectMovedInAllowedState() {
		if (isObjectMovedAnContainerUnit())
			return true;
		else
			return   (!
					(
							getWamatObjectMovedStatus().equals(WamatObjectStatusType.PROCESSATO) ||
							getWamatObjectMovedStatus().equals(WamatObjectStatusType.CANCELLATO)
							)
					);

	}

	public boolean isObjectMovedAnObjectRequestedBySameOperationalGroup() {
		if (containingUnitMoved!=null)
		{
			if (containingUnitMoved.getWamatObjectsForFkContainingUnit()!=null)
			{
				for (WamatObject item : containingUnitMoved.getWamatObjectsForFkContainingUnit())
				{
					this.wamatObjectPackaged = item;
					for (WamatObject itemInner : listWamatObjectToReadByGroup)
					{
						if (item.getIdWamatObject()==itemInner.getIdWamatObject())
							return true;
					}
				}
			}
		}
		else
		{
			for (WamatObject item : listWamatObjectToReadByGroup)
			{
				if (wamatObjectMoved.getIdWamatObject()==item.getIdWamatObject())
					return true;
			}
		}
		return false;
	}
	public WamatObject getWamatObjectPackaged() {
		return wamatObjectPackaged;
	}
	public void setWamatObjectPackaged(WamatObject wamatObjectPackaged) {
		this.wamatObjectPackaged = wamatObjectPackaged;
	}
	public List<ReadingObjectCoordinate> getListObjectCoordinate() {
		return listObjectCoordinate;
	}
	public void setListObjectCoordinate(
			List<ReadingObjectCoordinate> listObjectCoordinate) {
		this.listObjectCoordinate = listObjectCoordinate;
	}
	public boolean isDuplicateAdmitted() {
		return duplicateAdmitted;
	}
	public void setDuplicateAdmitted(boolean duplicateAdmitted) {
		this.duplicateAdmitted = duplicateAdmitted;
	}
}
