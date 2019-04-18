package eg.edu.alexu.csd.oop.draw.parsers;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.draw.Shape;

public class JsonLoader {
	public LinkedList<Shape> LoadJsonShape(File file) {
		Shape shape = null;
		// int count =0;
		LinkedList<Shape> shapes = new LinkedList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.equals("{\"shapes\":{")) {

					// count =2;
					line = reader.readLine();
				}
				while (!line.equals("}}")) {

					if (line.equals("},")) {
						line = reader.readLine();
					}

					if (line.contains("\"Shape")) {
						// count++;
						// System.out.println(line);
						line = reader.readLine();
						if (shape != null) {
							Shape clone = (Shape) shape.clone();
							shapes.add(clone);
						}
					}
					if (line.contains("\"Class_name\":")) {

						String regex = "\"Class_name\":\"([\\w.]+)\",";
						Pattern p = Pattern.compile(regex);
						Matcher m = p.matcher(line);
						if (m.find()) {
							Class<?> c = Class.forName(m.group(1));
							shape = (Shape) c.newInstance();
							line = reader.readLine();
						}
					}
					if (line.equals("\"position\":{")) {
						line = reader.readLine();
						Point po = new Point(0, 0);
						while (!line.contains("}")) {
							String regex = "\"([\\w.]+)\": ([\\d.]+),?";
							Pattern p = Pattern.compile(regex);
							Matcher m = p.matcher(line);
							if (m.find()) {

								if (m.group(1).equals("point.x")) {
									po.x = Integer.parseInt(m.group(2));
								}
							}
							line = reader.readLine();
							System.out.println(line);
							m = p.matcher(line);
							if (m.find()) {

								if (m.group(1).equals("point.y")) {
									po.y = Integer.parseInt(m.group(2));

								}

							}

							line = reader.readLine();
						}
						line = reader.readLine();
						System.out.println(po);
						shape.setPosition(po);

					}
					if (line.contains("\"Color_border\"")) {
						String regex = "\"Color_border\":\\sjava\\.awt\\.Color\\[r=(\\d+),g=(\\d+),b=(\\d+)\\],";
						Pattern p = Pattern.compile(regex);
						Matcher m = p.matcher(line);
						if (m.find()) {
							int r = Integer.parseInt(m.group(1));
							int g = Integer.parseInt(m.group(2));
							int b = Integer.parseInt(m.group(3));
							shape.setColor(new Color(r, g, b));
						} else {
							shape.setColor(null);
						}
						line = reader.readLine();

					}
					if (line.contains("\"Color_fill\"")) {
						String regex = "\"Color_fill\":\\sjava\\.awt\\.Color\\[r=(\\d+),g=(\\d+),b=(\\d+)\\]";
						Pattern p = Pattern.compile(regex);
						Matcher m = p.matcher(line);
						if (m.find()) {
							int r = Integer.parseInt(m.group(1));
							int g = Integer.parseInt(m.group(2));
							int b = Integer.parseInt(m.group(3));
							shape.setFillColor(new Color(r, g, b));
						} else {
							shape.setFillColor(null);
						}
						line = reader.readLine();

					}
					if (line.equals("\"shape_properties\":{")) {
						line = reader.readLine();
						Map<String, Double> map = new HashMap<>();
						String regex = "(\"([\\w]+)\" :([\\d.]+),?)+";
						Pattern p = Pattern.compile(regex);
						Matcher m = p.matcher(line);
						while (m.find()) {
							map.put(m.group(2), Double.parseDouble(m.group(3)));

						}
						shape.setProperties(map);
						line = reader.readLine();
						line = reader.readLine();
					}
					if (line.equals("}")) {
						line = reader.readLine();
					}
				}
				if (shape != null) {
					Shape clone = (Shape) shape.clone();
					shapes.add(clone);
				}

				line = reader.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shapes;
	}

}
