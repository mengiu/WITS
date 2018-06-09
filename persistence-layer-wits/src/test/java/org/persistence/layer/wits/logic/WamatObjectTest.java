package org.persistence.layer.wits.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.ContainingUnitDAO;
import org.persistence.layer.wits.dao.OwnerStDAO;
import org.persistence.layer.wits.dao.StatusStDAO;
import org.persistence.layer.wits.dao.WamatObjectDAO;
import org.persistence.layer.wits.form.ContainingUnit;
import org.persistence.layer.wits.form.OwnerSt;
import org.persistence.layer.wits.form.StatusSt;
import org.persistence.layer.wits.form.WamatObject;
import org.persistence.layer.wits.form.Wmo2ActHiProcinst;
import org.persistence.layer.wits.identity.WitsAuthentication;
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
public class WamatObjectTest {
	@Autowired
	WamatObjectDAO wamatObjectDAO;
	@Autowired
	StatusStDAO statusStDAO;
	@Autowired
    OwnerStDAO ownerStDAO;
	@Autowired
	ContainingUnitDAO containingUnitDAO;

	@Test
	public void wamatObjectDAO()
	{
		List<WamatObject> list = wamatObjectDAO.listWamatObject();
		WamatObject item = wamatObjectDAO.getWamatObject(264);
		for ( Wmo2ActHiProcinst innerItem : item.getWmo2ActHiProcinsts())
		{
			innerItem.getInitiator();
		}
	}
	@Test
	public void wamatListWamatObject()
	{
		List<WamatObject> list = wamatObjectDAO.listWamatObject(null,3, null ,
				null , null ,
				null , null,
				null, null);
		for ( WamatObject item : list)
		{
			item.getNameWamatObject();
		}
	}

	@Test
	public void listWamatObject()
	{
		StatusSt approvato = statusStDAO.getStatusSt(4);
		StatusSt chiuso = statusStDAO.getStatusSt(9);
		StatusSt elaborazione = statusStDAO.getStatusSt(2);
		List<StatusSt> listStatusSt = new ArrayList<StatusSt>();
		listStatusSt.add(elaborazione);
		listStatusSt.add(chiuso);
		listStatusSt.add(approvato);
		OwnerSt ownerSt = ownerStDAO.getOwnerSt(11);
		List<WamatObject> list = wamatObjectDAO.listWamatObject(listStatusSt,ownerSt);
		if (list!=null)
		{
			for ( WamatObject item : list)
			{
				if (item.getStatusSt().getIdStatusSt()==2)
				 System.out.println(item.getNameWamatObject());
			}
		}
	}

	@Test
	public void listWamatObjectFilter()
	{
		List<Integer> list = new ArrayList<Integer>();
		list.add(16);
		List<WamatObject> listWamatObject = wamatObjectDAO.listWamatObject(null, null, null, null, 
				null, null, null, "000000123", "000000245");
		for (WamatObject item : listWamatObject)
			System.out.println(item.getNameWamatObject());
			
	}
	@Test
	public void setWamatObjectOnPostUpdateTest()
	{
		WitsAuthentication.setAuthenticatedUserId("castepo");
		WamatObject item = wamatObjectDAO.getWamatObject(264);
		item.setDescriptionWamatObject("pippo");;
		wamatObjectDAO.updateWamatObject(item);
	}
	
	@Test
	public void getWamatObjectTest()
	{
		ContainingUnit containingUnit = containingUnitDAO.getContainingUnit(7465);
		WamatObject wamatObject = wamatObjectDAO.getWamatObject(containingUnit, (short)0, (short)0, (short)0);
		if (wamatObject!=null)
		{
			System.out.println(wamatObject.getNameWamatObject());
		}
	}
}
