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
public class SeaFood extends FoodItem{

	private String seafoodSupplier;	//instance variable
	
	/**
	 * Default constructor
	 */
	public SeaFood() {};
	
	/**
	 * Contains formatter object in order to write data to file
	 * @param writer formatter object used to write data members to a file
	 */
	@Override
	public void outputItem(Formatter writer) {
		writer.format("%s%n", "s");	//writes heading 's' to file for sea food items
		super.outputItem(writer);	//writes other data members to file using inheritance
		writer.format("%s%n", seafoodSupplier);	//writes sea food supplier to file
	}
	
	/**
	 * Initializes the instance variables of a particular food item
	 * @param scanner Accepts scanner object
	 * @param fromFile File to read from
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		
		super.addItem(scanner, fromFile); //(inheritance)
		if(fromFile == true) {
		
		seafoodSupplier = scanner.nextLine();	//read name of sea food supplier from file
		}
		
		else{
		
		System.out.print("Enter the supplier of the seafood: ");	//prompts user for sea food supplier
		seafoodSupplier = scanner.nextLine();  //reads the sea food supplier
		} 
		
		return true; // return true if process successful
		
	}
	
	/**
	 * Displays a String representation of a particular food item
	 */
	@Override
	public String toString() {
		return String.format("%s supplier: %s%n", super.toString(), seafoodSupplier);	//display results
	}


}