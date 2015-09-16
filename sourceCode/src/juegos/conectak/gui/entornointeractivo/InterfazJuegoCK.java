package juegos.conectak.gui.entornointeractivo;

import entornointeractivo.gui.InterfazJuego;
import entornointeractivo.gui.interfaces.ControladorJuego;
import entornointeractivo.gui.interfaces.PanelJuego;
import entornointeractivo.gui.interfaces.PanelOpcionesJuego;
import entornointeractivo.gui.jugar.ControladorPantallaJuego;
import estrategias.agentes.Jugador;
import juegos.EstadoJuego;
import juegos.conectak.estrategias.agentes.JugadorHumanoCKEstadisticas;
import juegos.conectak.gui.TableroCK;
import juegos.conectak.juego.ConectaK;
import juegos.util.Movimiento;

public class InterfazJuegoCK implements InterfazJuego {

	// CONSTANTES
	private static final String NOMBRE_JUEGO = "Conecta-K";
	
	/**
	 * Número de filas del tablero por defecto.
	 */
	public static final int N_FILAS = 6;
	
	/**
	 * Número de columnas del tablero por defecto.
	 */
	public static final int N_COLUMNAS = 7;
	
	/**
	 * Longitud ganadora del juego por defecto.
	 */
	public static final int LONGITUD_GANADORA = 4;
	
	
	
	private static PanelOpcionesJuego panelOpciones;
	private static PanelJuego panelJuego;
	private static EstadoJuego estadoJuego;
	
	public InterfazJuegoCK() {
		panelOpciones = null;
	}
	
	@Override
	public String nombre() {
		return NOMBRE_JUEGO;
	}

	@Override
	public PanelOpcionesJuego panelOpciones() {
		if (panelOpciones == null) {
			panelOpciones = new PanelOpcionesConectaK();
		}
		return panelOpciones;
	}

	@Override
	public PanelJuego panelJuego(EstadoJuego estadoJuego) {
		ConectaK ck = (ConectaK) estadoJuego;
		return new TableroCK(ck.tablero().nFilas(), ck.tablero().nColumnas());
	}

	@Override
	public ControladorJuego controladorJuego(EstadoJuego estadoJuego, Jugador jug1, Jugador jug2, PanelJuego p, ControladorPantallaJuego ctrPantallaJuego) {
		return new ControladorJuegoCK(estadoJuego, jug1, jug2, p, ctrPantallaJuego);
	}

	@Override
	public EstadoJuego estadoJuego() {
		return new ConectaK(N_FILAS, N_COLUMNAS, LONGITUD_GANADORA);
	}

	@Override
	public Jugador estrategiaHumana() {
		return new JugadorHumanoCKEstadisticas();
	}

	@Override
	public String informacion(EstadoJuego e) {
		ConectaK ck = (ConectaK) e;
		
		String i = "Juego del Conecta-K.";
		i += "\nTablero: " + ck.tablero().nFilas() + " x " + ck.tablero().nColumnas();
		i += "\nLongitud ganadora: " + ck.longGanadora();
		i += "\nJugador 1 juega con " + TableroCK.sColorJ1;
		i += "\nJugador 2 juega con " + TableroCK.sColorJ2;
		return i;
	}

	@Override
	public String informacionDetalle(EstadoJuego e) {
		ConectaK ck = (ConectaK) e;
		Movimiento mov = ck.ultimoMovimiento();
		if (mov != null) {
			return "Último movimiento: columna " + (ck.ultimoMovimiento().getColumna()+1);	
		} else {
			return "";
		}
		
	}

}
