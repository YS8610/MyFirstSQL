package tfip.database.firstdatabase.repo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import tfip.database.firstdatabase.models.Book;
import static tfip.database.firstdatabase.repo.SQL.*;

@Repository
public class BookRepo {
    
    @Autowired
    private JdbcTemplate template;

    public List<Book> getAllBooks(){

        final List<Book> books = new LinkedList<>();
        final SqlRowSet rs=  template.queryForRowSet(SQL_GET_ALL_BOOKS);
        while (rs.next()){
            books.add( Book.populate(rs));
        }
        
        return books;
    }

    public List<Book> getAllBooks(int limit){
        final List<Book> books = new LinkedList<>();
        final SqlRowSet rs=  template.queryForRowSet(SQL_GET_ALL_BOOKS_BY_LIMIT,limit);
        while (rs.next()){
            books.add( Book.populate(rs));
        }
        
        return books;
    }

    public List<Book> getAllBooks(int limit,int offset){
        final List<Book> books = new LinkedList<>();
        final SqlRowSet rs=  template.queryForRowSet(SQL_GET_ALL_BOOKS_BY_LIMIT_BY_OFFSET,limit,offset);
        while (rs.next()){
            books.add( Book.populate(rs));
        }
        
        return books;
    }

    public List<String> getAllFormat(){
        final List<String> formats = new LinkedList<>();
        final SqlRowSet rs=  template.queryForRowSet("select distinct format from book2018;");
        while(rs.next()){
            formats.add( rs.getString("format") );
        }

        return formats;
    } 

    public int getAllFormat(String format){
        int count =0;
        final SqlRowSet rs=  template.queryForRowSet("select count(format) as count from book2018 where format like ?", format);
        while(rs.next()){
            count = rs.getInt("count");
        }


        return count;
    } 

    public boolean setBook(String bookid, String title, String pages, String author){
        int add = template.update(SQL_CREATE_BOOK_RECORD,bookid,title,pages,author );
        return add>0; 
    }

}
