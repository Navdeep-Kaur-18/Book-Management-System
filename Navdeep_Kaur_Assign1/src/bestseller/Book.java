
package bestseller;

public class Book {
	// Attributes of the Book class
	  private int index;
	  private String title;
	  private String author;
	  private String originalLanguage;
	  private int firstPublished;
	  private float salesInMillions;
	  private String genre;
	  
	// Constructor
	  public Book (int index, String title , String author , String originalLanguage , int firstPublished, float salesInMillions, String genre)
	  {
		  this.index= index;
		  this.title = title;
		  this.author = author;
		  this.originalLanguage = originalLanguage;
		  this.firstPublished = firstPublished;
		  this.salesInMillions = salesInMillions;
		  this.genre = genre ;
		    
	  }
	  
	  // setters
	  public void setIndex (int index) {
		  this.index= index;
	  }
	  
	  public void setTitle (String title) {
		  this.title= title;
	  }
	  public void setAuthor (String author) {
		  this.author= author;
	  }
	  public void setOriginalLanguage (String originalLanguage) {
		  this.originalLanguage= originalLanguage;
	  }
	  public void setFirstPublished (int firstPublished) {
		  this.firstPublished= firstPublished;
	  }
	  public void setSalesInMillions (float salesInMillions) {
		  this.salesInMillions= salesInMillions;
	  }
	  public void setGenre (String genre) {
		  this.genre= genre;
	  }
	  
	  
	  //getters
	  public int getIndex(){
		  return this.index;
	  }
	  public String getTitle() {
		 return this.title;
	  }
	  public String getAuthor() {
		  return this.author;
	  }
	  public String getOriginalLanguage() {
		  return this.originalLanguage;
	  }
	  public int getFirstPublished() {
		  return this.firstPublished;
	  }
	  public float getSalesInMillions() {
		  return this.salesInMillions;
	  }
	  public String getGenre() {
		  return this.genre;
	  }
	  
	 
	//toString method to provide a string representation of the Book object
	  public String toString() {
		    return " Book " + "["+index +"]"+" { Book =' " + title + "'"+ " , Author(s)= " + author +
		           ", Original Language= " + originalLanguage + ", First Published=" + firstPublished + ", Approximate sales in milions=" + salesInMillions
	           + ", Genre="+genre +"}";
	}
}
	
