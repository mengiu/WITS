package org.services.layer.wits.services.parser.impl;

import org.services.layer.wits.services.parser.ReadingBarcodeCommand;
import org.services.layer.wits.services.parser.ReadingBarcodeParserConstants;

public class ReadingBarcodeCommandImpl implements ReadingBarcodeCommand {
	ReadingBarcodeCommandEnum readingBarcodeCommandEnum;
	@Override
	public String readCommand(String inputToRead) {
		String retMessage = ReadingBarcodeParserConstants.SINTAX_CHECK_OK;
		readingBarcodeCommandEnum = ReadingBarcodeCommandEnum.UNDEFINED;
		if (inputToRead.substring(0, 1).equals("A") &&
				inputToRead.length()==ReadingBarcodeParserConstants.LEN_COMMAND_COORDINATE)
		{
			Integer command = new Integer(inputToRead.substring(1, inputToRead.length()));
			if (command.intValue()==ReadingBarcodeCommandEnum.RESET.ordinal())
			{
				readingBarcodeCommandEnum = ReadingBarcodeCommandEnum.RESET;
			}
			else
			{
				if (command.intValue()==ReadingBarcodeCommandEnum.CLEAR_LAST.ordinal())
				{
					readingBarcodeCommandEnum = ReadingBarcodeCommandEnum.CLEAR_LAST;

				}
				else
				{
					if (command.intValue()==ReadingBarcodeCommandEnum.TERMINATE.ordinal())
					{
						readingBarcodeCommandEnum = ReadingBarcodeCommandEnum.TERMINATE;

					}
					else
					{
					 retMessage = ReadingBarcodeParserConstants.COMMAND_UNDEFINED;
					}
				}
			}
		}
		else
		{
	   		if (inputToRead.substring(0, 1).equals("C"))
    			retMessage = ReadingBarcodeParserConstants.CHARACTER_NOT_COVERED;
    		else
    			retMessage = ReadingBarcodeParserConstants.LENGTH_LINE_NOT_COVERED;   
			
		}
		
		return retMessage;
	}
	@Override
	public ReadingBarcodeCommandEnum getReadingBarcodeCommandEnum() {
		return readingBarcodeCommandEnum;
	}

}
