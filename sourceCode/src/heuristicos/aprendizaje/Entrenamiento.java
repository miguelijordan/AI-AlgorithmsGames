package heuristicos.aprendizaje;

import java.util.List;
import java.util.Random;

import juegos.EstadoJuego;
import juegos.Juego;
import entornointeractivo.gui.estrategias.PanelEntrenamiento;
import estrategias.agentes.Jugador;
import estrategias.agentes.estadisticas.Util;
import estrategias.agentes.evaluar.JugadorEvaluar;

/**
 * Clase Entrenamiento.
 * Ofrece métodos estáticos para entrenar jugadores evaluadores con tablas de valor o redes neuronales.
 * 
 * Dispone también de métodos para ver el aprendizaje de los jugadores.
 * Ofrece además dos métodos para entrenar jugadores actualizando la interfaz gráfica.
 * 
 * @author José Miguel Horcas Aguilera.
 *
 */
public class Entrenamiento {
	
	/**
	 * Generador de números aleatorios.
	 */
	private static Random random = new Random();
	
	/**
	 * Componente aleatoria para los movimientos del jugador a entrenar.
	 * Cada movimiento del jugador tendrá una probabilidad de ser aleatorio (exploración).
	 * En caso de que el movimiento sea exploratorio, no entrenaremos al evaluador.
	 */
	private static final double PROBABILIDAD_EXPLORACION = 0.1;
	
	private static double probabilidad_exploracion = PROBABILIDAD_EXPLORACION;
	
	/**
	 * @return the probabilidad_exploracion
	 */
	public static double getProbabilidad_exploracion() {
		return probabilidad_exploracion;
	}

	/**
	 * @param probabilidad_exploracion the probabilidad_exploracion to set
	 */
	public static void setProbabilidad_exploracion(double probabilidad_exploracion) {
		Entrenamiento.probabilidad_exploracion = probabilidad_exploracion;
	}

	/**
	 * Realiza una partida entre dos jugadores dado el estado inicial del juego.
	 * Supondremos que jug2 es un JugadorEvaluar con un evaluador entrenable por el método de las diferencias temporales.
	 * Este método modifica destructivamente el evaluador del jugador jug2 entrenándolo en el transcurso de la partida.
	 * Además, jug2 realiza movimientos de exploración aleatorios con una probabilidad dada.
	 * 
	 * @param <Evaluador>	El evaluador debe implementar la interfaz DiferenciasTemporales.
	 * @param jug1			Jugador 1.
	 * @param jug2			Jugador 2 (suponemos que es un JugadorEvaluar con un evaluador entrenable por el método de las diferencias temporales).
	 * @param e				Estado inicial del juego.
	 */
	@SuppressWarnings({ "unchecked" })
	public static <Evaluador extends DiferenciasTemporales> void aprendeJug2(Jugador jug1, JugadorEvaluar jug2, EstadoJuego e) {
		Evaluador ev = (Evaluador) jug2.getEvaluador();
		aprendeJug2(jug1, jug2, e, probabilidad_exploracion, ev);
	}
	
	/**
	 * Realiza una partida entre dos jugadores dado el estado inicial del juego.
	 * Supondremos que jug1 es un JugadorEvaluar con un evaluador entrenable por el método de las diferencias temporales.
	 * Este método modifica destructivamente el evaluador del jugador jug1 entrenándolo en el transcurso de la partida.
	 * Además, jug1 realiza movimientos de exploración aleatorios con una probabilidad dada.
	 * 
	 * @param <Evaluador>	El evaluador debe implementar la interfaz DiferenciasTemporales.
	 * @param jug1			Jugador 1.
	 * @param jug2			Jugador 2 (suponemos que es un JugadorEvaluar con un evaluador entrenable por el método de las diferencias temporales).
	 * @param e				Estado inicial del juego.
	 */
	@SuppressWarnings("unchecked")
	public static <Evaluador extends DiferenciasTemporales> void aprendeJug1(JugadorEvaluar jug1, Jugador jug2, EstadoJuego e) {
		Evaluador ev = (Evaluador) jug1.getEvaluador();
		aprendeJug1(jug1, jug2, e, probabilidad_exploracion, ev);
	}
	
