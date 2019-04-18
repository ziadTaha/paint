package eg.edu.alexu.csd.oop.draw.memento;

import java.util.LinkedList;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Memento {
	private LinkedList<Shape> state;
	private int limit;

	public Memento(LinkedList<Shape> state, int limit) {
		// TODO Auto-generated constructor stub
		this.state = state;
		this.limit = limit;
	}

	public LinkedList<Shape> getState() {
		return state;
	}

	public int getLimit() {
		return limit;
	}

}
