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
	}
	
	public void decrement() {
		value--;
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
	
	public byte getValue() {
		return value;
	}
	
	public void setValue(byte b) {
		value = b;
	}
}
