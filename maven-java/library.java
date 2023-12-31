import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.Console;

class Book {
    private String title;
    private String author;
    private String isbn;
    private int year;

    public Book(String title, String author, String isbn, int year) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
    }

    // Getters and setters for book properties

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println("Назва: " + book.getTitle() +
                    ", Автор: " + book.getAuthor() +
                    ", ISBN: " + book.getIsbn() +
                    ", Рік: " + book.getYear());
        }
    }
    

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void removeBookByIsbn(String isbn) {
        Book bookToRemove = null;
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                bookToRemove = book;
                break;
            }
        }
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            System.out.println("Книга з таким ISBN " + isbn + " видалена.");
        } else {
            System.out.println("Книга з таким ISBN " + isbn + " не знайдена.");
        }
    }
    
    public boolean isLibraryEmpty(){
        return books.size() == 0;  
    } 
}

public class Main {
    
    public static void main(String[] args) {
        
        Library library = new Library();
        Console console = System.console();
        try (Scanner scanner = new Scanner(System.in)) {
            while(true){
                System.out.println("1-Показати всі книжки в бібліотеці\n2-Додати нову книгу\n3-Пошук книги\n4-Видалити книгу\n0-Вийти з програми");
                int value = scanner.nextInt();
                 
                switch(value)
                {
                    case 0:
                        System.out.println("Програма завершилась:");
                        return ;
                    case 1:
                        if(library.isLibraryEmpty()){
                            System.out.println("В бібліотеці пусто:");
                        }
                        else{
                            System.out.println("Всі книги в бібліотеці:");
                            library.displayBooks();
                        }
                        break;
                    case 2:     
                            scanner.nextLine();
                            System.out.println("Введіть назву книги:");
                            String title = scanner.nextLine();
                            
                            
                            
                            System.out.println("Введіть автора книги:");
                            String author = scanner.nextLine();
                            
                            
   
                            System.out.println("Введіть ISBN книги:");
                            String isbn = scanner.next();
                            
                            System.out.println("Введіть рік книги:");
                            int year = scanner.nextInt();
                            
                            Book newBook = new Book(title, author, isbn, year);
                            library.addBook(newBook);
                            System.out.println("Книга додана до бібліотеки.");
                            break;
                        case 3:
                            System.out.println("Введіть назву книги, яку хочете знайти:");
                            scanner.nextLine(); // Очищення буфера після попереднього введення
                            String searchTitle = scanner.nextLine();
                            Book foundBook = library.findBookByTitle(searchTitle);
                                if (foundBook != null) {
                                    System.out.println("Знайдена книга - Назва: " + foundBook.getTitle() +
                                    ", Автор: " + foundBook.getAuthor() +
                                    ", ISBN: " + foundBook.getIsbn() +
                                    ", Рік: " + foundBook.getYear());
                                }
                                else {
                                    System.out.println("Книгу з назвою '" + searchTitle + "' не знайдено.");
                                }
                                break;
                         case 4: 
                              scanner.nextLine(); 
                              System.out.println("Введіть ISBN книги, яку хочете видалити:");
                              String isbnToDelete = scanner.next();
                              library.removeBookByIsbn(isbnToDelete);
                              break;
                        
                }
                System.out.println("Для продовження натисніть ENTER...");
                console.readLine();
                System.out.print("\033[H\033[2J");  
                System.out.flush();
            }
        }

    }
}