package org.services.layer.wits.services.parser;

import org.services.layer.wits.services.parser.impl.ReadingBarcodeCommandEnum;

public interface ReadingBarcodeCommand {
  public String readCommand( String inputRead );
  public ReadingBarcodeCommandEnum getReadingBarcodeCommandEnum();
}
