package org.services.layer.wits.services.parser.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.services.layer.wits.services.parser.ReadingObjectCoordinate;
import org.services.layer.wits.services.parser.ReadingObjectToMove;
import org.services.layer.wits.services.parser.ReadingBarcodeContex;
import org.services.layer.wits.services.parser.ReadingBarcodeParserConstants;
import org.services.layer.wits.services.parser.ReadingBarcodePhrase;

public class ReadingBarcodeContexImpl implements ReadingBarcodeContex , Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected List<ReadingBarcodePhrase> listReadingBarcodePhrase;
    protected String readinBarcodeContext;
    public ReadingBarcodeContexImpl()
    {
    	listReadingBarcodePhrase = new ArrayList<ReadingBarcodePhrase>();
    	readinBarcodeContext = "";
    }
    @Override
    public String readContext(String inputToRead) {
    	String retMessage = ReadingBarcodeParserConstants.SINTAX_CHECK_OK;
    	if (inputToRead.substring(0, 1).equals("C") &&
    			inputToRead.length()==ReadingBarcodeParserConstants.LEN_OBJECT_CONTAINER)
    	{
    		readinBarcodeContext = inputToRead;
    	}
    	else
    	{
    		if (!inputToRead.substring(0, 1).equals("C"))
    			retMessage = ReadingBarcodeParserConstants.CHARACTER_NOT_COVERED;
    		else
    			retMessage = ReadingBarcodeParserConstants.LENGTH_LINE_NOT_COVERED;   
    	}
    	return retMessage;	
    }
    public List<ReadingBarcodePhrase> getListReadingBarcodePhrase() {
    	return listReadingBarcodePhrase;
    }

    public void setListReadingBarcodePhrase(
    		List<ReadingBarcodePhrase> listReadingBarcodePhrase) {
    	this.listReadingBarcodePhrase = listReadingBarcodePhrase;
    }
    @Override
    public void addReadingBarcodePhrase(ReadingBarcodePhrase readingBarcodePhrase) 
    {
    	listReadingBarcodePhrase.add(readingBarcodePhrase);
    }
	@Override
	public void updateReadingBarcodePhrase(ReadingObjectCoordinate objectCoordinate) {
		listReadingBarcodePhrase.get(listReadingBarcodePhrase.size()-1).updateCoordinate(objectCoordinate);
		
	}
	@Override
	public void updateReadingBarcodePhrase(ReadingObjectToMove objectToMove) {
		listReadingBarcodePhrase.get(listReadingBarcodePhrase.size()-1).updateObjectToMove(objectToMove);
		
	}
	@Override
	public boolean isMissingObjectCoordinateFromReadingBarcodePhrase() {
		return listReadingBarcodePhrase.get(listReadingBarcodePhrase.size()-1).getCoordinate()==null;
	}
	@Override
	public boolean isListReadingBarcodePhraseEmpty() {
		return listReadingBarcodePhrase.size()==0;
	}
	@Override
	public boolean isMissingObjectToMoveFromReadingBarcodePhrase() {
	    return listReadingBarcodePhrase.get(listReadingBarcodePhrase.size()-1).getObjectToMove()==null;
	}
	public String getReadinBarcodeContext() {
		return readinBarcodeContext;
	}
	public void setReadinBarcodeContext(String readinBarcodeContext) {
		this.readinBarcodeContext = readinBarcodeContext;
	}
	@Override
	public ReadingObjectCoordinate inheritingObjectCoordinate() {
		ReadingObjectCoordinate retObjectCoordinate = null;
		if (listReadingBarcodePhrase.size()>0)
		 retObjectCoordinate = listReadingBarcodePhrase.get(listReadingBarcodePhrase.size()-1).getCoordinate();
		else
	     retObjectCoordinate = new ReadingObjectCoordinateImpl(); // default
		return retObjectCoordinate;
	}
	@Override
	public Integer inheritingValueCoordinate(String coordinate ) {
		Integer retCoordinate = 0;
		for (int i=listReadingBarcodePhrase.size()-1;i>=0;i--)
		{
			if (listReadingBarcodePhrase.get(i).getCoordinate()!=null &&
					listReadingBarcodePhrase.get(i).getCoordinate().isNotEmptyCoordinate(coordinate))
			{
				retCoordinate = listReadingBarcodePhrase.get(i).getCoordinate().getCoordinateXYZ().get(coordinate);
				break;
			}
		}
		return retCoordinate;
	}

	@Override
	public void clearLastWamatObject() {
		if (listReadingBarcodePhrase.size()>0)
		{
			if (listReadingBarcodePhrase.get(listReadingBarcodePhrase.size()-1).getObjectToMove()!=null)
			{
			 // delete last Read WamatObject
			 listReadingBarcodePhrase.get(listReadingBarcodePhrase.size()-1).setObjectToMove(null);
			}
		}
		
	}
 
}
