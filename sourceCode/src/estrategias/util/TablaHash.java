package estrategias.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import juegos.EstadoJuego;


/**
 * Tabla hash indexada por la clave del estado (EstadoJuego).
 * Permite almacenar cualquier tipo de dato.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 24/08/2011
 *
 * @param <T>	Tipo de dato a almacenar en la tabla.
 */
public class TablaHash<T> {

	/**
	 * Tabla hash.
	 */
	private Map<String, T> tabla;
	
	/**
	 * Función hash para indexar los nodos.
	 */
    private Hash valorHash;
	
    /**
     * Crea una tabla de transposición vacía.
     */
	public TablaHash() {
		tabla = new HashMap<String, T>();
        valorHash = new Hash();
	}
	
	/**
	 * Devuelve la información del estado e almacenada en la tabla hash, o null si no se encontró.
	 * 
	 * @param e		Estado.
	 * @return		Información del estado e o null si e no existe en la tabla.
	 */
	public T getNodo(EstadoJuego e) {
		return tabla.get(valorHash.getHash(e.clave()));
	}
	
	/**
	 * Inserta un nuevo nodo en la tabla hash.
	 * En caso de que exista el nodo, actualiza su información.
	 * 
	 * @param e		Estado.
	 * @param v		Valor.
	 */
	public void setNodo(EstadoJuego e, T n) {
		tabla.put(valorHash.getHash(e.clave()), n);
	}
	
	/**
	 * Devuelve verdadero si el nodo está en la tabla, falso en otro caso.
	 * 
	 * @param e		Estado.
	 * @return		Verdadero si el nodo e está en la tabla, falso en caso contrario.
	 */
	public boolean esta(EstadoJuego e) {
		return tabla.containsKey(valorHash.getHash(e.clave()));
	}
	
	/**
	 * Devuelve el tamaño de la tabla hash (número de nodos que contiene).
	 * 
	 * @return	Número de nodos que contiene la tabla hash.
	 */
	public int tamanyo() {
		return tabla.size();
	}
	
	public Collection<T> valores() {
		return tabla.values();
	}
	
	public Set<String> claves() {
		return tabla.keySet();
	}
}
