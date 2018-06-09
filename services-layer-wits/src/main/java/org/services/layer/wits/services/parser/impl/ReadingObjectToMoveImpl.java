package org.services.layer.wits.services.parser.impl;

import java.io.Serializable;

import org.services.layer.wits.services.parser.ReadingObjectToMove;
import org.services.layer.wits.services.parser.ReadingBarcodeParserConstants;

public class ReadingObjectToMoveImpl implements ReadingObjectToMove , Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String objectToMove;
	protected boolean containerUnit;

	public ReadingObjectToMoveImpl()
	{
		containerUnit = false;
	}
	@Override
	public String readObject(String inputToRead) {
		String returnMessage = ReadingBarcodeParserConstants.SINTAX_CHECK_OK;
		if ((inputToRead.substring(0, 1).equals("C") &&
				inputToRead.length()==ReadingBarcodeParserConstants.LEN_OBJECT_CONTAINER) ||
				inputToRead.length()==ReadingBarcodeParserConstants.LEN_OBJECT_WAMAT ||
				inputToRead.length()==ReadingBarcodeParserConstants.LEN_OBJECT_WAMAT_WITS2)
		{
			if (inputToRead.length()==ReadingBarcodeParserConstants.LEN_OBJECT_CONTAINER)
				containerUnit = true;
			if (inputToRead.length()==ReadingBarcodeParserConstants.LEN_OBJECT_WAMAT_WITS2)
				objectToMove = inputToRead.substring(0, inputToRead.length()-1);
			else
				objectToMove = inputToRead;

		}
		else
		{
			returnMessage = ReadingBarcodeParserConstants.LENGTH_LINE_NOT_COVERED;   

		}
		return returnMessage;
	}

	@Override
	public String getObjectToMove() {
		return objectToMove;
	}
	@Override
	public boolean isContainerUnit() {
		return containerUnit;
	}


}
