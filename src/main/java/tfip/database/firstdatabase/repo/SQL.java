package tfip.database.firstdatabase.repo;

public class SQL {
    
    public final static String SQL_GET_ALL_BOOKS = "select * from book2018";
    public final static String SQL_GET_ALL_BOOKS_BY_LIMIT = "select * from book2018 limit ?";
    public final static String SQL_GET_ALL_BOOKS_BY_LIMIT_BY_OFFSET = "select * from book2018 limit ? offset ?";
    public final static String SQL_CREATE_BOOK_RECORD = "insert into book2018(book_id,title,pages,authors) values(?,?,?,?)";
}