	/**
	 * Realiza una partida entre dos jugadores dado el estado inicial del juego.
	 * Supondremos que jug1 y jug2 son Jugadores Evaluadores con un evaluador entrenable por el método de las diferencias temporales.
	 * Este método modifica destructivamente el evaluador de los jugadores entrenándolos en el transcurso de la partida.
	 * Además, los jugadores realizan movimientos de exploración aleatorios con una probabilidad dada.
	 * 
	 * @param <Evaluador>	El evaluador debe implementar la interfaz DiferenciasTemporales.
	 * @param jug1			Jugador 1 (suponemos que es un JugadorEvaluar con un evaluador entrenable por el método de las diferencias temporales).
	 * @param jug2			Jugador 2 (suponemos que es un JugadorEvaluar con un evaluador entrenable por el método de las diferencias temporales).
	 * @param e				Estado inicial del juego.
	 */
	/*@SuppressWarnings("unchecked")
	public static <Evaluador extends DiferenciasTemporales> void aprendeJug(JugadorEvaluar jug1, JugadorEvaluar jug2, EstadoJuego e) {
		Evaluador ev = (Evaluador) jug1.getEvaluador();
		Evaluador ev2 = (Evaluador) jug2.getEvaluador();
		aprendeJug(jug1, jug2, e, PROBABILIDAD_EXPLORACION, ev, ev2);
	}*/
	
	/* INSUFICIENTE
	 * e2 puede ser final.
	 * No realiza movimientos exploratorios.
	 * 
	private static void aprendeJug2(Jugador jug1, JugadorEvaluar jug2, EstadoJuego e,  EvaluadorTv ev) {
		EstadoJuego e2 = jug1.mueve(e);
		EstadoJuego e3 = jug2.mueve(e2);
		if (e3.ganador() != null) {
			ev.estadoGanador(e3);
			ev.actualizaDt(e, e3);
		} else if (e3.agotado()) {
			ev.estadoEmpate(e3);
			ev.actualizaDt(e, e3);
		} else {
			ev.actualizaDt(e, e3);
			aprendeJug2(jug1, jug2, e3, ev);
		}
	}
	*/
	
	/**
	 * Función recursiva aprendeJug2.
	 * 
	 * @param <Evaluador>	El evaluador debe implementar la interfaz DiferenciasTemporales.
	 * @param jug1			Jugador 1.
	 * @param jug2			Jugador 2 (Jugador Evaluar).
	 * @param e				Estado inicial.
	 * @param pex			Probabilidad de exploración.
	 * @param ev			Evaluador.
	 */
	private static <Evaluador extends DiferenciasTemporales> void aprendeJug2(Jugador jug1, JugadorEvaluar jug2, EstadoJuego e, double pex, Evaluador ev) {
		EstadoJuego e2 = jug1.mueve(e);
		if (e2.ganador() != null) {
			ev.estadoPerdedor(e2);
			ev.actualizaDt(e, e2);	// Termina perdiendo.
		} else if (e2.agotado()) {
			ev.estadoEmpate(e2);
			ev.actualizaDt(e, e2);	// Termina empatando.
		} else {
			EstadoJuego e3;
			boolean explora = random.nextDouble() < pex;	// Cierto con probabilidad pex.
			if (explora) {
				List<EstadoJuego> lh = e2.hijos();
				e3 = lh.get(random.nextInt(lh.size()));
			} else {
				e3 = jug2.mueve(e2);
			}
			if (e3.ganador() != null) {
				ev.estadoGanador(e3);
				if (!explora) {
					ev.actualizaDt(e, e3);
				}
			} else if (e3.agotado()) {
				ev.estadoEmpate(e3);
				if (!explora) {
					ev.actualizaDt(e, e3);
				}
			} else {
				if (!explora) {
					ev.actualizaDt(e, e3);
				}
				aprendeJug2(jug1, jug2, e3, pex, ev);
			}
		}
	}	

	/**
	 * Realiza el primer movimiento y llama a aprendeJug2 con 
	 * los jugadores intercambiados para que aprenda el jugador 1.
	 * 
	 * @param <Evaluador>	El evaluador debe implementar la interfaz DiferenciasTemporales.
	 * @param jug1			Jugador 1.
	 * @param jug2			Jugador 2 (Jugador Evaluar).
	 * @param e				Estado inicial.
	 * @param pex			Probabilidad de exploración.
	 * @param ev			Evaluador.
	 */
	private static <Evaluador extends DiferenciasTemporales> void aprendeJug1(JugadorEvaluar jug1, Jugador jug2, EstadoJuego e, double pex, Evaluador ev) {
		EstadoJuego e2 = jug1.mueve(e);
		if (e2.ganador() != null) {
			ev.estadoGanador(e2);
			ev.actualizaDt(e, e2);
		} else if (e2.agotado()) {
			ev.estadoEmpate(e2);
			ev.actualizaDt(e, e2);
		} else {
			aprendeJug2(jug2, jug1, e2, pex, ev);
		}
	}
	
