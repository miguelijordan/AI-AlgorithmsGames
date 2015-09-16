package juegos.go.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import juegos.conectak.gui.TableroCK;

public class CasillaGo extends JPanel {

	// CONSTANTES
	private static final int ANCHURA = 50;
	private static final int ALTURA = 50;
	enum Linea {VERTICAL, HORIZONTAL, VERTICAL_SUP, VERTICAL_INF, HORIZONTAL_IZQ, HORIZONTAL_DER};
	private boolean vacia;
	
	// ATRIBUTOS
	private int fila;		// Fila de la casilla.
	private int columna;	// Columna de la casilla.
	private Color color;	// Color de la casilla.
	private Linea x;		// Línea de la casilla para la componente x.
	private Linea y;		// Línea de la casilla para la componente y.
	
	/**
	 * Constructor.
	 * 
	 * @param fil	Fila de la casilla.
	 * @param col	Columna de la casilla.
	 * @param c		Color de la casilla.
	 */
	public CasillaGo(int fil, int col, Color c, Linea x, Linea y) {
		this.fila = fil;
		this.columna = col;
		this.color = c;
		this.x = x;
		this.y = y;
		vacia = true;
		
		// Ponemos el color del fondo.
		setBackground(TableroGo.COLOR_FONDO);
		// Ponemos el color del componente.
		setForeground(color);
		// Establecemos el tamaño preferido.
		setPreferredSize(new Dimension(ANCHURA, ALTURA));
	}
	
	/**
	 * Registra el controlador de la casilla.
	 * 
	 * @param ctr	Controlador.
	 */
	public void controlador(MouseListener ctr) {
		addMouseListener(ctr);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (vacia) {
			setForeground(TableroGo.COLOR_LINEAS);
			dibujarLinea(g, x);
			dibujarLinea(g, y);
		} else {
			Dimension d = getSize();
			g.fillOval(0, 0, d.width, d.height);
		}
	}
	
	private void dibujarLinea(Graphics g, Linea l) {
		Dimension d = this.getSize();
		int anchura = d.width;
		int altura = d.height;
		switch (l) {
		case VERTICAL: 
			g.drawLine(anchura/2, 0, anchura/2, altura);		// línea vertical.
			break;
		case HORIZONTAL:
			g.drawLine(0, altura/2, anchura, altura/2);			// línea horizontal.
			break;
		case VERTICAL_SUP:
			g.drawLine(anchura/2, 0, anchura/2, altura/2);		// línea vertical superior.
			break;
		case VERTICAL_INF:
			g.drawLine(anchura/2, altura/2, anchura/2, altura);	// línea vertical inferior.
			break;
		case HORIZONTAL_IZQ:
			g.drawLine(0, altura/2, anchura/2, altura/2);		// línea horizontal izquierda.
			break;
		case HORIZONTAL_DER:
			g.drawLine(anchura/2, altura/2, anchura, altura/2);	// línea horizontal derecha.
			break;
		}
	}
	
	/**
	 * Pone una ficha en la casilla.
	 * 
	 * @param c	Color de la ficha.
	 */
	public void ponerFicha(Color c) {
		vacia = false;
		this.color = c;
		setForeground(color);
		update(getGraphics());
	}
	
	public void quitarFicha() {
		vacia = true;
		setForeground(TableroGo.COLOR_LINEAS);
		update(getGraphics());
	}

	/**
	 * @return Fila de la casilla.
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * @return Columna de la casilla.
	 */
	public int getColumna() {
		return columna;
	}
	
	public boolean libre() {
		return vacia;
	}
}
