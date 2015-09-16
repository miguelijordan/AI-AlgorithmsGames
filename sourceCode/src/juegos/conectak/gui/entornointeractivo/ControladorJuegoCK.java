package juegos.conectak.gui.entornointeractivo;

import entornointeractivo.gui.interfaces.ControladorJuego;
import entornointeractivo.gui.interfaces.PanelJuego;
import entornointeractivo.gui.jugar.ControladorPantallaJuego;
import estrategias.agentes.Jugador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import juegos.EstadoJuego;
import juegos.conectak.estrategias.agentes.JugadorHumanoCK;
import juegos.conectak.estrategias.agentes.JugadorHumanoCKEstadisticas;
import juegos.conectak.gui.Casilla;
import juegos.conectak.gui.TableroCK;
import juegos.conectak.juego.ConectaK;
import juegos.util.Movimiento;

public class ControladorJuegoCK extends ControladorJuego implements MouseListener {
		
	private String infoPartida;
	private int numMovimientos1;
	private long tiempoTotal1;
	private int numMovimientos2;
	private long tiempoTotal2;
	private long tiempoAux;
	
	
	public ControladorJuegoCK(EstadoJuego estadoJuego, Jugador jug1, Jugador jug2, PanelJuego panelJuego, ControladorPantallaJuego ctrPantallaJuego) {
		super(estadoJuego, jug1, jug2, panelJuego, ctrPantallaJuego);
		infoPartida = infoBasicaJuego((ConectaK) estadoJuego, null);
		ctrPantallaJuego.actualizarInfoPartida(infoPartida);
		
		tiempoTotal1 = 0;
		numMovimientos1 = 0;
		tiempoTotal2 = 0;
		numMovimientos2 = 0;
	}
	
	/**
	 * Controla el desarrollo del juego hasta que la partida haya terminado.
	 */
	public void jugar() {
		if (estadoJuego.agotado() || estadoJuego.ganador() != null) {
			finJuego();
		} else {
			if (estadoJuego.jug1()) {
				actualizarInfoAyuda("Le toca al jugador 1. Mueven " + TableroCK.sColorJ1 + ".");
				((TableroCK) panelJuego).setMensajeActual("Le toca al jugador 1. Mueven " + TableroCK.sColorJ1 + ".");
				pausa(1);
				if (! (jug1 instanceof JugadorHumanoCKEstadisticas)) {
					panelJuego.setEnabled(false);
					estadoJuego = jug1.mueve(estadoJuego);
					((TableroCK) panelJuego).pintarCasilla(((ConectaK) estadoJuego).ultimoMovimiento().getFila(), ((ConectaK) estadoJuego).ultimoMovimiento().getColumna(), TableroCK.colorJ1);
					infoPartida = infoBasicaJuego((ConectaK) estadoJuego, ((ConectaK) estadoJuego).ultimoMovimiento());
					ctrPantallaJuego.actualizarInfoPartida(infoPartida);
					jugar();
				} else {
					tiempoAux = System.currentTimeMillis();
					panelJuego.setEnabled(true);
				}
			} else { // Le toca al jugador 2.
				actualizarInfoAyuda("Le toca al jugador 2. Mueven " + TableroCK.sColorJ2 + ".");
				((TableroCK) panelJuego).setMensajeActual("Le toca al jugador 2. Mueven " + TableroCK.sColorJ2 + ".");
				pausa(1);
				if (! (jug2 instanceof JugadorHumanoCKEstadisticas)) {
					panelJuego.setEnabled(false);
					estadoJuego = jug2.mueve(estadoJuego);
					((TableroCK) panelJuego).pintarCasilla(((ConectaK) estadoJuego).ultimoMovimiento().getFila(), ((ConectaK) estadoJuego).ultimoMovimiento().getColumna(), TableroCK.colorJ2);
					infoPartida = infoBasicaJuego((ConectaK) estadoJuego, ((ConectaK) estadoJuego).ultimoMovimiento());
					ctrPantallaJuego.actualizarInfoPartida(infoPartida);
					jugar();
				} else {
					tiempoAux = System.currentTimeMillis();
					panelJuego.setEnabled(true);
				}
			}
		}
	}
	
