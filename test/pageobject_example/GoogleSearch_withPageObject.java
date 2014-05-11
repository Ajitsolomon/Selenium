package test.pageobject_example;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import test.pageobject_example.pages.GoogleSearchPage;
import test.pageobject_example.pages.GoogleSearchResultPage;
import test.pageobject_example.pages.GoogleSearchSuggestPage;

/**
 * Google search demo class.  This class shows how to use page objects.  
 * Compare with "GoogleSearch.java" class, and see how to use page objects. 
 * 
 *
 */
public class GoogleSearch_withPageObject {

    public static void main(String[] args) {
    	GoogleSearch.testGoogleSearch(); 
    	try{
    		GoogleSearch.testGoogleSuggest(); 
    	}catch(Exception e){
    		System.out.println("Unable to perform testGoogleSuggest() - " + 
    							e.getMessage());
    	}
    }
    
    public static void testGoogleSearch(){
    	//Create a new instance of GoogleSearch page
    	GoogleSearchPage googleSearchPage = new GoogleSearchPage(new HtmlUnitDriver()); 

        // go to Google ("http://www.google.com")
    	googleSearchPage.open(); 

        // Enter something to search for
    	googleSearchPage.enterSearchForm("Cheese!"); 

        // Now submit the form, and get the next page object
    	GoogleSearchResultPage googleSearchResultPage = googleSearchPage.submitForm(); 
               

        // Verify: Check the title of the page    	
        String pageTitle = googleSearchResultPage.getTitle();  
        System.out.println("Page title is: " + pageTitle);
        assertTrue("Got title: " + pageTitle, pageTitle.contains("Cheese!")); 
    }
    
    public static void testGoogleSuggest() throws Exception {
		
	GoogleSearchSuggestPage googleSuggestPage = new GoogleSearchSuggestPage(new FirefoxDriver());
        
        // Go to the Google Suggest home page: "http://www.google.com/webhp?complete=1&hl=en"
	googleSuggestPage.open();  
        
        // Enter the query string "Cheese", and get the list the suggestions
        List<WebElement> allSuggestions = googleSuggestPage.getSearchSuggestions("Cheese");
        System.out.println("PO::allSuggestions.size() = " + allSuggestions.size() );
        for (WebElement suggestion : allSuggestions) {
            System.out.println(suggestion.getText());
        }
     }
}
