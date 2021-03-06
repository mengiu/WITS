package org.front.end.wits;

/*import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverCommandProcessor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.vaadin.testbench.By;
import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.ScreenshotOnFailureRule;

public class FirstTestBenchWITS extends TestBenchTestCase {
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Rule
	public ScreenshotOnFailureRule screenshotOnFailureRule = new ScreenshotOnFailureRule(this, true);

	@Before
	public void setUp() throws Exception {
		setDriver(TestBench.createDriver(new FirefoxDriver()));
		baseUrl = "http://localhost:7001/";
	}

	@Test
	public void testFirstTestBenchWITS() throws Exception {
		try {
			driver.get(concatUrl(baseUrl, "/web-app-ispra-wits#tasks?processTypeselected=&stepSelected=&category=tasks"));
			testBenchElement(driver.findElement(By.vaadin("webappisprawits::/VVerticalLayout[0]/ChildComponentContainer[1]/VCssLayout[0]/VCssLayout$FlowPane[0]/VCustomComponent[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[2]/VScrollTable[0]/domChild[1]"))).click(268,53);
			driver.findElement(By.vaadin("webappisprawits::/VVerticalLayout[0]/ChildComponentContainer[0]/VCssLayout[0]/VCssLayout$FlowPane[0]/VHorizontalLayout[0]/ChildComponentContainer[2]/VButton[0]/domChild[0]/domChild[1]")).click();
			testBenchElement(driver.findElement(By.vaadin("webappisprawits::/VVerticalLayout[0]/ChildComponentContainer[1]/VCssLayout[0]/VCssLayout$FlowPane[0]/VCustomComponent[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[1]/VPanel[0]/VVerticalLayout[0]/ChildComponentContainer[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[7]/VPanel[0]/domChild[1]"))).scroll(118);
			Thread.sleep(500);
			testBenchElement(driver.findElement(By.vaadin("webappisprawits::/VVerticalLayout[0]/ChildComponentContainer[1]/VCssLayout[0]/VCssLayout$FlowPane[0]/VCustomComponent[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[1]/VPanel[0]/VVerticalLayout[0]/ChildComponentContainer[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[7]/VPanel[0]/domChild[1]"))).scroll(835);
			Thread.sleep(500);
			testBenchElement(driver.findElement(By.vaadin("webappisprawits::/VVerticalLayout[0]/ChildComponentContainer[1]/VCssLayout[0]/VCssLayout$FlowPane[0]/VCustomComponent[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[1]/VPanel[0]/VVerticalLayout[0]/ChildComponentContainer[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[7]/VPanel[0]/domChild[1]"))).scroll(1053);
			Thread.sleep(500);
			testBenchElement(driver.findElement(By.vaadin("webappisprawits::/VVerticalLayout[0]/ChildComponentContainer[1]/VCssLayout[0]/VCssLayout$FlowPane[0]/VCustomComponent[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[1]/VPanel[0]/VVerticalLayout[0]/ChildComponentContainer[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[7]/VPanel[0]/domChild[1]"))).scroll(1171);
			Thread.sleep(500);
			testBenchElement(driver.findElement(By.vaadin("webappisprawits::/VVerticalLayout[0]/ChildComponentContainer[1]/VCssLayout[0]/VCssLayout$FlowPane[0]/VCustomComponent[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[1]/VPanel[0]/VVerticalLayout[0]/ChildComponentContainer[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[7]/VPanel[0]/domChild[1]"))).scroll(1306);
			Thread.sleep(500);
			testBenchElement(driver.findElement(By.vaadin("webappisprawits::/VVerticalLayout[0]/ChildComponentContainer[1]/VCssLayout[0]/VCssLayout$FlowPane[0]/VCustomComponent[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[1]/VPanel[0]/VVerticalLayout[0]/ChildComponentContainer[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[7]/VPanel[0]/domChild[1]"))).scroll(599);
			Thread.sleep(500);
			testBenchElement(driver.findElement(By.vaadin("webappisprawits::/VVerticalLayout[0]/ChildComponentContainer[1]/VCssLayout[0]/VCssLayout$FlowPane[0]/VCustomComponent[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[1]/VPanel[0]/VVerticalLayout[0]/ChildComponentContainer[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[7]/VPanel[0]/domChild[1]"))).scroll(762);
			Thread.sleep(500);
			//testBenchElement(driver.findElement(By.vaadin("webappisprawits::/VVerticalLayout[0]/ChildComponentContainer[1]/VCssLayout[0]/VCssLayout$FlowPane[0]/VCustomComponent[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[1]/VPanel[0]/VVerticalLayout[0]/ChildComponentContainer[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[7]/VPanel[0]/VVerticalLayout[0]/ChildComponentContainer[0]/VVerticalLayout[0]/ChildComponentContainer[0]/VTree[0]#n[0]/n[8]/n[2]/n[0]"))).click(55,-753);
			driver.findElement(By.vaadin("webappisprawits::/VVerticalLayout[0]/ChildComponentContainer[1]/VCssLayout[0]/VCssLayout$FlowPane[0]/VCustomComponent[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[1]/VPanel[0]/VVerticalLayout[0]/ChildComponentContainer[1]/VHorizontalLayout[0]/ChildComponentContainer[0]/VGridLayout[0]/AbsolutePanel[0]/ChildComponentContainer[0]/VButton[0]/domChild[0]/domChild[0]")).click();
			driver.findElement(By.vaadin("webappisprawits::/VVerticalLayout[0]/ChildComponentContainer[0]/VCssLayout[0]/VCssLayout$FlowPane[0]/VHorizontalLayout[0]/ChildComponentContainer[1]/VButton[0]/domChild[0]/domChild[1]")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}*/
