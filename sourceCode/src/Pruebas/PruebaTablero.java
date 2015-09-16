package Pruebas;

import juegos.conectak.juego.TableroCK;
import juegos.util.Ficha;

/**
 * Prueba del tablero del ConectaK.
 * 
 * @author José Miguel Horcas Aguilera
 *
 */
public class PruebaTablero {

	/**
	 * Ejecutable.
	 * 
	 * @param args	Argumentos.
	 */
	public static void main(String[] args) {
		TableroCK tab = new TableroCK(3,3,new Ficha('_'));
		
		System.out.println(tab.toString());
		
		tab.soltarFicha(0, new Ficha('x'));
		System.out.println(tab.toString());
		tab.soltarFicha(0, new Ficha('o'));
		System.out.println(tab.toString());
		tab.soltarFicha(1, new Ficha('x'));
		System.out.println(tab.toString());
		System.out.println(tab.conectadas(2, 0, 0, -1));
	}
}
