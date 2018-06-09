package org.services.layer.wits.services.parser.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.services.layer.wits.services.parser.ReadingObjectCoordinate;
import org.services.layer.wits.services.parser.ReadingBarcodeParserConstants;

public class ReadingObjectCoordinateImpl implements ReadingObjectCoordinate , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Map<String, Integer > coordinateXYZ;
	public ReadingObjectCoordinateImpl()
	{
		coordinateXYZ = new HashMap<String, Integer>(); // dEFAULT
		coordinateXYZ.put("X", 0 );
		coordinateXYZ.put("Y", 0 );
		coordinateXYZ.put("Z", 0 );

	}

	public ReadingObjectCoordinateImpl( int X , int Y , int Z )
	{
		if (coordinateXYZ==null)
			coordinateXYZ = new HashMap<String, Integer>();
		coordinateXYZ.put("X", X );
		coordinateXYZ.put("Y", Y );
		coordinateXYZ.put("Z", Z );
	}

	@Override
	public String readCoordinate(String inputToRead ) {
		String retMessage = ReadingBarcodeParserConstants.SINTAX_CHECK_OK;
		if (((inputToRead.substring(0, 1).equals("X")) ||
				(inputToRead.substring(0, 1).equals("Y")) ||
				(inputToRead.substring(0, 1).equals("Z"))) &&
				inputToRead.length()==ReadingBarcodeParserConstants.LEN_COMMAND_COORDINATE)
		{
			if (inputToRead.substring(0, 1).equals("X"))
			{
				String position = inputToRead.substring(1, inputToRead.length());
				try {
					Integer positionNumber = new Integer(position);
					coordinateXYZ.put("X", positionNumber);
				} catch (NumberFormatException e) {
					retMessage = ReadingBarcodeParserConstants.NUMBER_FORMAT_EXCEPTION;
				}
			}
			else
			{
				if (inputToRead.substring(0, 1).equals("Y"))
				{
					String position = inputToRead.substring(1, inputToRead.length());
					try {
						Integer positionNumber = new Integer(position);
						coordinateXYZ.put("Y", positionNumber);
					} catch (NumberFormatException e) {
						retMessage = ReadingBarcodeParserConstants.NUMBER_FORMAT_EXCEPTION;
					}
				}
				else
				{
					if (inputToRead.substring(0, 1).equals("Z"))
					{
						String position = inputToRead.substring(1, inputToRead.length());
						try {
							Integer positionNumber = new Integer(position);
							coordinateXYZ.put("Z", positionNumber);
						} catch (NumberFormatException e) {
							retMessage = ReadingBarcodeParserConstants.NUMBER_FORMAT_EXCEPTION;
						}
					}

				}

			}
		}
		else
		{
			if (!((inputToRead.substring(0, 1).equals("X")) ||
					(inputToRead.substring(0, 1).equals("Y")) ||
					(inputToRead.substring(0, 1).equals("Z"))))
				retMessage = ReadingBarcodeParserConstants.CHARACTER_NOT_COVERED;
			else
				retMessage = ReadingBarcodeParserConstants.LENGTH_LINE_NOT_COVERED;   

		}
		return retMessage;
	}

	public Map<String, Integer> getCoordinateXYZ() {
		return coordinateXYZ;
	}

	public void setCoordinateXYZ(Map<String, Integer> coordinateXYZ) {
		this.coordinateXYZ = coordinateXYZ;
	}


	@Override
	public void updateCoordinate(ReadingObjectCoordinate coordinate) {
		for (String item : coordinate.getCoordinateXYZ().keySet())
		{
			if (coordinate.getCoordinateXYZ().get(item)!=0)
			{
				 coordinateXYZ.put(item, coordinate.getCoordinateXYZ().get(item));
			}
		}
		
	}
	@Override
	public boolean isEmptySomeCoordinate() {
		for (String item : coordinateXYZ.keySet())
		{
			if (coordinateXYZ.get(item)==0)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isEmptyCoordinate(String coordinate) {
			if (coordinateXYZ.get(coordinate)==0)
			{
				return true;
			}
		return false;
	}

	@Override
	public boolean isNotEmptyCoordinate(String coordinate) {
			if (coordinateXYZ.get(coordinate)>0)
			{
				return true;
			}
		return false;
	}

	@Override
	public List<String> getListEmptyCoordinate() {
		List<String> listEmptyCoordinate = new ArrayList<String>();
		for (String item : coordinateXYZ.keySet())
		{
			if (coordinateXYZ.get(item)==0)
			{
				listEmptyCoordinate.add(item);
			}
		}
		return listEmptyCoordinate;
	}

}
