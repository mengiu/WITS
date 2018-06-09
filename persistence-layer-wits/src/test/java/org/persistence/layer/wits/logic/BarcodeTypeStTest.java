package org.persistence.layer.wits.logic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.dao.BarcodeTypeStDAO;
import org.persistence.layer.wits.form.BarcodeTypeSt;
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
public class BarcodeTypeStTest {
	@Autowired
	BarcodeTypeStDAO barcodeTypeStDAO;
	
	@Test
	public void BarcodeTypeStDAOTest(){
		//BarcodeTypeSt barcodeTypeSt = new BarcodeTypeSt();
		/*barcodeTypeSt.setModelBarcode("Modello RP rateo di dose");
		barcodeTypeSt.setDescriptionModelBarcode("Modello RP rateo di dose");
		barcodeTypeStDAO.addBarcodeTypeSt(barcodeTypeSt);
		BarcodeTypeSt barcodeTypeSt1 = new BarcodeTypeSt();
		barcodeTypeSt1.setModelBarcode("Modello codice a barre piccolo");
		barcodeTypeSt1.setDescriptionModelBarcode("Modello codice a barre piccolo");
		barcodeTypeStDAO.addBarcodeTypeSt(barcodeTypeSt1);
		BarcodeTypeSt barcodeTypeSt2 = new BarcodeTypeSt();
		barcodeTypeSt2.setModelBarcode("Modello Codice a barre CC 220");
		barcodeTypeSt2.setDescriptionModelBarcode("Modello Codice a barre CC 220");*/
		/*barcodeTypeSt.setNameBarcodeTypeSt("hhcqqghh");
		barcodeTypeSt.setDescriptionBarcodeTypeSt("dlsddddd");
		barcodeTypeStDAO.addBarcodeTypeSt(barcodeTypeSt);*/
		barcodeTypeStDAO.removeBarcodeTypeSt(4);
		
	}
    
	@Test
	public void listBarcodeTypeStTest()
	{
		List<BarcodeTypeSt> list = barcodeTypeStDAO.listBarcodeTypeSt();
		for (BarcodeTypeSt item : list )
		{
			/*for (Field field : list.get(0).ge.getClass().getDeclaredFields()) {
				Column column = field.getAnnotation(Column.class);
				if (column != null) {
					System.out.print(column.name());
					column.columnDefinition();
				}
			}*/
			/*String idFieldName=null;
			List<String> collFieldNames = new ArrayList<String>();
			List<String> fkNames = new ArrayList<String>();
			for(Field f: item.getClass().getDeclaredFields()){
			    for( Annotation a: f.getAnnotations()){
			        if( a.annotationType().equals(javax.persistence.Id.class) ){
			            idFieldName = f.getName();
			        } else if(
			a.annotationType().equals(javax.persistence.OneToMany.class) ){
			            String foreignKey=null;
			            for( Method m: a.annotationType().getMethods()){
			                if( "mappedBy".equals(m.getName()) ){
			                    try {
									foreignKey  = (String) m.invoke(a);
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			                }
			            }
			            collFieldNames.add(f.getName());
			            fkNames.add(foreignKey);
			        }
			    }
			}*/


			try {
				item.getClass().getDeclaredField("nameBarcodeTypeSt").getAnnotation(Column.class).columnDefinition();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        
			//System.out.println(item.getNameBarcodeTypeSt());
		}
	}
}
