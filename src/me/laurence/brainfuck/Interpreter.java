package me.laurence.brainfuck;

import java.util.ArrayList;
import java.util.Scanner;

public class Interpreter {

	private Cell cell;
	private  ArrayList<Integer> loopBeginnings;
	private Scanner scan;
	public boolean integerValues;
	
	public Interpreter() {
		reset();
		scan = new Scanner(System.in);
	}
	
	public void reset() {
		cell = new Cell(null);
		loopBeginnings = new ArrayList<Integer>();
		integerValues = false;
	}
	
	public boolean isValid(String s) {
		
		int bracketCount = 0;
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch(c) {
			case '[': bracketCount++; break;
			case ']': bracketCount--; break;
			}
		}
		
		return bracketCount == 0;
	}
	
	public void interpret(String s) {
		if(!isValid(s)) return;
		
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch(c) {
			case '>':
				cell = cell.getNext();
				break;
				
			case '<':
				cell = cell.getPrev();
				break;
				
			case '+':
				cell.increment();
				break;
				
			case '-':
				cell.decrement();
				break;
				
			case '.':
				System.out.println((char) cell.getValue());
				if(integerValues) System.out.println("::" + cell.getValue());
				break;
			
			case ',':
				System.out.print("> ");
				char ch = scan.nextLine().toCharArray()[0];
				cell.setValue((byte) ch);
				if(integerValues) System.out.println("::" + (int)ch + "\n");
				break;
				
			case '[':
				if(cell.getValue() != 0)
					loopBeginnings.add(i);
				else {
					int bracketCount = 1;
					while(bracketCount != 0 || s.charAt(i) != ']') {
						i++;
						if(s.charAt(i) == '[') bracketCount++;
						else if(s.charAt(i) == ']') bracketCount--;
					}
				}
				break;
				
			case ']':
				if(cell.getValue() != 0) 
					i = loopBeginnings.get(loopBeginnings.size()-1);
				
				else
					loopBeginnings.remove(loopBeginnings.size()-1);
				
				break;
			}
		}
	}
	
}
