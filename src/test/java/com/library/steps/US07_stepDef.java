package com.library.steps;

import com.library.pages.BasePage;
import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.pages.DashBoardPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class US07_stepDef extends BasePage {

    BookPage bookPage = new BookPage();
    BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
    String globalBookName;

    @Given("the user searches for {string} book")
    public void the_user_searches_for_book(String string) {
       bookPage.search.sendKeys(string);
       globalBookName = string;
    }
    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {
    bookPage.borrowBook(globalBookName).click();
        BrowserUtil.waitFor(3);
    }
    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String module) {
        navigateModule(module);
       Assert.assertTrue(BrowserUtil.getElementsText(borrowedBooksPage.allBorrowedBooksName).contains(globalBookName));

    }
    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {
String query = "select distinct full_name, b.name\n" +
        "from users u\n" +
        "         inner join book_borrow bb on u.id = bb.user_id\n" +
        "         inner join books b on bb.book_id = b.id\n" +
        "where full_name = 'Test Student 5'\n" +
        "  and name = 'Hanna book'";

        DB_Util.runQuery(query);
        String expectedResult = DB_Util.getCellValue(1, 2);
        Assert.assertEquals(globalBookName,expectedResult);

    }
}
