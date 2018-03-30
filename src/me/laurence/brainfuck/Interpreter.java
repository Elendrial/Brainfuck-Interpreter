package me.laurence.brainfuck;

import java.util.ArrayList;
import java.util.Scanner;

public class Interpreter {

	private Cell cell;
	private  ArrayList<Integer> loopBeginnings;
	private String[] language;
	private Scanner scan;
	private String unusedString;
	private int longestLength;
	public boolean integerValues;
	
	public Interpreter() {
		this(new String[] {">", "<", "+", "-", ".", ",", "[", "]"});
	}
	
	public Interpreter(String[] language) {
		checkLanguage(language);
		this.language = language;
		reset();
		scan = new Scanner(System.in);
	}
	
	private void checkLanguage(String[] language) {
		if(language.length != 8) throw new IllegalArgumentException("Language must have length 8.");
		
		for(int i = 0; i < 8; i++) {
			
			if(language[i].length() == 0) throw new IllegalArgumentException("Language cannot have empty commands.");
			if(language[i].length() > longestLength) longestLength = language[i].length();
			
			for(int j = i+1; j < 8; j++) {
				if(language[i].equals(language[j])) throw new IllegalArgumentException("Two commands cannot be the same");
			}
		}
		
		boolean used;
		int unused = 0; // numbers are used as the language must be 8 strings long, so there must be a digit from 0-9 that is not used as a command.
		do {
			used = false;
			for(int i = 0 ; i < 8; i++)
				if(language[i].equals(unused + "")) used = true;
			if(used) unused++;
		} while(used);
		unusedString = unused + "";
		
		// TODO: Check that a command is not a concatenation of other commands.
	}
	
	public void reset() {
		cell = new Cell(null);
		loopBeginnings = new ArrayList<Integer>();
		integerValues = false;
	}
	
	public boolean isValid(String s) {
		
		int bracketCount = 0;
		String open = language[6];
		String close = language[7];
		
		for(int i = 0; i < s.length(); i++) {
			String c = getNextCommand(s.substring(i));
			
			if(c.equals(open)) bracketCount++;
			else if(c.equals(close)) bracketCount--;
			
			i += c.length()-1;
		}
		
		return bracketCount == 0;
	}
	
	public void interpret(String s) {
		if(!isValid(s)) return;
		
		String c;
		for(int i = 0; i < s.length(); i++) {
			c = getNextCommand(s.substring(i));
			i += c.length()-1;
			
			if(c.equals(language[0]))      cell = cell.getNext();
			else if(c.equals(language[1])) cell = cell.getPrev();
			else if(c.equals(language[2])) cell.increment();
			else if(c.equals(language[3])) cell.decrement();
			
			else if(c.equals(language[4])) {
				System.out.println((char) cell.getValue());
				if(integerValues) System.out.println("::" + cell.getValue());
			}
			
			else if(c.equals(language[5])) {
				System.out.print("> ");
				char ch = scan.nextLine().toCharArray()[0];
				cell.setValue((short) ch);
				if(integerValues) System.out.println("::" + (int)ch + "\n");
			}
			
			else if(c.equals(language[6])) {
				if(cell.getValue() != 0)
					loopBeginnings.add(i);
				else {
					int bracketCount = 1;
					i++;
					while(bracketCount != 0) {
						c = getNextCommand(s.substring(i));
						i += c.length();
						if(c.equals(language[6])) bracketCount++;
						else if(c.equals(language[7])) bracketCount--;
					}
					i--;
				}
			}
			
			else if(c.equals(language[7])) {
				if(cell.getValue() != 0) 
					i = loopBeginnings.get(loopBeginnings.size()-1);
				else
					loopBeginnings.remove(loopBeginnings.size()-1);
			}
		}
	}
	
	private String getNextCommand(String s) {
		int end = s.length() < longestLength ? s.length() : longestLength;
		
		for(int i = 0; i < end; i++)
			for(int j = 0; j < 8; j++)
				if(language[j].equals(s.substring(0, end-i))) return s.substring(0, end-i);
		
		return unusedString; // has length 1 so un-found commands do not halt the program.
	}

	public String[] getLanguage() {
		return language;
	}

	public void setLanguage(String[] language) {
		checkLanguage(language);
		this.language = language;
	}
	
}
