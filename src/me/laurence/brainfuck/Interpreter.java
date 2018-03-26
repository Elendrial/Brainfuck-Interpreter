package me.laurence.brainfuck;

import java.util.ArrayList;
import java.util.Scanner;

public class Interpreter {

	public Cell cell;
	public ArrayList<Integer> loopBeginnings;
	public Scanner scan;
	
	public Interpreter() {
		cell = new Cell(null);
		loopBeginnings = new ArrayList<Integer>();
		scan = new Scanner(System.in);
	}
	
	public void reset() {
		cell = new Cell(null);
		loopBeginnings = new ArrayList<Integer>();
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
				break;
			
			case ',':
				cell.setValue((byte) scan.nextLine().toCharArray()[0]);
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