	/**
	 * Función recursiva aprendeJug.
	 * 
	 * @param <Evaluador>	El evaluador debe implementar la interfaz DiferenciasTemporales.
	 * @param jug1			Jugador 1 (Jugador Evaluar).
	 * @param jug2			Jugador 2 (Jugador Evaluar).
	 * @param e				Estado inicial.
	 * @param pex			Probabilidad de exploración.
	 * @param ev			Evaluador del jugador 1.
	 * @param ev2			Evaluador del jugador 2.
	 */
	// Esta mal 
	/*private static <Evaluador extends DiferenciasTemporales> void aprendeJug(JugadorEvaluar jug1, JugadorEvaluar jug2, EstadoJuego e, double pex, Evaluador ev, Evaluador ev2) {
		EstadoJuego e2;
		boolean explora = random.nextDouble() < pex;	// Cierto con probabilidad pex.
		if (explora) {
			List<EstadoJuego> lh = e.hijos();
			e2 = lh.get(random.nextInt(lh.size()));
		} else {
			e2 = jug1.mueve(e);
		}
		if (e2.ganador() != null) {
			ev.estadoGanador(e2);
			ev2.estadoPerdedor(e2);
			if (!explora) {
				ev.actualizaDt(e, e2);
				ev2.actualizaDt(e, e2);
			}
		} else if (e2.agotado()) {
			ev.estadoEmpate(e2);
			ev2.estadoEmpate(e2);
			if (!explora) {
				ev.actualizaDt(e, e2);
				ev2.actualizaDt(e, e2);
			}
		} else {
			if (!explora) {
				ev.actualizaDt(e, e2);
				ev2.actualizaDt(e, e2);
			}
			aprendeJug(jug2, jug1, e2, pex, ev2, ev);
		}
	}*/
	
	/**
	 * Realiza nPartidas para cada jugador sobre el juego dado entrenando a los dos jugadores.
	 * 
	 * @param nPartidas	Número de partidas a jugar para cada jugador.
	 * @param jug1		Jugador 1.
	 * @param jug2		Jugador 2.
	 * @param e			Estado inicial.
	 */
	public static void entrenarJug(int nPartidas, JugadorEvaluar jug1, JugadorEvaluar jug2, EstadoJuego e) {
		for (int i = 1; i <= nPartidas; i++) {
			aprendeJug1(jug1, jug2, e);
			aprendeJug2(jug1, jug2, e);
		}
	}
	
	/**
	 * Realiza nPartidas sobre el juego dado entrenando al jugador 2.
	 * 
	 * @param nPartidas	Número de partidas a jugar.
	 * @param jug1		Jugador 1.
	 * @param jug2		Jugador 2.
	 * @param e			Estado inicial.
	 */
	public static void entrenarJug2(int nPartidas, Jugador jug1, JugadorEvaluar jug2, EstadoJuego e) {
		for (int i = 1; i <= nPartidas; i++) {
			aprendeJug2(jug1, jug2, e);
		}
	}
	
	/**
	 * Realiza nPartidas sobre el juego dado entrenando al jugador 1.
	 * 
	 * @param nPartidas	Número de partidas a jugar.
	 * @param jug1		Jugador 1.
	 * @param jug2		Jugador 2.
	 * @param e			Estado inicial.
	 */
	public static void entrenarJug1(int nPartidas, JugadorEvaluar jug1, Jugador jug2, EstadoJuego e) {
		for (int i = 1; i <= nPartidas; i++) {
			aprendeJug1(jug1, jug2, e);
		}
	}


