package eg.edu.alexu.csd.oop.draw.cs18;

import java.awt.Point;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Move {

	public void ShapeChange(Shape shape, Point clicked, Point p) {
		int xdiff = p.x - clicked.x;
		int ydiff = p.y - clicked.y;
		String name = shape.getClass().getName();
		String[] name2 = name.split("\\.");
		String s = name2[name2.length - 1];
		if (s.equals("Square") || s.equals("Rectangel") || s.equals("Circel") || s.equals("MyEllipse")
				|| s.equals("MyLine")) {

			int x = (int) (xdiff + shape.getProperties().get("x"));
			int y = (int) (ydiff + shape.getProperties().get("y"));
			shape.getProperties().put("x", (double) x);
			shape.getProperties().put("y", (double) y);
			Point pos = new Point(xdiff + shape.getPosition().x, ydiff + shape.getPosition().y);
			shape.setPosition(pos);
		} else if (s.equals("Triangle")) {
			int x1 = (int) (xdiff + shape.getProperties().get("x1"));
			int y1 = (int) (ydiff + shape.getProperties().get("y1"));
			int x2 = (int) (xdiff + shape.getProperties().get("x2"));
			int y2 = (int) (ydiff + shape.getProperties().get("y2"));
			int x3 = (int) (xdiff + shape.getProperties().get("x3"));
			int y3 = (int) (ydiff + shape.getProperties().get("y3"));
			shape.getProperties().put("x1", (double) x1);
			shape.getProperties().put("y1", (double) y1);
			shape.getProperties().put("x2", (double) x2);
			shape.getProperties().put("y2", (double) y2);
			shape.getProperties().put("x3", (double) x3);
			shape.getProperties().put("y3", (double) y3);

		}
	}

}
