package juegos.go.gui.entornointeractivo;

import entornointeractivo.gui.interfaces.ControladorJuego;
import entornointeractivo.gui.interfaces.PanelJuego;
import entornointeractivo.gui.jugar.ControladorPantallaJuego;
import estrategias.agentes.Jugador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;

import juegos.EstadoJuego;
import juegos.go.estrategias.agentes.JugadorHumanoGoEstadisticas;
import juegos.go.gui.CasillaGo;
import juegos.go.gui.TableroGo;
import juegos.go.juego.Go;
import juegos.util.Ficha;
import juegos.util.Movimiento;

public class ControladorJuegoGo extends ControladorJuego implements MouseListener {
		
	private String infoPartida;
	private int numMovimientos1;
	private long tiempoTotal1;
	private int numMovimientos2;
	private long tiempoTotal2;
	private long tiempoAux;
	
	
	public ControladorJuegoGo(EstadoJuego estadoJuego, Jugador jug1, Jugador jug2, PanelJuego panelJuego, ControladorPantallaJuego ctrPantallaJuego) {
		super(estadoJuego, jug1, jug2, panelJuego, ctrPantallaJuego);
		infoPartida = infoBasicaJuego((Go) estadoJuego, null, false);
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
				((TableroGo) panelJuego).setMensajeActual("Le toca al jugador 1. Mueven " + TableroGo.sColorJ1 + ".");
				pausa(1);
				if (! (jug1 instanceof JugadorHumanoGoEstadisticas)) {
					panelJuego.setEnabled(false);
					estadoJuego = jug1.mueve(estadoJuego);
					if (((Go) estadoJuego).getUltimoMov() == null) {
						((TableroGo) panelJuego).setMensajeActual("El jugador 1 pasa turno.");
						infoPartida = infoBasicaJuego((Go) estadoJuego, null, true);
					} else {
						((TableroGo) panelJuego).pintarCasilla(((Go) estadoJuego).getUltimoMov().getFila(), ((Go) estadoJuego).getUltimoMov().getColumna(), TableroGo.colorJ1);
						infoPartida = infoBasicaJuego((Go) estadoJuego, ((Go) estadoJuego).getUltimoMov(), false);
						// Hay que eliminar las fichas del tablero.
						eliminarFichasCapturadas();
					}
					ctrPantallaJuego.actualizarInfoPartida(infoPartida);
					jugar();
				} else {
					tiempoAux = System.currentTimeMillis();
					panelJuego.setEnabled(true);
				}
			} else { // Le toca al jugador 2.
				((TableroGo) panelJuego).setMensajeActual("Le toca al jugador 2. Mueven " + TableroGo.sColorJ2 + ".");
				pausa(1);
				if (! (jug2 instanceof JugadorHumanoGoEstadisticas)) {
					panelJuego.setEnabled(false);
					estadoJuego = jug2.mueve(estadoJuego);
					if (((Go) estadoJuego).getUltimoMov() == null) {
						((TableroGo) panelJuego).setMensajeActual("El jugador 2 pasa turno.");
						infoPartida = infoBasicaJuego((Go) estadoJuego, null, true);
					} else {
						((TableroGo) panelJuego).pintarCasilla(((Go) estadoJuego).getUltimoMov().getFila(), ((Go) estadoJuego).getUltimoMov().getColumna(), TableroGo.colorJ2);
						infoPartida = infoBasicaJuego((Go) estadoJuego, ((Go) estadoJuego).getUltimoMov(), false);
						// Hay que eliminar las fichas del tablero.
						eliminarFichasCapturadas();
					}
					ctrPantallaJuego.actualizarInfoPartida(infoPartida);
					jugar();
				} else {
					tiempoAux = System.currentTimeMillis();
					panelJuego.setEnabled(true);
				}
			}
		}
	}
	
	private void eliminarFichasCapturadas() {
		Go go = (Go) estadoJuego;
		//juegos.go.juego.TableroGo tab = go.tablero();
		List<Movimiento> capturas = go.getUltimasFichasCapturadas();
		if (capturas != null) {
			for (Movimiento m : capturas) {
				((TableroGo) panelJuego).quitarFicha(m.getFila(), m.getColumna());
			}	
		}
		
		/*for (int f = 0; f < tab.nFilas(); f++) {
			for (int c = 0; c < tab.nColumnas(); c++) {
				if (tab.contenido(f, c).equals(tab.getPVacia())) {
					((TableroGo) panelJuego).quitarFicha(f, c);
				}
			}
		}*/
		
	}

	/**
	 * Cambia el mensaje de información indicando el resultado de la partida.
	 */
	private void finJuego() {
		// Actualiza estadísticas del jugador humano.
		if (jug1 instanceof JugadorHumanoGoEstadisticas) {
			((JugadorHumanoGoEstadisticas) jug1).setNumMovimientos(numMovimientos1);
			((JugadorHumanoGoEstadisticas) jug1).setTiempoTotal(tiempoTotal1);
		}
		if (jug2 instanceof JugadorHumanoGoEstadisticas) {
			((JugadorHumanoGoEstadisticas) jug2).setNumMovimientos(numMovimientos2);
			((JugadorHumanoGoEstadisticas) jug2).setTiempoTotal(tiempoTotal2);
		}		
		
		Ficha fichaGanadora = estadoJuego.ganador();
		int ganador;
		String res = new String();
		if (fichaGanadora == null && estadoJuego.agotado()) {
			res = "¡EMPATE!";
			ganador = 0;
			((TableroGo) panelJuego).setMensajeActual("Fin del juego. " + res);
		} else if (fichaGanadora != null) {
			if (fichaGanadora.equals(Go.getFicha2())) {
				res = "¡GANA EL JUGADOR 2!";
				ganador = -1;
				((TableroGo) panelJuego).setMensajeActual("Fin del juego. " + res + " (" + TableroGo.sColorJ2 + ")");
			} else {
				res = "¡GANA EL JUGADOR 1!";
				ganador = 1;
				((TableroGo) panelJuego).setMensajeActual("Fin del juego. " + res + " (" + TableroGo.sColorJ1 + ")");
			}
		} else { // No debería llegar aquí nunca, (lo pongo por si acaso) si llegara es porque hay un error en la implementación del juego pero lo siguiente oculta el error.
			res = "¡EMPATE!";
			ganador = 0;
			((TableroGo) panelJuego).setMensajeActual("Fin del juego. " + res);
		}
		panelJuego.setEnabled(false);
		finJuego(ganador, res);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (!e.isConsumed() && panelJuego.isEnabled()) {
			if (estadoJuego.jug1() && jug1 instanceof JugadorHumanoGoEstadisticas || !estadoJuego.jug1() && jug2 instanceof JugadorHumanoGoEstadisticas) {
				if (e.getSource() instanceof JButton) {
					if (estadoJuego.jug1()) {
						tiempoTotal1 += System.currentTimeMillis() - tiempoAux;
						numMovimientos1++;
					} else {
						tiempoTotal2 += System.currentTimeMillis() - tiempoAux;
						numMovimientos2++;
					}
					estadoJuego = ((Go) estadoJuego).pasarTurno();
					((TableroGo) panelJuego).setMensajeActual("El jugador ha pasado turno");
					infoPartida = infoBasicaJuego((Go) estadoJuego, null, true);
				} else {
					CasillaGo casilla = (CasillaGo) e.getSource(); 
					int columna = casilla.getColumna();
					int fila = casilla.getFila();
					if (((Go) estadoJuego).movimientoValido(fila, columna)) {
						if (estadoJuego.jug1()) {
							tiempoTotal1 += System.currentTimeMillis() - tiempoAux;
							numMovimientos1++;
							estadoJuego = ((Go) estadoJuego).elegirSuc(fila, columna);
							((TableroGo) panelJuego).pintarCasilla(((Go) estadoJuego).getUltimoMov().getFila(), ((Go) estadoJuego).getUltimoMov().getColumna(), TableroGo.colorJ1);
						} else {
							tiempoTotal2 += System.currentTimeMillis() - tiempoAux;
							numMovimientos2++;
							estadoJuego = ((Go) estadoJuego).elegirSuc(fila, columna);
							((TableroGo) panelJuego).pintarCasilla(((Go) estadoJuego).getUltimoMov().getFila(), ((Go) estadoJuego).getUltimoMov().getColumna(), TableroGo.colorJ2);
						}
						infoPartida = infoBasicaJuego((Go) estadoJuego, ((Go) estadoJuego).getUltimoMov(), false);
						eliminarFichasCapturadas();
					}	
				}
				ctrPantallaJuego.actualizarInfoPartida(infoPartida);
				jugar();
				e.consume();
			}
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
	
	private String infoBasicaJuego(Go go, Movimiento ultimoMov, boolean pasaTurno) {
		String i = new String();
		
		i += "Último movimiento: ";
		if (ultimoMov != null) {
			i += "\n  posición (" + (ultimoMov.getFila()+1) + "," + (ultimoMov.getColumna()+1) + ")";	
		} else if (pasaTurno) {
			i += "\n  jugador " + (!go.jug1() ? "1" : "2") + " pasa turno";
		} else {
			i += "\n";
		}
		i += "\nTurno: ";
		i += "\n  mueve el jugador " + (go.jug1() ? "1" : "2") + " (" +  (go.jug1() ? TableroGo.sColorJ1 : TableroGo.sColorJ2) + ")";
		
		i += "\n----------";
		i += "\nFichas jugador 1: " +  go.getnFichasJug1();
		i += "\nFichas jugador 2: " +  go.getnFichasJug2();
		
		i += "\nFichas capturadas del jugador 1: " + go.getnFichasCapturadasJug1();
		i += "\nFichas capturadas del jugador 2: " + go.getnFichasCapturadasJug2();
		
		return i;
	}
}
