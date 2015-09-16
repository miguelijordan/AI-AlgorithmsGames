package Pruebas;

import heuristicos.aprendizaje.Entrenamiento;
import heuristicos.evaluadores.EvaluadorTv;
import juegos.Juego;
import juegos.conectak.estrategias.agentes.JugadorHumanoCK;
import juegos.conectak.heuristicos.EvaluadorCK;
import juegos.conectak.juego.ConectaK;
import estrategias.agentes.Jugador;
import estrategias.agentes.aleatorio.JugadorAleatorio;
import estrategias.agentes.alfabeta.JugadorAlfaBeta;
import estrategias.agentes.alfabeta.JugadorAlfaBetaLimitado;
import estrategias.agentes.evaluar.JugadorEvaluar;
import estrategias.agentes.minimax.JugadorMinimax;
import estrategias.agentes.minimax.JugadorMinimaxLimitado;
import estrategias.agentes.montecarlo.JugadorMonteCarlo;
import estrategias.agentes.montecarlo.JugadorMonteCarloLimitado;
import estrategias.agentes.montecarlotreesearch.JugadorMonteCarloTreeSearch;
import estrategias.agentes.montecarlotreesearch.JugadorMonteCarloTreeSearchLimitado;
import estrategias.agentes.tablatransposicion.JugadorAlfaBetaTablaTransposicion;
import estrategias.agentes.tablatransposicion.JugadorMinimaxTablaTransposicion;

/**
 * Clase de prueba para el ConectaK en modo gráfico a través de la terminal.
 * 
 * @author José Miguel Horcas Aguilera
 *
 */
public class PruebaConectaK {
	
	/**
	 * Prueba un jugador con evaluador heurístico frente a una estrategia aleatoria.
	 */
	private static void pruebaEvaluadorHeuristico() {
		Jugador j1 = new JugadorEvaluar(new EvaluadorCK(6,7,4));
		Jugador j2 = new JugadorAleatorio();
		
		Juego.nPartidas(100, j1, j2, new ConectaK(3,3,3), true);
	}
	
	/**
	 * Prueba un jugador humano contra un jugador evaluar con tablas de valor entrenado con un jugador aleatorio.
	 */
	private static void pruebaHumanoVSEvaluar() {
		Jugador ja = new JugadorAleatorio();
		JugadorEvaluar je = new JugadorEvaluar(new EvaluadorTv());
		Entrenamiento.entrenarJug2(7000, ja, je, new ConectaK(3,3,3));
		
		Jugador jHumano = new JugadorHumanoCK();
		int gana = Juego.jugar(jHumano, je, new ConectaK(3,3,3), true);
		if (gana == 1) {
			System.out.println("Gana el jugador 1.");
		} else if (gana == -1) {
			System.out.println("Gana el jugador 2.");
		} else {
			System.out.println("Empate.");
		}
	}
	
	/**
	 * Prueba un jugador evaluar con tablas de valor contra uno aleatorio.
	 * Juega n partidas y muestra las estadísticas.
	 */
	private static void pruebaEvaluadorTV() {
		Jugador ja = new JugadorAleatorio();
		JugadorEvaluar je = new JugadorEvaluar(new EvaluadorTv());
		Entrenamiento.entrenarJug2(7000, ja, je, new ConectaK(3,3,3));
		Juego.nPartidas(100, ja, je, new ConectaK(3,3,3), true);
		
	}
	
	/**
	 * Prueba un jugador minimax contra una estrategia aleatoria.
	 */
	private static void pruebaMinimax() {
		ConectaK ck = new ConectaK(6,7,4);
		Jugador jug2 = new JugadorMinimax(new EvaluadorCK(6,7,4), 3);
		Jugador jug1 = new JugadorAleatorio();
		//Jugador jug1 = new JugadorHumanoCK();
		
		Juego.nPartidas(100, jug1, jug2, ck, true);
	}
	
	/**
	 * Prueba un jugador minimax contra una estrategia aleatoria.
	 */
	private static void pruebaMinimaxTT() {
		ConectaK ck = new ConectaK(6,7,4);
		Jugador jug2 = new JugadorMinimaxTablaTransposicion(new EvaluadorCK(6,7,4),3);
		Jugador jug1 = new JugadorAleatorio();
		//Jugador jug1 = new JugadorHumanoCK();
		
		Juego.nPartidas(100, jug1, jug2, ck, true);
	}
	
	/**
	 * Prueba un jugador minimax limitado contra una estrategia aleatoria.
	 */
	private static void pruebaMinimaxLimitado() {
		ConectaK ck = new ConectaK(6,7,4);
		Jugador jug2 = new JugadorMinimaxLimitado(new EvaluadorCK(6,7,4), 3);
		Jugador jug1 = new JugadorAleatorio();
		
		Juego.nPartidas(100, jug1, jug2, ck, true);
	}
	
