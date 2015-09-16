package juegos.go.heuristicos;

import heuristicos.evaluadores.Evaluador;
import juegos.EstadoJuego;
import juegos.go.juego.Go;
import juegos.util.Ficha;

/**
 * Evaluador heurístico para el juego del Go.
 * El valor heurístico de un estado es la diferencia entre los puntos
 * de MAX y de MIN según las reglas de puntuación chinas.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 3/10/2011
 *
 */
public class EvaluadorGoPuntosCh implements Evaluador {

	@Override
	public double evaluacion(EstadoJuego estado, Ficha fmax, Ficha fmin) {
		Go e = (Go) estado;
		return e.puntuacionReglasChinas(fmax) - e.puntuacionReglasChinas(fmin);
	}
	
	@Override
	public String toString() {
		String res = "Evaluador heurístico para el juego del Go.";
		res += " El valor heurístico de un estado es la diferencia entre los puntos de MAX y de MIN según las reglas de puntuación chinas.";
		return res;
	}	

}
