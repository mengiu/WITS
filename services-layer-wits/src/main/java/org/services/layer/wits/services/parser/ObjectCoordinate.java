package org.services.layer.wits.services.parser;

import java.util.Map;

public interface ObjectCoordinate {
	  public String readCoordinate( String inputToRead );
      public void updateCoordinate( ObjectCoordinate coordinate );
      public Map<String, Integer> getCoordinateXYZ();
      public boolean isEmptySomeCoordinate();
}
