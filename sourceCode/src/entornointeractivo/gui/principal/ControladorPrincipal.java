package entornointeractivo.gui.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import juegos.EstadoJuego;
import entornointeractivo.gui.Estrategias;
import entornointeractivo.gui.InterfazEstrategia;
import entornointeractivo.gui.InterfazEvaluador;
import entornointeractivo.gui.InterfazJuego;
import entornointeractivo.gui.Juegos;
import entornointeractivo.gui.estrategias.ControladorEstrategia;
import entornointeractivo.gui.interfaces.ControladorJuego;
import entornointeractivo.gui.juegos.ControladorOpcionesJuego;
import entornointeractivo.gui.jugar.ControladorPantallaJuego;
import entornointeractivo.gui.resultados.ControladorEstadisticas;
import entornointeractivo.gui.resultados.PanelResultadosAnalizar;
import entornointeractivo.gui.resultados.PanelResultadosJugar;
import entornointeractivo.gui.resultados.PanelResultadosSimulacion;
import entornointeractivo.gui.simular.ControladorSimulacion;
import entornointeractivo.gui.util.InformacionEntrenamiento;
import entornointeractivo.gui.util.InformacionEstadisticas;
import estrategias.agentes.Jugador;
import estrategias.agentes.estadisticas.EstadisticasJugador;
import estrategias.agentes.gui.humano.InterfazHumano;

public class ControladorPrincipal implements ActionListener, MouseListener {

	private PantallaPrincipal pPrincipal;
	private Jugador jugador1;
	private Jugador jugador2;
	private EstadoJuego estadoJuego;
	private ControladorJuego controladorJuego;
	
	private InterfazJuego iJuego;							// Juego seleccionado.
	private InterfazEstrategia iJugador1;
	private InterfazEstrategia iJugador2;
	private InterfazEvaluador iEvJugador1;
	private InterfazEvaluador iEvJugador2;
	private InformacionEntrenamiento infoEntrenamientoJug1;
	private InformacionEntrenamiento infoEntrenamientoJug2;
	
	
	public ControladorPrincipal() {
		this.iJugador1 = Estrategias.HUMANO.getInterfaz();
		this.iEvJugador1 = null;
		this.iJugador2 = Estrategias.HUMANO.getInterfaz();
		this.iEvJugador2 = null;
		infoEntrenamientoJug1 = null;
		infoEntrenamientoJug2 = null;
		
		
		pPrincipal = new PantallaPrincipal();
		pPrincipal.setVisible(true);
		pPrincipal.registrarControlador(this);
		pPrincipal.registrarControladorInformacion(this);
		
		iJuego = ((Juegos) pPrincipal.getCmbJuego().getSelectedItem()).getInterfaz();
		estadoJuego = iJuego.estadoJuego();
		actualizarInfoJuego();
		controladorJuego = null;
		
		inicializarJugadores();
		/*
		InterfazEstrategia iEstr = Estrategias.values()[0].getInterfaz();
		PanelConfiguracionEstrategia pConfEstr = iEstr.panelConfiguracion(estadoJuego); 
		this.jugador1 = (pConfEstr == null) ? iEstr.estrategia(estadoJuego) : pConfEstr.estrategia();
		this.jugador2 = (pConfEstr == null) ? iEstr.estrategia(estadoJuego) : pConfEstr.estrategia();
		if (this.jugador1 == null) {
			JOptionPane.showMessageDialog(new JFrame(), "ERROR. No se proporciona una estrategia: " + iEstr.getClass().getName());
			System.exit(0);
		}*/
	}
	
