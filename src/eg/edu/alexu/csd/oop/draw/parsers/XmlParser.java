package eg.edu.alexu.csd.oop.draw.parsers;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class XmlParser {
	private String xmlObject;
	private LinkedList<Shape> shapesList;

	public XmlParser(LinkedList<Shape> shapeList) {
		// TODO Auto-generated constructor stub
		this.shapesList = shapeList;
	}

	public LinkedList<Shape> getShapesList() {
		return shapesList;
	}

	public void setShapesList(LinkedList<Shape> shapesList) {
		this.shapesList = shapesList;
	}

	public String getXmlObject() {
		return xmlObject;
	}

	public void setXmlObject(String xmlObject) {
		this.xmlObject = xmlObject;
	}

	/*
	 * public String shapeToXml(Shape shape) {
	 *
	 * StringBuilder xS = new StringBuilder(); Map<String, Double> shapeMap =
	 * shape.getProperties(); xS.append("<shape properties>"); xS.append("\n");
	 * xS.append("<x> " + shapeMap.get("x") + " </x>"); xS.append("\n");
	 * xS.append("<y> " + shapeMap.get("y") + " <y>"); xS.append("\n");
	 * xS.append("</shape properties>"); xS.append("\n"); xS.append("<position>");
	 * xS.append("\n"); xS.append("<point.x> " + shape.getPosition().x +
	 * " </point.x>"); xS.append("\n"); xS.append("<point.y> " +
	 * shape.getPosition().y + " </point.y>"); xS.append("\n");
	 * xS.append("</position>"); xS.append("\n"); xS.append("<Color border> " +
	 * shape.getColor() + "</Color border>"); xS.append("\n");
	 * xS.append("<Color fill> " + shape.getFillColor() + "</Color fill>"); return
	 * xS.toString();
	 *
	 * }
	 */

	public String allshapeToXml(Shape shape) {

		StringBuilder xS = new StringBuilder();
		Point p = shape.getPosition();
		Map<String, Double> shapeMap = shape.getProperties();
		String[] s=null;
		if(shapeMap==null)
		{
		  s=null;
		}
		else
		{
		  s = shapeMap.keySet().toArray(new String[shapeMap.keySet().size()]);
		  xS.append("<shape_properties>");
			xS.append("\n");
			for (int i = 0; i < s.length; i++) {
				xS.append("<" + s[i] + ">  " + shapeMap.get(s[i]) + " </" + s[i] + ">");
				xS.append("\n");
			}
			xS.append("</shape_properties>");
			xS.append("\n");
		}

		xS.append("<position>");
		xS.append("\n");
		if(p==null)
		{
			xS.append("<point.x> " + null + " </point.x>");
			xS.append("\n");
			xS.append("<point.y> " + null + " </point.y>");
			xS.append("\n");
		}
		else
		{
		xS.append("<point.x> " + p.x + " </point.x>");
		xS.append("\n");
		xS.append("<point.y> " + p.y + " </point.y>");
		xS.append("\n");
		}
		xS.append("</position>");
		xS.append("\n");
		xS.append("<Color_border> " +shape.getColor() + "</Color_border>");
		xS.append("\n");
		xS.append("<Color_fill> " + shape.getFillColor() + "</Color_fill>");
		return xS.toString();

	}

	private void allShapesToxml(LinkedList<Shape> shapes) {
		// TODO Auto-generated method stub
		StringBuilder xS = new StringBuilder();
		xS.append("<shapes>");
		xS.append("\n");
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			String classname = shape.getClass().getName();
			// String[] type = classname.split(".");
			// String s = type[type.length - 1];
			xS.append("<Shape>");
			xS.append("\n");
			xS.append("<Class_name>");
			xS.append("\n");
			xS.append(classname);
			xS.append("\n");
			xS.append("</Class_name>");
			xS.append("\n");
			xS.append(allshapeToXml(shape));
			xS.append("\n");
			xS.append("</Shape>");
			xS.append("\n");
		}
		xS.append("</shapes>");
		setXmlObject(xS.toString());
	}

	public void SaveToXml(String path) {
		File f = new File(path);
		allShapesToxml(shapesList);
		// File file = new File(path);
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(f));
			String[] lines = getXmlObject().split("\\r?\\n");
			for (String line : lines) {
				writer.write(line);
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
