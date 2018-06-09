package org.services.layer.wits.services.drools;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.activiti.bpmn.model.BusinessRuleTask;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.compiler.PackageBuilderConfiguration;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.rule.builder.dialect.mvel.MVELDialectConfiguration;
import org.drools.runtime.StatelessKnowledgeSession;
import org.drools.template.ObjectDataCompiler;
import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;
import org.services.layer.wits.services.model.LoanApplicant;
import org.services.layer.wits.services.model.SecondLevelControlPosition;
import org.services.layer.wits.services.model.StatusReturnAnswer;

public class PositionCheckRuleRunner {
	private static String droolsRulebaseAsDrl;
	
	private static void generateRules() {
		    //List<DiscountRuleEntry> ruleEntries = rulesDAO.findAllDiscountRuleEntries();

		    //InputStream is = this.getClass().getResourceAsStream("/test.drt");
		    //droolsRulebaseAsDrl = new ObjectDataCompiler().compile(ruleEntries, is);
		  }


	public static boolean runRules(SecondLevelControlPosition secondLevelControlPosition ) throws Exception {

		KnowledgeBase kbase = readKnowledgeBase();
		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		ksession.execute(secondLevelControlPosition);
		System.out.println("Done firing.. --> result = " + secondLevelControlPosition.getStatusReturnAnswer());
		BusinessRuleTask businessRuleTask;
		return secondLevelControlPosition.getStatusReturnAnswer().equals(StatusReturnAnswer.OK);
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
        PackageBuilderConfiguration conf = new PackageBuilderConfiguration();
        ((MVELDialectConfiguration) conf.getDialectConfiguration("mvel")).setStrict(false);
        ((MVELDialectConfiguration) conf.getDialectConfiguration("mvel")).setLangLevel(5);
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(conf);
		kbuilder.add(
				ResourceFactory.newByteArrayResource( droolsRulebaseAsDrl.getBytes("UTF-8")) ,
				//ResourceFactory.newClassPathResource("rules" 
				//        + File.separator + "CheckPositionRules.drl" , PositionCheckRuleRunner.class),
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
