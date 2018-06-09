package org.services.layer.wits.services.drools;

import java.io.File;

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
import org.services.layer.wits.services.model.SecondLevelControlPosition;
import org.services.layer.wits.services.model.StatusReturnAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PositionCheckRulesRunner {
	protected static Logger logger = LoggerFactory.getLogger(PositionCheckRulesRunner.class);
	public boolean runRules(SecondLevelControlPosition secondLevelControlPosition,ActGeBytearrayService actGeBytearrayService) throws Exception {

		KnowledgeBase kbase = readKnowledgeBase(actGeBytearrayService);
		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		ksession.execute(secondLevelControlPosition);
		logger.debug("Done firing.. --> result = " + secondLevelControlPosition.getStatusReturnAnswer());
		return !secondLevelControlPosition.getStatusReturnAnswer().equals(StatusReturnAnswer.ERROR);
	}

	private KnowledgeBase readKnowledgeBase(ActGeBytearrayService actGeBytearrayService) throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(
				ResourceFactory.newByteArrayResource(actGeBytearrayService.getActGeBytearrayRuleByte("%CheckPositionRules.drl")),
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
