import java.util.Formatter;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * This class contains instance variable and methods of a particular food item
 * Student Name: Chi Austin
 * Student Number:  041041623
 * Course: CST8130 - Data Structures
 * @author/Professor: James Mwangi PhD. 
 * 
 */
public class Fruit extends FoodItem{
	
	private String orchardName; //instance variable
	
	/**
	 * Default constructor
	 */
	public Fruit() {};
	
	/**
	 * Contains formatter object in order to write data to file
	 * @param writer formatter object used to write data members to a file
	 */
	@Override
	public void outputItem(Formatter writer) {
		writer.format("%s%n", "f");	//write heading 'f' for fruit items
		super.outputItem(writer);	//writes rest of data members (price, code etc...) to file
		writer.format("%s%n", orchardName);	//writes orchard name to file
	}
	
	/**
	 * Initializes instance variables of a particular food item
	 * @param scanner Accepts scanner object
	 * @param fromFile file to read from
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		
		super.addItem(scanner, fromFile);	//calls addItem to initialize data members (Inheritance)
		
		if(fromFile == true){
			
			orchardName = scanner.next() + " " + scanner.next();//reads orchard name
		}
		
		else {
		
		System.out.print("Enter the name of the orchard supplier: ");	//prompts user for name of orchard supplier
		orchardName = scanner.nextLine(); //reads the name of orchard
		}
		
		return true;	//returns true if process successful
		
	}
	
	/**
	 * return String representation of a food item
	 */
	@Override
	public String toString() {
		return String.format("%s orchard supplier: %s%n", super.toString(), orchardName);	//display inventory
	}


}