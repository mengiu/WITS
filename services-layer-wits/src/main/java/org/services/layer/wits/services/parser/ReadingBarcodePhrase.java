package org.services.layer.wits.services.parser;

public interface ReadingBarcodePhrase {
  public void addCoordinate( ReadingObjectCoordinate coordinate );
  public void addObjectToMove(ReadingObjectToMove objectToMove);
  public ReadingObjectCoordinate getCoordinate();
  public ReadingObjectToMove getObjectToMove();
  public void updateCoordinate( ReadingObjectCoordinate coordinate );
  public void updateObjectToMove(ReadingObjectToMove objectToMove);
  public void setObjectToMove(ReadingObjectToMove objectToMove);
}
