package gameUI;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TickTackToe{
	public static HashMap<Integer, String> boardState = new HashMap<>(9);
	public static boolean turnX = false;
	public static boolean turnY = false;
	public static Scanner scanner = new Scanner(System.in);
	public static boolean gameOver = false;
	public static final String playerX = "X";
	public static final String playerY = "Y";
	public static boolean xBoolean = false;


	public static void main(String[] args) {
		
		int numberX = 0;
		int numberY = 0;
		
		boolean startGame = true;
	
		
		for(int i = 0; i < 9; i++) {
			boardState.put(i+1, String.valueOf(i+1));
			
		}
		
		
		while(!gameOver) {

		if(startGame) {
		System.out.println("X or Y");
		String XorY = scanner.next();
		checkIfTextIsString(XorY);
		startGame = false;

		}
		
			
		if(turnX) {
			
			System.out.println("Enter a slot number between 1-9 to place " + playerX + " in:");
			try{ 
				
				if(scanner.hasNextInt()) {
					checkIfNumberIsInRange(scanner.nextInt(), "X");
					scoreTickToc("X", boardState);
					turnX = false;
					turnY = true;
				}
			}catch (InputMismatchException e) {
					// TODO: handle exception
					System.out.println("Error: " + e + "You entered an invalid character! Please try again!");
				}
			
		} else if(turnY) {

			System.out.println("Enter a slot number between 1-9 to place " + playerY + " in:");
			try{ 
			if(scanner.hasNextInt()) {
			numberY = scanner.nextInt();
			
			checkIfNumberIsInRange(numberY, "Y");
			
			scoreTickToc("Y", boardState);


		    turnX = true;
		    turnY = false;

				

			}
			} catch (InputMismatchException e) {
				// TODO: handle exception
				System.out.println("Error: " + e + "You entered an invalid character! Please try again!");
			}
		}
		
		System.out.println("|---|---|---|");
		System.out.println("| " + boardState.get(1) + " | " + boardState.get(2) + " | " + boardState.get(3) + " |");
		System.out.println("|-----------|");
		System.out.println("| " + boardState.get(4) + " | " + boardState.get(5) + " | " + boardState.get(6) + " |");
		System.out.println("|-----------|");
		System.out.println("| " + boardState.get(7) + " | " + boardState.get(8) + " | " + boardState.get(9) + " |");;
		System.out.println("|---|---|---|");
	
		}
		scanner.close();

	}
	
	private static boolean checkIfTextIsString(String playerChooseXorY) {
		// TODO Auto-generated method stub

		if(playerChooseXorY.equalsIgnoreCase("X")) {
			turnX = true;
			return turnX;
		} else if(playerChooseXorY.equalsIgnoreCase("Y")) {
			turnY = true;
			return turnY;
		}else {
			if(!xBoolean) {
				System.out.println("You have entered a wrong character. Please try again.");
			}
			xBoolean = true;
			checkIfTextIsString(scanner.nextLine());
		}
			
		return false;

	
	}
	
	
	private static String checkIfNumberIsInRange(int number, String playerTurn) {
		// TODO Auto-generated method stub
		if(playerTurn.equals("X")) {
		if(number > 0 && number <= 9) {
			boardState.replace(number, "X");
				return "";
		}else if (number < 0 || number > 9){
			System.out.println("You have entered a smaller or bigger number than what was specified. Please enter a number between 1 - 9 again.");
			checkIfNumberIsInRange(scanner.nextInt(), playerTurn);

		
		}
		} else if(playerTurn.equals("Y")) {
			
			if(number > 0 && number <= 9) {
				boardState.replace(number, "Y");
					return "";
			}else if (number < 0 || number > 9){
				System.out.println("You have entered a smaller or bigger number than what was specified. Please enter a number between 1 - 9 again.");
				checkIfNumberIsInRange(scanner.nextInt(), playerTurn);
			}
			
		}
		return null;
	}

	public static void scoreTickToc(String playerTurn, HashMap<Integer, String> hashMap) {
	    // Define the winning combinations
	    int[][] winningCombinations = {
	        {1, 2, 3}, {4, 5, 6}, {7, 8, 9}, // Rows
	        {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, // Columns
	        {1, 5, 9}, {3, 5, 7}              // Diagonals
	    };

	    // Check each winning combination
	    for (int[] combination : winningCombinations) {
	        if (hashMap.get(combination[0]).equals(playerTurn) &&
	            hashMap.get(combination[1]).equals(playerTurn) &&
	            hashMap.get(combination[2]).equals(playerTurn)) {
	            System.out.println("Player " + playerTurn + " wins!");
	            gameOver = true;
	            return;
	        }
	    }

	    // No winning combination found
	    System.out.println("No winner yet!");
	}

}