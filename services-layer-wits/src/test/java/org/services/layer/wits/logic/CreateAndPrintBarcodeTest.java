package org.services.layer.wits.logic;

import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.form.ContainingUnit;
import org.persistence.layer.wits.form.WamatObject;
import org.services.layer.wits.services.ContainingUnitService;
import org.services.layer.wits.services.WamatObjectService;
import org.services.layer.wits.services.util.CreateAndPrintBarcode;
import org.services.layer.wits.services.util.MergePDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.DocumentException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",
defaultRollback=false)
@Transactional( propagation = Propagation.REQUIRED )
public class CreateAndPrintBarcodeTest {

	@Autowired
	@Qualifier("wamatObjectService")
	WamatObjectService wamatObjectService;
	@Autowired
	@Qualifier("containingUnitService")
	ContainingUnitService containingUnitService;

	@Test
	public void createBarcodeModelloCodiceABarrePiccoloTest()
	{
		List<WamatObject> listWamatObject = new ArrayList<WamatObject>();
		for (int index=180;index<=190;index++)
		{
			listWamatObject.add(wamatObjectService.getWamatObject(index));
		}
		CreateAndPrintBarcode createAndPrintBarcode = new CreateAndPrintBarcode();
		List<InputStream> pdfs = new ArrayList<InputStream>();
		File fileConcat = new File("C:/Users/mennegi/barcodeFilePiccolo.pdf");
		try {
			int index = 1;
			for (WamatObject item : listWamatObject)
			{
				String filePathName = "C:/Users/mennegi/barcodeFilePiccolo" + index++ + ".pdf";
				File file = new File(filePathName);  
				createAndPrintBarcode.createBarcodeModelloCodiceABarrePiccolo(item, 
						file);
				pdfs.add(new FileInputStream(file));
			}
			if (pdfs.size()>0)
			{
				OutputStream output = new FileOutputStream(fileConcat);
				MergePDF.concatPDFs(pdfs, output, 290, 119 , false); // 102 mm x 42 mm
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void createBarcodeModelloRPRateodiDoseFormatoA5ContainingUnitOverpackTest()
	{
		List<ContainingUnit> listContainingUnit = new ArrayList<ContainingUnit>();
		for (int i=23;i<28;i++)
			listContainingUnit.add(containingUnitService.getContainingUnit(i));
		CreateAndPrintBarcode createAndPrintBarcode = new CreateAndPrintBarcode();
		List<InputStream> pdfs = new ArrayList<InputStream>();
		File fileConcat = new File("C:/Users/mennegi/barcodeFileA5.pdf");
		try {
			int index = 1;
			for (ContainingUnit item : listContainingUnit)
			{
				String filePathName = "C:/Users/mennegi/barcodeFilePiccolo" + index++ + ".pdf";
				File file = new File(filePathName);  
				createAndPrintBarcode.createBarcodeModelloRPRateodiDoseFormatoA5ContainingUnitOverpack(item, 
						file);
				pdfs.add(new FileInputStream(file));
			}
			if (pdfs.size()>0)
			{
				OutputStream output = new FileOutputStream(fileConcat);
				MergePDF.concatPDFs(pdfs, output, 420.0f, 595.0f , true); // 148 mm x 210 mm
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void createBarcodeModelloRPRateodiDoseFormatoA5Test()
	{
		List<WamatObject> listWamatObject = new ArrayList<WamatObject>();
		for (int index=180;index<=190;index++)
		{
			listWamatObject.add(wamatObjectService.getWamatObject(index));
		}
		CreateAndPrintBarcode createAndPrintBarcode = new CreateAndPrintBarcode();
		List<InputStream> pdfs = new ArrayList<InputStream>();
		File fileConcat = new File("C:/Users/mennegi/barcodeFileA5.pdf");
		try {
			int index = 1;
			for (WamatObject item : listWamatObject)
			{
				String filePathName = "C:/Users/mennegi/barcodeFileA5" + index++ + ".pdf";
				File file = new File(filePathName);  
				createAndPrintBarcode.createBarcodeModelloRPRateodiDoseFormatoA5(item, 
						file);
				pdfs.add(new FileInputStream(file));
			}
			if (pdfs.size()>0)
			{
				OutputStream output = new FileOutputStream(fileConcat);
				MergePDF.concatPDFs(pdfs, output, 420.0f, 595.0f , true); // 148 mm x 210 mm
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void createBarcodeModelloCodiceABarreCC220Test()
	{
		List<WamatObject> listWamatObject = new ArrayList<WamatObject>();
		for (int index=180;index<=182;index++)
		{
			listWamatObject.add(wamatObjectService.getWamatObject(index));
		}
		CreateAndPrintBarcode createAndPrintBarcode = new CreateAndPrintBarcode();
		List<InputStream> pdfs = new ArrayList<InputStream>();
		File fileConcat = new File("C:/Users/mennegi/barcodeFileCC220.pdf");
		try {
			int index = 1;
			for (WamatObject item : listWamatObject)
			{
				for (int ii=1;ii<=4;ii++)
				{
					String filePathName = "C:/Users/mennegi/barcodeFileCC220" + index++ + ".pdf";
					File file = new File(filePathName);  
					createAndPrintBarcode.createBarcodeModelloCodiceABarreCC220(item, 
							file);
					pdfs.add(new FileInputStream(file));
				}
			}
			if (pdfs.size()>0)
			{
				OutputStream output = new FileOutputStream(fileConcat);
				MergePDF.concatPDFs(pdfs, output, 284.0f, 1304.0f, false); // // 100 mm x 460 mm
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
