package org.persistence.layer.wits.logic;

import java.io.InputStream;
import java.util.List;

import org.activiti.bpmn.model.BusinessRuleTask;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;
import org.drools.template.ObjectDataCompiler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.ActGeBytearrayDAO;
import org.persistence.layer.wits.form.ActGeBytearray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
@Transactional( propagation = Propagation.REQUIRED )
public class ActGeBytearrayTest {
 @Autowired
 ActGeBytearrayDAO actGeBytearrayDAO;
 private ActGeBytearray actGeBytearray;
 private byte[] ruleBytes;
 
 @Test
 public void ActGeBytearrayDAOTest()
 {
	ruleBytes = actGeBytearrayDAO.getActGeBytearrayRuleByte("%CheckPositionRules.drl");
    //String droolsRulebaseAsDrl = new ObjectDataCompiler().compile(ruleEntries, is);
	boolean result = false;
	try {
	    
		result = runRules();
	} catch (Exception e) {
		e.printStackTrace();
	}	
	
 }
	public boolean runRules() throws Exception {

		KnowledgeBase kbase = readKnowledgeBase();
		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		ksession.execute(new Object());
		return true;
	}

	private KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(
				ResourceFactory.newByteArrayResource( ruleBytes ) ,
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
