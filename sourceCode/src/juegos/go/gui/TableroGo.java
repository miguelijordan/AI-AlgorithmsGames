package juegos.go.gui;

import entornointeractivo.gui.interfaces.ControladorJuego;
import entornointeractivo.gui.interfaces.InformacionAyuda;
import entornointeractivo.gui.interfaces.PanelJuego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import juegos.EstadoJuego;
import juegos.go.gui.CasillaGo.Linea;
import juegos.go.juego.Go;
import juegos.util.Ficha;

/**
 * Tablero para el juego del Go.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 1/12/2011
 *
 */
public class TableroGo extends PanelJuego implements InformacionAyuda {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// CONSTANTES
	private static final Color MARRON = new Color(228, 169, 79);
	public static final Color COLOR_LINEAS = Color.DARK_GRAY;
	public static final Color COLOR_FONDO = MARRON;
	
	public static final Color colorJ1 = Color.BLACK;
	public static final Color colorJ2 = Color.WHITE;
	public static final String sColorJ1 = "negras";
	public static final String sColorJ2 = "blancas";
	
	private CasillaGo[][] casillas;		// Casillas del tablero.
	private String mensajeActual;
	private JButton btnPasar;
	
	private ControladorJuego controlador;
	
	
	public TableroGo(int nFilas, int nColumnas) {
		setLayout(new BorderLayout());
		
		JPanel panelTablero = new JPanel();
		panelTablero.setLayout(new GridLayout(nFilas, nColumnas));
		
		// Asignamos un gestor de esquemas para las casillas.
		//setLayout(new GridLayout(nFilas,nColumnas));
		// Creamos las casillas.
		casillas = new CasillaGo[nFilas][nColumnas];
		for (int f = 0; f < nFilas; f++) {
			for (int c = 0; c < nColumnas; c++) {
				casillas[f][c] = crearCasilla(f,c);
				panelTablero.add(casillas[f][c]);
			}
		}
		add(panelTablero, BorderLayout.CENTER);
		
		addBotonPasar();
	}
	
	private TableroGo(int nFilas, int nColumnas, boolean botonPasar) {
		setLayout(new BorderLayout());
		JPanel panelTablero = new JPanel();
		panelTablero.setLayout(new GridLayout(nFilas, nColumnas));
		
		// Asignamos un gestor de esquemas para las casillas.
		//setLayout(new GridLayout(nFilas,nColumnas));
		// Creamos las casillas.
		casillas = new CasillaGo[nFilas][nColumnas];
		for (int f = 0; f < nFilas; f++) {
			for (int c = 0; c < nColumnas; c++) {
				casillas[f][c] = crearCasilla(f,c);
				panelTablero.add(casillas[f][c]);
			}
		}
		add(panelTablero, BorderLayout.CENTER);
		
		if (botonPasar) {
			addBotonPasar();	
		}
	}
	
	private void addBotonPasar() {
		btnPasar = new JButton("Pasar turno");
		
		JPanel panelSur = new JPanel();
		FlowLayout flowL = new FlowLayout();
		flowL.setAlignment(FlowLayout.RIGHT);
		panelSur.setLayout(flowL);
		panelSur.add(btnPasar);
		
		add(panelSur, BorderLayout.SOUTH);
	}

	private CasillaGo crearCasilla(int f, int c) {
		CasillaGo.Linea x;
		CasillaGo.Linea y;
		
		if (f == 0) {
			y = Linea.VERTICAL_INF;
		} else if (f == casillas.length-1) {
			y = Linea.VERTICAL_SUP;
		} else {
			y = Linea.VERTICAL;
		}
		
		if (c == 0) {
			x = Linea.HORIZONTAL_DER;
		} else if (c == casillas[0].length-1) {
			x = Linea.HORIZONTAL_IZQ;
		} else {
			x = Linea.HORIZONTAL;
		}
		return new CasillaGo(f, c, COLOR_LINEAS, x, y);
	}
	
	public void pintarCasilla(int f, int c, Color col) {
		casillas[f][c].ponerFicha(col);
	}
	
	public void quitarFicha(int f, int c) {
		casillas[f][c].quitarFicha();
	}
	
