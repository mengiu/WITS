package org.services.layer.wits.services.drools;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;
import org.services.layer.wits.services.ActGeBytearrayService;
import org.services.layer.wits.services.model.SecondLevelControlStartPWMO;
import org.services.layer.wits.services.model.StatusReturnAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindNumberInstacesProcessPWMOCheckRuleRunner {
	protected static Logger logger = LoggerFactory.getLogger(FindNumberInstacesProcessPWMOCheckRuleRunner.class);
	public boolean runRules(SecondLevelControlStartPWMO SecondLevelControlStartPWMO,ActGeBytearrayService actGeBytearrayService) throws Exception {

		KnowledgeBase kbase = readKnowledgeBase(actGeBytearrayService);
		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		ksession.execute(SecondLevelControlStartPWMO);
		logger.debug("Done firing.. --> result = " + SecondLevelControlStartPWMO.getStatusReturnAnswer());
		return !SecondLevelControlStartPWMO.getStatusReturnAnswer().equals(StatusReturnAnswer.ERROR);
	}

	private KnowledgeBase readKnowledgeBase(ActGeBytearrayService actGeBytearrayService) throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(
				ResourceFactory.newByteArrayResource(actGeBytearrayService.getActGeBytearrayRuleByte("%FindNumberInstacesProcess.drl")),
				ResourceType.DRL);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

}
