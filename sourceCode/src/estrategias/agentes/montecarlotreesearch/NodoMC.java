package estrategias.agentes.montecarlotreesearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import juegos.EstadoJuego;

/**
 * Información a almacenar en un nodo por el método de Monte-Carlo.
 *  
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 24/08/2011
 *
 */
public class NodoMC {

	private EstadoJuego s;			// Estado actual.
	private List<EstadoJuego> A;	// Estado accesibles desde s.
	private int ns;					// Número total de simulaciones realizadas desde el estado s.
	private List<Integer> nsa;		// Número total de simulaciones en las que se selecciona a.
	private List<Double> qsa;		// Valor de Monte-Carlo
	
	public NodoMC(EstadoJuego s) {
		this.s = s;
		ns = 0;
		A = s.hijos();
		nsa = new ArrayList<Integer>(A.size());
		qsa = new ArrayList<Double>(A.size());
		for (int i = 0; i < A.size(); i++) {
			nsa.add(0);
			qsa.add(0.0);
		}
		
	}
	
	public boolean equals(Object o) {
		return o instanceof NodoMC && s.equals(((NodoMC) o).getS());
	}

	/**
	 * @return the s
	 */
	public EstadoJuego getS() {
		return s;
	}


	/**
	 * @return the a
	 */
	public List<EstadoJuego> getA() {
		return A;
	}


	/**
	 * @return the ns
	 */
	public int getNs() {
		return ns;
	}

	
	/**
	 * @return the nsa
	 */
	public List<Integer> getNsa() {
		return nsa;
	}

	/**
	 * @return the qsa
	 */
	public List<Double> getQsa() {
		return qsa;
	}

	/**
	 * @param ns the ns to set
	 */
	public void setNs(int ns) {
		this.ns = ns;
	}

	public void setNsa(int i, int sa) {
		this.nsa.set(i, sa);
	}


	public void setQsa(int i, double qa) {
		this.qsa.set(i, qa);
	}

	public EstadoJuego argmax(double c) {
		List<Double> valores = new ArrayList<Double>();
		for (int i = 0; i < qsa.size(); i++) {
			valores.add(qsa.get(i) + c * Math.sqrt(Math.log(ns)/nsa.get(i)));
		}
		return A.get(valores.indexOf(Collections.max(valores))); 
	}
	
	public EstadoJuego argmin(double c) {
		List<Double> valores = new ArrayList<Double>();
		for (int i = 0; i < qsa.size(); i++) {
			valores.add(qsa.get(i) - c * Math.sqrt(Math.log(ns)/nsa.get(i)));
		}
		return A.get(valores.indexOf(Collections.min(valores))); 
	}
}
