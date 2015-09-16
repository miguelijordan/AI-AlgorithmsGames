package estrategias.agentes.evaluar;

import heuristicos.evaluadores.Evaluador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;

import org.encog.neural.networks.BasicNetwork;

import juegos.EstadoJuego;
import juegos.util.Ficha;

import estrategias.agentes.Jugador;
import estrategias.util.EstadoValor;


/**
 * Agente capaz de evaluar heurísticamente los estados de un juego.
 * Este jugador emplea un objeto evaluador para determinar si una situación dada le es favorable o no.
 * El agente emplea la siguiente estrategia de juego: "dada una situación, considera todos los movimientos inmediatos,
 * los evalúa heurísticamente y escoge el mejor."
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 16/08/2011
 *
 */
public class JugadorEvaluar implements Jugador {
	
	/**
	 * Función de evaluación.
	 */
	private Evaluador evaluador;
	
	/**
	 * Mejor movimiento junto con su evaluación.
	 */
	EstadoValor mejorMovimiento;
	
	/**
	 * Crea un agente evaluador heurístico a partir de la función de evaluación heurística ev.
	 * 
	 * @param ev	Función de evaluación.
	 */
	public JugadorEvaluar(Evaluador ev) {
		this.evaluador = ev;
		this.mejorMovimiento = null;
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		EstadoJuego mejorE = null;
		double mejorV = Double.NEGATIVE_INFINITY;
		double v2;
		
		//for (EstadoJuego e2 : e.hijos()) {
		for (EstadoJuego e2 : barajar(e.hijos())) {
			v2 = evalua(e2, e.fichaActual(), e.fichaOtro());
			if (v2 >= mejorV) {
				mejorV = v2;
				mejorE = e2;
			}
		}
		setMejorMovimiento(new EstadoValor(mejorE, mejorV));
		return mejorE;
	}

	/**
	 * Función de evaluación válida para cualquier juego que implemente la interfaz EstadoJuego.
	 * Recibe una descripción del estado y las fichas de MAX y MIN.
	 * Hace uso del evaluador heurístico.
	 *  
	 * @param e				Estado del juego.
	 * @param fmax			Ficha de MAX.
	 * @param fmin			Ficha de MIN.
	 * @return				Infinito si el estado es final y ganador para MAX.
	 * 						Infinito negativo si el estado es final y perdedor para MAX.
	 * 						El valor de la función heurística en otro caso.
	 */
	public double evalua(EstadoJuego e, Ficha fmax, Ficha fmin) {
		Ficha ganador = e.ganador();
		double resultado;
		
		if (ganador == null) {
			resultado = evaluador.evaluacion(e, fmax, fmin);
		} else if (ganador.equals(fmax)) {
			resultado = Double.POSITIVE_INFINITY;
		} else {
			resultado = Double.NEGATIVE_INFINITY;
		}
		return resultado;
	}

	/**
	 * Baraja aleatoriamente una lista de EstadoJuego.
	 * 
	 * @param l Lista de EstadoJuego.
	 * @return	Lista barajada aleatoriamente.
	 */
	protected List<EstadoJuego> barajar(List<EstadoJuego> l) {
		Collections.shuffle(l);
		return l;
	}

	/**
	 * @return El evaluador heurístico que utiliza el agente.
	 */
	public Evaluador getEvaluador() {
		return evaluador;
	}
	
	/**
	 * @param evaluador Nuevo evaluador heurístico a usar.
	 */
	public void setEvaluador(Evaluador evaluador) {
		this.evaluador = evaluador;
	}

	/**
	 * @return Mejor movimiento encontrado.
	 */
	public EstadoValor getMejorMovimiento() {
		return mejorMovimiento;
	}

	/**
	 * Actualiza el mejor movimiento encontrado.
	 * 
	 * @param mejorMovimiento Nuevo movimiento.
	 */
	public void setMejorMovimiento(EstadoValor mejorMovimiento) {
		this.mejorMovimiento = mejorMovimiento;
	}

	@Override
	public String toString() {
		String res = "Evalúa heurísticamente todos los movimientos inmediatos a partir de una posición del juego.";
		res += "\n" + evaluador.toString();
		return res;
	}	
	
	/**
	 * Carga el evaluador almacenado en el fichero indicado.
	 *  
	 * @param filename	Nombre del fichero con el evaluador.
	 *//*
	public void cargarEvaluador(String filename) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			evaluador = (Evaluador) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	*//**
	 * Almacena el evaluador en el fichero indicado.
	 * 
	 * @param filename	Nombre del fichero.
	 *//*
	public void guardarEvaluador(String filename) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(evaluador);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
