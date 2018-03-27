package me.laurence.brainfuck;

// Basically a doubly-linked list
public class Cell {
	
	private Cell next;
	private Cell prev;
	
	private short value;
	
	public Cell(Cell prev) {
		this.prev = prev;
		value = 0;
	}
	
	public void increment() {
		value++;
		value %= 256;
	}
	
	public void decrement() {
		value--;
		if(value < 0) value += 256;
	}
	
	public Cell getNext() {
		if(next == null) next = new Cell(this);
		return next;
	}
	
	private void setNext(Cell c) {
		next = c;
	}
	
	public Cell getPrev() {
		if(prev == null) {
			prev = new Cell(null);
			prev.setNext(this);
		}
		return prev;
	}
	
	public short getValue() {
		return value;
	}
	
	public void setValue(short b) {
		value = b;
	}
}
