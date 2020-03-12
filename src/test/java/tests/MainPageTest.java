package tests;

import base.Base;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.MainPage;
import utils.TestAllureListener;
import utils.Util;

import java.nio.file.Path;
import java.nio.file.Paths;


@Listeners({TestAllureListener.class})
public class MainPageTest extends Base{
	
	MainPage mainPage;
	String sheetName = "search";

	@Test(priority = 1, enabled = true, groups = {"mainPageSmoke"}, description = "Verifying main page menu")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test case description : verifying menu on the Main Page")
	@Story("Story name: To check main page main menu functionality on the top of main page")
	public void mainPageMenuTestSmoke() {
		System.out.println("-- MainPage menu smoke test starts --");
		mainPage = new MainPage();
		mainPage.mainMenuSmoke();
	}
	
	@Test(priority = 2, enabled = true, groups = {"mainPageSmoke"}, description = "Verifying main page search")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test case description: verifying search visibility on the Main Page")
	@Story("Story name: To check search module visibility of the main page")
	public void mainPageStaysSearchTestSmoke() {
		System.out.println("-- MainPage search module smoke test starts --");
		mainPage = new MainPage();
		mainPage.searchSmoke();
	}
	
	@Test(priority = 3, enabled = true, groups = {"mainPageFunc"})
	@Severity(SeverityLevel.NORMAL)
	@Description("Test case description: verifying search functionality on the Main Page")
	@Story("Story name: To check search module functionality of the main page")
	public void mainPageMenuTestFunctional() {
		System.out.println("-- MainPage functionsl test starts --");
		mainPage = new MainPage();
		mainPage.mainMenuFunctional();
	}
	
	@Test(priority = 4, enabled = true, groups = {"mainPageRegression"}, dataProvider = "getTestData")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test case description: verifying search function on the Main Page")
	@Story("Story name: To check search functionality of the main page")
	public void mainPageSearchModuleTestRegression(String shortname, String destination, String checkin, String checkout) {
		System.out.println("-- MainPage regression test starts --");
		mainPage = new MainPage();
		mainPage.mainPageSearchModulRegression(shortname, destination, checkin, checkout);
	}

	@Test(priority = 5, enabled = false, groups = {"mainPageFunc"})
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test case description: upload file on Main Page")
	@Story("Story name: To validate file uploading on the Main page")
	public void uploadTest() throws InterruptedException {
		mainPage = new MainPage();
		mainPage.uploadFile("data.xlsx");
		Assert.assertEquals("sample.xlsx", mainPage.getUploadedFileName());
	}

    @Test(priority = 6, enabled = false, groups = {"mainPageFunc"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test case description: download file from Main Page")
    @Story("Story name: To validate file downloading from the Main page")
	public void downloadTest() throws InterruptedException {
	    mainPage.downloadFile();
        Path downloadsPath = Paths.get("/mnt/volume/output/downloads/testfile.txt");

//        Awaitility.await()
//                .atMost(1, TimeUnit.MINUTES)
//                .until(() -> {
//                   return downloadsPath.toFile().exists();
//                });
        Assert.assertTrue(downloadsPath.toFile().exists());
    }


	/**
	 * Data provider
	 * @return
	 */
	@DataProvider
	public Object [][] getTestData() {
		Object data [][] = Util.getTestData(sheetName);
		return data;
	}
}