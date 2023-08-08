package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class US05_StepDef {
    String query;
    //String expected result;
    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
        query = "select bc.name, count (*)\n" +
                "from books\n" +
                "      join book_categories be on be.id = books.book_category_id\n"
                +
                "join book_borrow bb on books.id = bb.book_id\n" +
                "group by name\n" +
                "order by count(*) desc";
        DB_Util.runQuery(query);
        List<String> mostPopularBooks = DB_Util.getColumnDataAsList(1);
        System.out.println(mostPopularBooks);
    }
    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String string) {

        DB_Util.runQuery(query);
    }
}
