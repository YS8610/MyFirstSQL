package tfip.database.firstdatabase.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tfip.database.firstdatabase.models.Book;
import tfip.database.firstdatabase.repo.BookRepo;

@RestController
@RequestMapping(path="/books",produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    @Autowired
    BookRepo bookRepo;

    Gson gson = new Gson();

    @GetMapping
    public ResponseEntity<String> getAllBooks(
        @RequestParam(defaultValue = "10") Integer limit,
        @RequestParam(defaultValue = "10") Integer offset
    ){
        List<Book> books = bookRepo.getAllBooks(limit,offset);

        String booksJsonString = gson.toJson(books).toString();
        return new ResponseEntity<String>(booksJsonString, HttpStatus.OK);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setBook(@RequestBody String jsonString){
        JsonObject jsonObject = (JsonObject)JsonParser.parseString(jsonString);
        String bookid = jsonObject.get("bookid").getAsString();
        String title = jsonObject.get("title").getAsString();
        String pages = jsonObject.get("pages").getAsString();
        String author = jsonObject.get("author").getAsString();
        
        boolean added =  bookRepo.setBook(bookid, title, pages, author);
        if (added){
            JsonObject msg = new JsonObject();
            msg.addProperty("msg", "successful");
            return new ResponseEntity<String>(msg.toString(), HttpStatus.OK);
        }
        else{
            JsonObject msg = new JsonObject();
            msg.addProperty("msg", "failed");
            return new ResponseEntity<String>(msg.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
