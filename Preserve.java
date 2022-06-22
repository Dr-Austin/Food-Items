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
public class Preserve extends FoodItem{
	
	private int jarSize;	//instance variables
	
	/**
	 * Default constructor
	 */
	public Preserve() {};
	
	/**
	 * Contains formatter object in order to write data to file
	 * @param writer formatter object used to write data members to a file
	 */
	@Override
	public void outputItem(Formatter writer) {
		
		writer.format("%s%n", "p");	//writes heading 'p' for preserve Food Items
		super.outputItem(writer);	//(Inheritance) invokes outputItem from super class to read other data members
		writer.format("%d%n", jarSize);	//writes jar size to file
		
	}
	
	/**
	 * initializes data members for food item
	 * @param scanner Accepts scanner object to read data members
	 * @param fromFile file to read from
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		
		super.addItem(scanner, fromFile);	//invoking super class's addItem method(inheritance)
		
		if(fromFile == true) {
		
		jarSize = scanner.nextInt();	//reads jar size from file
		}
		
		else{
		
		System.out.print("Enter the size of the jar in millilittres: "); //reads the size of the jar in milli litres
		jarSize = scanner.nextInt();	
		}
		
		return true;	//returns true if process is successful
		
	}
	
	/**
	 * returns string representation of the food item
	 */
	@Override
	public String toString() {
		return String.format("%s size: %dmL%n", super.toString(), jarSize); //display results
	}


}