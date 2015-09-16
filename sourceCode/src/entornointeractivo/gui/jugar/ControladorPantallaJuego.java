package entornointeractivo.gui.jugar;

import entornointeractivo.gui.InterfazJuego;
import entornointeractivo.gui.interfaces.ControladorJuego;
import entornointeractivo.gui.interfaces.PanelJuego;
import entornointeractivo.gui.principal.ControladorPrincipal;
import entornointeractivo.gui.util.InformacionEstadisticas;
import estrategias.agentes.Jugador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import juegos.EstadoJuego;

public class ControladorPantallaJuego implements ActionListener, MouseListener {

	private PantallaJuego pJuego;
	private PanelJuego panelJuego;
	private ControladorPrincipal ctrPrincipal;
	private long tiempo;
	
	private EstadoJuego estadoJuego;

	
	private Jugador jug1;
	private Jugador jug2;
	private Jugador jugHumano1;
	private Jugador jugHumano2;
	
	private ControladorJuego ctrJuego;
	private Thread threadJuego;
	
	public ControladorPantallaJuego(InterfazJuego juego, EstadoJuego estadoJuego, Jugador jug1, Jugador jug2, ControladorPrincipal ctrPrincipal, boolean analizar) {
		this.ctrPrincipal = ctrPrincipal;
		this.estadoJuego = estadoJuego;
		this.jug1 = jug1;
		this.jug2 = jug2;
		
		this.panelJuego = juego.panelJuego(estadoJuego);
		pJuego = new PantallaJuego(juego.nombre(), this.panelJuego);
		pJuego.actualizarInfoJug1(ctrPrincipal.infoJugador1());
		pJuego.actualizarInfoJug2(ctrPrincipal.infoJugador2());
		pJuego.registrarControlador(this);
		pJuego.registrarControladorInformacion(this);
		
		if (analizar) {
			pJuego.getBtnCancelarPartida().setVisible(false);
			pJuego.getBtnCancelarPartida().setEnabled(false);
			this.jugHumano1 = juego.estrategiaHumana();
			this.jugHumano2 = juego.estrategiaHumana();
			this.ctrJuego = juego.controladorJuego(estadoJuego, jugHumano1, jugHumano2, this.panelJuego, this);
		} else {
			this.jugHumano1 = null;
			this.jugHumano2 = null;
			pJuego.getBtnAnalizar().setVisible(false);
			pJuego.getBtnAnalizar().setEnabled(false);
			this.ctrJuego = juego.controladorJuego(estadoJuego, jug1, jug2, this.panelJuego, this);
		}
		pJuego.registrarControladorJuego(this.ctrJuego);
		pJuego.pack();
		pJuego.setVisible(true);
		pJuego.update(pJuego.getGraphics());
		tiempo = System.currentTimeMillis();
		
		threadJuego = new Thread(ctrJuego);
		threadJuego.setPriority(Thread.MAX_PRIORITY);
		threadJuego.start();
		
		//ctrJuego.jugar();			// Sin thread.
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals("Analizar")) {
			ctrJuego.desactivarControlador();
			long tiempoJ1;
			long tiempoJ2;
			EstadoJuego estadoPreAnalisis;
			EstadoJuego estadoPosAnalisis1;
			EstadoJuego estadoPosAnalisis2;
			
			actualizarInformacionAyudaAnalisis();
			
			estadoPreAnalisis = ctrJuego.getEstadoJuego();
			
			actualizarInfo("Realizando movimiento del jugador 1...");
			tiempoJ1 = System.currentTimeMillis();
			estadoPosAnalisis1 = jug1.mueve(estadoPreAnalisis);
			tiempoJ1 = System.currentTimeMillis() - tiempoJ1;
				
			actualizarInfo("Realizando movimiento del jugador 2...");
			tiempoJ2 = System.currentTimeMillis();
			estadoPosAnalisis2 = jug2.mueve(estadoPreAnalisis);
			tiempoJ2 = System.currentTimeMillis() - tiempoJ2;
			
			// COGER ESTADISTICAS, DESTRUIR VENTANA JUEGO, LLAMAR A CONTROLADOR PRINCIPAL Y MOSTRAR PANTALLA ESTADISTICAS CON LOS ESTADOS PRE Y PRO.
			//JOptionPane.showMessageDialog(new JFrame(), mensaje);
			pJuego.dispose();
			InformacionEstadisticas infoEstadisticas = new InformacionEstadisticas(null, 1, 0, null);
			infoEstadisticas.setEstadoJuegoInicial(estadoPreAnalisis);
			infoEstadisticas.setEstadoJuegoMov1(estadoPosAnalisis1);
			infoEstadisticas.setEstadoJuegoMov2(estadoPosAnalisis2);
			infoEstadisticas.setEstadoInicial(panelJuego.panelJuegoEstado(estadoPreAnalisis));
			infoEstadisticas.setEstadoMovJ1(panelJuego.panelJuegoEstado(estadoPosAnalisis1));
			infoEstadisticas.setEstadoMovJ2(panelJuego.panelJuegoEstado(estadoPosAnalisis2));
			infoEstadisticas.setTiempoAnalisisJ1(tiempoJ1);
			infoEstadisticas.setTiempoAnalisisJ2(tiempoJ2);
			
			ctrPrincipal.finAnalisis(infoEstadisticas);
		} else if (command.equals("Cancelar")) {
			//ctrJuego.setTerminar(true);
			//threadJuego.stop();
			threadJuego.interrupt();
			pJuego.dispose();
			ctrPrincipal.continuar();
		}
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

