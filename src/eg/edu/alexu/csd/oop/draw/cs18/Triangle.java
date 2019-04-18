package eg.edu.alexu.csd.oop.draw.cs18;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Triangle extends MyShape {
	Shape line = new MyLine();
	Map<String, Double> lineMap = new HashMap<>();

	public Triangle() {
		// TODO Auto-generated constructor stub
		Map<String, Double> l = new HashMap<>();
		setPosition(new Point(0, 0));
		setProperties(l);
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub

		if (getProperties().get("clicked").intValue() == 1) {
			lineMap.put("x", getProperties().get("x1"));
			lineMap.put("y", getProperties().get("y1"));
			line.setProperties(lineMap);
			Point p = new Point();
			p.setLocation(getProperties().get("Xmove"), getProperties().get("Ymove"));
			line.setPosition(p);
			line.draw(canvas);
		} else if (getProperties().get("clicked").intValue() == 2) {
			lineMap.put("x", getProperties().get("x1"));
			lineMap.put("y", getProperties().get("y1"));
			line.setProperties(lineMap);
			Point p = new Point();
			p.setLocation(getProperties().get("x2"), getProperties().get("y2"));
			line.setPosition(p);
			line.draw(canvas);
			lineMap.put("x", getProperties().get("x2"));
			lineMap.put("y", getProperties().get("y2"));
			line.setProperties(lineMap);
			p.setLocation(getProperties().get("Xmove"), getProperties().get("Ymove"));
			line.setPosition(p);
			line.draw(canvas);
			lineMap.put("x", getProperties().get("x1"));
			lineMap.put("y", getProperties().get("y1"));
			line.setProperties(lineMap);
			p.setLocation(getProperties().get("Xmove"), getProperties().get("Ymove"));
			line.setPosition(p);
			line.draw(canvas);
		} else if (getProperties().get("clicked").intValue() == 3) {
			int[] x = { getProperties().get("x1").intValue(), getProperties().get("x2").intValue(),
					getProperties().get("x3").intValue() };
			int[] y = { getProperties().get("y1").intValue(), getProperties().get("y2").intValue(),
					getProperties().get("y3").intValue() };
			canvas.setColor(getFillColor());
			canvas.fillPolygon(x, y, 3);
			canvas.setColor(getColor());
			canvas.drawPolygon(x, y, 3);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Triangle e = new Triangle();
		e.setProperties((Map<String, Double>) ((HashMap<String, Double>) this.getProperties()).clone());
		e.setPosition((Point) this.getPosition().clone());
		e.setColor(this.getColor());
		e.setFillColor(this.getFillColor());
		return e;
	}

}
