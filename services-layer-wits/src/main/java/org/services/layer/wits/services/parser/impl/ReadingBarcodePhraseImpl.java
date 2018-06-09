package org.services.layer.wits.services.parser.impl;

import java.io.Serializable;

import org.services.layer.wits.services.parser.ReadingObjectCoordinate;
import org.services.layer.wits.services.parser.ReadingObjectToMove;
import org.services.layer.wits.services.parser.ReadingBarcodePhrase;

public class ReadingBarcodePhraseImpl implements ReadingBarcodePhrase , Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	protected  ReadingObjectCoordinate coordinate;
    protected  ReadingObjectToMove objectToMove;

	@Override
	public void addCoordinate(ReadingObjectCoordinate coordinate) {
		this.coordinate = coordinate;
	}
	@Override
	public void addObjectToMove(ReadingObjectToMove objectToMove) {
		this.objectToMove = objectToMove;
	}
	@Override
	public ReadingObjectCoordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(ReadingObjectCoordinate coordinate) {
		this.coordinate = coordinate;
	}
	@Override
	public ReadingObjectToMove getObjectToMove() {
		return objectToMove;
	}
	@Override
	public void setObjectToMove(ReadingObjectToMove objectToMove) {
		this.objectToMove = objectToMove;
	}
	@Override
	public void updateCoordinate(ReadingObjectCoordinate coordinate) {
		this.coordinate.updateCoordinate(coordinate);
		
	}
	@Override
	public void updateObjectToMove(ReadingObjectToMove objectToMove) {
		this.objectToMove = objectToMove;
		
	}

}
