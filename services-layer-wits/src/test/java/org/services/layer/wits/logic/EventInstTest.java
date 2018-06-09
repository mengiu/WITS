package org.services.layer.wits.logic;

import java.util.Date;

import org.activiti.engine.impl.identity.Authentication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.enumusertypes.WITSEventType;
import org.persistence.layer.wits.form.EventInstance;
import org.persistence.layer.wits.form.EventTypeSt;
import org.persistence.layer.wits.form.WamatObject;
import org.persistence.layer.wits.form.WitsUserSt;
import org.services.layer.wits.services.EventInstService;
import org.services.layer.wits.services.EventTypeStService;
import org.services.layer.wits.services.WamatObjectService;
import org.services.layer.wits.services.WitsUserStService;
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
public class EventInstTest {
	@Autowired
	@Qualifier("eventInstService")
	EventInstService eventInstService;
	@Autowired
	@Qualifier("witsUserStService")
	WitsUserStService witsUserStService;
	@Autowired
	@Qualifier("wamatObjectService")
	WamatObjectService wamatObjectService;
	@Autowired
	@Qualifier("eventTypeStService")
	EventTypeStService eventTypeStService;
	
	@Test
    public void EventInstServiceTest()
    {
		EventInstance eventInst = new EventInstance();
		eventInst.setDescriptionEventInstance("Evento Prova");
		eventInst.setEventDate(new Date());
		EventTypeSt eventTypeSt = eventTypeStService.getEventTypeSt(0);
		WamatObject wamatObject = wamatObjectService.getWamatObject(84);
		WitsUserSt witsUserSt = witsUserStService.getWitsUserSt(0);
		eventInst.setEventTypeSt(eventTypeSt);
		eventInst.setWamatObject(wamatObject);
		eventInst.setWitsUserSt(witsUserSt);
		eventInstService.addEventInst(eventInst);
		
    }
	
	@Test
	public void registerEventInstTest()
	{
		eventInstService.registerEventInst("WamatObject con id : " + 49 + " creato da : guissje" , "guissje" , 
				  49 , WITSEventType.CREAZIONE.ordinal()+1, null , 
				  null , null , null);
		
	}
}
