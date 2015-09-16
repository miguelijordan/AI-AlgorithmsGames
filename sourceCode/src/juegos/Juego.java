package juegos;

import entornointeractivo.gui.simular.PantallaSimulacion;
import estrategias.agentes.Jugador;

/**
 * Proporciona los métodos estáticos para jugar una partida a un juego cualquiera y
 * para jugar nPartidas mostrando estadísticas de los resultados.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 18/08/2011
 *
 */
public class Juego {
	
	/**
	 * Controla el desarrollo de un juego hasta que la partida haya terminado.
	 * 
	 * @param jug1	Jugador 1.
	 * @param jug2	Jugador 2.
	 * @param e		Estado inicial de un juego.
	 * @param eco	Permite opcionalmente mostrar por pantalla el desarrollo del juego.
	 * @return		1, 0, -1, según gane el primer jugador, haya empate, o gane el segundo jugador, respectivamente.
	 */
	public static int jugar(Jugador jug1, Jugador jug2, EstadoJuego e, boolean eco) {
		int resultado;
		Jugador jug;
		
		if (eco) {
			System.out.println(e.toString());
		}
		if (e.ganador()!=null) {
			resultado = e.jug1() ? -1 : 1;
		} else if (e.agotado()) {
			resultado = 0;
		} else {
			jug = e.jug1() ? jug1 : jug2;		// Le toca a jug.
			resultado = jugar(jug1, jug2, jug.mueve(e), eco);
		}
		return resultado;
	}
	
	/**
	 * Controla el desarrollo de un juego hasta que la partida haya terminado.
	 * 
	 * @param jug1	Jugador 1.
	 * @param jug2	Jugador 2.
	 * @param e		Estado inicial de un juego.
	 * @param eco	Permite opcionalmente mostrar por pantalla el desarrollo del juego.
	 * @return		1, 0, -1, según gane el primer jugador, haya empate, o gane el segundo jugador, respectivamente.
	 */
	public static int jugarIter(Jugador jug1, Jugador jug2, EstadoJuego e, boolean eco) {
		int resultado = -2;
		Jugador jug;
		
		while (e.ganador() == null && !e.agotado()) {
			if (eco) {
				System.out.println(e.toString());
			}
			jug = e.jug1() ? jug1 : jug2;		// Le toca a jug.
			e = jug.mueve(e);
		}
		if (e.ganador()!=null) {
			resultado = e.jug1() ? -1 : 1;
		} else if (e.agotado()) {
			resultado = 0;
		}
		return resultado;
	}
	
	/**
	 * Permite jugar n partidas diferentes entre los jugadores jug1 y jug2 dado el estado inicial del juego e.
	 * Al terminar si el parámetro eco es true, se muestra por pantalla el porcentaje de partidas ganadas por el primer jugador,
	 * por el segundo, y el número de empates.
	 * 
	 * @param n		Número de partidas a jugar.
	 * @param jug1	Jugador 1.
	 * @param jug2	Jugador 2.
	 * @param e		Estado inicial del juego.
	 * @param eco	Indica si se muestran por pantalla las estadísticas.
	 * @return		Array con el número de partidas ganadas, empatadas y perdidas por jug1.
	 */
	public static double[] nPartidas(int n, Jugador jug1, Jugador jug2, EstadoJuego e, boolean eco) {
		int ganaJ1 = 0;
		int ganaJ2 = 0;
		int empates = 0;
		int res;
		
		for (int i = 1; i <= n; i++) {		
			res = Juego.jugar(jug1, jug2, e, false);
			switch (res) {
			case 1:	ganaJ1++;
					break;
			case -1: ganaJ2++;
					 break;
			case 0: empates++;
					break;
			}
		}
		double[] resultados = new double[3];
		resultados[0] = ((double)ganaJ1/(double)n);
		resultados[1] = ((double)empates/(double)n);
		resultados[2] = ((double)ganaJ2/(double)n);
		resultados = formatoResultados(resultados);
		if (eco) {
			System.out.println();
			System.out.println("Partidas ganadas por el jugador 1: " + resultados[0] + " %");
			System.out.println("Partidas empatadas: " + resultados[1] + " %");
			System.out.println("Partidas ganadas por el jugador 2: " + resultados[2] + " %");
		}
		return resultados;
	}
	
	/**
	 * Permite jugar n partidas diferentes entre los jugadores jug1 y jug2 dado el estado inicial del juego e.
	 * Al terminar se muestra por pantalla el porcentaje de partidas ganadas por el primer jugador,
	 * por el segundo, y el número de empates.
	 * 
	 * @param n		Número de partidas a jugar.
	 * @param jug1	Jugador 1.
	 * @param jug2	Jugador 2.
	 * @param e		Estado inicial del juego.
	 * @param p		Pantalla de la simulación que se actualizará en tiempo real.
	 * @return		Array con el número de partidas ganadas, empatadas y perdidas por jug1.
	 */
	public static double[] nPartidasGUI(int n, Jugador jug1, Jugador jug2, EstadoJuego e, PantallaSimulacion p) {
		int ganaJ1 = 0;
		int ganaJ2 = 0;
		int empates = 0;
		int res;
		
		for (int i = 1; i <= n; i++) {
			res = Juego.jugar(jug1, jug2, e, false);
			switch (res) {
			case 1:	ganaJ1++;
					break;
			case -1: ganaJ2++;
					 break;
			case 0: empates++;
					break;
			}
			p.actualizarSimulacion(n, i, (int)(100*((double)i/(double)n)));
		}
		
		double[] resultados = new double[3];
		resultados[0] = ((double)ganaJ1/(double)n);
		resultados[1] = ((double)empates/(double)n);
		resultados[2] = ((double)ganaJ2/(double)n);
		resultados = formatoResultados(resultados);
		
		return resultados;
	}
	
	/**
	 * Formatea los resultados convirtiendolos en porcentajes de 2 decimales.
	 * 
	 * @param res	Array con los resultados.
	 * @return		Array con los resultados formateados.
	 */
	private static double[] formatoResultados(double[] res) {
		for (int i = 0; i < res.length; i++) {
			res[i] = redondear(res[i]*100);
		}
		return res;
	}
	
	/**
	 * Redondea un valor real a dos decimales.
	 * 
	 * @param d	Valor.
	 * @return	Valor redondeado a dos decimales.
	 */
	private static double redondear(double d) {
		return (Math.rint(d*100)/100);		// Redondea a dos decimales.
	}
}
