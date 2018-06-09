package org.services.layer.wits.logic;

import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Formatter;
import java.util.Locale;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;

import org.junit.Test;
import org.persistence.layer.wits.form.WamatObject;
import org.services.layer.wits.RWMOVariables;
import org.services.layer.wits.services.util.PDFToImageConverter;
import org.services.layer.wits.services.util.PrintPdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;
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
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class PDFToImageConverterTest {
	@Test
	public void printBarcodeTest(){
		//Document document = new Document(new Rectangle(0,0,340,5102)); // 120 mm x 1800 mm

		// step 2
		try {
			File file = new File("C:/Users/mennegi/barcodeFile.pdf");  
			// maximum page size
			//Document document = new Document(new Rectangle(0,0,378,1739), 30,30,30,30); // 100 mm x 460 mm
			Document document = new Document(new Rectangle(0,0,284,1304)); // 100 mm x 460 mm
			// step 2
			PdfWriter writer =
					PdfWriter.getInstance(document, new FileOutputStream(file));
			// changes the user unit
			//writer.setUserunit(75000f);
			writer.setCompressionLevel(0);
			// step 3
			document.open();
			// step 4
			PdfContentByte cb = writer.getDirectContent();
			BarcodeInter25 code25 = new BarcodeInter25();
			code25.setGenerateChecksum(true);
			StringBuilder sb = new StringBuilder();
			Formatter formatter = new Formatter(sb, Locale.US);
			String wamatCode = formatter.format(RWMOVariables.BARCODE_FORMAT, 234).toString();
			code25.setCode(wamatCode);
			code25.setChecksumText(false);
			//code25.setBaseline(40.0f);
			code25.setBarHeight(1280.0f);
			code25.setX(2.9f);
			//code25.setSize(30.0f);
			Image img = code25.createImageWithBarcode(cb, null, null);
			img.getHeight(); //== 5100
			img.getWidth(); //== 336
			img.setAbsolutePosition(10,10);
			//img.setRotation(90f);
			//            (PageSize.POSTCARD.getWidth() - img.getScaledWidth()) / 2,
			//            (PageSize.POSTCARD.getHeight() - img.getScaledHeight()) / 2);
			writer.getDirectContent().addImage(img, true);
			BaseFont bf;
			try {
				bf = BaseFont.createFont();
				cb.beginText();
				cb.setFontAndSize(bf, 30 );
				//cb.setTextMatrix(0, 1, -1, 0, 270, 20 );
				//cb.showText("OBJECT ID : " + wamatCode );
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "OBJECT ID : " + wamatCode, 250, 370 , 270 );
				cb.endText();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//img.scaleAbsolute(340, 5102); 
			//Paragraph paragraph = new Paragraph(); 
			//paragraph.setLeading(img.getScaledHeight());
			//paragraph.add(new Chunk(img, 0, 0, true));
			//document.add(paragraph);
			// step 5
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void printBarcodeTest2(){
		//Document document = new Document(new Rectangle(0,0,340,5102)); // 120 mm x 1800 mm

		// step 2
		try {
			File file = new File("C:/Users/mennegi/barcodeFile.pdf");  
			// maximum page size
			Document document = new Document(new Rectangle(0,0,290,119)); // 102 mm x 42 mm
			// step 2
			PdfWriter writer =
					PdfWriter.getInstance(document, new FileOutputStream(file));
			// changes the user unit
			//writer.setUserunit(75000f);
			writer.setCompressionLevel(0);
			// step 3
			document.open();
			// step 4
			PdfContentByte cb = writer.getDirectContent();
			BarcodeInter25 code25 = new BarcodeInter25();
			code25.setGenerateChecksum(true);
			StringBuilder sb = new StringBuilder();
			Formatter formatter = new Formatter(sb, Locale.US);
			String wamatCode = formatter.format(RWMOVariables.BARCODE_FORMAT, 234).toString();
			code25.setCode(wamatCode);
			code25.setChecksumText(false);
			code25.setBaseline(16.0f);
			code25.setBarHeight(88.0f);
			code25.setX(3.4f);
			code25.setSize(18.0f);
			Image img = code25.createImageWithBarcode(cb, null, null);
			img.getHeight(); //== 5100
			img.getWidth(); //== 336
			img.setAbsolutePosition(10,10);
			writer.getDirectContent().addImage(img, true);
			BaseFont bf;
			try {
				bf = BaseFont.createFont();
				cb.beginText();
				cb.setFontAndSize(bf, 16.0f );
				//cb.setTextMatrix(0, 1, -1, 0, 270, 20 );
				//cb.showText("OBJECT ID : " + wamatCode );
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "WITS ID : " , 10 , 15 , 0 );
				cb.endText();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// step 5
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void printBarcodeTest3(){

		// step 2
		try {
			
			File file = new File("C:/Users/mennegi/barcodeFilePiccolo.pdf");  
			// maximum page size
			Document document = new Document(PageSize.A5); // 148 mm x 210 mm
			// step 2
			PdfWriter writer =
					PdfWriter.getInstance(document, new FileOutputStream(file));
			writer.setCompressionLevel(0);
			// step 3
			document.open();
			// step 4
			PdfContentByte cb = writer.getDirectContent();
			cb.setLineWidth(0f);
		    cb.moveTo(10, 585);
	        cb.lineTo(410, 585);
	        cb.moveTo(410, 585);
	        cb.lineTo(410, 10);
	        cb.moveTo(410, 10);
	        cb.lineTo(10, 10);
	        cb.moveTo(10, 10);
	        cb.lineTo(10, 585);
		    cb.moveTo(10, 510);
	        cb.lineTo(410, 510);
			int startY = 470;
			for (int index = 0;index<5;index++)
			{
			 cb.moveTo(10, startY);
		     cb.lineTo(410, startY);
		     startY -= 40;
			}
	        cb.stroke();
	        BarcodeInter25 code25 = new BarcodeInter25();
			code25.setGenerateChecksum(true);
			StringBuilder sb = new StringBuilder();
			Formatter formatter = new Formatter(sb, Locale.US);
			String wamatCode = formatter.format(RWMOVariables.BARCODE_FORMAT, 234).toString();
			code25.setCode(wamatCode);
			code25.setChecksumText(false);
			code25.setBaseline(6.0f);
			code25.setBarHeight(58.0f);
			code25.setX(2.4f);
			code25.setSize(8.0f);
			Image img = code25.createImageWithBarcode(cb, null, null);
			img.getHeight(); //== 5100
			img.getWidth(); //== 336
			img.setAbsolutePosition(210,515);
			writer.getDirectContent().addImage(img, true);
			Font fontnormal = FontFactory.getFont("Arial", 32.0f , Font.BOLD);
			BaseFont bf;
			try {
				bf = BaseFont.createFont();
				cb.beginText();
				cb.setFontAndSize(bf, 32.0f );
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT, wamatCode , 20 , 545 , 0 );
				cb.endText();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BaseFont bfWMO;
			try {
				bfWMO = BaseFont.createFont();
				cb.beginText();
				cb.setFontAndSize(bfWMO, 28.0f );
				int start = 480;
				for (int index = 0;index<5;index++)
				{
				 cb.showTextAligned(PdfContentByte.ALIGN_LEFT, wamatCode , 20 , start , 0 );
				 start-= 40;	
				}
				cb.endText();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// step 5
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void convertTest(){
		PDFToImageConverter converter = new PDFToImageConverter();  

		try {  
			File file = new File("C:/Users/mennegi/barcodeFile.pdf");  
			// Create temp pdf file.
			File tempPDF = File.createTempFile("pattern", ".pdf");
			// Create temp jpg file.
			File tempJPG = File.createTempFile("pattern", ".jpg");

			// Delete temp file when program exits.
			tempPDF.deleteOnExit();
			tempJPG.deleteOnExit();
			createPdf(tempPDF); 
			//converter.convert(tempPDF,tempJPG);  
			//printJPEGBarcode(tempJPG);
		} catch (Exception ex) {  

			ex.printStackTrace();  

		}  



	}
	@Test
	public void createAndPrintBarcodeTest() throws IOException, DocumentException {
		//File tempPDF = File.createTempFile("pattern", ".pdf");
		//tempPDF.deleteOnExit();
		Document document = new Document(new Rectangle(PageSize.A5));
		// step 2
		//PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(tempPDF));
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("C:/Users/mennegi/barcodeFile.pdf"));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//PdfWriter writer = PdfWriter.getInstance(document, baos);
		// step 3
		document.open();
		// step 4
		PdfContentByte cb = writer.getDirectContent();
		// INTER25
		BarcodeInter25 code25 = new BarcodeInter25();
		code25.setGenerateChecksum(true);
		code25.setCode("000000002");
		code25.setX(3.0f);
		//BaseFont bf = BaseFont.createFont();
		//Font fontbold = FontFactory.getFont("Times-Roman", 12, Font.BOLD);
		code25.setSize(20.0f);
		code25.setBaseline(30.0f);
		code25.setBarHeight(60.0f);
		Image img = code25.createImageWithBarcode(cb, null, null);
		img.scaleAbsolute(300, 150);

		//document.add(img);
		Paragraph paragraph = new Paragraph(); 
		paragraph.setLeading(img.getScaledHeight());
		paragraph.add(new Chunk(img, 0, 0, true));
		//paragraph.add(new Phrase("   "));
		//paragraph.add(new Phrase("000000002"));
		document.add(paragraph);
		LineSeparator ls = new LineSeparator();
		document.add(new Paragraph(new Chunk(ls)));
		/*code25.setCode("434567895");
		document.add(code25.createImageWithBarcode(cb, null, null));
		code25.setCode("434567895");
		code25.setChecksumText(true);
		document.add(code25.createImageWithBarcode(cb, null, null));*/

		// step 5
		document.close();
		/*try {
			PrintPdf printPDFFile = new PrintPdf(new FileInputStream(tempPDF), "Test Print PDF");
			printPDFFile.print();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		/*PDFToImageConverter converter = new PDFToImageConverter();  

         try {  

             converter.convert(baos);  

         } catch (Exception ex) {  

             ex.printStackTrace();  

         } */ 
	}
	/*
	 * @param filename the path to the new PDF document
	 * @throws DocumentException 
	 * @throws IOException
	 */
	@Test
	public void createPdf() throws IOException, DocumentException {
		// step 1
		Document document = new Document(new Rectangle(340, 340));
		// step 2
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("C:/Users/mennegi/barcodeFile.pdf"));
		// step 3
		document.open();
		// step 4
		// draw helper lines
		PdfContentByte cb = writer.getDirectContent();
		/*cb.setLineWidth(1f);
		cb.moveTo(0, 0);
		cb.lineTo(0, 340);
		cb.moveTo(0, 290);
		cb.lineTo(340, 290);
		cb.stroke();
		// draw text
		String text = "AWAY again ";
		BaseFont bf = BaseFont.createFont();
		cb.beginText();
		cb.setFontAndSize(bf, 8);
		cb.setTextMatrix(50, 340);*/
		//cb.showText(text);
		document.add(new Paragraph("2"));
		LineSeparator ls = new LineSeparator();
		document.add(new Chunk(ls));
		//cb.showTextAligned(PdfContentByte.ALIGN_CENTER, text + " Center", 50 , 280 , 0);
		document.add(new Paragraph("3"));
		//cb.endText();
		// step 5
		document.close();
	}

	/*
	 * @param filename the path to the new PDF document
	 * @throws DocumentException 
	 * @throws IOException
	 */
	@Test
	public void createPdf2() throws IOException, DocumentException {
		// step 1
		Document document = new Document();
		// step 2
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("C:/Users/mennegi/barcodeFile.pdf"));
		// step 3
		document.open();
		// step 4
		// draw helper lines
		PdfContentByte cb = writer.getDirectContent();
		cb.setLineWidth(0f);
		cb.moveTo(150, 600);
		cb.lineTo(150, 800);
		cb.moveTo(50, 760);
		cb.lineTo(250, 760);
		cb.moveTo(50, 700);
		cb.lineTo(250, 700);
		cb.moveTo(50, 640);
		cb.lineTo(250, 640);
		cb.stroke();
		// draw text
		String text = "AWAY again ";
		BaseFont bf = BaseFont.createFont();
		cb.beginText();
		cb.setFontAndSize(bf, 12);
		cb.setTextMatrix(50, 800);
		cb.showText(text);
		cb.showTextAligned(PdfContentByte.ALIGN_CENTER, text + " Center", 150, 760, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, text + " Right", 150, 700, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, text + " Left", 150, 640, 0);
		cb.showTextAlignedKerned(PdfContentByte.ALIGN_LEFT, text + " Left", 150, 628, 0);
		cb.setTextMatrix(0, 1, -1, 0, 300, 600);
		cb.showText("Position 300,600, rotated 90 degrees.");
		for (int i = 0; i < 360; i += 30) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT, text, 400, 700, i);
		}
		cb.endText();
		// step 5
		document.close();
	}

	@Test
	public void createAndPrintBarcodeModelloRPRateodiDoseFormatoA5Test() throws IOException, DocumentException {
		File tempPDF = File.createTempFile("pattern", ".pdf");
		tempPDF.deleteOnExit();
		Document document = new Document(new Rectangle(420,595)); // A5
		//Document document = new Document(); // A4
		// step 2
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(tempPDF));
		// step 3
		document.open();
		// step 4
		PdfContentByte cb = writer.getDirectContent();
		// INTER25
		document.add(new Paragraph("Barcode Interleaved 2 of 5"));
		BarcodeInter25 code25 = new BarcodeInter25();
		code25.setGenerateChecksum(true);
		code25.setCode("000 001 379");
		code25.setChecksumText(true);
		document.add(code25.createImageWithBarcode(cb, null, null));
		// step 5
		document.close();
		try {
			PrintPdf printPDFFile = new PrintPdf(new FileInputStream(tempPDF), "Test Print PDF");
			printPDFFile.print();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*PDFToImageConverter converter = new PDFToImageConverter();  

         try {  

             converter.convert(baos);  

         } catch (Exception ex) {  

             ex.printStackTrace();  

         } */ 
	}

	public void createPdf(File temppdf ) throws IOException, DocumentException {
		//String filename = "C:/Users/mennegi/barcodeFile.pdf";
		// step 1
		Document document = new Document(new Rectangle(340, 842));
		// step 2
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(temppdf));
		//ByteArrayOutputStream baos = new ByteArrayOutputStream();
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

	public void printJPEGBarcode(File temp) {
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.JPEG;
		PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, null);
		if (services.length > 0)
		{
			/*PrintService myService = null;
	        for(PrintService service : services) {
	            System.out.println(service.getName());
	            if(service.getName().contains("my printer")) {
	                myService = service;
	                break;
	            }
	        }*/
			String encoding = "UTF-8";

			MappedByteBuffer mbb = null;
			CharBuffer chrBuff = null;
			/*FileChannel fc = new FileInputStream(temp).getChannel();
			mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
			chrBuff = mbb.asCharBuffer();*/
			DocPrintJob printJob = services[2].createPrintJob();
			Doc doc;
			try {
				doc = new SimpleDoc( new FileInputStream(temp) , flavor , null);
				try {
					printJob.print(doc , new HashPrintRequestAttributeSet());
				} catch (PrintException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}



		}
		else
		{
			System.out.println("No PDF printer available.");
		}       

	}

}
