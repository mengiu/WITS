package org.services.layer.wits.logic;

import oracle.jdbc.xa.client.OracleXADataSource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistence.layer.wits.form.BarcodeTypeSt;
import org.persistence.layer.wits.form.MaterialSt;
import org.services.layer.wits.services.BarcodeTypeStService;
import org.services.layer.wits.services.MaterialStService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Jpeg;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.BarcodeCodabar;
import com.itextpdf.text.pdf.BarcodeDatamatrix;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BarcodeEANSUPP;
import com.itextpdf.text.pdf.BarcodeInter25;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.BarcodePostnet;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.MutableLocalEntityProvider;
 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="txManager",
                      defaultRollback=false)
@Transactional( propagation = Propagation.REQUIRED )
public class BarcodeTypeStTest {
	@Autowired
	@Qualifier("barcodeTypeStService")
	BarcodeTypeStService barcodeTypeStService;
	@Autowired
	@Qualifier("materialStService")
	MaterialStService materialStService;
	private static Logger logger = LoggerFactory.getLogger(BarcodeTypeStTest.class);
	
	private JPAContainer<MaterialSt> materials;
    @BeforeClass
    public static void setUpClass() throws Exception {
        // rcarver - setup the jndi context and the datasource
        try {
            // Create initial context
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES, 
                "org.apache.naming");            
            InitialContext ic = new InitialContext();

            ic.createSubcontext("java:");
            ic.createSubcontext("java:/comp");
            ic.createSubcontext("java:/comp/env");
            ic.createSubcontext("java:/comp/env/jdbc");
           
            // Construct DataSource
            OracleXADataSource ds = new OracleXADataSource();
            ds.setURL("jdbc:oracle:thin:@dbdev.jrc.org:1521:appl_duc");
            ds.setUser("WITSUSRW");
            ds.setPassword("ALd5fE7a");
            
            ic.bind("java:/comp/env/jdbc/dataSource", ds);
        } catch (NamingException ex) {
            logger.debug(ex.getMessage());
        }
    }
    
    @Before
    public void createJPAContainer()
    {
		materials = JPAContainerFactory.make(MaterialSt.class,
				"primary");
		materials.getEntityProvider().setEntitiesDetached(false);
		((MutableLocalEntityProvider<MaterialSt>) materials.getEntityProvider()).setTransactionsHandledByProvider(false);
    	
    }
    @Test
    public void testJPAContainer()
    {
    	MaterialSt materialSt = materialStService.getMaterialSt(81);
		materials.removeItem(materialSt.getIdMaterialSt());
    }
	@Test
	public void BarcodeTypeStServiceTest(){
		BarcodeTypeSt barcodeTypeSt = new BarcodeTypeSt();
		barcodeTypeSt.setNameBarcodeTypeSt("Modello Codice a barre CC 220");
		barcodeTypeStService.addBarcodeTypeSt(barcodeTypeSt);
	}
	@Test
	public void createAndPrintBarcodeTest() throws IOException, DocumentException {
        Document document = new Document(new Rectangle(340, 842));
        // step 2
        //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        // step 3
        document.open();
        // step 4
        PdfContentByte cb = writer.getDirectContent();
        // INTER25
        document.add(new Paragraph("Barcode Interleaved 2 of 5"));
        BarcodeInter25 code25 = new BarcodeInter25();
        code25.setGenerateChecksum(true);
        code25.setCode("41-1200076041-001");
        document.add(code25.createImageWithBarcode(cb, null, null));
        code25.setCode("411200076041001");
        document.add(code25.createImageWithBarcode(cb, null, null));
        code25.setCode("0611012345678");
        code25.setChecksumText(true);
        document.add(code25.createImageWithBarcode(cb, null, null));
        // step 5
        document.close();
        Jpeg imgjpg = new Jpeg(baos.toByteArray()); 
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.JPEG;
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        if (services.length > 0)
        {
            DocPrintJob printJob = services[2].createPrintJob();
            Doc doc = new SimpleDoc(code25.createImageWithBarcode(cb, null, null), flavor , null);
            try {
                printJob.print(doc , new HashPrintRequestAttributeSet());
            } catch (PrintException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        	
        }
	}

	/**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws    DocumentException 
     * @throws    IOException
     */
	@Test
    public void createPdf() throws IOException, DocumentException {
		String filename = "C:/Users/mennegi/barcodeFile.pdf";
		// step 1
        Document document = new Document(new Rectangle(340, 842));
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //PdfWriter writer = PdfWriter.getInstance(document, baos);
        // step 3
        document.open();
        // step 4
        PdfContentByte cb = writer.getDirectContent();
 
        // EAN 13
        document.add(new Paragraph("Barcode EAN.UCC-13"));
        BarcodeEAN codeEAN = new BarcodeEAN();
        codeEAN.setCode("4512345678906");
        document.add(new Paragraph("default:"));
        document.add(codeEAN.createImageWithBarcode(cb, null, null));
        codeEAN.setGuardBars(false);
        document.add(new Paragraph("without guard bars:"));
        document.add(codeEAN.createImageWithBarcode(cb, null, null));
        codeEAN.setBaseline(-1f);
        codeEAN.setGuardBars(true);
        document.add(new Paragraph("text above:"));
        document.add(codeEAN.createImageWithBarcode(cb, null, null));
        codeEAN.setBaseline(codeEAN.getSize());
 
        // UPC A
        document.add(new Paragraph("Barcode UCC-12 (UPC-A)"));
        codeEAN.setCodeType(Barcode.UPCA);
        codeEAN.setCode("785342304749");
        document.add(codeEAN.createImageWithBarcode(cb, null, null));
 
        // EAN 8
        document.add(new Paragraph("Barcode EAN.UCC-8"));
        codeEAN.setCodeType(Barcode.EAN8);
        codeEAN.setBarHeight(codeEAN.getSize() * 1.5f);
        codeEAN.setCode("34569870");
        document.add(codeEAN.createImageWithBarcode(cb, null, null));
 
        // UPC E
        document.add(new Paragraph("Barcode UPC-E"));
        codeEAN.setCodeType(Barcode.UPCE);
        codeEAN.setCode("03456781");
        document.add(codeEAN.createImageWithBarcode(cb, null, null));
        codeEAN.setBarHeight(codeEAN.getSize() * 3f);
 
        // EANSUPP
        document.add(new Paragraph("Bookland"));
        document.add(new Paragraph("ISBN 0-321-30474-8"));
        codeEAN.setCodeType(Barcode.EAN13);
        codeEAN.setCode("9781935182610");
        BarcodeEAN codeSUPP = new BarcodeEAN();
        codeSUPP.setCodeType(Barcode.SUPP5);
        codeSUPP.setCode("55999");
        codeSUPP.setBaseline(-2);
        BarcodeEANSUPP eanSupp = new BarcodeEANSUPP(codeEAN, codeSUPP);
        document.add(eanSupp.createImageWithBarcode(cb, null, BaseColor.BLUE));
 
        // CODE 128
        document.add(new Paragraph("Barcode 128"));
        Barcode128 code128 = new Barcode128();
        code128.setCode("0123456789 hello");
        document.add(code128.createImageWithBarcode(cb, null, null));
        code128.setCode("0123456789\uffffMy Raw Barcode (0 - 9)");
        code128.setCodeType(Barcode.CODE128_RAW);
        document.add(code128.createImageWithBarcode(cb, null, null));
 
        // Data for the barcode :
        String code402 = "24132399420058289";
        String code90 = "3700000050";
        String code421 = "422356";
        StringBuffer data = new StringBuffer(code402);
        data.append(Barcode128.FNC1);
        data.append(code90);
        data.append(Barcode128.FNC1);
        data.append(code421);
        Barcode128 shipBarCode = new Barcode128();
        shipBarCode.setX(0.75f);
        shipBarCode.setN(1.5f);
        shipBarCode.setSize(10f);
        shipBarCode.setTextAlignment(Element.ALIGN_CENTER);
        shipBarCode.setBaseline(10f);
        shipBarCode.setBarHeight(50f);
        shipBarCode.setCode(data.toString());
        document.add(shipBarCode.createImageWithBarcode(cb, BaseColor.BLACK,
                BaseColor.BLUE));
 
        // it is composed of 3 blocks whith AI 01, 3101 and 10
        Barcode128 uccEan128 = new Barcode128();
        uccEan128.setCodeType(Barcode.CODE128_UCC);
        uccEan128.setCode("(01)00000090311314(10)ABC123(15)060916");
        document.add(uccEan128.createImageWithBarcode(cb, BaseColor.BLUE,
                BaseColor.BLACK));
        uccEan128.setCode("0191234567890121310100035510ABC123");
        document.add(uccEan128.createImageWithBarcode(cb, BaseColor.BLUE,
                BaseColor.RED));
        uccEan128.setCode("(01)28880123456788");
        document.add(uccEan128.createImageWithBarcode(cb, BaseColor.BLUE,
                BaseColor.BLACK));
 
        // INTER25
        document.add(new Paragraph("Barcode Interleaved 2 of 5"));
        BarcodeInter25 code25 = new BarcodeInter25();
        code25.setGenerateChecksum(true);
        code25.setCode("41-1200076041-001");
        document.add(code25.createImageWithBarcode(cb, null, null));
        code25.setCode("411200076041001");
        document.add(code25.createImageWithBarcode(cb, null, null));
        code25.setCode("0611012345678");
        code25.setChecksumText(true);
        document.add(code25.createImageWithBarcode(cb, null, null));
 
        // POSTNET
        document.add(new Paragraph("Barcode Postnet"));
        BarcodePostnet codePost = new BarcodePostnet();
        document.add(new Paragraph("ZIP"));
        codePost.setCode("01234");
        document.add(codePost.createImageWithBarcode(cb, null, null));
        document.add(new Paragraph("ZIP+4"));
        codePost.setCode("012345678");
        document.add(codePost.createImageWithBarcode(cb, null, null));
        document.add(new Paragraph("ZIP+4 and dp"));
        codePost.setCode("01234567890");
        document.add(codePost.createImageWithBarcode(cb, null, null));
 
        document.add(new Paragraph("Barcode Planet"));
        BarcodePostnet codePlanet = new BarcodePostnet();
        codePlanet.setCode("01234567890");
        codePlanet.setCodeType(Barcode.PLANET);
        document.add(codePlanet.createImageWithBarcode(cb, null, null));
 
        // CODE 39
        document.add(new Paragraph("Barcode 3 of 9"));
        Barcode39 code39 = new Barcode39();
        code39.setCode("ITEXT IN ACTION");
        document.add(code39.createImageWithBarcode(cb, null, null));
 
        document.add(new Paragraph("Barcode 3 of 9 extended"));
        Barcode39 code39ext = new Barcode39();
        code39ext.setCode("iText in Action");
        code39ext.setStartStopText(false);
        code39ext.setExtended(true);
        document.add(code39ext.createImageWithBarcode(cb, null, null));
 
        // CODABAR
        document.add(new Paragraph("Codabar"));
        BarcodeCodabar codabar = new BarcodeCodabar();
        codabar.setCode("A123A");
        codabar.setStartStopText(true);
        document.add(codabar.createImageWithBarcode(cb, null, null));
 
        // PDF417
        document.add(new Paragraph("Barcode PDF417"));
        BarcodePDF417 pdf417 = new BarcodePDF417();
        String text = "Call me Ishmael. Some years ago--never mind how long "
            + "precisely --having little or no money in my purse, and nothing "
            + "particular to interest me on shore, I thought I would sail about "
            + "a little and see the watery part of the world.";
        pdf417.setText(text);
        Image img = pdf417.getImage();
        img.scalePercent(50, 50 * pdf417.getYHeight());
        document.add(img);
 
        document.add(new Paragraph("Barcode Datamatrix"));
        BarcodeDatamatrix datamatrix = new BarcodeDatamatrix();
        datamatrix.generate(text);
        img = datamatrix.createImage();
        document.add(img);
 
        document.add(new Paragraph("Barcode QRCode"));
        BarcodeQRCode qrcode = new BarcodeQRCode("Moby Dick by Herman Melville", 1, 1, null);
        img = qrcode.getImage();
        document.add(img);
 
        // step 5
        document.close();
        
        
    }

}
