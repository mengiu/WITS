package org.persistence.layer.wits.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.persistence.layer.wits.dao.GenerationProcessStDAO;
import org.persistence.layer.wits.dao.WasteManagementPlanStDAO;
import org.persistence.layer.wits.dao.Wmp2GenerationProcessDAO;
import org.persistence.layer.wits.form.GenerationProcessSt;
import org.persistence.layer.wits.form.WasteManagementPlanSt;
import org.persistence.layer.wits.form.Wmp2GenerationProcess;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
@Transactional( propagation = Propagation.REQUIRED )
public class Wmp2GenerationProcessTest {
    @Autowired
    WasteManagementPlanStDAO wasteManagementPlanStDAO;
    @Autowired
    GenerationProcessStDAO generationProcessStDAO;
    @Autowired
    Wmp2GenerationProcessDAO wmp2GenerationProcessDAO;
    
	@Test
	public void getWmp2GenerationProcessTest()
    {
		WasteManagementPlanSt wasteManagementPlanSt = wasteManagementPlanStDAO.getWasteManagementPlanSt(8);
		GenerationProcessSt generationProcessSt = generationProcessStDAO.getGenerationProcessSt(3);
		Wmp2GenerationProcess wmp2GenerationProcess = wmp2GenerationProcessDAO.getWmp2GenerationProcess(wasteManagementPlanSt, generationProcessSt);
		System.out.println(wmp2GenerationProcess.getIdWmp2GenerationProcess());
    }
 
}
