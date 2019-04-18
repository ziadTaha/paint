package eg.edu.alexu.csd.oop.draw.parsers;
import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import eg.edu.alexu.csd.oop.draw.Shape;

public class JsonParser {
	private String jsonObject;
	private LinkedList<Shape> shapesList;

	public JsonParser(LinkedList<Shape> shapesList) {
		// TODO Auto-generated constructor stub
		this.shapesList = shapesList;
	}

	public String allshapeToJson(Shape shape) {

		StringBuilder js = new StringBuilder();
		Point p = shape.getPosition();
		Map<String, Double> shapeMap = shape.getProperties();
		String[] s= null;
		if(shapeMap==null)
		{
			s=null;
		}
		else
		{
	     s = shapeMap.keySet().toArray(new String[shapeMap.keySet().size()]);
		js.append("\"shape_properties\":{");
		js.append("\n");
		for (int i = 0; i < s.length; i++) {
			if (i == s.length - 1) {
				js.append("\"" + s[i] + "\" :" + shapeMap.get(s[i]) + " },");
				js.append("\n");
			} else {
				js.append("\"" + s[i] + "\" :" + shapeMap.get(s[i]) + " ,");
			}
		}
		// js.append("}");
		js.append("\n");
		}
		js.append("\"position\":{");
		js.append("\n");
		if(p==null)
		{
			js.append("\"point.x\": " +null+ " ,");
			js.append("\n");
			js.append("\"point.y\": " + null);
			js.append("\n");
		}
		else
		{
		js.append("\"point.x\": " + p.x + " ,");
		js.append("\n");
		js.append("\"point.y\": " + p.y);
		js.append("\n");
		}
		js.append("},");
		js.append("\n");
		js.append("\"Color_border\": " + shape.getColor() + ",");
		js.append("\n");
		js.append("\"Color_fill\": " + shape.getFillColor());
		return js.toString();

	}

	public void allShapesToJson(LinkedList<Shape> shapes) {
		StringBuilder js = new StringBuilder();
		js.append("{\"shapes\":{");
		js.append("\n");
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			String classname = shape.getClass().getName();
			// String[] type = classname.split(".");
			// String s = type[type.length - 1];
			js.append("\"Shape" + i + "\":{");
			js.append("\n");
			js.append("\"Class_name\":");
			js.append("\"" + classname + "\"");
			js.append(",");
			js.append("\n");
			js.append(allshapeToJson(shape));
			js.append("\n");
			js.append("}");
			if (i != shapes.size() - 1) {
				js.append(",");
			}
			js.append("\n");
		}
		js.append("}}");

		setJsonObject(js.toString());
	}

	public void SaveToJson(String path) {

		allShapesToJson(shapesList);
		File f = new File(path);
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(f));
			String[] lines = getJsonObject().split("\\r?\\n");
			for (String line : lines) {
				writer.write(line);
				writer.newLine();
			}
			// writer.write(getXmlObject());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(String jsonObject) {
		this.jsonObject = jsonObject;
	}

}
