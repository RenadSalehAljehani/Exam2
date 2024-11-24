package com.example.exam2.Service;

import com.example.exam2.Model.Book;
import com.example.exam2.Model.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import static com.example.exam2.Service.UserService.users;

@Service
public class BookService {

    ArrayList<Book> books = new ArrayList<>();

    //CRUD
    // Get all books
    public ArrayList<Book> getBooks(){
        return books;
    }

    // Add new book
    public void addBook(Book book){
        books.add(book);
    }

    // Update a book
    public boolean updateBook(String ID, Book book){
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getID().equalsIgnoreCase(ID)){
                books.set(i,book);
                return true;
            }
        }
        return false;
    }

    // Delete a book
    public boolean deleteBook(String ID){
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getID().equalsIgnoreCase(ID)){
                books.remove(i);
                return true;
            }
        }
        return false;
    }

    // Create an endpoint that takes a Book name then returns one Book .
    public Book getBookByName(String name){
        for (Book book: books){
            if(book.getName().equalsIgnoreCase(name)){
                return book;
            }
        }
        return null;
    }

    // Create an endpoint that takes a category then return all books have this category.
    public ArrayList<Book> getBooksByCategory(String category){
        ArrayList<Book> booksByCategoryList = new ArrayList<>();
        for (Book book: books){
            if (book.getCategory().equalsIgnoreCase(category)){
                booksByCategoryList.add(book);
            }
        }
        if (booksByCategoryList.isEmpty()){
            return null;
        }
        return booksByCategoryList;
    }

    /* Create an endpoint that takes a number of pages and returns
    all Books who have this number of pages or above . */
    public ArrayList<Book> getBooksByNumOfPages(int numOfPages){
        ArrayList<Book> booksByNumOfPagesList = new ArrayList<>();
        for(Book book: books){
            if(book.getNumber_of_pages() >= numOfPages){
                booksByNumOfPagesList.add(book);
            }
        }
        if(booksByNumOfPagesList.isEmpty()){
            return null;
        }
        return booksByNumOfPagesList;
    }

    /* Create an endpoint that change a book status to unavailable
    (Only the librarian can change the status of the book)*/
    public int changeBookStatus(String bookID , String userID){
        for(User user: users) {
            if (user.getID().equalsIgnoreCase(userID) && user.getRole().equalsIgnoreCase("libraian")) {
                for (Book book: books) {
                    if (book.getID().equalsIgnoreCase(bookID)) {
                        book.setAvailable(false);
                        return 1;
                    }
                    else {
                        return 2;
                    }
                }
            } else if (!user.getID().equalsIgnoreCase(userID)) {
                return 3;
            } else if (!user.getRole().equalsIgnoreCase("libraian")){
                return 4;
            }
        }
        return 5;
    }
}