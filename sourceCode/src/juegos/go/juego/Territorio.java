package juegos.go.juego;

/**
 * Territorio cercado en un tablero de Go.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 28/09/2011
 *
 */
public class Territorio {

	/**
	 * Tamaño del territorio (número de posiciones que ocupa).
	 */
	private int nPosiciones;
	
	/**
	 * Número de fichas negras que dominan el territorio.
	 */
	private int nNegras;
	
	/**
	 * Número de fichas blancas que dominan el territorio.
	 */
	private int nBlancas;
	
	
	public Territorio() {
		nPosiciones = 0;
		nNegras = 0;
		nBlancas = 0;
	}

	/**
	 * @return Número de posiciones que ocupa el territorio.
	 */
	public int getnPosiciones() {
		return nPosiciones;
	}

	/**
	 * @return Número de fichas negras que dominan el territorio.
	 */
	public int getnNegras() {
		return nNegras;
	}

	/**
	 * @return Número de fichas blancas que dominan el territorio.
	 */
	public int getnBlancas() {
		return nBlancas;
	}
	
	/**
	 * Incrementa el número de posiciones del territorio en 1.
	 */
	public void incPosiciones() {
		nPosiciones++;
	}
	
	/**
	 * Incrementa el número de fichas negras que dominan el territorio en 1.
	 */
	public void incNegras() {
		nNegras++;
	}
	
	/**
	 * Incrementa el número de fichas blancas que dominan el territorio en 1.
	 */
	public void incBlancas() {
		nBlancas++;
	}

	/**
	 * @param nPosiciones número de posiciones del territorio.
	 */
	public void setnPosiciones(int nPosiciones) {
		this.nPosiciones = nPosiciones;
	}

	/**
	 * @param nNegras número de fichas negras que dominan el territorio.
	 */
	public void setnNegras(int nNegras) {
		this.nNegras = nNegras;
	}

	/**
	 * @param nBlancas número de fichas blancas que dominan el territorio en.
	 */
	public void setnBlancas(int nBlancas) {
		this.nBlancas = nBlancas;
	}
	
}
