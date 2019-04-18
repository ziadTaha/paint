package eg.edu.alexu.csd.oop.draw.cs18;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.memento.CareTaker;
import eg.edu.alexu.csd.oop.draw.memento.Originator;
import eg.edu.alexu.csd.oop.draw.parsers.JsonLoader;
import eg.edu.alexu.csd.oop.draw.parsers.JsonParser;
import eg.edu.alexu.csd.oop.draw.parsers.XmlLoader;
import eg.edu.alexu.csd.oop.draw.parsers.XmlParser;
import javafx.geometry.Orientation;

public class MyDrawingEngine implements DrawingEngine {

	private LinkedList<Shape> shapesState;
	private int pointer;
	private int moves;
	private Originator originator;
	private CareTaker careTaker;
	private List<Class<? extends Shape>> shapesList;

	public MyDrawingEngine() {
		// TODO Auto-generated constructor stub
		shapesState = new LinkedList<>();
		originator = new Originator();
		careTaker = new CareTaker();
		shapesState = new LinkedList<>();
		LinkedList<Shape> states = (LinkedList<Shape>) shapesState.clone();
		originator.setState(states);
		originator.setLimit(0);
		careTaker.add(originator.saveStateToMemento());
		pointer = 0;
		moves = 0;
		getSupportedShapes();
		installPluginShape("RoundRectangle.jar");
	}

	@Override
	public void refresh(Graphics canvas) {
		// TODO Auto-generated method stub
		for (int i = 0; i < shapesState.size(); i++) {
			Shape shape = shapesState.get(i);
			shape.draw(canvas);
		}

	}

	@Override
	public void addShape(Shape shape) {
		// TODO Auto-generated method stub
		shapesState.add(shape);
		LinkedList<Shape> states = (LinkedList<Shape>) shapesState.clone();
		originator.setState(states);
		final int state = moves;
		careTaker.removeFrom(pointer + 1);
		originator.setLimit(state);
		careTaker.add(originator.saveStateToMemento());
		pointer++;
		moves = 0;

	}

	@Override
	public void removeShape(Shape shape) {
		// TODO Auto-generated method stub
		shapesState.remove(shape);
		LinkedList<Shape> states = (LinkedList<Shape>) shapesState.clone();
		originator.setState(states);
		final int state = moves;
		careTaker.removeFrom(pointer + 1);
		originator.setLimit(state);
		careTaker.add(originator.saveStateToMemento());
		pointer++;
		moves = 0;
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub
		shapesState.set(shapesState.indexOf(oldShape), newShape);
		LinkedList<Shape> states = (LinkedList<Shape>) shapesState.clone();
		originator.setState(states);
		final int state = moves;
		careTaker.removeFrom(pointer + 1);
		originator.setLimit(state);
		careTaker.add(originator.saveStateToMemento());
		pointer++;
		moves = 0;

	}

