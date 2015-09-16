package juegos.util;

/**
 * Representación de las fichas para los juegos.
 *  
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 04/08/2011
 *
 */
public class Ficha {

	/**
	 * Una ficha se representa mediante un carácter.
	 */
	private char ficha;
	
	/**
	 * Crea una ficha representada por el caracter proporcionado.
	 * 
	 * @param ficha	Caracter que representa la ficha.
	 */
	public Ficha(char ficha) {
		this.ficha = ficha;
	}
	
	/**
	 * @return	Caracter que representa la ficha.
	 */
	public char getFicha() {
		return ficha;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Ficha && this.getFicha() == ((Ficha) o).getFicha();
	}
	
	@Override
	public String toString() {
		return String.valueOf(ficha);
	}
}
