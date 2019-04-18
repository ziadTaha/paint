package eg.edu.alexu.csd.oop.draw.memento;

import java.awt.List;
import java.util.LinkedList;

public class CareTaker {
	private LinkedList<Memento> mementoList = new LinkedList();

	public void add(Memento memento)
	{
		mementoList.add(memento);
	}
	public Memento get(int index)
	{
		return mementoList.get(index);
	}
	public void removeFrom(int index)
	{
		for(int i=mementoList.size()-1;i>=index;i--)
		{
		 mementoList.remove(mementoList.size()-1);
		}
	}

}
