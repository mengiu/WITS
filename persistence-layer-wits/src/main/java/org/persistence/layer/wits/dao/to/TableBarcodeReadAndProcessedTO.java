package org.persistence.layer.wits.dao.to;

import java.io.Serializable;

public interface TableBarcodeReadAndProcessedTO extends Serializable {
	public String getDestination();
	public void setDestination(String destination);
	public short getPositionX();
	public void setPositionX(short positionX);
	public short getPositionY();
	public void setPositionY(short positionY);
	public short getPositionZ();
	public void setPositionZ(short positionZ);
	public String getObject();
	public void setObject(String object);
	public String getObjectType();
	public void setObjectType(String objectType);
	public boolean isImmovable();
	public void setImmovable(boolean immovable);
	public boolean isPackaged();
	public void setPackaged(boolean packaged);

}