	/**
	 * Realiza un total de nPartidas de entrenamiento para un jugador evaluador de estados.
	 * Independientemente del tipo de evaluador. Entrena el jugador 2.
	 * Cada cadaM partidas se hace una pausa en el entrenamiento, se juegan kPruebas partidas contra jug1.
	 * 
	 * @param nPartidas		Número de partidas de entrenamiento.
	 * @param cadaM			Número de partidas tras las cuales se hacen las pruebas.
	 * @param kPruebas		Número de pruebas.
	 * @param jug1			Jugador 1.
	 * @param jug2			Jugador 2 entrenable.
	 * @param e				Estado inicial.
	 */
	public static void verAprendizajeJug2GUI(int nPartidas, int cadaM, int kPruebas, Jugador jug1, JugadorEvaluar jug2, EstadoJuego e, PanelEntrenamiento p) {
		double[] res;
		double[] res2;
		String resultados = new String();
		
		resultados = "N. partidas     Gana J1 %     empate %     Gana J2 %";
		p.actualizarAprendizaje(resultados);
		res = nPartidas(kPruebas, jug1, jug2, e);
		res2 = Util.formatoResultados(res);
		resultados = "\n" + 0 + "                    " + res2[0] + "               " + res2[2] + "               " + res2[1];
		p.actualizarAprendizaje(resultados);
		for (int i = cadaM; i <= nPartidas; i+=cadaM) {
			entrenarJug2(cadaM, jug1, jug2, e);
			res = nPartidas(kPruebas, jug1, jug2, e);
			res2 = Util.formatoResultados(res);
			resultados = "\n" + i + "                    " + res2[0] + "               " + res2[2] + "               " + res2[1];
			p.actualizarAprendizaje(resultados);
		}
	}
	
	/**
	 * Realiza un total de nPartidas de entrenamiento para un jugador evaluador de estados.
	 * Independientemente del tipo de evaluador. Entrena el jugador 1.
	 * Cada cadaM partidas se hace una pausa en el entrenamiento, se juegan kPruebas partidas contra jug2.
	 * 
	 * @param nPartidas		Número de partidas de entrenamiento.
	 * @param cadaM			Número de partidas tras las cuales se hacen las pruebas.
	 * @param kPruebas		Número de pruebas.
	 * @param jug1			Jugador 1 entrenable.
	 * @param jug2			Jugador 2.
	 * @param e				Estado inicial.
	 */
	public static void verAprendizajeJug1GUI(int nPartidas, int cadaM, int kPruebas, JugadorEvaluar jug1, Jugador jug2, EstadoJuego e, PanelEntrenamiento p) {
		double[] res;
		double[] res2;
		String resultados = new String();
		
		resultados = "N. partidas     Gana J1 %     empate %     Gana J2 %";
		p.actualizarAprendizaje(resultados);
		res = nPartidas(kPruebas, jug1, jug2, e);
		res2 = Util.formatoResultados(res);
		resultados = "\n" + 0 + "                    " + res2[0] + "               " + res2[2] + "               " + res2[1];
		p.actualizarAprendizaje(resultados);
		for (int i = cadaM; i <= nPartidas; i+=cadaM) {
			entrenarJug1(cadaM, jug1, jug2, e);
			res = nPartidas(kPruebas, jug1, jug2, e);
			res2 = Util.formatoResultados(res);
			resultados = "\n" + i + "                    " + res2[0] + "               " + res2[2] + "               " + res2[1];
			p.actualizarAprendizaje(resultados);
		}
	}
	
