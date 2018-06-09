package org.services.layer.wits.services.model;

import java.io.Serializable;

import org.persistence.layer.wits.enumusertypes.ContainingUnitTypeRequestType;
import org.persistence.layer.wits.enumusertypes.TypeContainer;
import org.persistence.layer.wits.enumusertypes.TypeRequestWaMat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestContainingUnit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContainingUnitTypeRequestType containingUnitTypeRequestType;
	private StatusReturnAnswerRequestContainer status;
	private TypeContainer typecontainer;
	private TypeRequestWaMat typeRequestWaMat;
	protected static final Logger logger = LoggerFactory.getLogger(RequestContainingUnit.class);

	public RequestContainingUnit(String typeRequest )
	{
		containingUnitTypeRequestType = ContainingUnitTypeRequestType.valueOf(typeRequest);
		if (isContainerRequested())
			status = StatusReturnAnswerRequestContainer.CREATE_CONTAINER;
		else
			status = StatusReturnAnswerRequestContainer.NO_CONTAINER;
		if (isContainerOverpack())
			typecontainer = TypeContainer.OVERPACK;
		else
			typecontainer = TypeContainer.NO_OVERPACK;
		setTypeRequestWaMat(TypeRequestWaMat.NO_CANCELLED);
	}

	public ContainingUnitTypeRequestType getContainingUnitTypeRequestType() {
		logger.info(containingUnitTypeRequestType.getValue());
		return containingUnitTypeRequestType;
	}

	public void setContainingUnitTypeRequestType(
			ContainingUnitTypeRequestType containingUnitTypeRequestType) {
		this.containingUnitTypeRequestType = containingUnitTypeRequestType;
	}

	public StatusReturnAnswerRequestContainer getStatus() {
		logger.info(status.getValue());
		return status;
	}

	public void setStatus(StatusReturnAnswerRequestContainer status) {
		this.status = status;
	}
	public Boolean isContainerRequested()
	{
		if (containingUnitTypeRequestType.equals(ContainingUnitTypeRequestType.NO_CU_REQUESTED))
			return false;
		else 
			return true;
	}
	public Boolean isContainerOverpack()
	{
		if (containingUnitTypeRequestType.equals(ContainingUnitTypeRequestType.OVERPACK))
			return true;
		else 
			return false;
	}

	public TypeContainer getTypecontainer() {
		return typecontainer;
	}

	public void setTypecontainer(TypeContainer typecontainer) {
		this.typecontainer = typecontainer;
	}

	public TypeRequestWaMat getTypeRequestWaMat() {
		return typeRequestWaMat;
	}

	public void setTypeRequestWaMat(TypeRequestWaMat typeRequestWaMat) {
		this.typeRequestWaMat = typeRequestWaMat;
	}


}
