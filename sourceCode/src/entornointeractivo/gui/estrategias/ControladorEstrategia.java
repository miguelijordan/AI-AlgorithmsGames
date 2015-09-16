package entornointeractivo.gui.estrategias;

import entornointeractivo.gui.Estrategias;
import entornointeractivo.gui.InterfazEstrategia;
import entornointeractivo.gui.InterfazEvaluador;
import entornointeractivo.gui.interfaces.PanelConfiguracionEstrategia;
import entornointeractivo.gui.principal.ControladorPrincipal;
import entornointeractivo.gui.util.InformacionEntrenamiento;
import estrategias.agentes.Jugador;
import estrategias.agentes.evaluar.JugadorEvaluar;

import heuristicos.evaluadores.Evaluador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;

import juegos.EstadoJuego;

public class ControladorEstrategia implements ActionListener, MouseListener {

	private PantallaEstrategiaJugador pEstrategia;
	private PanelConfiguracionEstrategia pConfiguracion;
	private boolean jug1;
	private ControladorPrincipal ctrPrincipal;
	private InterfazEstrategia iEstrategia;
	private Jugador jugador;
	private Evaluador evaluador;
	private EstadoJuego estadoJuego;
	private InterfazEvaluador iEvaluador;
	
	private InformacionEntrenamiento infoEntrenamiento1;
	private InformacionEntrenamiento infoEntrenamiento2;
	
	
	
