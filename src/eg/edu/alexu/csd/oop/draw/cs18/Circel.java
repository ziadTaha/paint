package eg.edu.alexu.csd.oop.draw.cs18;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Circel extends MyShape {
	public Circel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		int xend = getProperties().get("x").intValue();
		int yend = getProperties().get("y").intValue();
		int xstart = getPosition().x;
		int ystart = getPosition().y;
		int minX = Math.min(xend, xstart);
		int minY = Math.min(yend, ystart);
		int maxX = Math.max(xend, xstart);
		int maxY = Math.max(yend, ystart);
		int size = Math.min(maxX - minX, maxY - minY);
		if (minX < xstart) {
			minX = xstart - size;
		}
		if (minY < ystart) {
			minY = ystart - size;
		}

		canvas.setColor(getFillColor());
		canvas.fillOval(minX, minY, size, size);
		canvas.setColor(getColor());
		canvas.drawOval(minX, minY, size, size);
		getPosition().x = minX;
		getPosition().y = minY;
		getProperties().put("x", (double) (minX + size));
		getProperties().put("y", (double) (minY + size));

	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Circel e = new Circel();
		e.setProperties((Map<String, Double>) ((HashMap<String, Double>)this.getProperties()).clone());
		e.setPosition((Point) this.getPosition().clone());
		e.setColor(this.getColor());
		e.setFillColor(this.getFillColor());
		return e;
	}

}
