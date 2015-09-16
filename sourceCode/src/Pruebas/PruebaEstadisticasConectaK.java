package Pruebas;
//
//
//import java.util.Map;
//
//import juegos.conectak.estrategias.evaluadores.EvaluadorCK;
//import juegos.conectak.estrategias.evaluadores.EvaluadorNNCK;
//import juegos.conectak.juego.ConectaK;
//import juegos.util.Juego;
//import estrategias.agentes.Jugador;
//import estrategias.agentes.aleatorio.JugadorAleatorio;
//import estrategias.agentes.alfabeta.JugadorAlfaBetaLimitado;
//import estrategias.agentes.estadisticas.EstadisticasEvaluacion;
//import estrategias.agentes.evaluar.JugadorEvaluar;
//import estrategias.agentes.minimax.JugadorMinimax;
//import estrategias.evaluadores.EvaluadorTv;
//
///**
// * Clase de prueba para el ConectaK en modo gráfica a través de la terminal.
// * 
// * @author José Miguel Horcas Aguilera
// *
// */
//public class PruebaEstadisticasConectaK {
//	
//	/**
//	 * Muestra por pantalla las estadísticas de una búsqueda del mejor movimiento en un árbol de juego a partir de un estado inicial.
//	 * Esta función es válida para cualquier jugador evaluador que implemente la interfaz Estadisticas.
//	 * 
//	 * @param <Jugador>		Los jugadores deben implementar la interfaz Estadisticas.
//	 * @param jug			JugadorEvaluar.
//	 * @param ck			Estado inicial.
//	 */
//	@SuppressWarnings("hiding")
//	private static <JugadorEvaluar extends PARA_ELIMINAREstadisticasArbol> void verEstadisticas(JugadorEvaluar jug, ConectaK ck) {
//		System.out.println("Estado inicial:");
//		System.out.print(ck.tablero().toString());
//		
//		long tiempo = System.currentTimeMillis();
//		ConectaK ck2 = (ConectaK)((estrategias.agentes.evaluar.JugadorEvaluar) jug).mueve(ck);
//		tiempo = System.currentTimeMillis() - tiempo;
//		
//		Map<Integer, EstadisticasEvaluacion> estadisticas = jug.estadisticas();
//		for (int i = 0; i <= estadisticas.size()-1; i++) {
//			EstadisticasEvaluacion ee = estadisticas.get(i);
//			System.out.print(ee.getProfundidad() + "  ");
//			System.out.print(ee.getnNodos() + "  ");
//			if (ee.getEvaluacion() != null) {
//				System.out.print(ee.getEvaluacion());	
//			} else {
//				System.out.println("null");
//			}
//			System.out.println();
//		}
//		
//		System.out.println("Tiempo total (s): " + tiempo/1000.0);
//		System.out.println("Mejor movimiento:");
//		System.out.print(ck2.tablero().toString());
//		System.out.println("Evaluación: " + ((estrategias.agentes.evaluar.JugadorEvaluar)jug).getMejorMovimiento().getValor());
//	}
//	
//	/**
//	 * Crea un Jugador Minimax con Evaluador Heurístico y con la profundidad de búsqueda indicada.
//	 * Muestra las estadísticas del mejor movimiento a partir del estado inicial que recibe.
//	 * 
//	 * @param eIni	Estado inicial del juego ConectaK.
//	 * @param prof	Profundidad máxima de búsqueda.
//	 */
//	private static void pruebaJugadorMinimaxEvaluadorHeuristico(ConectaK eIni, int prof) {
//		int nFilas = eIni.tablero().nFilas();
//		int nColumnas = eIni.tablero().nColumnas();
//		int longGanadora = eIni.longGanadora();
//		JugadorMinimaxConEstadisticas jug = new JugadorMinimaxConEstadisticas(new EvaluadorCK(nFilas, nColumnas, longGanadora), prof);
//		verEstadisticas(jug, eIni);
//	}
//	
//	/**
//	 * Crea un Jugador Alfa-Beta con Evaluador Heurístico y con la profundidad de búsqueda indicada.
//	 * Muestra las estadísticas del mejor movimiento a partir del estado inicial que recibe.
//	 * 
//	 * @param eIni	Estado inicial del juego ConectaK.
//	 * @param prof	Profundidad máxima de búsqueda.
//	 */
//	private static void pruebaJugadorAlfaBetaEvaluadorHeuristico(ConectaK eIni, int prof) {
//		int nFilas = eIni.tablero().nFilas();
//		int nColumnas = eIni.tablero().nColumnas();
//		int longGanadora = eIni.longGanadora();
//		JugadorAlfaBetaConEstadisticas jug = new JugadorAlfaBetaConEstadisticas(new EvaluadorCK(nFilas, nColumnas, longGanadora), prof);
//		verEstadisticas(jug, eIni);
//	}
//
//	/**
//	 * Crea un Jugador Minimax con Evaluador Heurístico y con límite de tiempo.
//	 * Muestra las estadísticas del mejor movimiento a partir del estado inicial que recibe.
//	 * 
//	 * @param eIni		Estado inicial del juego ConectaK.
//	 * @param limiteT	Límite de tiempo.
//	 */
//	private static void pruebaJugadorMinimaxLimitadoEvaluadorHeuristico(ConectaK eIni, int limiteT) {
//		int nFilas = eIni.tablero().nFilas();
//		int nColumnas = eIni.tablero().nColumnas();
//		int longGanadora = eIni.longGanadora();
//		JugadorMinimaxLimitadoConEstadisticas jug = new JugadorMinimaxLimitadoConEstadisticas(new EvaluadorCK(nFilas, nColumnas, longGanadora), limiteT);
//		verEstadisticas(jug, eIni);
//	}
//	
//	/**
//	 * Crea un Jugador Alfa-Beta con Evaluador Heurístico y con límite de tiempo.
//	 * Muestra las estadísticas del mejor movimiento a partir del estado inicial que recibe.
//	 * 
//	 * @param eIni		Estado inicial del juego ConectaK.
//	 * @param limiteT	Límite de tiempo.
//	 */
//	private static void pruebaJugadorAlfaBetaLimitadoEvaluadorHeuristico(ConectaK eIni, int limiteT) {
//		int nFilas = eIni.tablero().nFilas();
//		int nColumnas = eIni.tablero().nColumnas();
//		int longGanadora = eIni.longGanadora();
//		JugadorAlfaBetaLimitadoConEstadisticas jug = new JugadorAlfaBetaLimitadoConEstadisticas(new EvaluadorCK(nFilas, nColumnas, longGanadora), limiteT);
//		verEstadisticas(jug, eIni);
//	}
//	
//	/**
//	 * Crea un Jugador Alfa-Beta que usa Tabla de Valor para evaluar los estados y con la profundidad de búsqueda indicada.
//	 * Muestra las estadísticas del mejor movimiento a partir del estado inicial que recibe.
//	 * 
//	 * @param eIni	Estado inicial del juego ConectaK.
//	 * @param prof	Profundidad máxima de búsqueda.
//	 */
//	private static void pruebaJugadorAlfaBetaTablaValor(ConectaK eIni, int prof) {
//		Jugador ja = new JugadorAleatorio();
//		JugadorEvaluar je = new JugadorEvaluar(new EvaluadorTv());
//		EntrenamientoAntiguo.entrenarJug2(7000, ja, je, eIni);
//		
//		JugadorAlfaBetaConEstadisticas jug = new JugadorAlfaBetaConEstadisticas(je.getEvaluador(), prof);
//		verEstadisticas(jug, eIni);
//	}
//
//	/**
//	 * Crea un Jugador Minimax que usa Tabla de Valor para evaluar los estados y con la profundidad de búsqueda indicada.
//	 * Muestra las estadísticas del mejor movimiento a partir del estado inicial que recibe.
//	 * 
//	 * @param eIni	Estado inicial del juego ConectaK.
//	 * @param prof	Profundidad máxima de búsqueda.
//	 */
//	private static void pruebaJugadorMinimaxTablaValor(ConectaK eIni, int prof) {
//		Jugador ja = new JugadorAleatorio();
//		JugadorEvaluar je = new JugadorEvaluar(new EvaluadorTv());
//		EntrenamientoAntiguo.entrenarJug2(7000, ja, je, eIni);
//		
//		JugadorMinimaxConEstadisticas jug = new JugadorMinimaxConEstadisticas(je.getEvaluador(), prof);
//		verEstadisticas(jug, eIni);
//	}
//	
//	/**
//	 * Crea un Jugador Alfa-Beta que usa Tabla de Valor para evaluar los estados y con tiempo limitado.
//	 * Muestra las estadísticas del mejor movimiento a partir del estado inicial que recibe.
//	 * 
//	 * @param eIni		Estado inicial del juego ConectaK.
//	 * @param limiteT	Tiempo máximo de búsqueda.
//	 */
//	private static void pruebaJugadorAlfaBetaLimitadoTablaValor(ConectaK eIni, int limiteT) {
//		Jugador ja = new JugadorAleatorio();
//		JugadorEvaluar je = new JugadorEvaluar(new EvaluadorTv());
//		EntrenamientoAntiguo.entrenarJug2(7000, ja, je, eIni);
//		
//		JugadorAlfaBetaLimitadoConEstadisticas jug = new JugadorAlfaBetaLimitadoConEstadisticas(je.getEvaluador(), limiteT);
//		verEstadisticas(jug, eIni);
//	}
//	
//	/**
//	 * Crea un Jugador Minimax que usa Tabla de Valor para evaluar los estados y con tiempo limitado.
//	 * Muestra las estadísticas del mejor movimiento a partir del estado inicial que recibe.
//	 * 
//	 * @param eIni		Estado inicial del juego ConectaK.
//	 * @param limiteT	Tiempo máximo de búsqueda.
//	 */
//	private static void pruebaJugadorMinimaxLimitadoTablaValor(ConectaK eIni, int limiteT) {
//		Jugador ja = new JugadorAleatorio();
//		JugadorEvaluar je = new JugadorEvaluar(new EvaluadorTv());
//		EntrenamientoAntiguo.entrenarJug2(7000, ja, je, eIni);
//		
//		JugadorMinimaxLimitadoConEstadisticas jug = new JugadorMinimaxLimitadoConEstadisticas(je.getEvaluador(), limiteT);
//		verEstadisticas(jug, eIni);
//	}
//	
//	/**
//	 * Crea un Jugador Alfa-Beta que usa una Red Neuronal para evaluar los estados y con la profundidad de búsqueda indicada.
//	 * Muestra las estadísticas del mejor movimiento a partir del estado inicial que recibe.
//	 * 
//	 * @param eIni	Estado inicial del juego ConectaK.
//	 * @param prof	Profundidad máxima de búsqueda.
//	 */
//	private static void pruebaJugadorAlfaBetaRedNeuronal(ConectaK eIni, int prof) {
//		int nFilas = eIni.tablero().nFilas();
//		int nColumnas = eIni.tablero().nColumnas();
//		int longGanadora = eIni.longGanadora();
//			
//		Jugador ja = new JugadorAleatorio();
//		JugadorEvaluar je = new JugadorEvaluar(new EvaluadorNNCK(nFilas, nColumnas, longGanadora));
//		EntrenamientoAntiguo.entrenarJug2(7000, ja, je, eIni);
//		
//		JugadorAlfaBetaConEstadisticas jug = new JugadorAlfaBetaConEstadisticas(je.getEvaluador(), prof);
//		verEstadisticas(jug, eIni);
//	}
//
//	/**
//	 * Crea un Jugador Minimax que usa una Red Neuronal para evaluar los estados y con la profundidad de búsqueda indicada.
//	 * Muestra las estadísticas del mejor movimiento a partir del estado inicial que recibe.
//	 * 
//	 * @param eIni	Estado inicial del juego ConectaK.
//	 * @param prof	Profundidad máxima de búsqueda.
//	 */
//	private static void pruebaJugadorMinimaxRedNeuronal(ConectaK eIni, int prof) {
//		int nFilas = eIni.tablero().nFilas();
//		int nColumnas = eIni.tablero().nColumnas();
//		int longGanadora = eIni.longGanadora();
//			
//		Jugador ja = new JugadorAleatorio();
//		JugadorEvaluar je = new JugadorEvaluar(new EvaluadorNNCK(nFilas, nColumnas, longGanadora));
//		EntrenamientoAntiguo.entrenarJug2(7000, ja, je, eIni);
//		
//		JugadorMinimaxConEstadisticas jug = new JugadorMinimaxConEstadisticas(je.getEvaluador(), prof);
//		verEstadisticas(jug, eIni);
//	}
//	
//	/**
//	 * Crea un Jugador Alfa-Beta que usa una Red Neuronal para evaluar los estados y con tiempo limitado.
//	 * Muestra las estadísticas del mejor movimiento a partir del estado inicial que recibe.
//	 * 
//	 * @param eIni		Estado inicial del juego ConectaK.
//	 * @param limiteT	Tiempo máximo de búsqueda.
//	 */
//	private static void pruebaJugadorAlfaBetaLimitadoRedNeuronal(ConectaK eIni, int limiteT) {
//		int nFilas = eIni.tablero().nFilas();
//		int nColumnas = eIni.tablero().nColumnas();
//		int longGanadora = eIni.longGanadora();
//		
//		Jugador ja = new JugadorAleatorio();
//		JugadorEvaluar je = new JugadorEvaluar(new EvaluadorNNCK(nFilas, nColumnas, longGanadora));
//		EntrenamientoAntiguo.entrenarJug2(7000, ja, je, eIni);
//		
//		JugadorAlfaBetaLimitadoConEstadisticas jug = new JugadorAlfaBetaLimitadoConEstadisticas(je.getEvaluador(), limiteT);
//		verEstadisticas(jug, eIni);
//	}
//	
//	/**
//	 * Crea un Jugador Minimax que usa una Red Neuronal para evaluar los estados y con tiempo limitado.
//	 * Muestra las estadísticas del mejor movimiento a partir del estado inicial que recibe.
//	 * 
//	 * @param eIni		Estado inicial del juego ConectaK.
//	 * @param limiteT	Tiempo máximo de búsqueda.
//	 */
//	private static void pruebaJugadorMinimaxLimitadoRedNeuronal(ConectaK eIni, int limiteT) {
//		int nFilas = eIni.tablero().nFilas();
//		int nColumnas = eIni.tablero().nColumnas();
//		int longGanadora = eIni.longGanadora();
//		
//		Jugador ja = new JugadorAleatorio();
//		JugadorEvaluar je = new JugadorEvaluar(new EvaluadorNNCK(nFilas, nColumnas, longGanadora));
//		EntrenamientoAntiguo.entrenarJug2(7000, ja, je, eIni);
//		
//		JugadorMinimaxLimitadoConEstadisticas jug = new JugadorMinimaxLimitadoConEstadisticas(je.getEvaluador(), limiteT);
//		verEstadisticas(jug, eIni);
//	}
//	
//	/**
//	 * Compara una estrategia Minimax contra una estrategia Alfa-Beta.
//	 * Entrena ambos jugadores y juega un número determinado de partidas.
//	 */
//	private static void experimento() {
//		ConectaK eIni = new ConectaK(6,7,4);
//		int nFilas = eIni.tablero().nFilas();
//		int nColumnas = eIni.tablero().nColumnas();
//		int longGanadora = eIni.longGanadora();
//		
//		
//		JugadorEvaluar jug1 = new JugadorEvaluar(new EvaluadorTv());
//		Jugador jugEntrenamiento = new JugadorAleatorio();
//		JugadorEvaluar jug2 = new JugadorEvaluar(new EvaluadorTv());
//		
//		/*
//		JugadorMinimax jug1 = new JugadorMinimax(new EvaluadorTv(), 3);
//		Jugador jugEntrenamiento = new JugadorAleatorio();
//		JugadorAlfaBeta jug2 = new JugadorAlfaBeta(new EvaluadorTv(), 3);
//		*/
//		
//		long t1entr = System.currentTimeMillis();		
//		EntrenamientoAntiguo.entrenarJug1(7000, jug1, jugEntrenamiento, eIni);	
//		long t2entr = System.currentTimeMillis();
//		System.out.println("Tiempo entrenamiento jug1: " + (t2entr-t1entr)/1000 + " segundos.");
//
//
//		long t1entr2 = System.currentTimeMillis();		
//		EntrenamientoAntiguo.entrenarJug2(7000, jugEntrenamiento, jug2, eIni);	
//		long t2entr2 = System.currentTimeMillis();
//		System.out.println("Tiempo entrenamiento jug2: " + (t2entr2-t1entr2)/1000 + " segundos.");
//		
//		JugadorMinimax jugAB1 = new JugadorMinimax(jug1.getEvaluador(), 3);
//		JugadorMinimax jugAB2 = new JugadorMinimax(jug2.getEvaluador(), 3);
//		
//		long t1j = System.currentTimeMillis();		
//		Juego.nPartidas(1000, jugAB1, jugAB2, eIni);	
//		long t2j = System.currentTimeMillis();
//		System.out.println("Tiempo ejecución: " + (t2j-t1j)/1000 + " segundos.");	
//	}
//	
//	private static void experimento2() {
//		ConectaK eIni = new ConectaK(6,7,4);
//		int nFilas = eIni.tablero().nFilas();
//		int nColumnas = eIni.tablero().nColumnas();
//		int longGanadora = eIni.longGanadora();
//		
//		JugadorAlfaBetaLimitado jug1 = new JugadorAlfaBetaLimitado(new EvaluadorCK(nFilas, nColumnas, longGanadora), 1);
//		JugadorAlfaBetaLimitado jug2 = new JugadorAlfaBetaLimitado(new EvaluadorCK(nFilas, nColumnas, longGanadora), 1);	
//		long t1j = System.currentTimeMillis();		
//		Juego.nPartidas(100, jug1, jug2, eIni);
//		long t2j = System.currentTimeMillis();
//		System.out.println("Tiempo ejecución: " + (t2j-t1j)/1000 + " segundos.");	
//	}
//	
//	/**
//	 * Ejecutable.
//	 * 
//	 * @param args	Argumentos.
//	 */
//	public static void main(String[] args) {		
//		// Creación del estado inicial.
//		ConectaK ck = new ConectaK(6,7,4);
//		ck = (ConectaK) ck.elegirSucNth(1); // MAX
//		ck = (ConectaK) ck.elegirSucNth(2);
//		ck = (ConectaK) ck.elegirSucNth(3);
//		ck = (ConectaK) ck.elegirSucNth(4);
//		ck = (ConectaK) ck.elegirSucNth(5);
//		ck = (ConectaK) ck.elegirSucNth(1);
//		ck = (ConectaK) ck.elegirSucNth(2);
//		ck = (ConectaK) ck.elegirSucNth(3);
//		ck = (ConectaK) ck.elegirSucNth(4);
//		ck = (ConectaK) ck.elegirSucNth(2);
//		ck = (ConectaK) ck.elegirSucNth(3);
//		ck = (ConectaK) ck.elegirSucNth(2);
//		ck = (ConectaK) ck.elegirSucNth(6);
//		ck = (ConectaK) ck.elegirSucNth(3);
//		ck = (ConectaK) ck.elegirSucNth(0);
//	//	ConectaK ck = new ConectaK(4,4,3);
//	/*	ck = (ConectaK) ck.elegirSucNth(1);
//		ck = (ConectaK) ck.elegirSucNth(2);
//		ck = (ConectaK) ck.elegirSucNth(1);
//		ck = (ConectaK) ck.elegirSucNth(1);*/
//		
//		
//		
//		int profMax = 15;
//		int limiteT = 1; // Segundos.
//		
//		pruebaJugadorMinimaxEvaluadorHeuristico(ck, profMax);
//		//pruebaJugadorAlfaBetaEvaluadorHeuristico(ck, profMax);
//		//pruebaJugadorMinimaxLimitadoEvaluadorHeuristico(ck, limiteT);
//		//pruebaJugadorAlfaBetaLimitadoEvaluadorHeuristico(ck, limiteT);
//		//pruebaJugadorAlfaBetaTablaValor(ck, profMax);
//		//pruebaJugadorAlfaBetaRedNeuronal(ck, profMax);
//		//pruebaJugadorAlfaBetaLimitadoTablaValor(ck, limiteT);
//		//pruebaJugadorAlfaBetaLimitadoRedNeuronal(ck, limiteT);
//		//pruebaJugadorMinimaxTablaValor(ck, profMax);
//		//pruebaJugadorMinimaxRedNeuronal(ck, profMax);
//		//pruebaJugadorMinimaxLimitadoTablaValor(ck, limiteT);
//		//pruebaJugadorMinimaxLimitadoRedNeuronal(ck, limiteT);
//		//experimento();
//		//experimento2();
//	}	
//}

public class PruebaEstadisticasConectaK {
	
}