	public ControladorEstrategia(boolean jug1, EstadoJuego estadoJuego, ControladorPrincipal ctrPrincipal) {
		this.jug1 = jug1;
		this.estadoJuego = estadoJuego;
		this.ctrPrincipal = ctrPrincipal;
		this.evaluador = null;
		this.iEvaluador = null;
		infoEntrenamiento1 = null;
		infoEntrenamiento2 = null;
		
		pEstrategia = new PantallaEstrategiaJugador(jug1);
		pEstrategia.registrarControlador(this);
		pEstrategia.registrarControladorInformacion(this);
		pEstrategia.setVisible(true);
		
		// La primera estrategia seleccionable siempre será el jugador humano.
		iEstrategia = ((Estrategias) pEstrategia.getCmbEstrategia().getSelectedItem()).getInterfaz();
		pEstrategia.actualizarInfoEstrategia(iEstrategia.informacion());
		pConfiguracion = iEstrategia.panelConfiguracion(estadoJuego, this);
		if (pConfiguracion == null) {
			jugador = iEstrategia.estrategia(estadoJuego);
		} else {
			jugador = pConfiguracion.estrategia();
			pEstrategia.cargarPanelConfiguracion(pConfiguracion);
			pConfiguracion.registrarControladorInformacion(this);
			//pConfiguracion.setControladorEstrategia(this);
		}
		// controladorEstrategia = null;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		String info = pEstrategia.infoComponente(e.getComponent());
		if (info == null) {
			info = pConfiguracion.infoComponente(e.getComponent());
		}
		actualizarInfo(info);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		actualizarInfo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals("Estrategia")) {
			iEvaluador = null;
			JComboBox cb = (JComboBox)e.getSource();
			iEstrategia = ((Estrategias) cb.getSelectedItem()).getInterfaz();
			pEstrategia.actualizarInfoEstrategia(iEstrategia.informacion());
			pConfiguracion = iEstrategia.panelConfiguracion(estadoJuego, this);
			if (pConfiguracion == null) {
				jugador = iEstrategia.estrategia(estadoJuego);
				pEstrategia.cargarPanelConfiguracion(null);
				//this.iEvaluador = null;
			} else {
				jugador = pConfiguracion.estrategia();
				pEstrategia.cargarPanelConfiguracion(pConfiguracion);
				pConfiguracion.registrarControladorInformacion(this);
				//pConfiguracion.setControladorEstrategia(this);
				
			}
			// A LO MEJOR HACE FALTA HACERLE UN PACK DENTRO DE cargarPanelConfiguracion
			// controladorEstrategia = null;
		} else	if (command.equals("Aceptar")) {
			if (pConfiguracion == null) { // Obtiene la estrategia por defecto.
				jugador = iEstrategia.estrategia(estadoJuego);
			} else { // Obtiene la estrategia configurada por el panel.
				jugador = pConfiguracion.estrategia();	
			}
			InterfazEstrategia iEstrategiaSeleccionada = ((Estrategias) pEstrategia.getCmbEstrategia().getItemAt(0)).getInterfaz();
			if (jugador == null && iEstrategia.equals(iEstrategiaSeleccionada)) { // Obtiene la estrategia humana.
				jugador = ctrPrincipal.getiJuego().estrategiaHumana();
			}
			if (jugador != null) {
				if (jug1) {	// Actualiza los jugadores.
					ctrPrincipal.setJugador1(jugador);
					ctrPrincipal.setiJugador1(iEstrategia);
					ctrPrincipal.setiEvJugador1(iEvaluador);
				} else {
					ctrPrincipal.setJugador2(jugador);
					ctrPrincipal.setiJugador2(iEstrategia);
					ctrPrincipal.setiEvJugador2(iEvaluador);
				}
				actualizarInformacionEntrenamiento();
				pEstrategia.dispose();
				ctrPrincipal.continuar();
			}
		} else if (command.equals("Cancelar")) {
			pEstrategia.dispose();
			ctrPrincipal.continuar();
		}
	}
	
	public Jugador getJugador() {
		Jugador jug;
		if (pConfiguracion == null) {
			jug = iEstrategia.estrategia(estadoJuego);
		} else {
			jug = pConfiguracion.estrategia();
		}
		return jug;
	}

	public boolean isJug1() {
		return jug1;
	}

	public void actualizarInfo(String info) {
		pEstrategia.actualizarInfo(info);
	}

	/**
	 * @param evaluador the evaluador to set
	 */
	public void setEvaluador(Evaluador evaluador) {
		this.evaluador = evaluador;
		((JugadorEvaluar)this.jugador).setEvaluador(this.evaluador);
	}
	
	public void setiEvaluador(InterfazEvaluador iEvaluador) {
		this.iEvaluador = iEvaluador;
	}

	public Evaluador getEvaluador() {
		return evaluador;
	}

	public ControladorPrincipal getCtrPrincipal() {
		return ctrPrincipal;
	}

	public EstadoJuego getEstadoJuego() {
		return estadoJuego;
	}

	public PanelConfiguracionEstrategia getpConfiguracion() {
		return pConfiguracion;
	}	
	
	public void continuar() {
		this.pEstrategia.setEnabled(true);
		this.pEstrategia.setVisible(true);
	}

	public InformacionEntrenamiento getInfoEntrenamiento1() {
		return infoEntrenamiento1;
	}

	public void setInfoEntrenamiento1(InformacionEntrenamiento infoEntrenamiento1) {
		this.infoEntrenamiento1 = infoEntrenamiento1;
	}

	public InformacionEntrenamiento getInfoEntrenamiento2() {
		return infoEntrenamiento2;
	}

	public void setInfoEntrenamiento2(InformacionEntrenamiento infoEntrenamiento2) {
		this.infoEntrenamiento2 = infoEntrenamiento2;
	}
	
	private void actualizarInformacionEntrenamiento() {
		if (isJug1()) {
			if (iEvaluador != null && iEvaluador.entrenable()) {
				if (infoEntrenamiento1 == null) {
					getCtrPrincipal().setInfoEntrenamientoJug1(new InformacionEntrenamiento());	
				} else {
					getCtrPrincipal().setInfoEntrenamientoJug1(infoEntrenamiento1);
				}
			} else {
				getCtrPrincipal().setInfoEntrenamientoJug1(null);
			}
		} else {
			if (iEvaluador != null && iEvaluador.entrenable()) {
				if (infoEntrenamiento2 == null) {
					getCtrPrincipal().setInfoEntrenamientoJug2(new InformacionEntrenamiento());
				} else {
					getCtrPrincipal().setInfoEntrenamientoJug2(infoEntrenamiento2);
				}
			} else {
				getCtrPrincipal().setInfoEntrenamientoJug2(null);
			}
		}
	}

	public void desactivarPantalla() {
		pEstrategia.setEnabled(false);
	}
}
