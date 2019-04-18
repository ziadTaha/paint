package eg.edu.alexu.csd.oop.draw.cs18;
import java.awt.Point;
import eg.edu.alexu.csd.oop.draw.Shape;

public class Selection {
	public boolean isSelected(int x, int y, Shape shape) {
		String name = shape.getClass().getName();
		String[] name2 = name.split("\\.");
		String s = name2[name2.length - 1];
		if (s.equals("Square") || s.equals("Rectangel") || s.equals("Circel") || s.equals("MyEllipse")
				|| s.equals("MyLine")) {
			return isRectangleSelected(x, y, shape);
		} else if (s.equals("Triangle")) {
			return isTriangleSelected(x, y, shape);
		} else if (s.equals("MyLine")) {
			return isLineSelected(x, y, shape);
		}
		return false;
	}

	private boolean isLineSelected(int x, int y, Shape shape) {
		Point p1 = new Point(x, y);
		int x2 = shape.getProperties().get("x").intValue();
		int y2 = shape.getProperties().get("y").intValue();
		Point end = new Point(x2, y2);
		return ((calculateDistance(p1, shape.getPosition())
				+ (calculateDistance(p1, end)) == calculateDistance(shape.getPosition(), end)));

	}

	private double calculateDistance(Point p1, Point p2) {

		return Math.sqrt((Math.pow(p1.x - p2.x, 2)) + (Math.pow(p1.y - p2.y, 2)));

	}

	private boolean isRectangleSelected(int x, int y, Shape shape) {
		int x1 = shape.getPosition().x;
		int y1 = shape.getPosition().y;
		int x2 = shape.getProperties().get("x").intValue();
		int y2 = shape.getProperties().get("y").intValue();
		if (x < x1 && x < x2) {
			return false;
		}
		if (x > x1 && x > x2) {
			return false;
		}
		if (y > y1 && y > y2) {
			return false;
		}
		if (y < y1 && y < y2) {
			return false;
		}
		return true;
	}

	private boolean isTriangleSelected(int x, int y, Shape shape) {
		int x1 = shape.getProperties().get("x1").intValue();
		int y1 = shape.getProperties().get("y1").intValue();
		int x2 = shape.getProperties().get("x2").intValue();
		int y2 = shape.getProperties().get("y2").intValue();
		int x3 = shape.getProperties().get("x3").intValue();
		int y3 = shape.getProperties().get("y3").intValue();
		int minX = Math.min(x1, x2);
		minX = Math.min(x3, minX);
		int maxX = Math.max(x1, x2);
		maxX = Math.max(x3, maxX);
		int minY = Math.min(y1, y2);
		minY = Math.min(y3, minY);
		int maxY = Math.max(y1, y2);
		maxY = Math.max(y3, maxY);
		if (x >= minX && x <= maxX && y >= minY && y <= maxX) {
			return true;
		}
		return false;
	}

}
