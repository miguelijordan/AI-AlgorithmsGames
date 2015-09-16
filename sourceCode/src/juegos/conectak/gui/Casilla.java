package juegos.conectak.gui;

import java.awt.*;
import java.awt.event.MouseListener;

import javax.swing.*;

/**
 * Clase Casilla.
 * Representa una casilla de un tablero.
 * 
 * @author José Miguel Horcas Aguilera
 *
 */
@SuppressWarnings("serial")
public class Casilla extends JPanel {

	// CONSTANTES
	private static final int ANCHURA = 50;
	private static final int ALTURA = 50;
	public static final Color COLOR_VACIA = Color.GRAY;
	
	// ATRIBUTOS
	private int fila;		// Fila de la casilla.
	private int columna;	// Columna de la casilla.
	private Color color;	// Color de la casilla.
	
	/**
	 * Constructor.
	 * 
	 * @param fil	Fila de la casilla.
	 * @param col	Columna de la casilla.
	 * @param c		Color de la casilla.
	 */
	public Casilla(int fil, int col) {
		this.fila = fil;
		this.columna = col;
		this.color = COLOR_VACIA;
		
		// Ponemos el color del fondo.
		setBackground(Color.BLACK);
		// Ponemos el color del componente.
		setForeground(COLOR_VACIA);
		// Establecemos el tamaño preferido.
		setPreferredSize(new Dimension(ANCHURA,ALTURA));
		//this.setMinimumSize(new Dimension(10,10));
		//this.setMaximumSize(new Dimension(ANCHURA,ALTURA));
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
		Dimension d = this.getSize();
		g.fillOval(0, 0, d.width, d.height);
		//g.fillOval(0, 0, ANCHURA, ALTURA);
	}
	
	/**
	 * Cambia el color de la casilla.
	 * 
	 * @param c	Nuevo color de la casilla.
	 */
	public void cambiaColor(Color c) {
		this.color = c;
		setForeground(color);
		//validate();
		//repaint();
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
	
	/**
	 * Comprueba si la casilla está libre u ocupada.
	 * 
	 * @return	Verdadero si la casilla está libre, falso si está ocupada.
	 */
	public boolean libre() {
		return color.equals(COLOR_VACIA);
	}
}
