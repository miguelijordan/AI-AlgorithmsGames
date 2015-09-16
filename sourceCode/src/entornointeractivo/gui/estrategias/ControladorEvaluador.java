package entornointeractivo.gui.estrategias;

import entornointeractivo.gui.InterfazEstrategia;
import entornointeractivo.gui.InterfazEvaluador;
import entornointeractivo.gui.util.InformacionEntrenamiento;
import estrategias.agentes.Jugador;
import estrategias.agentes.aleatorio.JugadorAleatorio;
import estrategias.agentes.evaluar.JugadorEvaluar;
import estrategias.agentes.gui.humano.InterfazHumano;

import heuristicos.aprendizaje.Entrenamiento;
import heuristicos.evaluadores.Evaluador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import juegos.EstadoJuego;


public class ControladorEvaluador implements ActionListener, MouseListener {

	private PantallaConfiguracionEvaluador pantallaConfiguracionEvaluador;
	private PanelEntrenamiento pEntrenamiento;
	private Evaluador evaluador;
	private InterfazEvaluador iEvaluador;
	private ControladorEstrategia ctrEstrategia;
	
	private Jugador jugador1;
	private Jugador jugador2;
	private InformacionEntrenamiento infoEntrenamiento1;
	private InformacionEntrenamiento infoEntrenamiento2;
	
	public ControladorEvaluador(InterfazEvaluador iEvaluador, ControladorEstrategia ctrEstrategia) {
		this.iEvaluador = iEvaluador;
		this.ctrEstrategia = ctrEstrategia;
		this.evaluador = iEvaluador.evaluador(ctrEstrategia.getEstadoJuego());
		
		if (ctrEstrategia.isJug1()) {
			this.jugador1 = ctrEstrategia.getJugador();
			this.jugador2 = null;
			if (this.jugador1 != null) {
				crearPantallaConfiguracionEvaluador();
			} else {
				ctrEstrategia.continuar();
			}
		} else {
			this.jugador1 = null;
			this.jugador2 = ctrEstrategia.getJugador();
			if (this.jugador2 != null) {
				crearPantallaConfiguracionEvaluador();
			} else {
				ctrEstrategia.continuar();
			}
		}
		infoEntrenamiento1 = null;
		infoEntrenamiento2 = null;
	}
	
