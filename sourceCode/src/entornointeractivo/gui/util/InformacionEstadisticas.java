package entornointeractivo.gui.util;

import javax.swing.JPanel;

import juegos.EstadoJuego;

public class InformacionEstadisticas {

	private double[] resultados;
	private int nPartidas;
	private long tiempo;
	private String ganador;
	private JPanel estadoFinal;
	private JPanel estadoInicial;
	private JPanel estadoMovJ1;
	private JPanel estadoMovJ2;
	private long tiempoAnalisisJ1;
	private long tiempoAnalisisJ2;
	
	private EstadoJuego estadoJuegoInicial;
	private EstadoJuego estadoJuegoFinal;
	private EstadoJuego estadoJuegoMov1;
	private EstadoJuego estadoJuegoMov2;
	

	public InformacionEstadisticas(double[] resultados, int nPartidas, long tiempo, String ganador) {
		this.resultados = resultados;
		this.nPartidas = nPartidas;
		this.tiempo = tiempo;
		this.ganador = ganador;
		estadoInicial = null;
		estadoMovJ1 = null;
		estadoMovJ2 = null;
		estadoJuegoInicial = null;
		estadoJuegoFinal = null;
		estadoJuegoMov1 = null;
		estadoJuegoMov2 = null;
	}
	
	
	/**
	 * @return the estadoJuegoInicial
	 */
	public EstadoJuego getEstadoJuegoInicial() {
		return estadoJuegoInicial;
	}


	/**
	 * @param estadoJuegoInicial the estadoJuegoInicial to set
	 */
	public void setEstadoJuegoInicial(EstadoJuego estadoJuegoInicial) {
		this.estadoJuegoInicial = estadoJuegoInicial;
	}


	/**
	 * @return the estadoJuegoFinal
	 */
	public EstadoJuego getEstadoJuegoFinal() {
		return estadoJuegoFinal;
	}


	/**
	 * @param estadoJuegoFinal the estadoJuegoFinal to set
	 */
	public void setEstadoJuegoFinal(EstadoJuego estadoJuegoFinal) {
		this.estadoJuegoFinal = estadoJuegoFinal;
	}


	/**
	 * @return the estadoJuegoMov1
	 */
	public EstadoJuego getEstadoJuegoMov1() {
		return estadoJuegoMov1;
	}


	/**
	 * @param estadoJuegoMov1 the estadoJuegoMov1 to set
	 */
	public void setEstadoJuegoMov1(EstadoJuego estadoJuegoMov1) {
		this.estadoJuegoMov1 = estadoJuegoMov1;
	}


	/**
	 * @return the estadoJuegoMov2
	 */
	public EstadoJuego getEstadoJuegoMov2() {
		return estadoJuegoMov2;
	}


	/**
	 * @param estadoJuegoMov2 the estadoJuegoMov2 to set
	 */
	public void setEstadoJuegoMov2(EstadoJuego estadoJuegoMov2) {
		this.estadoJuegoMov2 = estadoJuegoMov2;
	}


	public JPanel getEstadoFinal() {
		return estadoFinal;
	}

	public void setEstadoFinal(JPanel estadoFinal) {
		this.estadoFinal = estadoFinal;
	}
	
	public double[] getResultados() {
		return resultados;
	}

	public int getnPartidas() {
		return nPartidas;
	}

	public void setResultados(double[] resultados) {
		this.resultados = resultados;
	}

	public void setnPartidas(int nPartidas) {
		this.nPartidas = nPartidas;
	}

	public long getTiempo() {
		return tiempo;
	}

	public void setTiempo(long tiempo) {
		this.tiempo = tiempo;
	}

	public String getGanador() {
		return ganador;
	}

	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	public JPanel getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(JPanel estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	public JPanel getEstadoMovJ1() {
		return estadoMovJ1;
	}

	public void setEstadoMovJ1(JPanel estadoMovJ1) {
		this.estadoMovJ1 = estadoMovJ1;
	}

	public JPanel getEstadoMovJ2() {
		return estadoMovJ2;
	}

	public void setEstadoMovJ2(JPanel estadoMovJ2) {
		this.estadoMovJ2 = estadoMovJ2;
	}

	public long getTiempoAnalisisJ1() {
		return tiempoAnalisisJ1;
	}

	public void setTiempoAnalisisJ1(long tiempoAnalisisJ1) {
		this.tiempoAnalisisJ1 = tiempoAnalisisJ1;
	}

	public long getTiempoAnalisisJ2() {
		return tiempoAnalisisJ2;
	}

	public void setTiempoAnalisisJ2(long tiempoAnalisisJ2) {
		this.tiempoAnalisisJ2 = tiempoAnalisisJ2;
	}
	
	
}
