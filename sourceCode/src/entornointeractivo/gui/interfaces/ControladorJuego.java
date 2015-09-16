package entornointeractivo.gui.interfaces;

import juegos.EstadoJuego;
import entornointeractivo.gui.jugar.ControladorPantallaJuego;
import estrategias.agentes.Jugador;

/**
 * Controlador del juego para la interfaz gráfica.
 * 
 * @author José Miguel Horcas Aguilera
 *
 */
public abstract class ControladorJuego implements Runnable {

	protected Jugador jug1;
	protected Jugador jug2;
	protected EstadoJuego estadoJuego;
	protected PanelJuego panelJuego;
	protected ControladorPantallaJuego ctrPantallaJuego;
	//private boolean terminar;
	
	public ControladorJuego(EstadoJuego estadoJuego, Jugador jug1, Jugador jug2, PanelJuego panelJuego, ControladorPantallaJuego ctrPantallaJuego) {
		this.estadoJuego = estadoJuego;
		this.jug1 = jug1;
		this.jug2 = jug2;
		this.panelJuego = panelJuego;
		this.ctrPantallaJuego = ctrPantallaJuego;
		//terminar = false;
		
		this.panelJuego.registrarControlador(this);
	}

	/**
	 * @return the jug1
	 */
	public Jugador getJug1() {
		return jug1;
	}

	/**
	 * @param jug1 the jug1 to set
	 */
	public void setJug1(Jugador jug1) {
		this.jug1 = jug1;
	}

	/**
	 * @return the jug2
	 */
	public Jugador getJug2() {
		return jug2;
	}

	/**
	 * @param jug2 the jug2 to set
	 */
	public void setJug2(Jugador jug2) {
		this.jug2 = jug2;
	}

	/**
	 * @return the estadoJuego
	 */
	public EstadoJuego getEstadoJuego() {
		return estadoJuego;
	}

	/**
	 * @param estadoJuego the estadoJuego to set
	 */
	public void setEstadoJuego(EstadoJuego estadoJuego) {
		this.estadoJuego = estadoJuego;
	}

	/**
	 * @return the panelJuego
	 */
	public PanelJuego getPanelJuego() {
		return panelJuego;
	}

	/**
	 * @param panelJuego the panelJuego to set
	 */
	public void setPanelJuego(PanelJuego panelJuego) {
		this.panelJuego = panelJuego;
	}
	
	
	// metodo que hay que llamar al final del juego (cuando la partida termina).
	// este metodo se implementa aqui y lo que hace es llamar al controlador principal para que este siga con la lógica de la aplicación.
	/**
	 * Debe ser invocado al acabar la partida.
	 * 
	 * @param ganador	1 si gana el jugador 1, -1 si gana el jugador 2 y 0 si hay empate.
	 * @param msg		Mensaje informativo.
	 */
	public void finJuego(int ganador, String msg) {
		ctrPantallaJuego.finPartida(ganador, msg);
	}
	
	@Override
	public void run() {
		try {
			jugar();	
		} catch (Exception e) {}
	}
	/*
	public void siguienteMovimiento() {
		if (!terminar) {
			try {
				jugar();	
			} catch (Exception e){}
		}
	}
	*/
	/**
	 * @param terminar the terminar to set
	 */
/*	public void setTerminar(boolean terminar) {
		this.terminar = terminar;
	}*/

	/**
	 * Se llamará para iniciar la partida.
	 * Se encarga de controlar el desarrollo de la partida.
	 */
	public abstract void jugar();

	public void desactivarControlador() {
		panelJuego.setEnabled(false);
		panelJuego.setVisible(false);
	}
	
	/**
	 * Actualiza la información sobre la partida que se mostrará en el área de la pantalla de juego dedicada a mostrar la información del desarrollo de la partida.
	 * 
	 * @param info	Información.
	 */
	public void actualizarInfoPartida(String info) {
		ctrPantallaJuego.actualizarInfoPartida(info);
	}
	
	/**
	 * Actualiza información sobre el movimiento actual u otra información adicional de ayuda.
	 * 
	 * @param info	Información.
	 */
	public void actualizarInfoAyuda(String info) {
		ctrPantallaJuego.actualizarInfo(info);
	}
}
