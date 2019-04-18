package eg.edu.alexu.csd.oop.draw.memento;

import java.util.LinkedList;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Originator {
	private LinkedList<Shape> state;
	private int limit;

	public LinkedList<Shape> getState() {
		return state;
	}

	public void setState(LinkedList<Shape> state) {
		this.state = state;
	}

	public Memento saveStateToMemento() {
		return (new Memento(state, limit));
	}

	public void getStateFromMemento(Memento memento) {
		state = memento.getState();
		limit = memento.getLimit();

	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
