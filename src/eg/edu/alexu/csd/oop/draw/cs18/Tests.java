package eg.edu.alexu.csd.oop.draw.cs18;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import eg.edu.alexu.csd.oop.draw.Shape;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class Tests extends JFrame {

	private static final long serialVersionUID = 1L;
	private DrawingPanal contentPane;
	private Shape shape;
	private Color fillcolor;
	private Color bordercolor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					Tests frame = new Tests();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.s
	 */
	public Tests() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 947, 603);
		contentPane = new DrawingPanal();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(827, 0, 116, 566);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel colorpanel = new JPanel();
		colorpanel.setBounds(0, 0, 829, 556);
		contentPane.add(colorpanel);
		colorpanel.setLayout(null);
		colorpanel.setVisible(false);

		JButton btnC = new JButton("");
		Image c = new ImageIcon(getClass().getResource("/circle.png")).getImage();
		btnC.setIcon(new ImageIcon(c));
		btnC.setBounds(31, 26, 45, 25);
		panel.add(btnC);

		JButton btnL = new JButton();
		Image l = new ImageIcon(getClass().getResource("/line.png")).getImage();
		btnL.setIcon(new ImageIcon(l));
		btnL.setBounds(31, 99, 45, 25);
		panel.add(btnL);

		JButton btnE = new JButton();
		Image e = new ImageIcon(getClass().getResource("/ellipse.png")).getImage();
		btnE.setIcon(new ImageIcon(e));
		btnE.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shape = new MyEllipse();
				contentPane.setTriangel(false);
				contentPane.setSelecte(false);
				shape.setColor(bordercolor);
				shape.setFillColor(fillcolor);
				contentPane.setShape(shape);
			}
		});
		btnE.setBounds(31, 64, 45, 25);
		panel.add(btnE);

		JButton btnS = new JButton();
		Image s = new ImageIcon(getClass().getResource("/square.png")).getImage();
		btnS.setIcon(new ImageIcon(s));
		btnS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shape = new Square();
				shape.setColor(bordercolor);
				shape.setFillColor(fillcolor);
				contentPane.setTriangel(false);
				contentPane.setSelecte(false);
				contentPane.setShape(shape);
			}
		});
		btnS.setBounds(31, 137, 45, 25);
		panel.add(btnS);

		JButton btnR = new JButton();
		Image r = new ImageIcon(getClass().getResource("/rectangle.png")).getImage();
		btnR.setIcon(new ImageIcon(r));
		btnR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shape = new Rectangel();
				shape.setColor(bordercolor);
				shape.setFillColor(fillcolor);
				contentPane.setTriangel(false);
				contentPane.setSelecte(false);
				contentPane.setShape(shape);
			}
		});
		btnR.setBounds(31, 175, 45, 25);
		panel.add(btnR);

		JButton btnT = new JButton();
		Image t = new ImageIcon(getClass().getResource("/triangle.png")).getImage();
		btnT.setIcon(new ImageIcon(t));
		btnT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				shape = new Triangle();
				contentPane.setTriangel(true);
				contentPane.setSelecte(false);
				shape.setColor(bordercolor);
				shape.setFillColor(fillcolor);
				contentPane.setShape(shape);
			}
		});
		btnT.setBounds(31, 209, 45, 25);
		panel.add(btnT);

		JButton btnNewButton_1 = new JButton("setcolor");
		btnNewButton_1.setBounds(12, 436, 85, 25);
		panel.add(btnNewButton_1);

		JButton btnundo = new JButton("undo");
		btnundo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contentPane.getEngine().undo();
				contentPane.setShape(null);
				repaint();
			}
		});
		btnundo.setBounds(12, 394, 85, 25);
		panel.add(btnundo);

		JButton btnRedo = new JButton("redo");
		btnRedo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contentPane.getEngine().redo();
				contentPane.setShape(null);
				repaint();
			}
		});
		btnRedo.setBounds(12, 353, 85, 25);
		panel.add(btnRedo);

		JButton refresh = new JButton("refresh");
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contentPane.getEngine().refresh(getGraphics());
				repaint();
			}
		});
		refresh.setBounds(12, 316, 85, 25);
		panel.add(refresh);

		JButton btnSave = new JButton("save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					contentPane.getEngine().save(fileChooser.getSelectedFile().getPath());
				}
			}
		});
		btnSave.setBounds(12, 474, 85, 25);
		panel.add(btnSave);

		JButton btnload = new JButton("load");
		btnload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setDialogTitle("Save your shapes");
				int result = filechooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					contentPane.setEngine(new MyDrawingEngine());
					contentPane.setShape(null);
					contentPane.getEngine().load(filechooser.getSelectedFile().getPath());
					repaint();

				}

			}
		});
		btnload.setBounds(12, 512, 85, 25);
		panel.add(btnload);

		JButton btnM = new JButton("resize");
		btnM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnM.getText().equals("move")) {
					contentPane.setMove(true);
					btnM.setText("resize");
				} else {
					contentPane.setMove(false);
					btnM.setText("move");
				}
			}
		});
		btnM.setBounds(12, 285, 85, 25);
		panel.add(btnM);

		JButton btnRemove = new JButton("remove");
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (contentPane.isSelected()) {
					contentPane.getEngine().removeShape(contentPane.getSelectedshape());
					contentPane.setShape(null);
					contentPane.setSelecte(false);
					repaint();
				} else {

				}

			}
		});
		btnRemove.setBounds(12, 247, 85, 25);
		panel.add(btnRemove);
		btnL.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Shape shape = new MyLine();
				shape.setColor(bordercolor);
				shape.setFillColor(fillcolor);
				contentPane.setSelecte(false);
				contentPane.setTriangel(false);
				contentPane.setShape(shape);
			}
		});

		btnC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				shape = new Circel();
				shape.setColor(bordercolor);
				shape.setFillColor(fillcolor);
				contentPane.setTriangel(false);
				contentPane.setSelecte(false);
				contentPane.setShape(shape);
			}
		});

		JColorChooser choose = new JColorChooser();
		choose.setBounds(28, 33, 648, 409);
		AbstractColorChooserPanel[] panels = choose.getChooserPanels();
		for (AbstractColorChooserPanel accp : panels) {
			if (!accp.getDisplayName().equals("Swatches")) {
				choose.removeChooserPanel(accp);
			}
		}
		colorpanel.add(choose);
		choose.setPreviewPanel(new JPanel());

		JButton btnSetFillColor = new JButton("set fill color");
		btnSetFillColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				fillcolor = choose.getColor();
				if (shape != null) {
					shape.setFillColor(choose.getColor());
				}
				if (contentPane.isSelected()) {
					contentPane.getShape().setFillColor(fillcolor);
				}

			}
		});
		btnSetFillColor.setBounds(430, 487, 126, 32);
		colorpanel.add(btnSetFillColor);

		JButton btnSetBorderColor = new JButton("set border color");
		btnSetBorderColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSetFillColor.setEnabled(true);
				btnSetBorderColor.setEnabled(false);
				bordercolor = choose.getColor();
				if (shape != null) {
					shape.setColor(choose.getColor());
				}

			}
		});
		btnSetBorderColor.setBounds(568, 487, 126, 32);
		colorpanel.add(btnSetBorderColor);

		JButton btnNewButton = new JButton("ok");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				colorpanel.setVisible(false);
				btnT.setEnabled(true);
				btnC.setEnabled(true);
				btnS.setEnabled(true);
				btnR.setEnabled(true);
				btnL.setEnabled(true);
				btnE.setEnabled(true);
				btnRemove.setEnabled(true);
				btnRedo.setEnabled(true);
				btnSave.setEnabled(true);
				btnundo.setEnabled(true);
				btnload.setEnabled(true);
				btnM.setEnabled(true);
				refresh.setEnabled(true);
				btnSetFillColor.setEnabled(true);
				btnSetBorderColor.setEnabled(true);

				if (contentPane.isSelected()) {
					try {
						Shape shape = (Shape) contentPane.getShape().clone();
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					contentPane.getShape().setColor(bordercolor);
					contentPane.getShape().setFillColor(fillcolor);
					contentPane.getEngine().updateShape(contentPane.getSelectedshape(), contentPane.getShape());
					contentPane.setShape(null);
					contentPane.setSelecte(false);
					repaint();
				} else {
					contentPane.setShape(shape);
				}

				repaint();
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				colorpanel.setVisible(true);
				btnT.setEnabled(false);
				btnC.setEnabled(false);
				btnS.setEnabled(false);
				btnR.setEnabled(false);
				btnL.setEnabled(false);
				btnE.setEnabled(false);
				btnRemove.setEnabled(false);
				btnRedo.setEnabled(false);
				btnSave.setEnabled(false);
				btnundo.setEnabled(false);
				btnload.setEnabled(false);
				btnM.setEnabled(false);
				refresh.setEnabled(false);
				btnSetFillColor.setEnabled(false);
			}
		});

		btnNewButton.setBounds(706, 487, 60, 32);
		colorpanel.add(btnNewButton);
	}
}
