package Pruebas;

import heuristicos.evaluadores.EvaluadorTv;

import java.util.List;

import juegos.Juego;
import juegos.go.juego.Go;
import juegos.go.juego.TableroGo;
import juegos.util.Ficha;
import juegos.util.Movimiento;
import estrategias.agentes.Jugador;
import estrategias.agentes.aleatorio.JugadorAleatorio;
import estrategias.agentes.evaluar.JugadorEvaluar;

public class PruebasGo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TableroGo t = new TableroGo(9,9, new Ficha('.'));
		System.out.println(t.toString());
		TableroGo tt2 = new TableroGo(t);
		
		t.ponerFicha(8, 1, new Ficha('o'));
		t.ponerFicha(8, 3, new Ficha('o'));
		t.ponerFicha(7, 0, new Ficha('o'));
		t.ponerFicha(7, 2, new Ficha('o'));
		t.ponerFicha(6, 0, new Ficha('x'));
		t.ponerFicha(6, 1, new Ficha('o'));
		t.ponerFicha(6, 2, new Ficha('x'));
		t.ponerFicha(5, 0, new Ficha('x'));
		t.ponerFicha(5, 1, new Ficha('x'));
		/*t.ponerFicha(0, 8, new Ficha('x'));
		t.ponerFicha(1, 4, new Ficha('x'));
		t.ponerFicha(1, 8, new Ficha('x'));
		t.ponerFicha(2, 4, new Ficha('x'));
		t.ponerFicha(2, 5, new Ficha('x'));
		t.ponerFicha(2, 6, new Ficha('x'));
		t.ponerFicha(2, 7, new Ficha('x'));
		t.ponerFicha(2, 8, new Ficha('x'));
		t.ponerFicha(1, 6, new Ficha('o'));
		
		t.ponerFicha(5, 0, new Ficha('o'));
		t.ponerFicha(5, 1, new Ficha('o'));
		t.ponerFicha(5, 2, new Ficha('o'));
		t.ponerFicha(5, 3, new Ficha('o'));
		t.ponerFicha(5, 4, new Ficha('o'));
		t.ponerFicha(5, 5, new Ficha('o'));
		t.ponerFicha(5, 6, new Ficha('o'));
		t.ponerFicha(5, 7, new Ficha('o'));
		t.ponerFicha(5, 8, new Ficha('o'));
		*/
		t.ponerFicha(7, 1, new Ficha('x'));
		List<Movimiento> nFichasCapturadas = t.capturarFichas(7, 1);
		int nTerritorios = t.contarTerritorios(new Ficha('o'));
		
		System.out.println(t.toString());
		System.out.println("Fichas capturadas: " + nFichasCapturadas);
		System.out.println("Nº territorios: " + nTerritorios);
		System.out.println("----------");
		//Juego.jugar(new JugadorAleatorio(), new JugadorEvaluar(new EvaluadorGoTerritorios()), new Go(9,9), true);
	
		//Jugador j2 = new JugadorEvaluar(new EvaluadorGoTerritorios());
		Jugador j1 = new JugadorAleatorio();
		Jugador j2 = new JugadorEvaluar(new EvaluadorTv());
		
		//Jugador j1 = new JugadorEvaluar(new EvaluadorGoPuntosJp());
		//Jugador j1 = new JugadorAleatorio();
		
		//Juego.jugar(j1,j2,new Go(9, 9, 0.0, null, true, Go.REGLAS_JAPONESAS), true);
		/*long t1 = System.currentTimeMillis();
		Juego.nPartidas(100, j1, j2, new Go(9, 9, 0.0, null, true, Go.REGLAS_JAPONESAS), true);
		long t2 = System.currentTimeMillis();
		System.out.println("tiempo: " + (t2-t1));*/
	}

}
