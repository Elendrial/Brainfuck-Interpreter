package me.laurence.brainfuck;

// Basically a doubly-linked list
public class Cell {
	
	private Cell next;
	private Cell prev;
	
	private long value;
	private long highestValue;
	
	protected Cell(Cell prev, long highestValue) {
		this.prev = prev;
		value = 0;
		this.highestValue = highestValue;
	}
	
	public void increment() {
		value++;
		value %= highestValue;
	}
	
	public void decrement() {
		value--;
		if(value < 0) value += highestValue;
	}
	
	public Cell getNext() {
		if(next == null) next = new Cell(this, highestValue);
		return next;
	}
	
	private void setNext(Cell c) {
		next = c;
	}
	
	public Cell getPrev() {
		if(prev == null) {
			prev = new Cell(null, highestValue);
			prev.setNext(this);
		}
		return prev;
	}
	
	public long getValue() {
		return value;
	}
	
	public void setValue(long b) {
		value = b;
	}
	
	public void updateHighestValue(long newValue) {
		updateThis(newValue);
		if(next != null) next.updateNext(newValue);
		if(prev != null) prev.updatePrev(newValue);
	}
	
	private void updateNext(long newValue) {
		updateThis(newValue);
		if(next != null) next.updateNext(newValue);
	}
	
	private void updatePrev(long newValue) {
		updateThis(newValue);
		if(prev != null) prev.updatePrev(newValue);
	}
	
	private void updateThis(long newValue) {
		highestValue = newValue;
		if(value > highestValue) value %= highestValue;
	}
}
