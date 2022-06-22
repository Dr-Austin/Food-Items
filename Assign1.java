import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * This class contains method main to run the program
 * Student Name: Chi Austin
 * Student Number:  041041623
 * Course: CST8130 - Data Structures
 * @author/Professor: James Mwangi PhD. 
 * 
 */
public class Assign1 {

	/**
	 * Method runs program
	 * @param args main method to run program
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);	//scanner object
		
		Inventory i = new Inventory();	//instance of inventory class
		
		int sentinel = 0;	//sentinel value to control while loop
		
		while(sentinel != -1) {	//start of while loop
			
			try {	//beginning of try statement
			
			displayMenu();	//invoking the displayMenu method
			
			int option = sc.nextInt();	//read the user's option
			sc.nextLine();	//clear scanner bug
			
			switch(option) {	//begin switch statement
			
			case 1:
				
				i.addItem(sc, false);	//adding item to inventory array
				break;
				
			case 2:
				
				System.out.print(i.toString());	//Display food items in inventory array
				break;
				
			case 3:
				
				i.updateQuantity(sc, true);	//Buy a particular item depending on the code
				break;
				
			case 4:
				
				try {
				i.updateQuantity(sc, false);	//sell a food item depending on the item's code
				}
				catch(NullPointerException e) {	//if inventory is empty display error message
					System.out.println("Error... could not sell item");	//error message
				}
				break;
				
			case 5:
				
				i.searchForItem(sc);	//search for a particular item in list based on the item code
				break;
				
			case 6:
				
				try {
					i.saveToFile(sc);	//save food items in list to a file specified by the user
				} catch (FileNotFoundException e1) {	//display error message of file is not found
					
					System.out.println("\nFile Not Found, ignoring...\n");	//display error message if file does not exist
				}
				break;
				
			case 7:
				
				try {
					i.readFromFile(sc);	//read food items from a file specified by the user
				} catch (IOException e) {
					
					System.out.println("\nFile Not Found, ignoring...\n");	//display error message if found does not exist
				}
				break;
				
			case 8:
				
				System.out.println("Exiting...");	//output exiting... to exit program
				sentinel = -1; // converts sentinel value to -1 to end while loop
				break;
				
			default:
				System.out.println("\n...Must be a number between 1 - 8...\n");	//output message if case is out of range
				break;
			
			}
			}
			catch(InputMismatchException e) {	//catch input mismatrch exception
				
				System.out.println("\n...Invalid input, please enter a number...\n");	//display error message
				sc.nextLine();//clear scanner bug
			}
			
			catch(NullPointerException e) {	//catch null pointer exception
				
				System.out.println("");
				
			}
			
			}
		
		}//end method main
	
	/**
	 * Displays menu from which user selects choice
	 */
	public static void displayMenu() {
		System.out.println("Please select one of the following:");
		System.out.println("1. Add Item to Inventory");	//1 to add item to inventory
		System.out.println("2. Display Current Inventory");	//display the items in inventory
		System.out.println("3. Buy Item(s)");	//3 to buy an item 
		System.out.println("4. Sell Item(s)");	// 4 to sell an item
		System.out.println("5. Search for Item");	//5 to search for an item
		System.out.println("6. Save Inventory to File");	//6 to save to file
		System.out.println("7. Read Inventory from File"); // 7 to read from file
		System.out.println("8. To Exit");	// 8 to exit program
		System.out.print("> ");
		
	}

}