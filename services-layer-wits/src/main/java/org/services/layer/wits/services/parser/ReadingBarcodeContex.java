package org.services.layer.wits.services.parser;

import java.util.List;

public interface ReadingBarcodeContex {
  public String readContext( String inputToRead );
  public void addReadingBarcodePhrase(ReadingBarcodePhrase readingBarcodePhrase); 
  public void updateReadingBarcodePhrase(ReadingObjectCoordinate objectCoordinate); 
  public void updateReadingBarcodePhrase(ReadingObjectToMove objectToMove);
  public boolean isMissingObjectCoordinateFromReadingBarcodePhrase();
  public boolean isListReadingBarcodePhraseEmpty();
  public boolean isMissingObjectToMoveFromReadingBarcodePhrase();
  public List<ReadingBarcodePhrase> getListReadingBarcodePhrase();
  public String getReadinBarcodeContext();
  public ReadingObjectCoordinate inheritingObjectCoordinate();
  public void clearLastWamatObject();
  public Integer inheritingValueCoordinate(String coordinate );
}
