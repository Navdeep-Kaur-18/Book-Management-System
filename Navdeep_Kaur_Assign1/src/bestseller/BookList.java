/*
* Student Name: Navdeep Kaur
* Lab Professor: Howard
* Due Date: 9 June, 2024.
* Modified: 9 June, 2024.
* Description: This is a general program to read to data from a CSV file
* 
*/
// Descrption : This Class is responsible for managing the list of books

package bestseller;
import java.io.*;
import java.util.ArrayList;


public class BookList {
	// Define the default titles
	private static final String[] DEFAULT_TITLE = {"Book", "Author(s)", "Original Language", "First published","Approximate sales in millions","Genre"};
	// Method to read a line and split it into an array of strings, considering commas and quotes
	public String[] lineReader(String line) {
		final int NUMCOLS=6;
		String[] str = new String[NUMCOLS];
		int index = 0;
		final char chComma = ',';
		final char chQuotes = '"';
		int start = 0;
		int end = line.indexOf(chComma);
		String value;
		while (start < end) {
			if (line.charAt(start) == chQuotes) {
				start++;
				end = line.indexOf(chQuotes, start + 1);
			}
			value = line.substring(start, end);
			value = value.trim();
			str[index++] = value;
			if (line.charAt(end) == chQuotes)
				start = end + 2;
			else
				start = end + 1;
			end = line.indexOf(chComma, start + 1);
		}
		if (start < line.length()) {
			value = line.substring(start);
			str[index++] = value;
		}
		return str;
	}
	
	// List to store the books
    private ArrayList<Book> bestsellers = new ArrayList<>();
 // Method to create a list of books from a CSV file
    public void createList(String csvFile) throws BookException {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            
            String line = br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] stringArray = lineReader(line);
                
                    	int index=bestsellers.size();
                    	String name = stringArray.length > 0 ? stringArray[0] : "Null";
                    	String author = stringArray.length > 1 ? stringArray[1] : "Null";
                    	String originalLanguage = stringArray.length > 2 ? stringArray[2] : "Null";
                    	int firstPublished = stringArray.length > 3 && !stringArray[3].isEmpty() ? Integer.parseInt(clean(stringArray[3])) : 0;
                    	float millionSales = stringArray.length > 4 && !stringArray[4].isEmpty() ? Float.parseFloat(clean(stringArray[4])) : 0.0F;
                    	String genre = stringArray.length > 5 ? stringArray[5] : "Null ";
                    	Book book = new Book(index, name, author, originalLanguage, firstPublished, millionSales, genre);
                    	bestsellers.add(book);
            }
                    

            } catch (IOException e) {
            throw new BookException("Error reading the CSV file ");
            }
        }

 // Helper method to clean and trim a string
	private String clean(String value) {
		return (value==null)? "":value.trim();
	}
	// Method to print the list of books
    public void printList() {
        for (Book book : bestsellers) {
            System.out.println(book);
        }
    }
 // Method to add a book to the list
    public void add(Book b) {
        b.setIndex(bestsellers.size());
        bestsellers.add(b);
    }
    // Method to edit an existing book in the list
    public void edit(int pos, Book b) throws BookException {
        Book existingBook = findBookByIndex(pos);
        if (existingBook != null) {
            existingBook.setTitle(b.getTitle());
            existingBook.setAuthor(b.getAuthor());
            existingBook.setOriginalLanguage(b.getOriginalLanguage());
            existingBook.setFirstPublished(b.getFirstPublished());
            existingBook.setSalesInMillions(b.getSalesInMillions());
            existingBook.setGenre(b.getGenre());
            System.out.println("Book edited successfully. ");
        } else {
            throw new BookException("Book not found.");
        }
    }
 // Method to delete a book from the list
    public void delete(int pos) throws BookException {
        Book book = findBookByIndex(pos);
        if (book != null) {
            bestsellers.remove(book);
            // Update indices of remaining books
            for (int i = pos; i < bestsellers.size(); i++) {
                bestsellers.get(i).setIndex(i);
            }
        } else {
            throw new BookException("Book not found.");
        }
    }

 // Method to save the list of books to a CSV file
    public void saveList(String csvFile) throws BookException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
        	bw.write(String.join(",", DEFAULT_TITLE));
        	bw.newLine();
            for (Book book : bestsellers) {
            	// Handle quotes and commas in the book fields
            	String title = "\"" + book.getTitle().replace("\"", "\"\"") + "\"";
                String author = "\"" + book.getAuthor().replace("\"", "\"\"") + "\"";
                String originalLanguage = "\"" + book.getOriginalLanguage().replace("\"", "\"\"") + "\"";
                String genre = book.getGenre() != null ? "\"" + book.getGenre().replace("\"", "\"\"") + "\"" : "\"\"";
                
                bw.write(title + "," + author + "," + originalLanguage + "," +
                        book.getFirstPublished() + "," + book.getSalesInMillions() + "," + genre);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            throw new BookException("Error saving the book list: " + e.getMessage());
        }
    }
    
 // Method to find a book by its index
    public Book findBookByIndex(int index) {
        for (Book book : bestsellers) {
            if (book.getIndex() == index) {
                return book;
            }
            
            
        }
        return null;
    }
 // Method to search for books by a search term
    public void search(String searchTerm) {
	for (Book book : bestsellers) {
		
		if (book.getTitle() != null && book.getTitle().toLowerCase().contains(searchTerm))
            System.out.println(book);
        
        if (book.getAuthor() != null && book.getAuthor().toLowerCase().contains(searchTerm))
            System.out.println(book);
        
        if (book.getOriginalLanguage() != null && book.getOriginalLanguage().toLowerCase().contains(searchTerm))
            System.out.println(book);
        
        if(String.valueOf(book.getFirstPublished()).toLowerCase().contains(searchTerm))
			System.out.println(book);
		if(String.valueOf(book.getSalesInMillions()).toLowerCase().contains(searchTerm))
			System.out.println(book);
		
		if (book.getGenre() != null && book.getGenre().toLowerCase().contains(searchTerm))
            System.out.println(book);
		
		
	}
    }

    
 // Getter for the bestsellers list
	 public ArrayList<Book> getBestsellers() {
	        return bestsellers;
	    }
}
	
