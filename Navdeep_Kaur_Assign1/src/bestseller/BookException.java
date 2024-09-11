/*
* Student Name: Navdeep Kaur
* Lab Professor: Howard
* Due Date: 9 June, 2024.
* Modified: 9 June, 2024.
* Description: This is a general program to read to data from a CSV file
*/
/* Description: Custom exception class for Book-related errors */

package bestseller;


public class BookException extends Exception {
    
	private static final long serialVersionUID = 1L;

	// Constructor that takes an error message as a parameter
    public BookException(String errorMessage) {
        // Print the error message to the standard error output
        System.err.println("BookException: " + errorMessage);
    }
}