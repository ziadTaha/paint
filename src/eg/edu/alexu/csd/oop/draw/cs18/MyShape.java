package eg.edu.alexu.csd.oop.draw.cs18;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

abstract public class MyShape implements Shape {
	private Point shapePosition;
	private Color shapeColor;
	private Color shapeFillColor;
	private Map<String, Double> ShapeProperties;

	public abstract Object clone() throws CloneNotSupportedException;

	@Override
	public void setPosition(Point position) {
		// TODO Auto-generated method stub
		shapePosition = position;

	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return shapePosition;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		ShapeProperties = properties;
	}

	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return ShapeProperties;
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		shapeColor = color;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return shapeColor;
	}

	@Override
	public void setFillColor(Color color) {
		// TODO Auto-generated method stub
		shapeFillColor = color;

	}

	@Override
	public Color getFillColor() {
		// TODO Auto-generated method stub
		return shapeFillColor;
	}

	abstract public void draw(Graphics canvas);

}
