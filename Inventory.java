import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * This class contains an array for working with several food items
 * Student Name: Chi Austin
 * Student Number:  041041623
 * Course: CST8130 - Data Structures
 * @author/Professor: James Mwangi PhD. 
 * 
 */
public class Inventory {
	
	private ArrayList<FoodItem> inventory = new ArrayList<FoodItem>(6);	//array list of food items 
	private int numItems;	//number of items in array list
	
	/**
	 * returns the index of a food item if item code matches
	 * @param item food item used to check
	 * @return return the index of item in exists or -1 if not
	 */
	public int alreadyExists(FoodItem item) {
		
		for(FoodItem food : inventory) {	//enhanced for loop
			
			if(food.isEqual(item)) {
				
				return inventory.indexOf(food); //returns index for item that already exists
				
			}
			
		}
		
		return -1;	//returns -1 if items does not already exist
		
		}
	
	/**
	 * This searches for an item using the item code and displays its toString method if found in inventory
	 * @param scanner receives scanner object
	 */
	public void searchForItem(Scanner scanner) {
		
		Collections.sort(inventory);	//sort food items found in inventory
		System.out.print("Enter the code for the item: ");	//prompts user for item code
		int itemCode = scanner.nextInt();//reads code from user input
		
		int location = -1; //initialize location to -1
		int low = 0;
		int high = inventory.size()-1;  //sets high as last value in list
		int mid = (low + high + 1)/2;
		
		while(low <= high && location == -1) {	//This is the base case
		
		if(itemCode == inventory.get(mid).getItemCode()) {	//sets location to mid if code == code of value at mid index of list
			location = mid;
		}
		
		else if(itemCode < inventory.get(mid).getItemCode()) {	//drops fist half of list if code is less then code of value at mid index of list
			high = mid - 1;
		}
		
		else if(itemCode > inventory.get(mid).getItemCode()) {	//drops second half of list if code is greater than code of value at mid index of array
			low = mid + 1;
		}
		
		mid = (low + high + 1)/2;	//resets mid with respect to the above
		
		}
		
		if(location != -1) {
			System.out.printf("%s%n", inventory.get(location).toString());	//display output if code is in list
		}
		
		else {
			System.out.println("\nCode not found in inventory...\n");	//display output if code is not found in inventory
		}
	}
	
	/**
	 * This method saves food items to a file
	 * @param scanner receives scanner object to read file to save to
	 * @throws FileNotFoundException If file does not exist
	 */
	public void saveToFile(Scanner scanner) throws FileNotFoundException {
		
		System.out.print("Enter file name to save to: ");	//prompts user for file to save to
		
		try(Formatter writer = new Formatter(scanner.next())){	//beginning of try block 
		
			for(FoodItem f : inventory) {	//enhanced for loop
			
				f.outputItem(writer);	//save each and every food item to file
		}
			
			writer.close();	//close write object
		}
		catch(FileNotFoundException e) {
			System.out.println("\nFile Not Found, ignoring...\n");	//display message if file not found
		}
	}
	
