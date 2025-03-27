package AutomationFramework.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import automationframework.pageobjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	//public static WebDriver driver1;
	public LoginPage loginPage;

	@BeforeSuite
	public static ExtentReports configReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/index.html");
		reporter.config().setReportName("Selenium Web Automation");
		reporter.config().setDocumentTitle("Test Results");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Gk");
		return extent;
	}
	
	@BeforeTest
	public void initializeDriver() throws IOException {
		String filePath = System.getProperty("user.dir")+"/src/main/java/automationframework/resources/GlobalData.properties";
 		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);
		String getBrowserName = prop.getProperty("browser");
		

		if(getBrowserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup(); 
			//driver = new ChromeDriver();
			driver.set(new ChromeDriver());
		} else if(getBrowserName.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			//driver = new SafariDriver();
			driver.set(new SafariDriver());
		}
		
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		System.out.println("Driver initialized: " + driver.get());
	}
	
	
	public String getScreenshot(String testCasename) throws IOException {
        if (driver.get() == null) {
            throw new IllegalStateException("Driver is null! Make sure initializeDriver() is executed before calling getScreenshot.");
        }
        System.out.println("Driver for Screenshot: " + driver.get());
        
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        
		TakesScreenshot ts = (TakesScreenshot)driver.get();
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File((System.getProperty("user.dir")+"/reports/"+testCasename+"_"+timestamp+".png"));
		System.out.println(destination);
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir")+"/reports/"+testCasename+".png";
	}
	
	@BeforeMethod
	public LoginPage launchApplication() throws IOException {
		//driver = initializeDriver();
		loginPage = new LoginPage(driver.get());
		loginPage.goTo();
		return loginPage;
	}
	
	@AfterTest
	public void tearDown() {
		driver.get().close();
	}
	
//	@AfterTest
//	public void quitBrowser() {
//		driver.get().quit();
//	}

}
