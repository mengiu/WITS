package org.services.layer.wits.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.services.layer.wits.services.parser.BarcodeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class BarcodeParserTest {
	@Autowired
	@Qualifier("barcodeParser")
	BarcodeParser barcodeParser;
	
	@Test
	public void BarcodeParserImplTest(){
		List<String> readLinee = new ArrayList<String>();
		readLinee.add("C000011");
		readLinee.add("X02");
		readLinee.add("Y01");
		readLinee.add("Z02");
		readLinee.add("000000243");
		readLinee.add("C000013");
		readLinee.add("X03");
		readLinee.add("Y02");
		readLinee.add("Z01");
		readLinee.add("000000245");
		int index = 1;
		for (String item : readLinee)
		{
			barcodeParser.readSession(item, index++);
		}
		barcodeParser.writeSessionInfoOnLogger();
	}
	@Test
	public void BarcodeParserImplTest2(){
		List<String> readLinee = new ArrayList<String>();
		readLinee.add("U00000123459");
		readLinee.add("X10");
		readLinee.add("Y05");
		readLinee.add("Z02");
		readLinee.add("W00000654329");
		readLinee.add("X03");
		readLinee.add("Y01");
		readLinee.add("Z04");
		readLinee.add("W00000020479");
		readLinee.add("C0");
		readLinee.add("U00000663459");
		readLinee.add("X09");
		readLinee.add("Y08");
		readLinee.add("Z07");
		readLinee.add("U00000667329");
		readLinee.add("X03");
		readLinee.add("Y04");
		readLinee.add("Z05");
		readLinee.add("W00000045479");
		readLinee.add("C2");
		int index = 1;
		for (String item : readLinee)
		{
			barcodeParser.readSession(item, index++);
		}
		barcodeParser.writeSessionInfoOnLogger();
	}
	@Test
	public void BarcodeParserImplTest3(){
		List<String> readLinee = new ArrayList<String>();
		readLinee.add("U00000123459");
		readLinee.add("X10");
		readLinee.add("Y05");
		readLinee.add("Z02");
		readLinee.add("W00000654329");
		readLinee.add("X03");
		readLinee.add("Y01");
		readLinee.add("Z04");
		readLinee.add("W00000020479");
		readLinee.add("W00000044479");
		readLinee.add("C2");
		int index = 1;
		for (String item : readLinee)
		{
			barcodeParser.readSession(item, index++);
		}
		barcodeParser.writeSessionInfoOnLogger();
	}
	@Test
	public void BarcodeParserImplTest4(){
		List<String> readLinee = new ArrayList<String>();
		readLinee.add("U00000123459");
		readLinee.add("W00000654329");
		readLinee.add("W00000020479");
		readLinee.add("W00000044479");
		readLinee.add("C2");
		int index = 1;
		for (String item : readLinee)
		{
			barcodeParser.readSession(item, index++);
		}
		barcodeParser.writeSessionInfoOnLogger();
	}
	@Test
	public void BarcodeParserImplTest5(){
		List<String> readLinee = new ArrayList<String>();
		readLinee.add("U00000123459");
		readLinee.add("W00000654329");
		readLinee.add("W00000020479");
		readLinee.add("X03");
		readLinee.add("Z04");
		readLinee.add("W00000044479");
		readLinee.add("C2");
		int index = 1;
		for (String item : readLinee)
		{
			barcodeParser.readSession(item, index++);
		}
		barcodeParser.writeSessionInfoOnLogger();
	}
	@Test
	public void BarcodeParserImplTest6(){
		List<String> readLinee = new ArrayList<String>();
		readLinee.add("U00000123459");
		readLinee.add("X10");
		readLinee.add("Y05");
		readLinee.add("Z02");
		readLinee.add("W00000654329");
		readLinee.add("X03");
		readLinee.add("Y01");
		readLinee.add("Z04");
		readLinee.add("W00000020479");
		readLinee.add("U00000055479");
		readLinee.add("X07");
		readLinee.add("Y05");
		readLinee.add("Z03");
		readLinee.add("W00000774329");
		readLinee.add("X01");
		readLinee.add("Y09");
		readLinee.add("Z06");
		readLinee.add("W00000033379");
		readLinee.add("C2");
		int index = 1;
		for (String item : readLinee)
		{
			barcodeParser.readSession(item, index++);
		}
		barcodeParser.writeSessionInfoOnLogger();
	}
	@Test
	public void BarcodeParserImplTest7(){
		List<String> readLinee = new ArrayList<String>();
		readLinee.add("U00000123459");
		readLinee.add("X10");
		readLinee.add("Y05");
		readLinee.add("Z02");
		readLinee.add("W00000654329");
		readLinee.add("X03");
		readLinee.add("Y01");
		readLinee.add("Z04");
		readLinee.add("W00000020479");
		readLinee.add("C1");
		readLinee.add("W00000044479");
		readLinee.add("C2");
		int index = 1;
		for (String item : readLinee)
		{
			barcodeParser.readSession(item, index++);
		}
		barcodeParser.writeSessionInfoOnLogger();
	}

}
