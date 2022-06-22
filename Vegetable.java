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
public class Vegetable extends FoodItem{
	
	private String farmName;	//instance variable
	
	/**
	 * Default constructor
	 */
	public Vegetable() {};
	
	/**
	 * Contains formatter object in order to write data to file
	 * @param writer formatter object used to write data members to a file
	 */
	@Override
	public void outputItem(Formatter writer) {
		writer.format("%s%n", "v"); //writes heading 'v' for vegetable items to file
		super.outputItem(writer);	//(Inheritance) invokes outputItem from super class to read other data members
		writer.format("%s%n", farmName);	//writes farm name to file
	}
	
	/**
	 * Initializes the instance variables of a food item
	 * @param scanner Accepts scanner object
	 * @param fromFile File to read from
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		
		super.addItem(scanner, fromFile); //inheritance
		
		if(fromFile == true){
			
			farmName = scanner.next() + " " + scanner.next(); //reads farm name from file
		}
		
		else {
		
		System.out.print("Enter the name of the farm supplier: ");	//prompt for user to enter farm supplier name
		farmName = scanner.nextLine(); //reads farm name from user input
		} 
		
		return true; //returns true if process successful
		
	}
	
	/**
	 * String representation of the food item
	 */
	@Override
	public String toString() {
		return String.format("%s farm supplier: %s%n", super.toString(), farmName); //display results	
	}

}