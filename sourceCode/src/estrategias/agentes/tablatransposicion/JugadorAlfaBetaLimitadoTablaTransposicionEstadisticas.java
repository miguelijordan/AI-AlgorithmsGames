package estrategias.agentes.tablatransposicion;

import heuristicos.evaluadores.Evaluador;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import juegos.EstadoJuego;
import juegos.util.Ficha;
import estrategias.agentes.estadisticas.EstadisticasEvaluacion;
import estrategias.agentes.estadisticas.EstadisticasJugador;
import estrategias.util.Alarma;
import estrategias.util.EstadoValor;



/**
 * Estrategia minimax con poda alfa-beta, con límite de tiempo en la búsqueda y tabla de transposición.
 * Este jugador realiza una búsqueda con poda alfa-beta y dispone de un tiempo limitado para evaluar los estados de un juego.
 * Hace uso de una tabla de transposición para detectar los estados que ya han sido visitados y evaluados
 * anteriormente por otro camino.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 18/08/2011
 * 
 */
public class JugadorAlfaBetaLimitadoTablaTransposicionEstadisticas extends JugadorAlfaBetaLimitadoTablaTransposicion implements EstadisticasJugador {

	// ATRIBUTOS ESTADISTICAS
	private Map<Integer, EstadisticasEvaluacion> estadisticas;
	private int numMovimientos;
	private long tiempoTotal;
	private int tamTablaTotal;
	private int numTransposiciones;
	private int nExpansionesIteracion;		

	/**
	 * Crea un jugador alfa-beta con un evaluador, un tiempo límite de búsqueda y
	 * una tabla de transposición inicialmente vacía.
	 * 
	 * @param ev		Evaluador.
	 * @param limiteT	Límite de tiempo.
	 */
	public JugadorAlfaBetaLimitadoTablaTransposicionEstadisticas (Evaluador ev, long limiteT) {
		super(ev, limiteT);
		inicializarEstadisticas();
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		numMovimientos++;
		long tiempo = System.currentTimeMillis();
		EstadoJuego movimiento = podaAlfaBetaLimitado(e, e.fichaActual(), e.fichaOtro());
		if (movimiento == null) {
			movimiento = barajar(e.hijos()).get(0);		// Por si no le da tiempo.
		}
		tiempoTotal += System.currentTimeMillis() - tiempo;
		tamTablaTotal += tt.tamanyo();
		return movimiento;
	}
	
	@Override
	public EstadoJuego podaAlfaBetaLimitado(EstadoJuego e, Ficha fmax, Ficha fmin) {
		Alarma a = new Alarma(limiteT);
		EstadoValor mejorMovimiento = null;
		EstadoValor movimiento = null;
		setFinBusqueda(false);
		EstadisticasEvaluacion ee = null;
		int cota = 0;
		while (!a.alarma() && !isFinBusqueda()) {
			tt = new TablaTransposicion();
			nExpansionesIteracion = 0;
			movimiento = podaAlfaBetaConAlarma(e, cota, fmax, fmin, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, a, 1);
			/* Estadisticas */
			EstadisticasEvaluacion eeAux = estadisticas.get(cota);
			if (eeAux != null) {
				int nExp = eeAux.getnNodos() + nExpansionesIteracion;
				ee = new EstadisticasEvaluacion(cota, nExp);	
			} else {
				ee = new EstadisticasEvaluacion(cota, nExpansionesIteracion);
			}
			/* Fin estadísticas */
			if (!a.alarma()) {
				if (movimiento.getEstado() != null) {
					mejorMovimiento = movimiento;
				}
				if (movimiento.getValor() == Double.POSITIVE_INFINITY || movimiento.getValor() == Double.NEGATIVE_INFINITY) {
					setFinBusqueda(true);
				}
				ee.setEvaluacion(movimiento.getValor());
			}
			estadisticas.put(cota, ee);
			cota = cota + 1;
		}
		setMejorMovimiento(mejorMovimiento);
		return mejorMovimiento.getEstado();
	}
	
	@Override
	public EstadoValor podaAlfaBetaConAlarma(EstadoJuego e, int prof, Ficha fmax, Ficha fmin, double alfa, double beta, Alarma alarma, int signo) {
		EstadoJuego mejorE = null;
		Double mejorV = Double.NEGATIVE_INFINITY;
		Double v2;
		EstadoJuego e2;
		/* Estadísticas */
		nExpansionesIteracion++;
		/* Fin Estadísticas */
		
		if (alarma.alarma()) {
			mejorV = null;
		} else if (prof == 0 || e.ganador()!=null || e.agotado()) {
			mejorV = signo*evalua(e, fmax, fmin);
			if (e.agotado()) {
				setFinBusqueda(true);
			}
		} else if (tt.esta(e)) {		// Si esta en la tabla cojo la información almacenada.
			numTransposiciones++;
			EstadoValor n = tt.getNodo(e);
			mejorV = n.getValor();
			mejorE = n.getEstado();
		} else {
			Iterator<EstadoJuego> i = barajar(e.hijos()).iterator();
			//Iterator<EstadoJuego> i = e.hijos().iterator();
			while (i.hasNext() && beta > alfa) {
				e2 = (EstadoJuego)i.next();
				v2 = podaAlfaBetaConAlarma(e2, prof-1, fmax, fmin, -beta, -alfa, alarma, -signo).getValor();
				if (v2 != null) {
					v2 = -v2;
					alfa = Math.max(alfa, v2);
					if (v2 >= mejorV) {
						mejorV = v2;
						mejorE = e2;
					}
				} else {
					mejorV = null;
					mejorE = null;
				}
			}
			if (i.hasNext()) {
				mejorV = Double.POSITIVE_INFINITY;
			}
			// Añado el mejor valor y el mejor movimiento para e en la tabla de transposición.
			EstadoValor n = new EstadoValor(mejorE, mejorV);
			tt.setNodo(e, n);
		}
		return new EstadoValor(mejorE, mejorV);
	}


	@Override
	public String getEstadisticas() {
		String res = "It.      Nº de nodos expandidos\n";
		int total = 0;
		
		for (int i = 0; i <= estadisticas.size()-1; i++) {
			EstadisticasEvaluacion ee = estadisticas.get(i);
			res += ee.getProfundidad();
			res += "      " + ee.getnNodos();
			res += "\n";
			total += ee.getnNodos();
		}
		res += "\nNº total de nodos expandidos: " + total;
		res += "\nTamaño medio de la tabla de transposición en cada movimiento: " + tamTablaTotal / numMovimientos + " nodos.";
		res += "\nNº total de transposiciones encontradas: " + numTransposiciones;
		res += "\n\nSe muestra el número total de expansiones que ha realizado el algoritmo en cada iteración, ";
		res += "teniendo en cuenta que se realiza una profundización progresiva.";
		
		return res;
	}

	@Override
	public long tiempoMedioPorMovimiento() {
		return tiempoTotal / numMovimientos;
	}

	@Override
	public int numTotalMovimientos() {
		return numMovimientos;
	}

	@Override
	public void inicializarEstadisticas() {
		estadisticas = new HashMap<Integer, EstadisticasEvaluacion>();
		numMovimientos = 0;
		tiempoTotal = 0;
		tamTablaTotal = 0;
		numTransposiciones = 0;
		nExpansionesIteracion = 0;
	}
}
