package eg.edu.alexu.csd.oop.draw.cs18;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class MyLine extends MyShape {

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		int x1 = getPosition().x;
		int y1 = getPosition().y;
		int x2 = getProperties().get("x").intValue();
		int y2 = getProperties().get("y").intValue();
		canvas.setColor(getFillColor());
		canvas.drawLine(x1, y1, x2, y2);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone() throws CloneNotSupportedException {
		MyLine e = new MyLine();
		e.setProperties((Map<String, Double>) ((HashMap<String, Double>)this.getProperties()).clone());
		e.setPosition((Point) this.getPosition().clone());
		e.setColor(this.getColor());
		e.setFillColor(this.getFillColor());
		return e;
	}

}
