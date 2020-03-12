package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utils.Util;
import utils.WebEventListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
	
	public static WebDriver driver;
	public Properties prop;
	public WebDriverWait wait;
	public EventFiringWebDriver e_driver;
	public WebEventListener eventListener;
	
	public Base() {
		try {
			prop = new Properties();
					FileInputStream file = new FileInputStream(System.getProperty("user.dir")
					+ "/config.properties");
			prop.load(file);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeClass(alwaysRun = true)
	@Parameters({"environment","platform","browserName","url"})
	//@Parameters({"environment","platform","browserName","url"})
//	public void selectRunningSource(@Optional("local") String environment, @Optional("ANY") String platform, @Optional("chrome") String browserName, String url, ITestContext ctx) throws MalformedURLException {
		public void selectRunningSource(String environment, String platform, String browserName, String url, ITestContext ctx) throws MalformedURLException {
		BrowserFactory factory = new BrowserFactory(browserName);
		if(environment.equals("grid")) {
				driver = factory.parallelRun(environment,platform,browserName,url);
		} else {
				driver = factory.initialization(browserName);
		}
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		
		//driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Util.PAGE_LOAD_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Util.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(url);
		
		setCurrentThreadName();
		String testName = ctx.getCurrentXmlTest().getName();


	}
	
	private void setCurrentThreadName() {
		Thread thread = Thread.currentThread();
		String threadName = thread.getName();
		String threadId = String.valueOf(thread.getId());
		if (!threadName.contains(threadId)) {
			thread.setName(threadName + " " + threadId);
			System.out.println(threadName + " " + threadId);
		}
	}
	
	@AfterClass(alwaysRun = true)
	protected void tearDown() {
		try {
			System.out.println("[Closing session]");
			this.driver.quit();
		} catch(Exception e) {
			System.out.println("ERROR: " + e);
		}
	}
}
