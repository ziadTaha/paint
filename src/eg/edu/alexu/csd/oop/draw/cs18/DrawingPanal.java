package eg.edu.alexu.csd.oop.draw.cs18;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.nio.channels.NetworkChannel;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import eg.edu.alexu.csd.oop.draw.Shape;

public class DrawingPanal extends JPanel {

	private static final long serialVersionUID = 1L;
	private Shape shape;
	private Shape SelectedShape;
	private MyDrawingEngine engine = new MyDrawingEngine();
	private boolean triangel = false;
	private boolean draged = false;
	private boolean move = true;
	private boolean selected;

	public DrawingPanal() {
		// TODO Auto-generated constructor stub
		MouseInputAdapter mA = new MyMouseAdapter();
		addMouseListener(mA);
		addMouseMotionListener(mA);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

		super.paintComponent(g);
		Shape[] all_shapes = engine.getShapes();
		g.setColor(new Color(0, 0, 0));
		for (int i = 0; i < all_shapes.length; i++) {
			g.setColor(new Color(0, 0, 0));
			all_shapes[i].draw(g);
		}
		if (shape != null) {
			shape.draw(g);
		}
	}

	public void setShape(Shape shapetype) {
		this.shape = shapetype;
	}

	public Shape getShape() {
		return shape;
	}

	public MyDrawingEngine getEngine() {
		return engine;
	}

	public void setEngine(MyDrawingEngine s) {
		engine = s;
	}

	public void setTriangel(boolean triangel) {
		this.triangel = triangel;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelecte(boolean selected) {
		this.selected = selected;
	}

	public void setMove(boolean move) {
		this.move = move;
	}
	public Shape getSelectedshape () {
		return SelectedShape;
	}

	class MyMouseAdapter extends MouseInputAdapter {
		private Point clickPoint;
		private int clicks = 0;

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				if (shape != null && triangel && !selected) {
					clicks++;
					if (clicks == 1) {
						shape.getProperties().put("x1", (double) e.getX());
						shape.getProperties().put("y1", (double) e.getY());
						shape.getProperties().put("clicked", (double) clicks);
					} else if (clicks == 2) {
						shape.getProperties().put("x2", (double) e.getX());
						shape.getProperties().put("y2", (double) e.getY());
						shape.getProperties().put("clicked", (double) clicks);
						repaint();
					} else if (clicks == 3) {
						shape.getProperties().put("x3", (double) e.getX());
						shape.getProperties().put("y3", (double) e.getY());
						shape.getProperties().put("clicked", (double) clicks);
						try {
							engine.addShape((Shape) shape.clone());
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						repaint();
						clickPoint = null;
						clicks = 0;
					}
				}
			} else if (e.getClickCount() == 2) {
				Selection s = new Selection();
				Shape[] all_shapes = engine.getShapes();
				for (int i = all_shapes.length - 1; i >= 0; i--) {
					boolean sel = s.isSelected(e.getX(), e.getY(), all_shapes[i]);
					if (sel) {
						SelectedShape = all_shapes[i];
						try {
							shape = (Shape) SelectedShape.clone();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						selected = true;
						break;
					}
				}
			}

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (clicks >= 1 && triangel && !selected && shape != null) {
				Point p = new Point();
				p = new Point(e.getPoint());
				shape.getProperties().put("Xmove", p.getX());
				shape.getProperties().put("Ymove", p.getY());
				repaint();
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			clickPoint = new Point(e.getPoint());
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			if (shape != null && !triangel && !selected) {
				draged = true;
				Map<String, Double> pro = new HashMap<String, Double>();
				pro.put("x", (double) e.getX());
				pro.put("y", (double) e.getY());
				Point start = new Point(clickPoint);
				shape.setPosition(start);
				shape.setProperties(pro);
				repaint();
			} else if (selected && shape != null) {
				if (move) {
					Move m = new Move();
					m.ShapeChange(shape, clickPoint, e.getPoint());
					clickPoint = e.getPoint();
				} else {
					ReSize m = new ReSize();
					m.ShapeChange(shape, clickPoint, e.getPoint());
					clickPoint = e.getPoint();
				}
				repaint();

			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if (shape != null && !triangel && draged) {
				try {
					engine.addShape((Shape) shape.clone());
					draged = false;
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
			} else if (selected && shape != null) {
				engine.updateShape(SelectedShape, shape);
				selected = false;
				shape = null;
				SelectedShape = null;
				repaint();
			}
		}

	}

}
