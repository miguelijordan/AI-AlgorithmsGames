package juegos.conectak.gui;

import entornointeractivo.gui.interfaces.ControladorJuego;
import entornointeractivo.gui.interfaces.InformacionAyuda;
import entornointeractivo.gui.interfaces.PanelJuego;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import juegos.EstadoJuego;
import juegos.conectak.estrategias.agentes.JugadorHumanoCKEstadisticas;
import juegos.conectak.juego.ConectaK;
import juegos.util.Ficha;


public class TableroCK extends PanelJuego implements InformacionAyuda {
	
	// CONSTANTES
	public static final Color colorJ1 = Color.BLUE;
	public static final Color colorJ2 = Color.RED;
	public static final String sColorJ1 = "azules";
	public static final String sColorJ2 = "rojas";
		
		
	private Casilla[][] casillas;		// Casillas del tablero.
	private String mensajeActual;
	private ControladorJuego controlador;
	
	public TableroCK(int nFilas, int nColumnas) {
		// Asignamos un gestor de esquemas para las casillas.
		setLayout(new GridLayout(nFilas,nColumnas));
		// Creamos las casillas.
		casillas = new Casilla[nFilas][nColumnas];
		for (int f = 0; f < nFilas; f++) {
			for (int c = 0; c < nColumnas; c++) {
				casillas[f][c] = new Casilla(f, c);
				add(casillas[f][c]);
			}
		}
		mensajeActual = null;
	}

	public void pintarCasilla(int f, int c, Color col) {
		casillas[f][c].cambiaColor(col);
		//this.update(getGraphics());		// Refresca la ventana. Cuidado que se vé el refresco y queda mal esteticamente.
		
	}

	@Override
	public String infoComponente(Component c) {
		boolean jug1 = controlador.getEstadoJuego().jug1();
		if (jug1 && controlador.getJug1() instanceof JugadorHumanoCKEstadisticas || !jug1 && controlador.getJug2() instanceof JugadorHumanoCKEstadisticas) {
			for (int fi = 0; fi < casillas.length; fi++) {
				for (int co = 0; co < casillas[fi].length; co++) {
					if (c.equals(casillas[fi][co])) {
						if (columnaLibre(co)) {
							return "Soltar ficha en la columna " + (co+1);
						} else {
							return "No puede soltar más fichas en esta columna.";
						}
					}
				}
			}
		}
		return mensajeActual;
	}

	private boolean columnaLibre(int co) {
		int f = 0;
		while (f < casillas.length && !casillas[f][co].libre()) {
			f++;
		}
		return f < casillas.length;
	}
		
	@Override
	public void actualizarInfo(String info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		for (int f = 0; f < casillas.length; f++) {
			for (int c = 0; c < casillas[f].length; c++) {
				casillas[f][c].controlador(ctr);
			}
		}
	}

	/**
	 * @param mensajeActual the mensajeActual to set
	 */
	public void setMensajeActual(String mensajeActual) {
		this.mensajeActual = mensajeActual;
	}

	@Override
	public void registrarControlador(ControladorJuego ctr) {
		for (int f = 0; f < casillas.length; f++) {
			for (int c = 0; c < casillas[f].length; c++) {
				casillas[f][c].controlador((MouseListener) ctr);
			}
		}
		controlador = ctr;
	}

	@Override
	public JPanel panelJuegoEstado(EstadoJuego e) {
		ConectaK ck = (ConectaK) e;
		Ficha fActual = ck.fichaActual();
		Ficha fOtro = ck.fichaOtro();
		juegos.conectak.juego.TableroCK tablero = ck.tablero();
		TableroCK tableroNuevo = new TableroCK(ck.tablero().nFilas(), ck.tablero().nColumnas());
		if (ck.jug1()) {
			tableroNuevo = colocarFichas(fActual, fOtro, tablero, tableroNuevo, colorJ1, colorJ2);
		} else {
			tableroNuevo = colocarFichas(fActual, fOtro, tablero, tableroNuevo, colorJ2, colorJ1);
		}
		return tableroNuevo;
	}

	private TableroCK colocarFichas(Ficha fActual, Ficha fOtro, juegos.conectak.juego.TableroCK tablero, TableroCK tableroNuevo, Color colorJ1, Color colorJ2) {
		for (int f = 0; f < tableroNuevo.casillas.length; f++) {
			for (int c = 0; c < tableroNuevo.casillas[f].length; c++) {
				Ficha fCas = tablero.contenido(f, c);
				if (fCas.equals(fActual)) {
					tableroNuevo.casillas[f][c].cambiaColor(colorJ1);
				} else if (fCas.equals(fOtro)) {
					tableroNuevo.casillas[f][c].cambiaColor(colorJ2);
				}
			}
		}
		return tableroNuevo;
	}
}
