import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * This class contains informations of a food item
 * Student Name: Chi Austin
 * Student Number:  041041623
 * Course: CST8130 - Data Structures
 * @author/Professor: James Mwangi PhD. 
 * 
 */
public class FoodItem implements Comparable<FoodItem>{
	
	private int itemCode;	//instance variables code of item
	private String itemName;	// name of item
	private float itemPrice;	//price of item
	protected int itemQuantityInStock;	//quantity available
	private float itemCost;	//cost of item
	
	/**
	 * updates the quantity of a particular food item
	 * @param amount The amount by which a food item is updated
	 * @return return true or false if process is successful
	 */
	public boolean updateItem(int amount) {
		
		itemQuantityInStock += amount;	//adds amount to quantity
		return true;	//return true if successful
		
		}
	
	/**
	 * Gets the code of the item
	 * @return returns item code
	 */
	public int getItemCode() {
		return itemCode;	//returns the code of a particular item
	}
		
		
	/**
	 * checks if the code of two items are equal and returns true of false based on that condition
	 * @param item food item used to check
	 * @return returns true or false depending on the success of the method
	 */
	public boolean isEqual(FoodItem item) {
		
		if(itemCode != item.itemCode) {	//return false if code do not match
			return false;	//returns false if codes dont match
			
		}
		
		else {
			return true;	//else return true
		}
		
	}
	
	/**
	 * This method writes various data members to a file
	 * @param writer Writes data to a file
	 */
	public void outputItem(Formatter writer) {
		
		writer.format("%d%n", itemCode);	//writes item code to file
		writer.format("%s%n", itemName);	//writes item name to a file
		writer.format("%d%n", itemQuantityInStock);	//writes item quantity to file
		writer.format("%.2f%n", itemCost);	//writes cost of item to a file
		writer.format("%.2f%n", itemPrice);	//writes the item price to file
	}
	
	/**
	 * Adds food items into an array of food items
	 * @param scanner Reads input to initialize instance variables
	 * @param fromFile file to read from
	 * @return return booleans value depending on success of method
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		
		if(fromFile == true) { //if true, read from a file
			
			itemName = scanner.next()+scanner.nextLine();	//read item name from file
			itemQuantityInStock = scanner.nextInt(); 	//reads quantity from file
			itemCost = scanner.nextFloat();	//read from cost from file
			itemPrice = scanner.nextFloat();	//reads price of item from file
			
		}
		
		else {	//else read from user input
		
		int sentinel = 0;	//sentinel value to control while loop

		scanner.nextLine();	// clear scanner bug
		
		sentinel = 0;
		
		while(sentinel != -1) {	//while name is not string
			
			try {	//start of try block
		
				System.out.print("Enter the name for the item: ");	//display for user to input
				itemName = scanner.nextLine();	//read item name
				sentinel = -1;
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid entry");	//display error message of item name is not String
				scanner.nextLine();	//clear scanner bug
			}
		}
		
		sentinel = 0;	//refresh sentinel value to test while loop
		
		while(sentinel != -1) {
			
			try {	//beginning of try block
		
				System.out.print("Enter the quantity for the item: ");
				itemQuantityInStock = scanner.nextInt();	//read quantity of item
		
				if(itemQuantityInStock < 0) {
			
					throw new IllegalArgumentException("Invalid entry");   //if quantity is -, display error message
			
				}
		
				else {
					scanner.nextLine();	//clear scanner bug 
					sentinel = -1;
				}
		
			}
			catch(InputMismatchException e) {	//catch input mismatch exception
				System.out.println("Invalid entry");
				scanner.nextLine();	//clear scanner bug
			}
			catch(IllegalArgumentException e) {	//catch illegal argument exception
				System.out.println(e.getMessage());	//display message
			}
			
		}
		
		sentinel = 0;
		
		while(sentinel != -1) {	//while cost of item is not valid
			
			try {
		
				System.out.print("Enter the cost of the item: ");	//prompt user for cost of item
				itemCost = scanner.nextFloat();//read cost of item
		
				if(itemCost < 0) {
					throw new IllegalArgumentException("Invalid entry");	//display error message if cost is -.
				}
		
				else {
					sentinel = -1;	//converts sentinel value to 1
				}
		
			}
			catch(InputMismatchException e){
				System.out.println("Invalid entry");	// display error message 
				scanner.nextLine();
			}
			catch(IllegalArgumentException e) {
				System.out.println("Invalid entry");	//display error message
			}
		}
		
		sentinel = 0;	//sentinel value to control while loop
		
		while(sentinel != -1) { //begin while loop, while sales price is not valid
			
			try {
				
				System.out.print("Enter the sales price of the item: ");	//prompt user
				itemPrice = scanner.nextFloat();	//reads item price
		
				if(itemPrice < 0) {
					throw new IllegalArgumentException("Invalid entry");	//display error message of price is -
				}
		
				else {
					scanner.nextLine();	//clear scanner bug
					sentinel = -1;	//converts sentinel value to -1
				}
		
			}
			
			catch(InputMismatchException e) {
				System.out.println("Invalid entry");	//display error message
				scanner.nextLine();	//clear scanner bug
			}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());	//display error message
			}
			
		}
		}
			return true;	//return true if successful
		
	}
	
	
	/**
	 * Reads a valid item code from the scanner object and returns true/false if successful
	 * @param scanner accepts scanner object
	 * @param fromFile text file
	 * @return returns boolean value depending on success of method
	 */
	public boolean inputCode(Scanner scanner, boolean fromFile) {
		
		this.itemCode = scanner.nextInt();
		return true;
		
		}
	
	/**
	 * toString method to return food items and in properties
	 */
	@Override
	public String toString() {
		
		return String.format("Item: %d %s %d price: $%.2f cost: $%.2f", itemCode, itemName, itemQuantityInStock, itemPrice, itemCost);
		
	}

	/**
	 * This method compares two objects with respect to their code for sorting
	 */
	@Override
	public int compareTo(FoodItem o) {
		
		if(this.getItemCode() > o.getItemCode()) {	//return 1 if o's code is less than this.code
			return 1;
		}
		
		else if(this.getItemCode() < o.getItemCode()) { //return -1 if o's code is greater than this.code
			return -1;
		}
		
		else {
			return 0;	//else return 0
		}
		
		
	}

}