	/**
	 * Realiza un total de nPartidas de entrenamiento para cada jugador evaluador de estados.
	 * Independientemente del tipo de evaluador. Entrena a los dos jugadores simultáneamente.
	 * Cada cadaM partidas se hace una pausa en el entrenamiento, se juegan kPruebas partidas entre ellos.
	 * 
	 * @param nPartidas		Número de partidas de entrenamiento.
	 * @param cadaM			Número de partidas tras las cuales se hacen las pruebas.
	 * @param kPruebas		Número de pruebas.
	 * @param jug1			Jugador 1 entrenable.
	 * @param jug2			Jugador 2 entrenable.
	 * @param e				Estado inicial.
	 */
	public static void verAprendizajeJugGUI(int nPartidas, int cadaM, int kPruebas, JugadorEvaluar jug1, JugadorEvaluar jug2, EstadoJuego e, PanelEntrenamiento p) {
		double[] res;
		double[] res2;
		String resultados = new String();
		
		resultados = "N. partidas     Gana J1 %     empate %     Gana J2 %";
		p.actualizarAprendizaje(resultados);
		res = nPartidas(kPruebas, jug1, jug2, e);
		res2 = Util.formatoResultados(res);
		resultados = "\n" + 0 + "                    " + res2[0] + "               " + res2[2] + "               " + res2[1];
		p.actualizarAprendizaje(resultados);
		for (int i = cadaM; i <= nPartidas; i+=cadaM) {
			entrenarJug(cadaM, jug1, jug2, e);
			res = nPartidas(kPruebas, jug1, jug2, e);
			res2 = Util.formatoResultados(res);
			resultados = "\n" + i + "                    " + res2[0] + "               " + res2[2] + "               " + res2[1];
			p.actualizarAprendizaje(resultados);
		}
	}
	
	
	/**
	 * Realiza un total de nPartidas de entrenamiento para un evaluador con tabla de valor para el estado dado.
	 * Cada cadaM partidas se hace una pausa en el entrenamiento, se juegan kPruebas partidas contra una estrategia aleatoria,
	 * y se muestra por pantalla una estadística de los resultados.
	 * 
	 * @param nPartidas				Número de partidas de entrenamiento.
	 * @param cadaM					Número de partidas tras las cuales se hacen las pruebas.
	 * @param kPruebas				Número de pruebas.
	 * @param nf					Número de filas del tablero.
	 * @param nc					Número de columnas del tablero.
	 * @param k						Número de la longitud ganadora del juego.
	 * @return						Devuelve la tabla aprendida.
	 */
	/*public static HashMap<String, Double> verAprendizajeTv(int nPartidas, int cadaM, int kPruebas, int nf, int nc, int k) {
		Jugador ja = new JugadorAleatorio();
		JugadorEvaluar je = new JugadorEvaluar(new EvaluadorTv());
		double[] res;
		
		System.out.println("Num. partidas     pierdeJ2 %     empate %     ganaJ2 %     Tam. tabla");
		for (int i = 0; i <= nPartidas; i+=cadaM) {
			entrenarJug2(i, ja, je, new ConectaK(nf,nc,k));
			res = nPartidas(kPruebas, ja, je, new ConectaK(nf,nc,k));
			mostrarEstadisticas(i, res[0], res[1], res[2]);
			System.out.print(((EvaluadorTv)je.getEvaluador()).tam()+ "          ");
			System.out.println();
		}
		return ((EvaluadorTv)je.getEvaluador()).getTabla();
	}*/
	
	/**
	 * Realiza un total de nPartidas de entrenamiento para entrenar el jugador 1 en el juego indicado por el estado e.
	 * Cada cadaM partidas se hace una pausa en el entrenamiento, se juegan kPruebas partidas contra una estrategia aleatoria,
	 * y se muestra por pantalla una estadística de los resultados.
	 * 
	 * @param nPartidas				Número de partidas de entrenamiento.
	 * @param cadaM					Número de partidas tras las cuales se hacen las pruebas.
	 * @param kPruebas				Número de pruebas.
	 * @param e						Estado inicial.
	 * @param jug1					Jugador 1 entrenable.
	 * @param jug2					Jugador 2.
	 */
	public static void verAprendizajeJug1(int nPartidas, int cadaM, int kPruebas, EstadoJuego e, JugadorEvaluar jug1, Jugador jug2) {
		double[] res;
		
		System.out.println("N. partidas     Gana J1 %     empate %     Gana J2 %");
		res = nPartidas(kPruebas, jug1, jug2, e);
		mostrarEstadisticas(0, res[0], res[1], res[2]);
		System.out.println();
		for (int i = cadaM; i <= nPartidas; i+=cadaM) {
			entrenarJug1(cadaM, jug1, jug2, e);
			res = nPartidas(kPruebas, jug1, jug2, e);
			mostrarEstadisticas(i, res[0], res[1], res[2]);
			System.out.println();
		}
	}
	