	@Override
	public Shape[] getShapes() {
		// TODO Auto-generated method stub
		Shape[] shapesArray = new Shape[shapesState.size()];
		for (int i = 0; i < shapesState.size(); i++) {
			shapesArray[i] = shapesState.get(i);
		}
		return shapesArray;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		File file = new File("shapes.txt");
		File jarsFile = new File("shapesJars.txt");
		try {
			List<Class<? extends Shape>> shapes = new LinkedList<>();
			if (!file.exists()) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				writer.write("eg.edu.alexu.csd.oop.draw.cs18.MyLine");
				writer.newLine();
				writer.write("eg.edu.alexu.csd.oop.draw.cs18.MyEllipse");
				writer.newLine();

				writer.write("eg.edu.alexu.csd.oop.draw.cs18.Rectangel");
				writer.newLine();
				writer.write("eg.edu.alexu.csd.oop.draw.cs18.Triangle");
				writer.newLine();

				writer.write("eg.edu.alexu.csd.oop.draw.cs18.Circel");
				writer.newLine();
				writer.write("eg.edu.alexu.csd.oop.draw.cs18.Square");
				writer.newLine();

				writer.close();
				Class line = Class.forName("eg.edu.alexu.csd.oop.draw.cs18.MyLine");
				shapes.add(line);
				Class ellipse = Class.forName("eg.edu.alexu.csd.oop.draw.cs18.MyEllipse");
				shapes.add(ellipse);

				Class rectangle = Class.forName("eg.edu.alexu.csd.oop.draw.cs18.Rectangel" + "");
				shapes.add(rectangle);
				Class triangle = Class.forName("eg.edu.alexu.csd.oop.draw.cs18.Triangle");
				shapes.add(triangle);

				Class circle = Class.forName("eg.edu.alexu.csd.oop.draw.cs18.Circel");
				shapes.add(circle);
				Class square = Class.forName("eg.edu.alexu.csd.oop.draw.cs18.Square");
				shapes.add(square);

				setShapesList(shapes);

				return shapes;
			}
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = br.readLine()) != null) {
					shapes.add((Class) Class.forName(line));
				}

				br.close();

			}

			if (jarsFile.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(jarsFile));
				String line;
				while ((line = br.readLine()) != null) {
					shapes.add(getClassFromJar(line));
				}
				br.close();
				setShapesList(shapes);
			}
			return shapes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		if (moves < 20 && pointer > 0) {
			pointer--;
			if (moves == 0) {

				moves = careTaker.get(pointer + 1).getLimit();

			}
			LinkedList<Shape> states = (LinkedList<Shape>) careTaker.get(pointer).getState().clone();
			setShapesState(states);
			if (moves < 20) {
				moves++;
			}
		}
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		if (moves > 0) {
			pointer++;
			LinkedList<Shape> states = (LinkedList<Shape>) careTaker.get(pointer).getState().clone();
			setShapesState(states);
			moves--;
		}
	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub
		// String[] s = path.split(".");
		String extension = "";

		int i = path.lastIndexOf('.');
		if (i > 0) {
			extension = path.substring(i + 1);
		}
		if (extension.toUpperCase().equals("XML")) {
			XmlParser parser = new XmlParser(shapesState);
			parser.SaveToXml(path);
		} else if (extension.toUpperCase().equals("JSON")) {
			JsonParser parser = new JsonParser(shapesState);
			parser.SaveToJson(path);
		}

	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub
		// String[] s = path.split("//.");
		String extension = "";
		int i = path.lastIndexOf('.');
		if (i > 0) {
			extension = path.substring(i + 1);
		}
		/*if (extension.toUpperCase().equals("XML") || extension.toUpperCase().equals("JSON")) {
			shapesState = new LinkedList<>();
			originator = new Originator();
			careTaker = new CareTaker();
			shapesState = new LinkedList<>();
			pointer = 0;
			moves = 0;
		}*/
		if (extension.toUpperCase().equals("XML")) {
			XmlLoader loader = new XmlLoader();
			File file = new File(path);
			setShapesState(loader.LoadXmlShape(file));
			LinkedList<Shape> states = (LinkedList<Shape>) shapesState.clone();
			originator.setState(states);
			originator.setLimit(0);
			careTaker.add(originator.saveStateToMemento());
		} else if (extension.toUpperCase().equals("JSON")) {
			JsonLoader loader = new JsonLoader();
			File file = new File(path);
			setShapesState(loader.LoadJsonShape(file));
			LinkedList<Shape> states = (LinkedList<Shape>) shapesState.clone();
			originator.setState(states);
			originator.setLimit(0);
			careTaker.add(originator.saveStateToMemento());
		}
	}

	public LinkedList<Shape> getShapesState() {
		return shapesState;
	}

	public void setShapesState(LinkedList<Shape> shapesState) {
		this.shapesState = shapesState;
	}

	@Override
	public void installPluginShape(String jarPath) {
		// TODO Auto-generated method stub
		try {
			List<Class<? extends Shape>> shapes = getSupportedShapes();
			JarFile jarFile = new JarFile(jarPath);
			Enumeration<JarEntry> e = jarFile.entries();

			URL[] urls = { new URL("jar:file:" + jarPath + "!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);

			while (e.hasMoreElements()) {
				JarEntry je = e.nextElement();
				if (je.isDirectory() || !je.getName().endsWith(".class")) {
					continue;
				}
				// -6 because of .class
				String className = je.getName().substring(0, je.getName().length() - 6);
				className = className.replace('/', '.');
				Class c = cl.loadClass(className);
				writeJarClass(jarPath);
				getSupportedShapes();

			}
			// getSupportedShapes();

		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Class<? extends Shape>> getShapesList() {
		return shapesList;
	}

	public void setShapesList(List<Class<? extends Shape>> shapesList) {
		this.shapesList = shapesList;
	}

	/**
	 *
	 * @param path
	 * @return class from jar file to use while loading
	 *
	 */
	public Class getClassFromJar(String path) {
		try {
			JarFile jarFile = new JarFile(path);
			Enumeration<JarEntry> e = jarFile.entries();

			URL[] urls = { new URL("jar:file:" + path + "!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);

			while (e.hasMoreElements()) {
				JarEntry je = e.nextElement();
				if (je.isDirectory() || !je.getName().endsWith(".class")) {
					continue;
				}
				// -6 because of .class
				String className = je.getName().substring(0, je.getName().length() - 6);
				className = className.replace('/', '.');
				Class c = cl.loadClass(className);
				return c;

			}

		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * write the jar path
	 *
	 * @param path
	 */
	public void writeJarClass(String path) {
		File file = new File("shapesJars.txt");
		LinkedList<String> jars = new LinkedList<>();
		try {
			if (file.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine()) != null) {
					jars.add(line);
				}
				br.close();
			}
			if (!jars.contains(path)) {
				jars.add(path);
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < jars.size(); i++) {
				bw.write(jars.get(i));
				bw.newLine();
			}
			bw.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
