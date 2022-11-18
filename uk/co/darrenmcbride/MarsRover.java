package uk.co.darrenmcbride;

import java.math.BigInteger;
import java.util.Scanner;

public class MarsRover {
	private BigInteger plateauHeight, plateauWidth;
	private BigInteger positionX, positionY;
	private short direction;
	
	MarsRover(BigInteger plateauHeight, BigInteger plateauWidth) {
		this.plateauHeight = plateauHeight;
		this.plateauWidth = plateauWidth;
		
		positionX = new BigInteger("1");
		positionY = new BigInteger("1");
		
		direction = 0;
	}	
	
	public void processCommands(String command) {
		char[] commands = command.toLowerCase().toCharArray();
		
		for (char c : commands) {
			switch (c) {
				case 'l':
					turnLeft();
					break;
				case 'r':
					turnRight();
					break;
				case 'f':
					moveFoward();
					break;
			}
		}
		
		System.out.println(String.format("The Rover ended at X:%d, Y:%d, Facing: %s", positionX, positionY, getDirectionString()));
	}
	
	private void turnLeft() {		
		direction --;
		if (direction < 0)
			direction = 3;
	}
	
	private void turnRight() {
		direction ++;
		direction %= 4;
	}
	
	private void moveFoward() {
		switch (getDirectionString()) {
			case "North":
				// Checks that the height is greater than the Y Position
				if (plateauHeight.compareTo(positionY) == 1)
					positionY = positionY.add(BigInteger.ONE);
				break;
			case "East":
				// Checks that the width is greater than the X position
				if (plateauWidth.compareTo(positionX) == 1)
					positionX = positionX.add(BigInteger.ONE);
				break;
			case "South":
				// Checks that the Y Position is greater than 1
				if (positionY.compareTo(BigInteger.ONE) == 1)
					positionY = positionY.subtract(BigInteger.ONE);
				break;
			case "West":
				// Checks that the X Position is greater than 1
				if (positionX.compareTo(BigInteger.ONE) == 1)
					positionX = positionX.subtract(BigInteger.ONE);
				break;
		}
	}
	
	private String getDirectionString() {
		if (direction == 0)
			return "North";
		else if (direction == 1)
			return "East";
		else if (direction == 2)
			return "South";
		else if (direction == 3)
			return "West";
		return "Error";
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter a map size in the format [X]x[Y]");
		String[] plateauSize = in.nextLine().toLowerCase().split("x");
		BigInteger plateauWidth = new BigInteger(plateauSize[0]), plateauHeight = new BigInteger(plateauSize[1]);
		
		MarsRover rover = new MarsRover(plateauHeight, plateauWidth);
		
		System.out.println("Enter a command string. (L is turn left, R is turn right, F is move foward)");
		String command = in.nextLine();
		
		in.close();
		rover.processCommands(command);
	}
}