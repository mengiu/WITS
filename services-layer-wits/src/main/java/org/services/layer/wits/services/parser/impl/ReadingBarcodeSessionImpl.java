package org.services.layer.wits.services.parser.impl;


import java.io.Serializable;

import org.services.layer.wits.services.parser.ReadingBarcodeContex;
import org.services.layer.wits.services.parser.ReadingBarcodeSession;

public class ReadingBarcodeSessionImpl implements ReadingBarcodeSession , Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	enum statusRetCheck { OK , WARNING , ERROR };
	protected ReadingBarcodeContex readingBarcodeContex;
	
	@Override
	public void setReadingBarcodeContex(ReadingBarcodeContex readingBarcodeContex) {
		this.readingBarcodeContex = readingBarcodeContex;
	}

	@Override
	public ReadingBarcodeContex getReadingBarcodeContex() {
		return readingBarcodeContex;
	}

    

}
