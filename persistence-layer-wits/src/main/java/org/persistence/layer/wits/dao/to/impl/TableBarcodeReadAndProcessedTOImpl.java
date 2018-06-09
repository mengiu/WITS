package org.persistence.layer.wits.dao.to.impl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.persistence.layer.wits.dao.to.TableBarcodeReadAndProcessedTO;

@XmlRootElement
public class TableBarcodeReadAndProcessedTOImpl implements
TableBarcodeReadAndProcessedTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String destination;
	protected short positionX;
	protected short positionY;
	protected short positionZ;
	protected String object;
	protected String objectType;
	protected boolean immovable;
	protected boolean packaged;
	public TableBarcodeReadAndProcessedTOImpl()
	{
		immovable = false;
		packaged = false;
	}
	@Override
	@XmlAttribute
	public String getDestination() {
		return destination;
	}
	@Override
	public void setDestination(String destination) {
		this.destination = destination;
	}
	@Override
	@XmlAttribute
	public short getPositionX() {
		return positionX;
	}
	@Override
	public void setPositionX(short positionX) {
		this.positionX = positionX;
	}
	@Override
	@XmlAttribute
	public short getPositionY() {
		return positionY;
	}
	@Override
	public void setPositionY(short positionY) {
		this.positionY = positionY;
	}
	@Override
	@XmlAttribute
	public short getPositionZ() {
		return positionZ;
	}
	@Override
	public void setPositionZ(short positionZ) {
		this.positionZ = positionZ;
	}
	@Override
	@XmlAttribute
	public String getObject() {
		return object;
	}
	@Override
	public void setObject(String object) {
		this.object = object;
	}
	@Override
	@XmlAttribute
	public String getObjectType() {
		return objectType;
	}
	@Override
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	@Override
	@XmlAttribute
	public boolean isImmovable() {
		return immovable;
	}
	@Override
	public void setImmovable(boolean immovable) {
		this.immovable = immovable;
	}
	@Override
	@XmlAttribute
	public boolean isPackaged() {
		return packaged;
	}
	@Override
	public void setPackaged(boolean packaged) {
		this.packaged = packaged;
	}

}