	private void crearPantallaConfiguracionEvaluador() {
		pantallaConfiguracionEvaluador = new PantallaConfiguracionEvaluador(iEvaluador.nombre(), iEvaluador.panelConfiguracion(), iEvaluador.entrenable(), ctrEstrategia.isJug1());
		pantallaConfiguracionEvaluador.registrarControlador(this);
		pantallaConfiguracionEvaluador.registrarControladorInformacion(this);
		pantallaConfiguracionEvaluador.setVisible(true);
		pEntrenamiento = pantallaConfiguracionEvaluador.getPanelEntrenamiento();
		pEntrenamiento.setTextoOponente(obtenerOponente(false).toString(), "");
		if (isOponenteHumano() && pEntrenamiento != null) {
			pEntrenamiento.getChckbxJugOponente().setEnabled(false);
		} else {
			pEntrenamiento.getChckbxJugOponente().setEnabled(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		String info = pantallaConfiguracionEvaluador.infoComponente(arg0.getComponent());
		pantallaConfiguracionEvaluador.actualizarInfo(info);
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		pantallaConfiguracionEvaluador.actualizarInfo(null);
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals("Aceptar")) {
			Jugador jug = obtenerJugadorConEvaluador();
			if (jug != null) {
				if (ctrEstrategia.isJug1()) {
					this.jugador1 = jug;
					ctrEstrategia.setEvaluador(((JugadorEvaluar) this.jugador1).getEvaluador());
					ctrEstrategia.setiEvaluador(iEvaluador);
				} else {
					this.jugador2 = jug;
					ctrEstrategia.setEvaluador(((JugadorEvaluar) this.jugador2).getEvaluador());
					ctrEstrategia.setiEvaluador(iEvaluador);
				}
				pantallaConfiguracionEvaluador.dispose();
				ctrEstrategia.continuar();	
			}
		/*} else if (command.equals("Cancelar")) {
			// El entrenamiento del jugador 1 se cancelará. Pero el del jugador 2 no.
			this.evaluador = iEvaluador.evaluador(ctrEstrategia.getEstadoJuego());
			pantallaConfiguracionEvaluador.dispose();
			ctrEstrategia.continuar();*/
		} else if (command.equals("UsarJug2")) {	// Activa o desactiva el botón de entrenamiento simultaneo.
			if (pEntrenamiento.oponenteJug()) {
				pEntrenamiento.getBtnEntrenamientoSimultaneo().setVisible(true);
				pEntrenamiento.setTextoOponente(obtenerOponente(true).toString(), infoEntrenamientoOponente());
				if (isOponenteEntrenable()) {
					pEntrenamiento.getBtnEntrenamientoSimultaneo().setEnabled(true);
				} else {
					pEntrenamiento.getBtnEntrenamientoSimultaneo().setEnabled(false);
				}
			} else {
				pEntrenamiento.getBtnEntrenamientoSimultaneo().setVisible(false);
				pEntrenamiento.setTextoOponente(obtenerOponente(false).toString(), "");
			}
		} else if (command.equals("Entrenar")) {
			pEntrenamiento.inicializarAprendizaje();
			entrenamiento(false);
		} else if (command.equals("Entrenar2")) {
			pEntrenamiento.inicializarAprendizaje();
			entrenamiento(true);
			pEntrenamiento.setTextoOponente(obtenerOponente(true).toString(), infoEntrenamientoOponente());
		}
	}
	
	private String infoEntrenamientoOponente() {
		InformacionEntrenamiento info;
		if (ctrEstrategia.isJug1()) {
			info = ctrEstrategia.getCtrPrincipal().getInfoEntrenamientoJug2();
		} else {
			info = ctrEstrategia.getCtrPrincipal().getInfoEntrenamientoJug1();
		}
		return info == null ? "" : info.toString();
	}
	
	/**
	 * 
	 * @param oponente	Indica si hay que entrenar simultáneamente al oponente.
	 */
	private void entrenamiento(boolean oponente) {
		Integer nPartidasEntrenamiento = pEntrenamiento.nPartidasEntrenamiento();
		if (nPartidasEntrenamiento != null) {
			Integer cadaN = pEntrenamiento.pausaCadaN();
			if (cadaN != null) {
				Integer nPruebas = pEntrenamiento.nPruebas();
				if (nPruebas != null) {
					Double pex = pEntrenamiento.probabilidadExploracion();
					if (pex != null) {
						entrenar(nPartidasEntrenamiento, cadaN, nPruebas, pex, oponente);	
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param nPartidasEntrenamiento
	 * @param cadaN
	 * @param nPruebas
	 * @param pex
	 * @param op						Indica si hay que entrenar simultáneamente al jugador contrario o no.
	 */
	private void entrenar(int nPartidasEntrenamiento, int cadaN, int nPruebas, double pex, boolean op) {
		EstadoJuego e = ctrEstrategia.getEstadoJuego();
		Jugador jugador = obtenerJugadorConEvaluador();
		if (jugador != null) { // Error en los parametros del evaluador.
			Jugador oponente = obtenerOponente(pEntrenamiento.getChckbxJugOponente().isSelected());
			if (ctrEstrategia.isJug1()) {
				this.jugador1 = jugador;
				this.jugador2 = oponente;
			} else {
				this.jugador1 = oponente;
				this.jugador2 = jugador;	
			}

			
			InformacionEntrenamiento infoEntr1 = ctrEstrategia.getInfoEntrenamiento1();
			InformacionEntrenamiento infoEntr2 = ctrEstrategia.getInfoEntrenamiento2();
			
			Entrenamiento.setProbabilidad_exploracion(pex);
			if (op) {	// Entrenan los dos jugadores.
				actualizarInformacionAyudaEntrenamiento();
				Entrenamiento.verAprendizajeJugGUI(nPartidasEntrenamiento, cadaN, nPruebas, (JugadorEvaluar)jugador1, (JugadorEvaluar)jugador2, e, pEntrenamiento);
				actualizarInformacionAyudaEntrenamientoTerminado();
				
				// Actualiza la información del entrenamiento.
				if (ctrEstrategia.isJug1()) {
					if (infoEntr1 == null) {
						infoEntrenamiento1 = new InformacionEntrenamiento(nPartidasEntrenamiento);
					} else {
						infoEntrenamiento1 = new InformacionEntrenamiento(nPartidasEntrenamiento + infoEntr1.getnPartidas());
					}
					ctrEstrategia.setInfoEntrenamiento1(infoEntrenamiento1);
					// Actualiza informaciona entrenamiento jugador contrario.
					// IMPORTANTE: LA INFO DEL JUGADOR CONTRARIO SE ACTUALIZA DE VERDAD EN LA PANTALLA PRINCIPAL (LA DEL JUGADOR 1 NO).
					infoEntrenamiento2 = new InformacionEntrenamiento(nPartidasEntrenamiento + ctrEstrategia.getCtrPrincipal().getInfoEntrenamientoJug2().getnPartidas());
					ctrEstrategia.getCtrPrincipal().setInfoEntrenamientoJug2(infoEntrenamiento2);
				} else {
					if (infoEntr2 == null) {
						infoEntrenamiento1 = new InformacionEntrenamiento(nPartidasEntrenamiento);
					} else {
						infoEntrenamiento1 = new InformacionEntrenamiento(nPartidasEntrenamiento + infoEntr2.getnPartidas());
					}
					ctrEstrategia.setInfoEntrenamiento2(infoEntrenamiento1);
					// Actualiza informaciona entrenamiento jugador contrario.
					// IMPORTANTE: LA INFO DEL JUGADOR CONTRARIO SE ACTUALIZA DE VERDAD EN LA PANTALLA PRINCIPAL (LA DEL JUGADOR 1 NO).
					infoEntrenamiento2 = new InformacionEntrenamiento(nPartidasEntrenamiento + ctrEstrategia.getCtrPrincipal().getInfoEntrenamientoJug1().getnPartidas());
					ctrEstrategia.getCtrPrincipal().setInfoEntrenamientoJug1(infoEntrenamiento2);
				} 
			} else if (ctrEstrategia.isJug1()) {
				actualizarInformacionAyudaEntrenamiento();
				Entrenamiento.verAprendizajeJug1GUI(nPartidasEntrenamiento, cadaN, nPruebas, (JugadorEvaluar)jugador1, jugador2, e, pEntrenamiento);
				actualizarInformacionAyudaEntrenamientoTerminado();
				if (infoEntr1 == null) {
					infoEntrenamiento1 = new InformacionEntrenamiento(nPartidasEntrenamiento);
				} else {
					infoEntrenamiento1 = new InformacionEntrenamiento(nPartidasEntrenamiento + infoEntr1.getnPartidas());
				}
				ctrEstrategia.setInfoEntrenamiento1(infoEntrenamiento1);
				
			} else {
				actualizarInformacionAyudaEntrenamiento();
				Entrenamiento.verAprendizajeJug2GUI(nPartidasEntrenamiento, cadaN, nPruebas, jugador1, (JugadorEvaluar)jugador2, e, pEntrenamiento);
				actualizarInformacionAyudaEntrenamientoTerminado();
				if (infoEntr2 == null) {
					infoEntrenamiento1 = new InformacionEntrenamiento(nPartidasEntrenamiento);
				} else {
					infoEntrenamiento1 = new InformacionEntrenamiento(nPartidasEntrenamiento + infoEntr2.getnPartidas());
				}
				ctrEstrategia.setInfoEntrenamiento2(infoEntrenamiento1);
				
			}
			// FALTA ASIGNAR LOS JUGADORES SOLO SI SE PULSA EL BOTON ACEPTAR Y (SI HACE FALTA (PENSARLO) AÑADIR LA OPCION DE USAR EL OPONENTE COMO JUGADOR 2 
			// HAY UN ERROR EN TODO ESTO Y ES QUE NO SE TIENEN QUE MODIFICAR LOS JUGADORES SINO SOLO LOS EVALUADORES.
			// SINO ENCUENTRO SOLUCIÓN (QUITAR EL BOTÓN CANCELAR)	
		}
	}
	
	private Jugador obtenerJugadorConEvaluador() {
		//Jugador jug = ctrEstrategia.getpConfiguracion().estrategia();
		Jugador jug = ctrEstrategia.getJugador();
		Evaluador ev = pantallaConfiguracionEvaluador.getpConfEv().evaluador(ctrEstrategia.getEstadoJuego());
		if (ev == null) {
			return null;
		}
		((JugadorEvaluar) jug).setEvaluador(ev);
		return jug;
	}
	
	/**
	 * Obtiene el oponente con el que el jugador se entrenará.
	 * Por defecto se trata de un jugador aleatorio.
	 * 
	 * @param op	Indica si se usará el jugador contrario actual como oponente.
	 * @return		Jugador oponente para entrenar.
	 */
	private Jugador obtenerOponente(boolean op) {
		Jugador jug = new JugadorAleatorio();
		if (op) {
			if (ctrEstrategia.isJug1()) {
				jug = ctrEstrategia.getCtrPrincipal().getJugador2();
			} else {
				jug = ctrEstrategia.getCtrPrincipal().getJugador1();
			}
		} 
		return jug;
	}
	
	private boolean isOponenteEntrenable() {
		InterfazEvaluador iEv;
		if (ctrEstrategia.isJug1()) {
			iEv = ctrEstrategia.getCtrPrincipal().getiEvJugador2();
		} else {
			iEv = ctrEstrategia.getCtrPrincipal().getiEvJugador1();
		}
		return iEv != null && iEv.entrenable();
	}
	
	private boolean isOponenteHumano() {
		InterfazEstrategia iE;
		if (ctrEstrategia.isJug1()) {
			iE = ctrEstrategia.getCtrPrincipal().getiJugador2();
		} else {
			iE = ctrEstrategia.getCtrPrincipal().getiJugador1();
		}
		return iE != null && iE instanceof InterfazHumano;
	}
	
	private void actualizarInformacionAyudaEntrenamiento() {
		String info = "Entrenando...";
		info += "\nTenga paciencia, ";
		info += "el entrenamiento puede durar varios minutos o incluso horas, dependiendo del tamaño del juego y del número de partidas de entrenamiento.";
		pantallaConfiguracionEvaluador.actualizarInfo(info);
		pantallaConfiguracionEvaluador.update(pantallaConfiguracionEvaluador.getGraphics());
	}
	
	private void actualizarInformacionAyudaEntrenamientoTerminado() {
		String info = "Entrenamiento terminado";
		pantallaConfiguracionEvaluador.actualizarInfo(info);
		pantallaConfiguracionEvaluador.update(pantallaConfiguracionEvaluador.getGraphics());
	}
}
