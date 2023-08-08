package com.library.steps;

import com.library.pages.BasePage;
import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class US03_stepDef extends BasePage {

    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    List<String> actualBookList = new ArrayList<>();


    @Given("the {string} on the home page")
    public void the_on_the_home_page(String string) {
      loginPage.login(string);
    }
    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String string) {
    navigateModule(string);
        BrowserUtil.waitFor(2);
    }
    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
        Select select = new Select(bookPage.mainCategoryElement);
        List<WebElement> actualResult = select.getAllSelectedOptions();
        for (WebElement eachBook : actualResult) {
          actualBookList.add(eachBook.getText()) ;
           // System.out.println(eachBook.getText());
        }
        System.out.println(actualBookList);
       // bookPage.mainCategoryElement.click();

    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        DB_Util.runQuery("select name from book_categories");
        List<String> expectedCategoryList = DB_Util.getColumnDataAsList(1);
        System.out.println(expectedCategoryList);
        Assert.assertEquals(expectedCategoryList,actualBookList);
    }
}
