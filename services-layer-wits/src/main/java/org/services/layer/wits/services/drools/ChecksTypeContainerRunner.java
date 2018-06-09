package org.services.layer.wits.services.drools;

import java.io.File;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.compiler.PackageBuilderConfiguration;
import org.drools.io.ResourceFactory;
import org.drools.rule.builder.dialect.mvel.MVELDialectConfiguration;
import org.drools.runtime.StatelessKnowledgeSession;
import org.services.layer.wits.services.model.RequestContainingUnit;
import org.services.layer.wits.services.model.StatusReturnAnswerRequestContainer;

public class ChecksTypeContainerRunner {
	public static boolean runRules(RequestContainingUnit requestContainingUnit) throws Exception {
		KnowledgeBase kbase = readKnowledgeBase();
		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		System.out.println("Fire the rules!");
		ksession.execute(requestContainingUnit);
		return requestContainingUnit.getStatus().equals(StatusReturnAnswerRequestContainer.NO_CONTAINER);
	}
	private static KnowledgeBase readKnowledgeBase() throws Exception {
		PackageBuilderConfiguration conf = new PackageBuilderConfiguration();
		((MVELDialectConfiguration) conf.getDialectConfiguration("mvel")).setStrict(false);
		((MVELDialectConfiguration) conf.getDialectConfiguration("mvel")).setLangLevel(5);
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(conf);
		kbuilder.add(
				ResourceFactory.newClassPathResource("rules" 
						+ File.separator + "ChecksTypeContainer.drl"),
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
