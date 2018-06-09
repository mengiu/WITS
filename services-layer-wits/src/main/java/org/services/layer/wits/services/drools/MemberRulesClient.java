package org.services.layer.wits.services.drools;

import org.drools.builder.ResourceType;
import org.drools.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("memberRulesClient")
public class MemberRulesClient {
	@Autowired
	@Qualifier("resTypeCheckPositionRules")
	private Resource resTypeCheckPositionRules;
	//private ResourceType resourceType;

	public Resource getResTypeCheckPositionRules() {
		return resTypeCheckPositionRules;
	}


	/*public ResourceType getResourceType() {
	return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
	this.resourceType = resourceType;
	}*/


}
