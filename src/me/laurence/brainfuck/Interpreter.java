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
	
	public void interpret(String s) {
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
				loopBeginnings.add(i);
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
