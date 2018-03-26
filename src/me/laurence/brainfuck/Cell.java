package me.laurence.brainfuck;

// Basically a doubly-linked list
public class Cell {
	
	private Cell next;
	private Cell prev;
	
	private byte value;
	
	public Cell(Cell prev) {
		this.prev = prev;
		value = 0;
	}
	
	public void increment() {
		value++;
	//	value %= 256;
	}
	
	public void decrement() {
		value--;
	//	if(value < 0) value += 256;
	}
	
	public Cell getNext() {
		if(next == null) next = new Cell(this);
		return next;
	}
	
	public Cell getPrev() {
		if(prev == null) prev = new Cell(null);
		return prev;
	}
	
	public byte getValue() {
		return value;
	}
	
	public void setValue(byte b) {
		value = b;
	}
}