	/**
	 * @param mensajeActual the mensajeActual to set
	 */
	public void setMensajeActual(String mensajeActual) {
		this.mensajeActual = mensajeActual;
	}

	@Override
	public String infoComponente(Component c) {
		if (c.equals(btnPasar)) {
			return "Pasa turno. La partida terminará si los dos jugadores pasan turno simultáneamente.";
		}
		try {
			CasillaGo cas = (CasillaGo) c;
			Go go = (Go) controlador.getEstadoJuego();
			if (go.jug1() && go.getnFichasJug1() == 0) {
				return "No quedan fichas para el jugador 1. Debe pasar turno.";
			} else if (!go.jug1() && go.getnFichasJug2() == 0) {
				return "No quedan fichas para el jugador 2. Debe pasar turno.";
			} else if (go.movimientoValido(cas.getFila(), cas.getColumna())) {
				return "Poner ficha en posición " + "(" + (cas.getFila()+1) + "," + (cas.getColumna()+1) + ")";
			} else {
				String res = "No se puede poner una ficha en esta posición. Comprueba que no este ocupada por otra ficha,";
				res += " que no se trata de un suicidio y que no viola la regla del Ko.";
				return res;
			}
		} catch (Exception e) {
			return mensajeActual;
		}
		
		
		
		
		/*for (int fi = 0; fi < casillas.length; fi++) {
			for (int co = 0; co < casillas[fi].length; co++) {
				if (c.equals(casillas[fi][co])) {
					Go go = (Go) controlador.getEstadoJuego();
					if (go.jug1() && go.getnFichasJug1() == 0) {
						return "No quedan fichas para el jugador 1. Debe pasar turno.";
					} else if (!go.jug1() && go.getnFichasJug2() == 0) {
						return "No quedan fichas para el jugador 2. Debe pasar turno.";
					} else if (go.movimientoValido(fi, co)) {
						return "Poner ficha en posición " + "(" + (fi+1) + "," + (co+1) + ")";
					} else {
						String res = "No se puede poner una ficha en esta posición. Comprueba que no este ocupada por otra ficha,";
						res += " que no se trata de un suicidio y que no viola la regla del Ko.";
						return res;
					}
				}
			}
		}*/
		//return mensajeActual;
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
		btnPasar.addMouseListener(ctr);
	}

	@Override
	public void registrarControlador(ControladorJuego ctr) {
		for (int f = 0; f < casillas.length; f++) {
			for (int c = 0; c < casillas[f].length; c++) {
				casillas[f][c].controlador((MouseListener) ctr);
			}
		}
		btnPasar.addMouseListener((MouseListener)ctr);
		this.controlador = ctr;
		//btnPasar.setActionCommand("Pasar");
	}

	@Override
	public JPanel panelJuegoEstado(EstadoJuego e) {
		Go go = (Go) e;
		Ficha fActual = go.fichaActual();
		Ficha fOtro = go.fichaOtro();
		juegos.go.juego.TableroGo tablero = go.tablero();
		TableroGo tableroNuevo = new TableroGo(go.tablero().nFilas(), go.tablero().nColumnas(), false);
		if (go.jug1()) {
			tableroNuevo = colocarFichas(fActual, fOtro, tablero, tableroNuevo, colorJ1, colorJ2);
		} else {
			tableroNuevo = colocarFichas(fActual, fOtro, tablero, tableroNuevo, colorJ2, colorJ1);
		}
		return tableroNuevo;
	}
	
	private TableroGo colocarFichas(Ficha fActual, Ficha fOtro, juegos.go.juego.TableroGo tablero, TableroGo tableroNuevo, Color colorJ1, Color colorJ2) {
		for (int f = 0; f < tableroNuevo.casillas.length; f++) {
			for (int c = 0; c < tableroNuevo.casillas[f].length; c++) {
				Ficha fCas = tablero.contenido(f, c);
				if (fCas.equals(fActual)) {
					tableroNuevo.casillas[f][c].ponerFicha(colorJ1);
				} else if (fCas.equals(fOtro)) {
					tableroNuevo.casillas[f][c].ponerFicha(colorJ2);
				}
			}
		}
		return tableroNuevo;
	}
}
