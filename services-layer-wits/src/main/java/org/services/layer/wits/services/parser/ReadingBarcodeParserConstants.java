package org.services.layer.wits.services.parser;

public interface ReadingBarcodeParserConstants {
	  static final String SINTAX_CHECK_OK               = "OK";
	  static final String LENGTH_LINE_NOT_COVERED       = "length.line.not.covered";
	  static final String CHARACTER_NOT_COVERED         = "character.not.covered";
	  static final String NUMBER_FORMAT_EXCEPTION       = "number.format.exception";
	  //static final int LEN_CONTEXT                      = 7;
	  static final int LEN_OBJECT_CONTAINER             = 7;
	  static final int LEN_OBJECT_WAMAT                 = 9;
	  static final int LEN_OBJECT_WAMAT_WITS2           = 10;
	  static final int LEN_COMMAND_COORDINATE           = 3;
	  static final String COMMAND_UNDEFINED             = "command.undefined";
	  static final String ACTION_NOT_ALLOWED            = "action.not.allowed";
	  
}
