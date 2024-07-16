package SelfLearning.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SelfLearning.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public  LandingPage lp;
	public WebDriver initializeDriver() throws IOException
	{
		//properties class can read the global properties file
		
		Properties prop = new Properties();
		//FileInputStream fis = new FileInputStream("C:\\Users\\Pavithra.Mathivanan.ACS\\OneDrive - Innova Solutions\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\SelfLearning\\resources\\GlobalData.properties");
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+"//src//main//java//SelfLearning//resources//GlobalData.properties");
				prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("headless"))
				{
				options.addArguments("headless");
				}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));	//setting browser maximum mode in headless run

		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{	
			WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{	
			WebDriverManager.edgedriver().setup();
			 driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
			//read Json to string
		String jsonContent = FileUtils.readFileToString
		(new File(System.getProperty("User.dir")+"//src//test//java//SelfLearning//data//PurchaseOrder.json"),StandardCharsets.UTF_8);
//		
	//	String jsonContent = FileUtils.readFileToString
	//			(new File(filePath),StandardCharsets.UTF_8);
			
			// string in to hashmap Jackson databind
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap <String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
			return data;		
	}	
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		 driver = initializeDriver();
		 lp = new LandingPage(driver);
		 lp.goTo();
		 return lp;
	}
	
	public String getScreenshot(String testcasename, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"\\reports\\" + testcasename+".png");
		FileUtils.copyFile(source, target);
		return System.getProperty("user.dir")+ "\\reports\\" + "Screenshot.png";
	}
	
	//@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
}
