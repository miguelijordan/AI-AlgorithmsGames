package estrategias.agentes.minimax;

import heuristicos.evaluadores.Evaluador;

import java.util.HashMap;
import java.util.Map;

import juegos.EstadoJuego;
import juegos.util.AnchoFijo;
import juegos.util.Ficha;
import estrategias.agentes.estadisticas.EstadisticasEvaluacion;
import estrategias.agentes.estadisticas.EstadisticasJugador;
import estrategias.util.Alarma;
import estrategias.util.EstadoValor;


/**
 * Clase JugadorMinimaxLimitado.
 * Jugador Minimax que dispone de un tiempo limitado para evaluar estados de un juego. 
 * 
 * @author José Miguel Horcas Aguilera
 *
 */
public class JugadorMinimaxLimitadoEstadisticas extends JugadorMinimaxLimitado implements EstadisticasJugador  {

	// ATRIBUTOS
	private Map<Integer, EstadisticasEvaluacion> estadisticas;
	private int nExpansionesIteracion;							// nº expansiones en esa iteración.
	private int numMovimientos;
	private long tiempoTotal;
	
	/**
	 * Constructor.
	 * 
	 * @param ev		Evaluador.
	 * @param limiteT	Límite de tiempo.
	 */
	public JugadorMinimaxLimitadoEstadisticas(Evaluador ev, long limiteT) {
		super(ev, limiteT);
		inicializarEstadisticas();
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		numMovimientos++;
		long tiempo = System.currentTimeMillis();
		EstadoJuego movimiento = minimaxLimitado(e, e.fichaActual(), e.fichaOtro());
		if (movimiento == null) {
			movimiento = barajar(e.hijos()).get(0);		// Por si no le da tiempo.
		}
		tiempoTotal += System.currentTimeMillis() - tiempo;
		return movimiento;
	}
	
	/**
	 * Realiza la evaluación MINIMAX de un estado dado un límite de tiempo.
	 * 
	 * @param e		Estado.
	 * @param fmax	Ficha de MAX.
	 * @param fmin	Ficha de MIN.
	 * @return		Mejor movimiento.
	 */
	public EstadoJuego minimaxLimitado(EstadoJuego e, Ficha fmax, Ficha fmin) {
		Alarma a = new Alarma(getLimiteT());
		EstadoValor mejorMovimiento = null;
		EstadoValor movimiento = null;
		setFinBusqueda(false);
		EstadisticasEvaluacion ee = null;
		int cota = 0;
		while (!a.alarma() && !isFinBusqueda()) {
			nExpansionesIteracion = 0;
			movimiento = negamaxConAlarma(e, cota, fmax, fmin, a, 1);
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
	 * Realiza una evaluación MINIMAX de un estado dado en un tiempo límite.
	 * Devuelve el mejor movimiento junto con su evaluación.
	 * 
	 * @param e			Estado.
	 * @param prof		Profundidad de búsqueda.
	 * @param fmax		Ficha de MAX.
	 * @param fmin		Ficha de MIN.
	 * @param alarma	Alarma.
	 * @param signo		Tomará inicialmente el valor 1, e irá alternando de signo con la profundidad.
	 * @return			Mejor movimiento y su evaluación (objeto de la clase EstadoValor) o null si no se encontró ningún estado cuando el tiempo se acabe.
	 */
	public EstadoValor negamaxConAlarma(EstadoJuego e, int prof, Ficha fmax, Ficha fmin, Alarma alarma, int signo) {
		EstadoJuego mejorE = null;
		Double mejorV = Double.NEGATIVE_INFINITY;
		Double v2;
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
			//for (EstadoJuego e2 : e.hijos()) {
			for (EstadoJuego e2 : barajar(e.hijos())) {
				v2 = negamaxConAlarma(e2, prof-1, fmax, fmin, alarma, -signo).getValor();
				if (v2 != null) {
					v2 = -v2;
					if (v2 >= mejorV) {
						mejorV = v2;
						mejorE = e2;
					}
				} else {
					mejorV = null;
					mejorE = null;
				}
			}
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
		nExpansionesIteracion = 0;
		numMovimientos = 0;
		tiempoTotal = 0;
		estadisticas = new HashMap<Integer, EstadisticasEvaluacion>();
	}
}
