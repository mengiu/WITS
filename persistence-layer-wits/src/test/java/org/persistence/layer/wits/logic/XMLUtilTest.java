package org.persistence.layer.wits.logic;

import java.util.Date;
import java.util.List;

import javax.persistence.Table;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.AttachedDocument2TableDAO;
import org.persistence.layer.wits.dao.ViewMetadataColumnTableDAO;
import org.persistence.layer.wits.dao.WamatObjectDAO;
import org.persistence.layer.wits.dao.to.WamatObjectTO;
import org.persistence.layer.wits.dao.to.impl.AttachedDocument2TableTOImpl;
import org.persistence.layer.wits.dao.to.impl.WamatObjectTOImpl;
import org.persistence.layer.wits.enumusertypes.EntityEventType;
import org.persistence.layer.wits.form.AttachedDocument2Table;
import org.persistence.layer.wits.form.AttachedDocument2TableId;
import org.persistence.layer.wits.form.ViewMetadataColumnTable;
import org.persistence.layer.wits.form.WamatObject;
import org.persistence.layer.wits.util.XmlUtil;
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
public class XMLUtilTest {
	@Autowired
	WamatObjectDAO wamatObjectDAO;
	@Autowired
	AttachedDocument2TableDAO attachedDocument2TableDAO;
	
	@Autowired
	ViewMetadataColumnTableDAO viewMetadataColumnTableDAO;
	@Test
	public void convertToXmlTest()
	{
		WamatObject wamatObject = wamatObjectDAO.getWamatObject(168);
		List<ViewMetadataColumnTable> listViewMetadataColumnTable = viewMetadataColumnTableDAO.listViewMetadataColumnTable(WamatObject.class.getAnnotation(Table.class).name());
		WamatObjectTOImpl wamatObjectTO = new WamatObjectTOImpl(wamatObject,listViewMetadataColumnTable);
		WamatObjectTOImpl wamatObjectTORet = null;
		XmlUtil xmlUtil = new XmlUtil();
		try {
			String xml = xmlUtil.convertToXml(wamatObjectTO, wamatObjectTO.getClass());
			wamatObjectTORet = (WamatObjectTOImpl)xmlUtil.convertToObject(xml, wamatObjectTO.getClass());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void convertToXmlTest2()
	{
		AttachedDocument2TableId attachedDocument2TableId = new AttachedDocument2TableId();
		attachedDocument2TableId.setFkTableName("WAMAT_OBJECT");
		attachedDocument2TableId.setFkAttachedDocument(1);
		attachedDocument2TableId.setFkTableId(1);
		AttachedDocument2Table attachedDocument2Table = attachedDocument2TableDAO.getAttachedDocument2Table(attachedDocument2TableId);
		List<ViewMetadataColumnTable> listViewMetadataColumnTable = viewMetadataColumnTableDAO.listViewMetadataColumnTable(AttachedDocument2Table.class.getAnnotation(Table.class).name());
		AttachedDocument2TableTOImpl attachedDocument2TableTOPost = 
				new AttachedDocument2TableTOImpl(attachedDocument2Table,
						EntityEventType.POST_UPDATE,
						listViewMetadataColumnTable );
		XmlUtil xmlUtil = new XmlUtil();
		try {
			String xml = xmlUtil.convertToXml(attachedDocument2TableTOPost, attachedDocument2TableTOPost.getClass());
			AttachedDocument2TableTOImpl wamatObjectTORet = (AttachedDocument2TableTOImpl)xmlUtil.convertToObject(xml, attachedDocument2TableTOPost.getClass());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
