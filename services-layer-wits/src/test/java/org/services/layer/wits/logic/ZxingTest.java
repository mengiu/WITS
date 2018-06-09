package org.services.layer.wits.logic;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;

import org.junit.Test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.CodaBarWriter;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.ITFWriter;

public class ZxingTest {
	 private static final int BLACK = 0xff000000;
	 private static final int WHITE = 0xFFFFFFFF;
    
	@Test
	public void BarcodeTypeStServiceTest(){
		int width = 300; 
	    int height = 48;
	     
	       
	    BitMatrix bitMatrix;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 try {
		 //bitMatrix = new ITFWriter().encode("41120007604100",BarcodeFormat.ITF,width,height,null);
		 bitMatrix = new MultiFormatWriter().encode("41120007604100",BarcodeFormat.ITF,0,0,null);
		 writeToFile(bitMatrix, "png", baos);
		 //MatrixToImageWriter.writeToStream(bitMatrix, "png", baos );
		 DocFlavor flavor = DocFlavor.BYTE_ARRAY.PNG;
		 PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, null);
		 if (services.length > 0)
		 {
			 DocPrintJob printJob = services[2].createPrintJob();
			 Doc doc = new SimpleDoc(baos.toByteArray(), flavor , null);
			 try {
				 printJob.print(doc , new HashPrintRequestAttributeSet());
			 } catch (PrintException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }
		 }
	     
	 } catch (WriterException e) {
	     // TODO Auto-generated catch block
	     e.printStackTrace();
	 } catch (Exception e) {
	     // TODO Auto-generated catch block
	     e.printStackTrace();
	 } 
		
	}
	
	  public static void writeToFile(BitMatrix matrix, String format, ByteArrayOutputStream baos) throws IOException {
		          BufferedImage image = toBufferedImage(matrix);
		          ImageIO.write(image, format, baos);
		       }
		   
		       /**
		        * 生成二維碼內容<br>
		        * 
		        * @param matrix
		        * @return
		        */
	  public static BufferedImage toBufferedImage(BitMatrix matrix) {
		           int width = matrix.getWidth();
		           int height = matrix.getHeight();
		           BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		           for (int x = 0; x < width; x++) {
		               for (int y = 0; y < height; y++) {
		                   image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);
		               }
		           }
		           return image;
		       }


}
