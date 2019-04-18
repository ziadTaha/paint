package eg.edu.alexu.csd.oop.draw.cs18;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class testShape {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         DrawingEngine d = new MyDrawingEngine();
         Shape s1 = new MyLine();
         Shape s2 = new MyEllipse();
         Shape s3 = new Rectangel();
         Shape s4 = new Circel();
         d.addShape(s1);
         d.addShape(s2);
         d.addShape(s3);
         d.addShape(s4);
         d.removeShape(s3);
         for(int i=0;i<d.getShapes().length;i++)
         {
        	System.out.println( d.getShapes()[i].getClass().getName());
         }


	}

}
