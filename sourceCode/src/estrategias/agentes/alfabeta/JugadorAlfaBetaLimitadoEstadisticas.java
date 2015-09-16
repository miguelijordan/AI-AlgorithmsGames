package estrategias.agentes.alfabeta;

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
 * Clase JugadorAlfaBetaLimitado.
 * Jugador que realiza una búsqueda con poda alfa-beta y que dispone de un tiempo limitado para evaluar estados de un juego. 
 * 
 * @author José Miguel Horcas Aguilera
 *
 */
public class JugadorAlfaBetaLimitadoEstadisticas extends JugadorAlfaBetaLimitado implements EstadisticasJugador {

	// ATRIBUTOS
	private Map<Integer, EstadisticasEvaluacion> estadisticas;
	private int nExpansionesIteracion;
	private int numMovimientos;
	private long tiempoTotal;
	
	/**
	 * Constructor.
	 * 
	 * @param ev		Evaluador.
	 * @param limiteT	Límite de tiempo.
	 */
	public JugadorAlfaBetaLimitadoEstadisticas(Evaluador ev, long limiteT) {
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
		return movimiento;
	}
	
	/**
	 * Realiza la búsqueda con poda alfa-beta dado un límite de tiempo.
	 * 
	 * @param e		Estado.
	 * @param fmax	Ficha de MAX.
	 * @param fmin	Ficha de MIN.
	 * @return		Mejor movimiento.
	 */
	public EstadoJuego podaAlfaBetaLimitado(EstadoJuego e, Ficha fmax, Ficha fmin) {
		Alarma a = new Alarma(limiteT);
		EstadoValor mejorMovimiento = null;
		EstadoValor movimiento = null;
		setFinBusqueda(false);
		EstadisticasEvaluacion ee = null;
		int cota = 0;
		while (!a.alarma() && !isFinBusqueda()) {
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
	
	/**
	 * Realiza una búsqueda con poda alfa-beta durante un tiempo limitado.
	 * Devuelve el mejor movimiento posible junto con su evaluación.
	 * 
	 * @param e			Estado inicial del juego.
	 * @param prof		Profundidad máxima de búsqueda.
	 * @param fmax		Ficha del jugador MAX.
	 * @param fmin		Ficha del jugador MIN.
	 * @param alfa		Valor de corte alfa.
	 * @param beta		Valor de corte beta.
	 * @param alarma	Alarma.
	 * @param signo		Tomará inicialmente el valor 1, e irá alternando de signo con la profundidad.
	 * @return			Mejor movimiento y su evaluación (objeto de la clase EstadoValor) o null si no se encontró ningún estado cuando el tiempo se acabe.
	 */
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
		}
		return new EstadoValor(mejorE, mejorV);
	}
	
	/**
	 * Inicializa las estadísticas.
	 */
	public void inicializarEstadisticas() {
		estadisticas = new HashMap<Integer, EstadisticasEvaluacion>();
		nExpansionesIteracion = 0;
		numMovimientos = 0;
		tiempoTotal = 0;
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
}
