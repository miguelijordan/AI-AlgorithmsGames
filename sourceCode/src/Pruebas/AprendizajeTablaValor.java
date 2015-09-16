package Pruebas;

import heuristicos.aprendizaje.Entrenamiento;
import heuristicos.evaluadores.EvaluadorTv;
import juegos.Juego;
import juegos.conectak.juego.ConectaK;
import juegos.go.juego.Go;
import estrategias.agentes.Jugador;
import estrategias.agentes.aleatorio.JugadorAleatorio;
import estrategias.agentes.evaluar.JugadorEvaluar;

/**
 * Prueba del entrenamiento de la tabla de valor.
 * 
 * @author José Miguel Horcas Aguilera
 *
 */
public class AprendizajeTablaValor {

	private static void go() {
		Go e = new Go(9, 9, 0.0, null, true, Go.REGLAS_JAPONESAS);

		JugadorEvaluar j1 = new JugadorEvaluar(new EvaluadorTv());
		JugadorEvaluar j2 = new JugadorEvaluar(new EvaluadorTv());
		JugadorEvaluar j3 = new JugadorEvaluar(new EvaluadorTv());
		JugadorEvaluar j4 = new JugadorEvaluar(new EvaluadorTv());
		
		Jugador ja = new JugadorAleatorio();
		
		Entrenamiento.verAprendizajeJug1(2000, 100, 100, e, j1, ja);
		Entrenamiento.verAprendizajeJug2(2000, 100, 100, e, ja, j2);
		Entrenamiento.verAprendizajeJug(2000, 100, 100, e, j3, j4);
		Juego.nPartidas(100, j1, j2, e, true);
		Juego.nPartidas(100, j1, j4, e, true);
		Juego.nPartidas(100, j3, j2, e, true);
		Juego.nPartidas(100, j3, j4, e, true);
	}
	
	private static void conectak() {
		ConectaK e = new ConectaK(6,7,4);

		JugadorEvaluar j1 = new JugadorEvaluar(new EvaluadorTv());
		JugadorEvaluar j2 = new JugadorEvaluar(new EvaluadorTv());
		JugadorEvaluar j3 = new JugadorEvaluar(new EvaluadorTv());
		JugadorEvaluar j4 = new JugadorEvaluar(new EvaluadorTv());
		
		Jugador ja = new JugadorAleatorio();
		
		Entrenamiento.verAprendizajeJug1(2000, 100, 100, e, j1, ja);
		Entrenamiento.verAprendizajeJug2(2000, 100, 100, e, ja, j2);
		Entrenamiento.verAprendizajeJug(2000, 100, 100, e, j3, j4);
		Juego.nPartidas(100, j1, j2, e, true);
		Juego.nPartidas(100, j1, j4, e, true);
		Juego.nPartidas(100, j3, j2, e, true);
		Juego.nPartidas(100, j3, j4, e, true);
	}
	
	/**
	 * Ejecutable.
	 * 
	 * @param args	Argumentos.
	 */
	public static void main(String[] args) {
		conectak();
		go();
	}

}
