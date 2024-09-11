/*
* Student Name: Navdeep Kaur
* Lab Professor: Howard
* Due Date: 9 June, 2024.
* Modified: 9 June, 2024.
* Description: This is a general program to read to data from a CSV file
*/
/* Description: this class is responsible for managing the book list and user interactions*/

package bestseller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookManager {
	// Define constants for menu options
	private final int OPTION1_CRT=1;
	  private final int OPTION2_LST=2;
	  private final int OPTION3_ADD=3;
	  private final int OPTION4_EDT=4;
	  private final int OPTION5_DEL=5;
	  private final int OPTION6_SAV=6;
	  private final int OPTION7_SRC=7;
	  private final int OPTION8_EXT=8;
	  private final int FIRST_OPTION=OPTION1_CRT;
	 private final int LAST_OPTION=OPTION8_EXT;
	// Instance variables for book list and scanner
    private BookList booklist = new BookList();
    private Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        BookManager manager = new BookManager();
        manager.manageBookList();
    }
   // Main method to manage the book list
    public void manageBookList() {

        int option = 0;
        do {
            showMenu();
            try {
            	// Read user input for menu option
                option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case OPTION1_CRT:
                        createBookList();
                        break;
                    case OPTION2_LST:
                        displayBookList();
                        break;
                    case OPTION3_ADD:
                        addBook();
                        break;
                    case OPTION4_EDT:
                        editBook();
                        break;
                    case OPTION5_DEL :
                        deleteBook();
                        break;
                    case OPTION6_SAV:
                        saveBookList();
                        break;
                    case OPTION7_SRC:
                        search();
                        break;
                    case OPTION8_EXT:
                        exit();
                        break;
                    default:
                        System.out.println("Invalid option. Please choose again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("BookException: Invalid entry: enter an integer between "+FIRST_OPTION+ "and " + LAST_OPTION);
                scanner.nextLine(); // Consume invalid input
            } catch (BookException e) {
                System.out.println(e.getMessage());
            }
        } while (option != 8);
        scanner.close();
    }
 // Method to display the menu
    private void showMenu() {
        System.out.println("================================");
        System.out.println("|| Menu – Best Sellers OOP/A1 ||");
        System.out.println("================================");
        System.out.println("1. Create Booklist");
        System.out.println("2. Show Booklist");
        System.out.println("3. Add book");
        System.out.println("4. Edit a book");
        System.out.println("5. Delete a book");
        System.out.println("6. Save booklist");
        System.out.println("7. Search in the list");
        System.out.println("8. Exit");
        System.out.print("Choose an option: ");
    }

 // Method to create a book list
    private void createBookList() throws BookException {
        System.out.print("Name of the file to create booklist: ");
        String csvFile = scanner.next();
        scanner.nextLine();
        booklist.createList(csvFile);
        System.out.println("Book list created successfully! ");
    }
    

 // Method to display the book list
    private void displayBookList() {
    	System.out.println(" BOOKLIST ....................");
        booklist.printList();
    }
 // Method to add a book to the list
    private void addBook() throws BookException {
    	System.out.println("Enter the Book data:");
        Book b = createBook(booklist.getBestsellers().size());
        booklist.add(b);
        System.out.println("Book added successfully. ");
    }
 // Method to edit an existing book in the list
    private void editBook() throws BookException {
        System.out.print("Enter the index of the book to edit: ");
        int pos = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the Book data:");
        Book b = createBook(pos);
        booklist.edit(pos, b);
      
    }
 // Method to delete a book from the list
    private void deleteBook() throws BookException {
        System.out.print("Enter the index of the book to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine(); 
        booklist.delete(index);
        System.out.println("Book ["+index+ "] deleted succesfully.");
       
    }
 // Method to save the book list to a file
    private void saveBookList() throws BookException {
        System.out.print("Enter the name of the CSV file to save: ");
        
        String csvFile = scanner.nextLine();
        booklist.saveList(csvFile);
        System.out.println("File "+ csvFile +" created.");
    }
 // Method to search for a book in the list
    private void search() throws BookException {
        System.out.println("Enter one string for search in the list.  ");
        String data = scanner.nextLine();
        System.out.println(" Results from search... ");
       
        if (data.isEmpty() || data == null) throw new BookException("Search data cannot be empty.");
        booklist.search(data);
         
    }
    
 // Method to exit the application
    private void exit() {
    	
    	System.out.println("================================");
        System.out.println("||    [Application ended ]  ||  ");
        System.out.println("================================");
        
    }
    
 // Method to create a book with user input
    public Book createBook(int index) throws BookException {
      
         
         System.out.print("-Name: ");
         String title = scanner.nextLine();
         if (title.isEmpty()) throw new BookException("Title cannot be empty ");
         
         System.out.print("-Author: ");
         String author = scanner.nextLine();
         if (author.isEmpty()) throw new BookException("Author cannot be empty ");

         System.out.print("-Original language:  ");
         String originalLanguage = scanner.nextLine();
         if (originalLanguage.isEmpty()) throw new BookException("Original language cannot be empty ");

        
         int firstPublished;
         while (true) {
             System.out.print("-First Published: ");
             try {
                 firstPublished = Integer.parseInt(scanner.nextLine());
                 if (firstPublished < 0 || firstPublished > 2024) {
                     throw new NumberFormatException();
                 }
                 break; 
             } catch (NumberFormatException e) {
                 System.out.println("BookException: The year must be positive between 0 and 2024");
             }
         }
         float salesInMillions;
         while (true) {
             System.out.print("-Millions of Sales: ");
             try {
                 salesInMillions = Float.parseFloat(scanner.nextLine());
                 if (salesInMillions <= 0) {
                     throw new NumberFormatException();
                 }
                 break; 
             } catch (NumberFormatException e) {
                 System.out.println("Invalid input! Please enter a positive number.");
             }
         }

         System.out.print("-Genre: ");
         String genre = scanner.nextLine();
         
         if (genre.isEmpty()) throw new BookException("Genre cannot be empty.");

        
         return new Book(index, title, author, originalLanguage, firstPublished, salesInMillions, genre);
         
     }
   
    }


    
