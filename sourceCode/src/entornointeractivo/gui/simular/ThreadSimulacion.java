package entornointeractivo.gui.simular;

import entornointeractivo.gui.principal.ControladorPrincipal;
import juegos.Juego;

public class ThreadSimulacion implements Runnable {

	private int nPartidas;
	private ControladorPrincipal ctrPrincipal;
	private ControladorSimulacion ctrSimulacion;
	private PantallaSimulacion pantallaSimulacion;
	private double[] resultados;
	private boolean fin;
	
	public ThreadSimulacion(int nPartidas, ControladorPrincipal ctrPrincipal, PantallaSimulacion pantallaSimulacion, ControladorSimulacion ctrSimulacion) {
		this.nPartidas = nPartidas;
		this.ctrPrincipal = ctrPrincipal;
		this.pantallaSimulacion = pantallaSimulacion;
		this.ctrSimulacion = ctrSimulacion;
		resultados = null;
		fin = false;
	}
	
	@Override
	public void run() {
		resultados = Juego.nPartidasGUI(nPartidas, ctrPrincipal.getJugador1(), ctrPrincipal.getJugador2(), ctrPrincipal.getEstadoJuego(), pantallaSimulacion);
		if (!fin) {
			ctrSimulacion.finSimulacion(resultados, nPartidas);
		}
	}

	public double[] resultados() {
		return resultados;
	}
	
	public void fin() {
		fin = true;
	}
}
