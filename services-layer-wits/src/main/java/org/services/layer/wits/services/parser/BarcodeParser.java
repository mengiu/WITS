package org.services.layer.wits.services.parser;

import java.util.List;

public interface BarcodeParser {
  public String readSession(String InputRead, int line);
  public void writeSessionInfoOnLogger();
  public List<ReadingBarcodeSession> getListReadingBarcodeSession();
}
