package juegos.go.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FichaGo extends JPanel {

	// CONSTANTES
	private static final int ANCHURA = 25;
	private static final int ALTURA = 25;
		
	private Color color;	// Color de la ficha.
	
	
	public FichaGo() {
		this.color = null;
		
		// Ponemos el color del fondo.
		setBackground(TableroGo.COLOR_FONDO);
		// Ponemos el color del componente.
		setForeground(color);
		// Establecemos el tamaño preferido.
		setPreferredSize(new Dimension(ANCHURA, ALTURA));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setForeground(color);
		Dimension d = getSize();
		g.fillOval(0, 0, d.width, d.height);
	}

	public void setColor(Color color) {
		this.color = color;
		setForeground(color);
	}
}
