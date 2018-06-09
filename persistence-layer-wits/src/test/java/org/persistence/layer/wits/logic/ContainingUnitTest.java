package org.persistence.layer.wits.logic;

import java.util.List;

import org.apache.commons.lang.SystemUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.ContainingUnitDAO;
import org.persistence.layer.wits.enumusertypes.ContainingUnitType;
import org.persistence.layer.wits.form.ContainingUnit;
import org.persistence.layer.wits.form.WamatObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:activiti.cfg.xml", "classpath:activiti-context.xml"})
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
@Transactional( propagation = Propagation.REQUIRED )
public class ContainingUnitTest {
	@Autowired
	ContainingUnitDAO containingUnitDAO;

	@Test
	public void containingUnitDAOTest() {
		ContainingUnit containingUnit = containingUnitDAO.getContainingUnit(40);
		ContainingUnit cu = containingUnit.getContainingUnit();
		System.out.println(cu.getNameContainingUnit());
	}
	@Test
	public void getContainingUnitTest()
	{
		ContainingUnit containingUnitDest = containingUnitDAO.getContainingUnit(143);
		ContainingUnit containingUnit = 
				containingUnitDAO.getContainingUnit(containingUnitDest,
						new Integer(6).shortValue(),
						new Integer(10).shortValue(),
						new Integer(6).shortValue());
		if (containingUnit!=null)
			System.out.println(containingUnit.getNameContainingUnit());
		else
			System.out.println("No Container Found!");

	}
	@Test
	public void listContainingUnitTest() {
		List<ContainingUnit> listContainingUnit = containingUnitDAO.listContainingUnit(true,true);
		for (ContainingUnit item : listContainingUnit)
			System.out.println(item.getNameContainingUnit());
	}

	@Test
	public void listContainingUnitTest2() {
		List<ContainingUnit> listContainingUnit = containingUnitDAO.listContainingUnit(true,null);
		for (ContainingUnit item : listContainingUnit)
			System.out.println(item.getNameContainingUnit());
	}

}