	/**
	 * This method reads food items from a file specified by the user
	 * @param scanner receives scanner object
	 * @throws IOException if file not found
	 */
	public void readFromFile(Scanner scanner) throws IOException {
		
		System.out.print("Enter the file name to read from: ");	//prompts user for file name to read from
		try(Scanner sc = new Scanner(Paths.get(scanner.nextLine()))){	//beginning of try block
			
			while(sc.hasNext()) {	//while there's still data to read from file
				
				if(sc.next().equals("f")) {	//for fruit items
					
					FoodItem fruit = new Fruit();	//polymorphism
					
					if(checkCode(sc, fruit)) {	//if code already exists
						
						fruit.addItem(sc, true);	//invokes add item to read data from file
						inventory.add(fruit);	//add fruit item to array list
						numItems++;	//keep track of numbers of food items in inventory
					
					}
					else {
						System.out.println("Error Encountered while reading the file, aborting...\n");	//display message when code already exists in list
						return;
					}
					
					}
				
				if(sc.next().equals("p")) {	//for preserves
					
					FoodItem preserve = new Preserve();	//polymophism
					
					if(checkCode(sc, preserve)) {	//check if code already exists
						
						preserve.addItem(sc, true);	//read data from file
						inventory.add(preserve);	//add preserve food item into inventory
						numItems++;	//keep track of number of food items in inventory
					}
					
					else {
						System.out.println("Error Encountered while reading the file, aborting...\n");	//display message when code already exists in inventory
						return;
					}
				}
				
				if(sc.next().equals("v")) {	//for vegetables
					
					FoodItem vegetable = new Vegetable();
					if(checkCode(sc, vegetable)) {	//check if code already exists
						
						vegetable.addItem(sc, true);	//read data from file
						inventory.add(vegetable);	//add vegetable food item into inventory
						numItems++;		//keep track of number of food item in inventory
					
					}
					else {
						System.out.println("Error Encountered while reading the file, aborting...\n"); //display message when code already exists in inventory
						return;
					}
				}
				
				else if(sc.next().equals("s")) {	//for sea foods
					
					FoodItem seafood = new SeaFood();
					
					if(checkCode(sc, seafood)) {	//check if code already exists
						seafood.addItem(sc, true);	//read data for sea food
						inventory.add(seafood);	//add sea food into inventory
						numItems++;	// keep track of food items in inventory
					}
					else {
						System.out.println("Error Encountered while reading the file, aborting...\n");
						return;
					}
				}
				
			}
			
		}catch(IOException e) {	//catch IO Exception
			System.out.println("\nFile Not Found, ignoring...\n");	//display message if file is not found
		}
		
			}
	
	/**
	 * This is a private helper method which returns false if item code already exists and true if not
	 * @param scanner scanner object
	 * @param item Food item whose code is compared
	 * @return	true or false based on whether code already exists or not
	 */
	private boolean checkCode(Scanner scanner, FoodItem item) {
		
		int sentinel1 = 0;	//sentinel value to control loop
		
		while(sentinel1 != -1) {	//beginning of while loop
			
			try {	//start of try block
				
				item.inputCode(scanner, true);	//invoking input code method
				
				if(alreadyExists(item) != -1) {	//check if item already exists based on the item's code
					
					System.out.println("\nItem code already exists");	//display message if item already exists
					
					return false;	//return false if item already exists
					
				}
				
				sentinel1 = -1;	//sentinel value for controlling while loop
				
				}
			
			catch(InputMismatchException e) {	//catch input mismatch exception
				System.out.println("Invalid code");	//display error message
				scanner.nextLine();	//clear scanner bug
			}
		}
		return true;	//return if item does not already exists
		
	}
	