	/**
	 * Prueba un jugador con poda alfa-beta contra una estrategia aleatoria.
	 */
	private static void pruebaPodaAlfaBeta() {
		ConectaK ck = new ConectaK(6,7,4);
		Jugador jug2 = new JugadorAlfaBeta(new EvaluadorCK(6,7,4), 3);
		Jugador jug1 = new JugadorAleatorio();
		//Jugador jug1 = new JugadorHumanoCK();
		
		Juego.nPartidas(100, jug1, jug2, ck, true);
	}
	
	/**
	 * Prueba un jugador con poda alfa-beta contra una estrategia aleatoria.
	 */
	private static void pruebaPodaAlfaBetaTT() {
		ConectaK ck = new ConectaK(6,7,4);
		Jugador jug2 = new JugadorAlfaBetaTablaTransposicion(new EvaluadorCK(6,7,4), 3);
		Jugador jug1 = new JugadorAleatorio();
		//Jugador jug1 = new JugadorHumanoCK();
		
		Juego.nPartidas(100, jug1, jug2, ck, true);
	}
	
	/**
	 * Prueba un jugador con poda alfa-beta y tiempo limitado contra un jugador Minimax Limitado.
	 */
	private static void pruebaPodaAlfaBetaLimitado() {
		ConectaK ck = new ConectaK(6,7,4);
		Jugador jug1 = new JugadorAlfaBetaLimitado(new EvaluadorCK(6,7,4), 3);
		Jugador jug2 = new JugadorMinimaxLimitado(new EvaluadorCK(6,7,4), 3);
		
		Juego.nPartidas(100, jug1, jug2, ck, true);
	}
	
	private static void pruebaMonteCarloTreeSearch() {
		ConectaK ck = new ConectaK(6,7,4);
		Jugador jug2 = new JugadorMonteCarloTreeSearch(100, 1.0, false);
		Jugador jug1 = new JugadorMinimax(new EvaluadorCK(6,7,4), 3);
		//Jugador jug1 = new JugadorAleatorio();
		
		Juego.nPartidas(100, jug1, jug2, ck, true);
		//Juego.jugar(jug1, jug2, ck, false);
	}
	
	private static void pruebaMonteCarloTreeSearchLimitado() {
		ConectaK ck = new ConectaK(6,7,4);
		Jugador jug2 = new JugadorMonteCarloTreeSearchLimitado(1);
		Jugador jug1 = new JugadorMinimaxLimitado(new EvaluadorCK(6,7,4), 1);
		//Jugador jug1 = new JugadorAleatorio();
		
		Juego.nPartidas(20, jug1, jug2, ck, true);
		//Juego.jugar(jug1, jug2, ck, false);
	}
	
	private static void pruebaMonteCarlo() {
		ConectaK ck = new ConectaK(6,7,4);
		Jugador jug2 = new JugadorMonteCarlo(0);
		Jugador jug1 = new JugadorMonteCarloTreeSearch(100, 1.0, false);
		//Jugador jug1 = new JugadorMinimax(new EvaluadorCK(6,7,4), 3);
		//Jugador jug1 = new JugadorAleatorio();
		
		Juego.nPartidas(20, jug1, jug2, ck, true);
		//Juego.jugar(jug1, jug2, ck, false);
	}
	
	private static void pruebaMonteCarloLimitado() {
		ConectaK ck = new ConectaK(6,7,4);
		Jugador jug2 = new JugadorMonteCarloLimitado(1);
		Jugador jug1 = new JugadorMinimaxLimitado(new EvaluadorCK(6,7,4), 1);
		//Jugador jug1 = new JugadorAleatorio();
		
		Juego.nPartidas(20, jug1, jug2, ck, true);
		//Juego.jugar(jug1, jug2, ck, false);
	}
	
	/**
	 * Ejecutable.
	 * 
	 * @param args	Argumentos.
	 */
	public static void main(String[] args) {
		pruebaHumanoVSEvaluar();
		pruebaEvaluadorHeuristico();
		pruebaEvaluadorTV();
		pruebaMinimax();
		pruebaMinimaxLimitado();
		pruebaMinimaxTT();
		pruebaPodaAlfaBeta();
		pruebaPodaAlfaBetaLimitado();
		pruebaPodaAlfaBetaTT();
		pruebaMonteCarloTreeSearch();
		pruebaMonteCarloTreeSearchLimitado();
		pruebaMonteCarlo();
		pruebaMonteCarloLimitado(); 
	}
	
}