	public void inicializarJugadores() {
		this.iJugador1 = Estrategias.HUMANO.getInterfaz();
		this.iJugador2 = Estrategias.HUMANO.getInterfaz();
		this.jugador1 = iJuego.estrategiaHumana();
		this.jugador2 = iJuego.estrategiaHumana();
		if (this.jugador1 == null) {
			JOptionPane.showMessageDialog(new JFrame(), "El juego seleccionado no proporciona una estrategia humana.");
			System.exit(0);
		}
		infoEntrenamientoJug1 = null;
		infoEntrenamientoJug2 = null;
		actualizarInfoJugadores();
		activarBotonSimularAnalizarOpcionesjuegos();
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
		String info = pPrincipal.infoComponente(e.getComponent());
		actualizarInfo(info);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		actualizarInfo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals("Jugador1")) {
			pPrincipal.setEnabled(false);
			new ControladorEstrategia(true, estadoJuego, this);
		} else if (command.equals("Jugador2")) {
			pPrincipal.setEnabled(false);
			new ControladorEstrategia(false, estadoJuego, this);
		} else if (command.equals("Juego")) {
			JComboBox cb = (JComboBox)e.getSource();
			iJuego = ((Juegos) cb.getSelectedItem()).getInterfaz();
			estadoJuego = iJuego.estadoJuego();
			pPrincipal.setTextoJuego(iJuego.informacion(estadoJuego));
			//get del estado del juego que me lo devuelve la pantalla de configuracion del juego.
			inicializarJugadores();
		} else if (command.equals("Opciones")) {
			pPrincipal.setEnabled(false);
			new ControladorOpcionesJuego(iJuego, this);
		} else if (command.equals("Jugar")) {
			new ControladorPantallaJuego(iJuego, estadoJuego, jugador1, jugador2, this, false);
			// actualizar la info de los jugadores en la pantalla de juego
			pPrincipal.setEnabled(false);
		} else if (command.equals("Simular")) {
			pPrincipal.setEnabled(false);
			new ControladorSimulacion(this);
		} else if (command.equals("Analizar")) {
			new ControladorPantallaJuego(iJuego, estadoJuego, jugador1, jugador2, this, true);
			// actualizar la info de los jugadores en la pantalla de juego
			pPrincipal.setEnabled(false);
		}
	}
	
	private void actualizarInfo(String info) {
		pPrincipal.actualizarInfo(info);
	}

	/**
	 * @return the pPrincipal
	 */
	public PantallaPrincipal getpPrincipal() {
		return pPrincipal;
	}

	/**
	 * @param estadoJuego the estadoJuego to set
	 */
	public void setEstadoJuego(EstadoJuego estadoJuego) {
		this.estadoJuego = estadoJuego;
	}

	/**
	 * @param jugador1 the jugador1 to set
	 */
	public void setJugador1(Jugador jugador1) {
		this.jugador1 = jugador1;
	}

	/**
	 * @param jugador2 the jugador2 to set
	 */
	public void setJugador2(Jugador jugador2) {
		this.jugador2 = jugador2;
	}
	
	public void continuar() {
		pPrincipal.setEnabled(true);	
		pPrincipal.setVisible(true);	
		actualizarInfoJugadores();
		actualizarInfoJuego();
		activarBotonSimularAnalizarOpcionesjuegos();
		inicializarEstadisticas();
		
		/*System.out.println("ControladorPrincipal: continuar().");
		System.out.println("J1: " + jugador1.getClass().toString());
		System.out.println("J2: " + jugador2.getClass().toString());
		if (iEvJugador1 == null) {
			System.out.println("J1ev: nulo");
		} else {
			System.out.println("J1ev: " + this.iEvJugador1.getClass().toString());
			//System.out.println(((JugadorEvaluar) jugador1).getEvaluador().getClass().toString());
		}
		if (iEvJugador2 == null) {
			System.out.println("J2ev: nulo");
		} else {
			System.out.println("J2ev: " + this.iEvJugador2.getClass().toString());	
			//System.out.println(((JugadorEvaluar) jugador2).getEvaluador().getClass().toString());
		}*/
	}

	public Jugador getJugador1() {
		return jugador1;
	}

	public Jugador getJugador2() {
		return jugador2;
	}

	public InterfazEstrategia getiJugador1() {
		return iJugador1;
	}

	public void setiJugador1(InterfazEstrategia iJugador1) {
		this.iJugador1 = iJugador1;
	}

	public InterfazEstrategia getiJugador2() {
		return iJugador2;
	}

	public void setiJugador2(InterfazEstrategia iJugador2) {
		this.iJugador2 = iJugador2;
	}

	public InterfazEvaluador getiEvJugador1() {
		return iEvJugador1;
	}

	public void setiEvJugador1(InterfazEvaluador iEvJugador1) {
		this.iEvJugador1 = iEvJugador1;
	}

	public InterfazEvaluador getiEvJugador2() {
		return iEvJugador2;
	}

	public void setiEvJugador2(InterfazEvaluador iEvJugador2) {
		this.iEvJugador2 = iEvJugador2;
	}

	public InterfazJuego getiJuego() {
		return iJuego;
	}
	
	private void actualizarInfoJugadores() {
		pPrincipal.setTextoJug1(infoJugador1());
		pPrincipal.setTextoJug2(infoJugador2());
	}
	
	private void actualizarInfoJuego() {
		pPrincipal.setTextoJuego(iJuego.informacion(estadoJuego));
	}

	public void setInfoEntrenamientoJug1(InformacionEntrenamiento infoEntrenamientoJug1) {
		this.infoEntrenamientoJug1 = infoEntrenamientoJug1;
	}

	public void setInfoEntrenamientoJug2(InformacionEntrenamiento infoEntrenamientoJug2) {
		this.infoEntrenamientoJug2 = infoEntrenamientoJug2;
	}

	public EstadoJuego getEstadoJuego() {
		return estadoJuego;
	}
	
	private void activarBotonSimularAnalizarOpcionesjuegos() {
		if (iJugador1 == null || iJugador1 instanceof InterfazHumano || iJugador2 == null || iJugador2 instanceof InterfazHumano) {
			pPrincipal.getBtnSimular().setEnabled(false);
			pPrincipal.getBtnAnalizarEstado().setEnabled(false);
		} else {
			pPrincipal.getBtnSimular().setEnabled(true);
			pPrincipal.getBtnAnalizarEstado().setEnabled(true);
		}
		if (iJuego != null && iJuego.panelOpciones() != null) {
			pPrincipal.getBtnOpcionesJuego().setEnabled(true);
		} else {
			pPrincipal.getBtnOpcionesJuego().setEnabled(false);
		}
	}
	
	public void finJuego(InformacionEstadisticas infoEstadisticas) {
		new ControladorEstadisticas(infoEstadisticas, new PanelResultadosJugar(), this);
	}
	
	public void finSimulacion(InformacionEstadisticas infoEstadisticas) {
		new ControladorEstadisticas(infoEstadisticas, new PanelResultadosSimulacion(), this);	
	}
	
	public void finAnalisis(InformacionEstadisticas informacionEstadisticas) {
		new ControladorEstadisticas(informacionEstadisticas, new PanelResultadosAnalizar(), this);
	}
	
	public String infoJugador1() {
		String entr = "";
		if (infoEntrenamientoJug1 != null) {
			entr = infoEntrenamientoJug1.toString();
		}
		return infoJugEntrenamiento(jugador1.toString(), entr);
	}
	
	public String infoJugador2() {
		String entr = "";
		if (infoEntrenamientoJug2 != null) {
			entr = infoEntrenamientoJug2.toString();
		}
		return infoJugEntrenamiento(jugador2.toString(), entr);
	}
	
	private String infoJugEntrenamiento(String jug, String entr) {
		String res = jug;
		if (entr != null) {
			res = jug + "\n" + entr;
		}
		return res;
	}

	private void inicializarEstadisticas() {
		try {
			((EstadisticasJugador) jugador1).inicializarEstadisticas();	
		} catch (Exception e) {}
		
		try {
			((EstadisticasJugador) jugador2).inicializarEstadisticas();	
		} catch (Exception e) {}
	}
	
	/*public void empezarDeNuevo() {
		continuar();
		inicializarEstadisticas();
	}*/

	public InformacionEntrenamiento getInfoEntrenamientoJug1() {
		return infoEntrenamientoJug1;
	}

	public InformacionEntrenamiento getInfoEntrenamientoJug2() {
		return infoEntrenamientoJug2;
	}
}
