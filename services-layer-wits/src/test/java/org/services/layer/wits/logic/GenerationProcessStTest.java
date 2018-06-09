package org.services.layer.wits.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.form.GenerationProcessSt;
import org.services.layer.wits.services.GenerationProcessStService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",
                      defaultRollback=true)
@Transactional( propagation = Propagation.REQUIRED )
public class GenerationProcessStTest {
	@Autowired
	GenerationProcessStService generationProcessStService;
	@Test
	public void GenerationProcessStServiceTest() {
		GenerationProcessSt wm = new GenerationProcessSt();
		wm.setNameGenerationProcessSt("First Process Type");
		generationProcessStService.addGenerationProcessSt(wm);
		wm = new GenerationProcessSt();
		wm.setNameGenerationProcessSt("Second Process Type");
		generationProcessStService.addGenerationProcessSt(wm);
	}
	
}
