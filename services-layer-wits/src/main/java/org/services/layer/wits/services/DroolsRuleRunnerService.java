package org.services.layer.wits.services;

import org.services.layer.wits.services.model.SecondLevelControlPosition;
import org.services.layer.wits.services.model.SecondLevelControlStartPWMO;

public interface DroolsRuleRunnerService {
	public boolean positionCheckRuleRunner(SecondLevelControlPosition secondLevelControlPosition) throws Exception;
	public boolean rangeWamatInputProcessPWMOCheckRuleRunner(SecondLevelControlStartPWMO SecondLevelControlStartPWMO) throws Exception;
	public boolean findNumberInstacesProcessPWMOCheckRuleRunner(SecondLevelControlStartPWMO SecondLevelControlStartPWMO) throws Exception;
	
}
