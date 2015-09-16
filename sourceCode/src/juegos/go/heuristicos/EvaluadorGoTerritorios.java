package juegos.go.heuristicos;

import heuristicos.evaluadores.Evaluador;
import juegos.EstadoJuego;
import juegos.go.juego.Go;
import juegos.util.Ficha;

/**
 * Evaluador heurístico para el juego del Go.
 * El valor heurístico de un estado es la diferencia entre el número de territorios
 * de MAX y de MIN.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 3/10/2011
 *
 */
public class EvaluadorGoTerritorios implements Evaluador {

	@Override
	public double evaluacion(EstadoJuego estado, Ficha fmax, Ficha fmin) {
		Go e = (Go) estado;
		return e.tablero().contarTerritorios(fmax) - e.tablero().contarTerritorios(fmin);
	}
	
	@Override
	public String toString() {
		String res = "Evaluador heurístico para el juego del Go.";
		res += " El valor heurístico de un estado es la diferencia entre el número de territorios de MAX y de MIN.";
		return res;
	}	

}
