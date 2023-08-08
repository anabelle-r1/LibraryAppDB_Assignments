select count(id) from users; -- actual

select count(distinct id) from users; -- expected

select distinct b.name as "Book Name",isbn as ISBN,year as "Year",
                author as "Author",bc.name as "Book Category"
from books b
        join book_categories bc on b.book_category_id = bc.id
where b.name = 'Hanna book';;