	/**
	 * Cambia el mensaje de información indicando el resultado de la partida.
	 */
	private void finJuego() {
		// Actualiza estadísticas del jugador humano.
		if (jug1 instanceof JugadorHumanoCKEstadisticas) {
			((JugadorHumanoCKEstadisticas) jug1).setNumMovimientos(numMovimientos1);
			((JugadorHumanoCKEstadisticas) jug1).setTiempoTotal(tiempoTotal1);
		}
		if (jug2 instanceof JugadorHumanoCKEstadisticas) {
			((JugadorHumanoCKEstadisticas) jug2).setNumMovimientos(numMovimientos2);
			((JugadorHumanoCKEstadisticas) jug2).setTiempoTotal(tiempoTotal2);
		}		
		
		int ganador;
		String res = new String();
		if (estadoJuego.ganador() == null && estadoJuego.agotado()) {
			res = "¡EMPATE!";
			ganador = 0;
			((TableroCK) panelJuego).setMensajeActual("Fin del juego. " + res);
		} else {
			if (estadoJuego.jug1()) {
				res = "¡GANA EL JUGADOR 2!";
				ganador = -1;
				((TableroCK) panelJuego).setMensajeActual("Fin del juego. " + res + " (" + TableroCK.sColorJ2 + ")");
			} else {
				res = "¡GANA EL JUGADOR 1!";
				ganador = 1;
				((TableroCK) panelJuego).setMensajeActual("Fin del juego. " + res + " (" + TableroCK.sColorJ1 + ")");
			}
		}
		panelJuego.setEnabled(false);
		finJuego(ganador, res);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (!e.isConsumed() && panelJuego.isEnabled()) {
			int columna = ((Casilla) e.getSource()).getColumna();
			if (((ConectaK) estadoJuego).posibleP(columna)) {
				if (estadoJuego.jug1()) {
					tiempoTotal1 += System.currentTimeMillis() - tiempoAux;
					numMovimientos1++;
					estadoJuego = ((ConectaK) estadoJuego).elegirSucNth(columna);
					((TableroCK) panelJuego).pintarCasilla(((ConectaK) estadoJuego).ultimoMovimiento().getFila(), ((ConectaK) estadoJuego).ultimoMovimiento().getColumna(), TableroCK.colorJ1);
				} else {
					tiempoTotal2 += System.currentTimeMillis() - tiempoAux;
					numMovimientos2++;
					estadoJuego = ((ConectaK) estadoJuego).elegirSucNth(columna);
					((TableroCK) panelJuego).pintarCasilla(((ConectaK) estadoJuego).ultimoMovimiento().getFila(), ((ConectaK) estadoJuego).ultimoMovimiento().getColumna(), TableroCK.colorJ2);
				}	
			}
			infoPartida = infoBasicaJuego((ConectaK) estadoJuego, ((ConectaK) estadoJuego).ultimoMovimiento());
			ctrPantallaJuego.actualizarInfoPartida(infoPartida);
			jugar();
			e.consume();
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * Realiza una pausa de s segundos.
	 * 
	 * @param s Segundos.
	 */
	private void pausa(int s) {
		long inicio = System.currentTimeMillis();
		long tiempo = s*1000;
		while (tiempo > System.currentTimeMillis()-inicio);
	}
	
	private String infoBasicaJuego(ConectaK ck, Movimiento ultimoMov) {
		/*String i = "Conecta-K.";
		i += "\nTablero: " + ck.tablero().nFilas() + " x " + ck.tablero().nColumnas();
		i += "\nLongitud ganadora: " + ck.longGanadora();
		
		i += "\n----------";*/
		String i = new String();
		
		i += "Último movimiento: ";
		if (ultimoMov != null) {
			i += "\n  columna " + (ultimoMov.getColumna()+1);	
		} else {
			i += "\n";
		}
		i += "\nTurno: ";
		i += "\n  mueve el jugador " + (ck.jug1() ? "1" : "2") + " (" +  (ck.jug1() ? TableroCK.sColorJ1 : TableroCK.sColorJ2) + ")";;
		
		return i;
	}
	
}
