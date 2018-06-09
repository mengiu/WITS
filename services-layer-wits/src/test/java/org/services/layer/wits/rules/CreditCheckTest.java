package org.services.layer.wits.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.services.layer.wits.services.drools.CreditCheckRuleRunner;
import org.services.layer.wits.services.model.LoanApplicant;
import org.junit.Test;

public class CreditCheckTest {
	
	@Test
	public void testCreditCheckFailed(){
		LoanApplicant piggy = new LoanApplicant();
		piggy.setName("Miss Piggy");
		piggy.setIncome(100);
		piggy.setLoanAmount(90);
		
		boolean result = false;
		
		try {
			 result = CreditCheckRuleRunner.runRules(piggy);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		assertFalse(result);
	}
	
	@Test
	public void testCreditCheckPassed(){
		LoanApplicant kermit = new LoanApplicant();
		kermit.setName("Kermit");
		kermit.setIncome(1000);
		kermit.setLoanAmount(10);
		
		boolean result = false;
		
		try {
			 result = CreditCheckRuleRunner.runRules(kermit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(result);
	}
	
}
