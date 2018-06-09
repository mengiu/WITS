package org.services.layer.wits.services.parser.impl;

public enum ReadingBarcodeCommandEnum {
     RESET("A00"),
     CLEAR_LAST("A01"),
     TERMINATE("A02"),
     UNDEFINED("A03");
	   
	    private final String readingBarcodeCommandTipe;
	    ReadingBarcodeCommandEnum (final String readingBarcodeCommandTipe )
	    {
	    	this.readingBarcodeCommandTipe = readingBarcodeCommandTipe;
	    }
		public String getValue() {
			return this.readingBarcodeCommandTipe;
		}

}
