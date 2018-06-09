package org.persistence.layer.wits.logic;

import javax.persistence.EntityManager;
//import javax.persistence.ParameterMode;
//import javax.persistence.StoredProcedureQuery;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
@Transactional( propagation = Propagation.REQUIRED )
public class ColumnMetadataJPA21Test {
	@Autowired
	@Qualifier("entityManager")
	private EntityManager em;

public void testStoredProcedure()
 {
		/*StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("WITS.VIEW_COLUMN_METADATA.SET_VALUES");
		// set parameters
		storedProcedure.registerStoredProcedureParameter("TABLENAME", String.class, ParameterMode.IN);
		storedProcedure.setParameter("TABLENAME", "WAMAT_OBJECT");
		// execute SP
		storedProcedure.execute();*/

 }
}
