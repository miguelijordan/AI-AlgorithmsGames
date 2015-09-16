package juegos.go.heuristicos;

import heuristicos.evaluadores.Evaluador;
import juegos.EstadoJuego;
import juegos.go.juego.Go;
import juegos.util.Ficha;

/**
 * Evaluador heurístico para el juego del Go.
 * El valor heurístico de un estado es la diferencia entre los puntos
 * de MAX y de MIN según las reglas de puntuación japonesas.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 3/10/2011
 *
 */
public class EvaluadorGoPuntosJp implements Evaluador {

	@Override
	public double evaluacion(EstadoJuego estado, Ficha fmax, Ficha fmin) {
		Go e = (Go) estado;
		return e.puntuacionReglasJaponesas(fmax) - e.puntuacionReglasJaponesas(fmin);
	}

	@Override
	public String toString() {
		String res = "Evaluador heurístico para el juego del Go.";
		res += " El valor heurístico de un estado es la diferencia entre los puntos de MAX y de MIN según las reglas de puntuación japonesas.";
		return res;
	}	
}