	/**
	 * @return the threadJuego
	 */
	public Thread getThreadJuego() {
		return threadJuego;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		String info = pJuego.infoComponente(e.getComponent());
		actualizarInfo(info);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		actualizarInfo(null);
	}

	/**
	 * Actualiza la información de ayuda.
	 * 
	 * @param info	Información.
	 */
	public void actualizarInfo(String info) {
		pJuego.actualizarInfo(info);
	}

	public void actualizarInfoPartida(String info) {
		pJuego.actualizarInfoPartida(info);
	}
	
	/**
	 * 
	 * @param res		1 si gana el jugador 1, -1 si gana el jugador 2 y 0 si hay empate.
	 * @param mensaje	Mensaje informativo.
	 */
	public void finPartida(int res, String mensaje) {
		tiempo = System.currentTimeMillis() - tiempo;
		JOptionPane.showMessageDialog(new JFrame(), mensaje);
		actualizarInfo("Fin del juego. " + mensaje);
		
		// destruir la ventana y llamar de nuevo al controlador principal para que cree la siguiente ventana (de resultados).
		this.pJuego.dispose();
		String ganador;
		if (res == 1) {
			ganador = "Ganador: Jugador 1";
		} else if (res == -1) {
			ganador = "Ganador: Jugador 2";
		} else if (res == 0) {
			ganador = "Empate";
		} else {
			ganador = "";
		}
		InformacionEstadisticas infoEstadisticas = new InformacionEstadisticas(null, 1, tiempo, ganador);
		infoEstadisticas.setEstadoJuegoFinal(ctrJuego.getEstadoJuego());
		infoEstadisticas.setEstadoFinal(panelJuego.panelJuegoEstado(ctrJuego.getEstadoJuego()));
		ctrPrincipal.finJuego(infoEstadisticas);
	}
	
	private void actualizarInformacionAyudaAnalisis() {
		String info = "Analizando estado...";
		info += "\nTenga paciencia, ";
		info += "el movimiento de cada jugador puede tardar varios minutos o incluso horas, dependiendo del tamaño del juego y la estrategia seleccionada.";
		pJuego.actualizarInfo(info);
		pJuego.actualizarGraficosInfo();
	}
}
