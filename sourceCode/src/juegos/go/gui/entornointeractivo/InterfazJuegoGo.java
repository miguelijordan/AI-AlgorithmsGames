package juegos.go.gui.entornointeractivo;

import entornointeractivo.gui.InterfazJuego;
import entornointeractivo.gui.interfaces.ControladorJuego;
import entornointeractivo.gui.interfaces.PanelJuego;
import entornointeractivo.gui.interfaces.PanelOpcionesJuego;
import entornointeractivo.gui.jugar.ControladorPantallaJuego;
import estrategias.agentes.Jugador;
import juegos.EstadoJuego;
import juegos.conectak.gui.TableroCK;
import juegos.go.estrategias.agentes.JugadorHumanoGoEstadisticas;
import juegos.go.gui.TableroGo;
import juegos.go.juego.Go;
import juegos.util.Movimiento;

public class InterfazJuegoGo implements InterfazJuego {

	// CONSTANTES
	private static final String NOMBRE_JUEGO = "Go";
	
	/**
	 * Número de filas del tablero por defecto.
	 */
	public static final int N_FILAS = 9;
	
	/**
	 * Número de columnas del tablero por defecto.
	 */
	public static final int N_COLUMNAS = 9;
	
	private static PanelOpcionesJuego panelOpciones;
	private static PanelJuego panelJuego;
	private static EstadoJuego estadoJuego;
	
	public InterfazJuegoGo() {
		panelOpciones = null;
	}
	
	@Override
	public String nombre() {
		return NOMBRE_JUEGO;
	}

	@Override
	public PanelOpcionesJuego panelOpciones() {
		if (panelOpciones == null) {
			panelOpciones = new PanelOpcionesGo();
		}
		return panelOpciones;
	}

	@Override
	public PanelJuego panelJuego(EstadoJuego estadoJuego) {
		Go go = (Go) estadoJuego;
		return new TableroGo(go.tablero().nFilas(), go.tablero().nColumnas());
	}

	@Override
	public ControladorJuego controladorJuego(EstadoJuego estadoJuego, Jugador jug1, Jugador jug2, PanelJuego p, ControladorPantallaJuego ctrPantallaJuego) {
		return new ControladorJuegoGo(estadoJuego, jug1, jug2, p, ctrPantallaJuego);
	}

	@Override
	public EstadoJuego estadoJuego() {
		int nFichasJug1 = N_FILAS*N_COLUMNAS/2 + N_FILAS*N_COLUMNAS%2;
		int nFichasJug2 = N_FILAS*N_COLUMNAS/2;
		return new Go(N_FILAS, N_COLUMNAS, 0, null, true, Go.REGLAS_JAPONESAS, nFichasJug1, nFichasJug2);
	}

	@Override
	public Jugador estrategiaHumana() {
		return new JugadorHumanoGoEstadisticas();
	}

	@Override
	public String informacion(EstadoJuego e) {
		Go go = (Go) e;
		String reglas = go.getReglas() == Go.REGLAS_JAPONESAS ? "Japonesas" : "Chinas";
		
		String i = "Juego del Go.";
		i += "\nTablero: " + go.tablero().nFilas() + " x " + go.tablero().nColumnas();
		i += "\nReglas de puntuación: " + reglas;
		i += "\nKomi: " + go.getKomi();
		i += "\nJugador 1 juega con " + TableroGo.sColorJ1;
		i += "\nJugador 2 juega con " + TableroGo.sColorJ2;
		return i;
	}

	@Override
	public String informacionDetalle(EstadoJuego e) {
		Go go = (Go) e;
		String i = "";
		Movimiento mov = go.getUltimoMov();
		if (mov != null) {
			i += "Último movimiento: (" + (go.getUltimoMov().getFila()+1) + "," + (go.getUltimoMov().getColumna()+1) + ")";	
		}
		i += "\nFichas capturadas del jugador 1: " + go.getnFichasCapturadasJug1();
		i += "\nFichas capturadas del jugador 2: " + go.getnFichasCapturadasJug2();
		
		if (go.getReglas() == Go.REGLAS_JAPONESAS) {
			i += "\nPuntos jugador 1: " + go.puntuacionReglasJaponesas(Go.getFicha1());
			i += "\nPuntos jugador 2: " + go.puntuacionReglasJaponesas(Go.getFicha2());
		} else {
			i += "\nPuntos jugador 1: " + go.puntuacionReglasChinas(Go.getFicha1());
			i += "\nPuntos jugador 2: " + go.puntuacionReglasChinas(Go.getFicha2());
		}
		return i;
	}

}
