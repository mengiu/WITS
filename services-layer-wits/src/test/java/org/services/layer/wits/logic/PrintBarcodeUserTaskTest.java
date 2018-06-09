package org.services.layer.wits.logic;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.form.OwnerSt;
import org.services.layer.wits.services.OwnerStService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",
                      defaultRollback=false)
@Transactional( propagation = Propagation.REQUIRED )
public class PrintBarcodeUserTaskTest {
	 @Autowired
	 @Qualifier("ownerStService")
	 OwnerStService ownerStService;
	 //@Autowired
	 //@Qualifier("roleStService")
	 //RoleStService roleStService;

	
	 @Test
	 public void printBarcodeUserTaskServiceTest()
	 {
			
		 StringBuilder sb = new StringBuilder();
		 Formatter formatter = new Formatter(sb, Locale.US);
		 formatter.format("%09d", 20);
		    List<String> usersList = new ArrayList<String>();
			
			OwnerSt ownerSt = ownerStService.getOwnerSt(new Integer("81"));
			/*RoleSt operator = roleStService.getRoleSt(3);
			for (WitsGroupSt item : ownerSt.getWitsGroupSts())
			{
			 for (WitsUser2Role2Group itemInner : item.getWitsUser2Role2Groups())
			 {
			  if (itemInner.getRoleSt().equals(operator))
			  {
			   usersList.add(itemInner.getWitsUser().getName()); 
			  }
			 }
			}*/
			String listAssignes = "";
			if (usersList.size()>0)
			{
			 for (String item : usersList )	
				listAssignes += item + ",";
			}
			else
			{
			  // generate custom exception 	
			}
			if (listAssignes.length()>0)
				listAssignes = listAssignes.substring(0, listAssignes.length()-1);	
		 
	 }
	
}
