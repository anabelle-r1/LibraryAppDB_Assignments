package com.library.steps;

import com.library.pages.BasePage;
import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US06_StepDef extends BasePage{

    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        bookPage.addBook.click();

    }
    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String string) {
    bookPage.bookName.sendKeys(string);
    }
    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String string) {
    bookPage.isbn.sendKeys(string);
    BrowserUtil.waitFor(3);
    }
    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String string) {
        bookPage.year.sendKeys(string);
        BrowserUtil.waitFor(2);
    }
    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String string) {
        bookPage.author.sendKeys(string);
        BrowserUtil.waitFor(2);
    }
    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String string) {
        bookPage.categoryDropdown.sendKeys(string);
        BrowserUtil.waitFor(2);
    }
    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        bookPage.saveChanges.click();
        BrowserUtil.waitFor(3);
    }
    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String expectedResult) {
        boolean actualResult = bookPage.toastMessage.isDisplayed();
        Assert.assertTrue(expectedResult,actualResult);
    }
    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String actualResult) {
        String query = "select distinct b.name as \"Book Name\", isbn as ISBN, year as \"Year\",\n" +
                "                author as \"Author\",bc.name as \"Book Category\"\n" +
                "from books b\n" +
                "         join book_categories bc on b.book_category_id = bc.id\n" +
                "where b.name = 'Hanna book'";
        DB_Util.runQuery(query);
        String expectedResult = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedResult,actualResult);


    }
}