	/**
	 * Method which reads informations of a particular food item
	 * @param scanner Accepts scanner object to read instance variable
	 * @param fromFile file to read from
	 * @return return true or false based on success of method
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		
		int sentinel = 0;	//sentinel value to control while loop
		
		while(sentinel != -1) {	//while value not -1
			
		System.out.print("Do you wish to add a fruit(f), vegetable(v), preserve(p) or a seafood(s)? ");	//prompts user for choice between f, v, p and s
		String choice = scanner.next();	//reads choice between f, v, p or s
		
		switch(choice) {	//switch statement
		
		case "f":	//for fruits
			
			FoodItem fruit = new Fruit(); //polymorphism
			System.out.print("Enter the code for the item: ");	//prompts user for code of item
			if(checkCode(scanner, fruit)) {	//check if code already exists in inventory
			
				fruit.addItem(scanner, fromFile);	//uses polymorphism to call add item method
				inventory.add(fruit);   //adds food item into array
				numItems++;				//keep track of number of food items in inventory		
				sentinel = -1;
			
			}
			
			else {
				return false; //return false if code already exists and fruit item cannot be added into inventory
			}
			break;
			
		case "v": // for vegetables
			
			FoodItem vegetable = new Vegetable();	//polymorphism
			System.out.print("Enter the code for the item: ");	//prompts user for item code
			
			if(checkCode(scanner, vegetable)) {	//check if code already exists in inventory
				
				vegetable.addItem(scanner, fromFile);	//uses polymorphism to call add item method
				inventory.add(vegetable);  //adds food item into array
				numItems++;			//keep track of number of food items in inventory		
				sentinel = -1;
				
				}
				
				else {
					return false;	//return false if code already exists and vegetable item cannot be added into inventory
				}
			break;
			
		case "p":	//in case of a preserve food item
			
			FoodItem preserve = new Preserve();	//polymorphism
			System.out.print("Enter the code for the item: ");	//prompts user for code of item
			
			if(checkCode(scanner, preserve)) {
				
				preserve.addItem(scanner, fromFile);	//uses polymorphism to call add item method
				inventory.add(preserve);	//adds food item into array
				numItems++;				//keep track of the amount of food items in inventory	
				sentinel = -1;	//sentinel value to keep track of while loop
				
				}
				
				else {
					return false;	//return false if code already exists and preserve item cannot be added into inventory
				}
			break;
			
		case "s":	//in case of a sea food
			
			FoodItem seafood = new SeaFood();	//polymorphism
			System.out.print("Enter the code for the item: ");	//prompts user for sea food item code
			
			if(checkCode(scanner, seafood)) {
				
				seafood.addItem(scanner, fromFile);	//uses polymorphism to call add item method
				
				inventory.add(seafood);//adds food item into array
				numItems++;			//keep track of number of food items found in inventory	
				sentinel = -1;
				
				}
				
				else {
					return false;	//return false if code already exists and sea food item cannot be added into inventory
				}
			break;
			
		default:
			System.out.println("Invalid entry");	//for value other than choice display invalid entry
			break;
			
			}
		
		}
		return true;	//returns true for successful process
	}
	
	/**
	 * Updates the quantity of a particular food item 
	 * @param scanner Accepts scanner object in order to know item to update
	 * @param buyOrSell	true to buy and false to sell
	 * @return	true or false if successful
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		
		if(buyOrSell == true) {	//buy item
			
			if(inventory.isEmpty()) {
				System.out.println("Error...could not buy item");	//display output of array is empty
			}
			else {
			
				System.out.print("Enter the code for the item: "); //else process to read code for item to update
				int code = scanner.nextInt();
				
				for(FoodItem f : inventory) {	//enhanced for loop 
					
					
					if(f.getItemCode()==code) {	//if codes match
						
						System.out.print("Enter valid quantity to buy: ");	//prompt for quantity to buy
					
						int quantity = scanner.nextInt();	//reads quantity
					
						if(quantity < 0) {
							System.out.println("Error...could not buy item");	//display error message if quantity is less than 0
						}
						else {
							f.updateItem(quantity);	//invokes update item
							}
					
						}
					
					}
				}
			}
		
		else if(buyOrSell == false) {	//sell item
			
			if(inventory.isEmpty()) {
				System.out.println("Error...could not sell item");	//display message if array if empty
			}
			
			else {
				System.out.print("Enter the code for the item: ");	//prompts user for code
				int code = scanner.nextInt();	//reads code
			
			
			for(FoodItem f : inventory) {	//enhanced for loop
				
				if(f.getItemCode()==code) {	//if code match
					
					System.out.print("Enter valid quantity to sell: ");	//prompt user for quantity
					int quantity = scanner.nextInt();	//reads quantity
					
					try {
					
						if(f.itemQuantityInStock < quantity) {	//display error message if quantity is less than amount to update
						
							throw new IllegalArgumentException("Error...could not sell item");	//display error message
						
						}
					
						else {
							f.updateItem(-quantity);	//invoke update item method
						}
					}
					catch(IllegalArgumentException e) {	//catch an exception based on above
						
						System.out.println(e.getMessage());	//display error message
						
						}
					}
				}
			}
		}
		
		return true;	//return true is process is successful
			
		}
	
	/**
	 * String representation of food item(Displays food items in inventory)
	 */
	@Override
	public String toString() {
		Collections.sort(inventory);	//sort food items in inventory
		
		System.out.println("Inventory:");	//display heading

		for(FoodItem i : inventory) {	//for loop through every food item's toString
			
			System.out.print(i.toString());	//call toString of food item
			
		}
		
		return "\n";	//exit method
		
	}
	}