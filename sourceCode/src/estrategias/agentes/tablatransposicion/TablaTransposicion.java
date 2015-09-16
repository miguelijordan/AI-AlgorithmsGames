package estrategias.agentes.tablatransposicion;

import java.util.HashMap;
import java.util.Map;

import estrategias.util.EstadoValor;
import estrategias.util.Hash;

import juegos.EstadoJuego;


/**
 * Tabla de transposición implementada mediante una tabla hash indexada por la clave del estado.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 14/08/2011
 *
 */
public class TablaTransposicion {

	/**
	 * Tabla hash.
	 */
	private Map<String, EstadoValor> tt;
	
	/**
	 * Función hash para indexar los nodos.
	 */
    private Hash valorHash;
	
    /**
     * Crea una tabla de transposición vacía.
     */
	public TablaTransposicion() {
		tt = new HashMap<String, EstadoValor>();
        valorHash = new Hash();
	}
	
	/**
	 * Devuelve la información del estado e almacenada en la tabla de transposición, o null si no se encontró.
	 * 
	 * @param e		Estado.
	 * @return		Información del estado e o null si e no existe en la tabla.
	 */
	public EstadoValor getNodo(EstadoJuego e) {
		return tt.get(valorHash.getHash(e.clave()));
	}
	
	/**
	 * Inserta un nuevo nodo en la tabla de transposición.
	 * En caso de que exista el nodo, actualiza su información.
	 * 
	 * @param e		Estado.
	 * @param v		Valor.
	 */
	public void setNodo(EstadoJuego e, EstadoValor n) {
		tt.put(valorHash.getHash(e.clave()), n);
	}
	
	/**
	 * Devuelve verdadero si el nodo está en la tabla, falso en otro caso.
	 * 
	 * @param e		Estado.
	 * @return		Verdadero si el nodo e está en la tabla, falso en caso contrario.
	 */
	public boolean esta(EstadoJuego e) {
		return tt.containsKey(valorHash.getHash(e.clave()));
	}
	
	/**
	 * Devuelve el tamaño de la tabla de transposición (número de nodos que contiene).
	 * 
	 * @return	Número de nodos que contiene la tabla de transposición.
	 */
	public int tamanyo() {
		return tt.size();
	}
}