	/**
	 * Realiza un total de nPartidas de entrenamiento para entrenar el jugador 1 en el juego indicado por el estado e.
	 * Cada cadaM partidas se hace una pausa en el entrenamiento, se juegan kPruebas partidas contra una estrategia aleatoria,
	 * y se muestra por pantalla una estadística de los resultados.
	 * 
	 * @param nPartidas				Número de partidas de entrenamiento.
	 * @param cadaM					Número de partidas tras las cuales se hacen las pruebas.
	 * @param kPruebas				Número de pruebas.
	 * @param e						Estado inicial.
	 * @param jug1					Jugador 1.
	 * @param jug2					Jugador 2 entrenable.
	 */
	public static void verAprendizajeJug2(int nPartidas, int cadaM, int kPruebas, EstadoJuego e, Jugador jug1, JugadorEvaluar jug2) {
		double[] res;
		
		System.out.println("N. partidas     Gana J1 %     empate %     Gana J2 %");
		res = nPartidas(kPruebas, jug1, jug2, e);
		mostrarEstadisticas(0, res[0], res[1], res[2]);
		System.out.println();
		for (int i = cadaM; i <= nPartidas; i+=cadaM) {
			entrenarJug2(cadaM, jug1, jug2, e);
			res = nPartidas(kPruebas, jug1, jug2, e);
			mostrarEstadisticas(i, res[0], res[1], res[2]);
			System.out.println();
		}
	}
	
	/**
	 * Realiza un total de nPartidas de entrenamiento para entrenar a los dos jugadores en el juego indicado por el estado e.
	 * Cada cadaM partidas se hace una pausa en el entrenamiento, se juegan kPruebas partidas entre ellos,
	 * y se muestra por pantalla una estadística de los resultados.
	 * 
	 * @param nPartidas				Número de partidas de entrenamiento.
	 * @param cadaM					Número de partidas tras las cuales se hacen las pruebas.
	 * @param kPruebas				Número de pruebas.
	 * @param e						Estado inicial.
	 * @param jug1					Jugador 1 entrenable.
	 * @param jug2					Jugador 2 entrenable.
	 */
	public static void verAprendizajeJug(int nPartidas, int cadaM, int kPruebas, EstadoJuego e, JugadorEvaluar jug1, JugadorEvaluar jug2) {
		double[] res;
		
		System.out.println("N. partidas     Gana J1 %     empate %     Gana J2 %");
		res = nPartidas(kPruebas, jug1, jug2, e);
		mostrarEstadisticas(0, res[0], res[1], res[2]);
		System.out.println();
		for (int i = cadaM; i <= nPartidas; i+=cadaM) {
			entrenarJug(cadaM, jug1, jug2, e);
			res = nPartidas(kPruebas, jug1, jug2, e);
			mostrarEstadisticas(i, res[0], res[1], res[2]);
			System.out.println();
		}
	}
	
	/**
	 * Realiza n partidas y devuelve un array con los resultados.
	 * 
	 * @param n			Número de partidas.
	 * @param jug1		Jugador 1.
	 * @param jug2		Jugador 2.
	 * @param e			Estado inicial.
	 * @return			Array con el número de partidas ganadas por el jugador 1, ganadas por el jugador 2 y empatadas.
	 */
	private static double[] nPartidas(int n, Jugador jug1, Jugador jug2, EstadoJuego e) {
		double[] resultados = new double[3];
		int res;
		
		for (int k = 0; k < resultados.length; k++) {
			resultados[k] = 0;
		}
		for (int i = 1; i <= n; i++) {
			res = Juego.jugar(jug1, jug2, e, false);
			switch (res) {
			case 1:	resultados[0]++;
					break;
			case -1: resultados[1]++;
					 break;
			case 0: resultados[2]++;
					break;
			}
		}
		for (int k = 0; k < resultados.length; k++) {
			resultados[k] = resultados[k]/(double)n;
		}
		return resultados;
	}

	/**
	 * Muestra por pantalla las estadísticas de un número de partidas.
	 * 
	 * @param numPartidas	Número de partidas.
	 * @param pierdeJ2		Porcentaje de partidas perdidas por el jugador 2.
	 * @param ganaJ2		Porcentaje de partidas ganadas por el jugador 2.
	 * @param empate		Porcentaje de partidas empatadas por el jugador 2.
	 */
	private static void mostrarEstadisticas(int numPartidas, double ganaJ1, double ganaJ2, double empate) {
		double gJ1 = Util.redondear(ganaJ1*100);
		double gJ2 = Util.redondear(ganaJ2*100);
		double emp = Util.redondear(empate*100);
		System.out.print(numPartidas + "     ");
		System.out.print(gJ1 + "     ");
		System.out.print(emp + "     ");
		System.out.print(gJ2 + "     ");
	}
	
}
