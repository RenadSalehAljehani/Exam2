package com.example.exam2.Controller;

import com.example.exam2.ApiResponse.ApiResponse;
import com.example.exam2.Model.Book;
import com.example.exam2.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // CRUD
    // Get
    @GetMapping("/getBooks")
    public ResponseEntity getBooks(){
        return  ResponseEntity.status(200).body(bookService.getBooks());
    }

    // Post
    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody @Valid Book book, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        bookService.addBook(book);
        return ResponseEntity.status(200).body(new ApiResponse("New Book Added."));
    }

    // Update
    @PutMapping("/updateBook/{ID}")
    public ResponseEntity updateBook(@PathVariable String ID, @RequestBody @Valid Book book, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if(bookService.updateBook(ID, book)){
            return ResponseEntity.status(200).body(new ApiResponse("Book Updated."));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID Not Found."));
    }

    // Delete
    @DeleteMapping("/deleteBook/{ID}")
    public ResponseEntity deleteBook(@PathVariable String ID){
        if (bookService.deleteBook(ID)){
            return ResponseEntity.status(200).body(new ApiResponse("Book Deleted."));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID Not Found."));
    }

    // getBookByName
    @GetMapping("/getBookByName/{name}")
    public ResponseEntity getBookByName(@PathVariable String name){
        if(bookService.getBookByName(name) != null){
            return ResponseEntity.status(200).body(bookService.getBookByName(name));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Book Not Found."));
    }

    // getBooksByCategory
    @GetMapping("/getBooksByCategory/{category}")
    public ResponseEntity getBooksByCategory(@PathVariable String category){
        if(bookService.getBooksByCategory(category) != null){
            return ResponseEntity.status(200).body(bookService.getBooksByCategory(category));
        }
        return ResponseEntity.status(400).body(new ApiResponse("There Are No Books In This Category."));
    }

    // getBooksByNumOfPages
    @GetMapping("/getBooksByNumOfPages/{numOfPages}")
    public ResponseEntity getBooksByNumOfPages(@PathVariable int numOfPages){
        if(bookService.getBooksByNumOfPages(numOfPages) != null){
            return ResponseEntity.status(200).body(bookService.getBooksByNumOfPages(numOfPages));
        }
        return ResponseEntity.status(400).body(new ApiResponse("There Are No Books With This Number Of Pages Or Above."));
    }

    // changeBookStatus
    @PutMapping("/changeBookStatus/{bookID}/{userID}")
    public ResponseEntity changeBookStatus(@PathVariable String bookID, @PathVariable String userID){
        if(bookService.changeBookStatus(bookID,userID) == 1){
            return ResponseEntity.status(200).body(new ApiResponse("Book Status Changed To Unavailable."));
        }if(bookService.changeBookStatus(bookID,userID) == 2){
            return ResponseEntity.status(400).body(new ApiResponse("Book ID (" + bookID + ") Not Found."));
        }
        if(bookService.changeBookStatus(bookID,userID) == 3){
            return ResponseEntity.status(400).body(new ApiResponse("User ID (" + userID + ") Not Found."));
        }
        if(bookService.changeBookStatus(bookID,userID) == 4){
            return ResponseEntity.status(400).body(new ApiResponse("User With ID (" + userID + ") Is Not A Libraian."));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Both Book and User ID Not Found."));
    }
}