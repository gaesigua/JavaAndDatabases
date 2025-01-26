package steps;

import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DatabasePage;
import pages.LoginPage;
import pages.TestBase;

public class LoginStepsDefinition extends TestBase{
	
	LoginPage loginPage;
	
	//Let's initiate the DatabasePage object, to be able to use when we want to retrieve username and password data down the line
	
	DatabasePage databasePage;
	
	
	//Now, How can we make sure that if let's say we are entering the data starting from password instead of username it will still work (we will switch up the steps in the feature file)
	//Let's initialize the driver, and the pageFactory
	
	@Before
	public void initDriverandPageFactory() {
		
		initDriver();
		loginPage = PageFactory.initElements(driver, LoginPage.class); //here we are initializing the pageFactory (POM), that calls the driver, and initialize the LoginPage class
		databasePage = new DatabasePage();
		
	}

	
	@Given ("^User is on the OrangeHRM login page$")
	public void user_is_on_the_OrangeHRM_login_page (){
// 1. In order to run this method, we need to call the initDriver method here. Remember that it is a static method, and we want to call it here (non-static method).
//	         which means, calling the method would generally work because the method is static (TestBase.initDriver()) but doing it that way would mean, we would always call that method everywhere we want to use it; so, instead we will extend the whole class
			
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}
	
	@When ("User enters {string} from database")   // we can use \"([^\"]*)\" instead of {String}
	public void user_enters_from_database(String userdata) {
		
//Here since we are using only one method for two steps (entering username and entering password), we will use the if---else statement, or the switch statement to enter the data;
//So let's first use the if----else statement
		
                                   //		if(userdata.equalsIgnoreCase("username")) {
                                   //			
                                   //			loginPage.enterUserName("Get username from DB");
                                   //			
                                   //		}else if(userdata.equalsIgnoreCase("password")) {
                                   //			
                                   //			loginPage.enterPassWord("Get password from DB");
                                   //			
                                   //		}else {
                                   //			System.out.println("Unable to retrieve data:' " + userdata + "' from DB");
                                   //		}
		
                                  //Now let's use a switch case statement
		
		switch (userdata) {
		case "username":
			
			loginPage.enterUserName(databasePage.getDataFromDB("username")); //Here we are retrieving the username from DB
			System.out.println("Username from DB is: " + databasePage.getDataFromDB("username"));
			break;
			
		case "password": 
			loginPage.enterPassWord(databasePage.getDataFromDB("password")); //Here we are retrieving the password from DB
			System.out.println("Password from DB is: " + databasePage.getDataFromDB("password"));
			break;
		
		default:
			System.out.println("Unable to retrieve data:'" + userdata + "' from DB");
		}
		
	}
	
	@And("User clicks on the submit button")
	public void user_clicks_on_the_submit_button() {
		
		loginPage.clickSignInButton();
	    
	}
	
	@Then("User should land on the Dashboard page")
	public void user_should_land_on_the_dashboard_page() {
		
		Assert.assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", loginPage.getPageUrl()); //Remember here not insert the Assert.assertEquals from "junit.framework.Assert" because it is deprecated; instead we use the "org.junit.Assert"
	    takeScreenshot(driver);
	}
		
	//Now let's close the browser
	@After
	public void tearDown() {
		driver.close();
		driver.quit();
		
		}

}
