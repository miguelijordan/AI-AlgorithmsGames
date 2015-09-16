package heuristicos.evaluadores;

import heuristicos.aprendizaje.DiferenciasTemporales;

import java.util.HashMap;

import estrategias.util.Hash;

import juegos.EstadoJuego;
import juegos.util.Ficha;

/**
 * Objeto evaluador que implementa la función de evaluación mediante una tabla de valor y
 * aprendizaje mediante el método de las diferencias temporales.
 * Almacena en la tabla el valor de la evaluación heurística para cada estado.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 21/08/2011
 *
 */
public class EvaluadorTv implements Evaluador, DiferenciasTemporales {

	// CONSTANTES
	/**
	 * Valor por defecto para los estados ganadores.
	 */
	public static final double VALOR_GANAR = 100;
	
	/**
	 * Valor por defecto para los estados perdedores.
	 */
	public static final double VALOR_PERDER = -100;
	
	/**
	 * Valor por defecto para los estados de empate.
	 */
	public static final double VALOR_EMPATAR = 0;
	
	/**
	 * Valor por defecto para la tasa de aprendizaje.
	 */
	public static final double ALFA = 0.1;
	
	// ATRIBUTOS
	
	/**
	 * Tabla hash indexada por la clave de los estados.
	 */
	private HashMap<String, Double> tabla;
	
	/**
	 * Valor para los estados ganadores.
	 */
	private double ganar;
	
	/**
	 * Valor para los estados perdedores.
	 */
	private double perder;
	
	/**
	 * Valor para los estados de empate.
	 */
	private double empatar;
	
	/**
	 * Tasa de aprendizaje.
	 */
	private double alfa;
	
	/**
	 * Función hash para indexar los estados.
	 */
    private Hash valorHash;
    
    // CONSTRUCTORES
    /**
     * Crea un evaluador con tabla de valor y aprendizaje mediante diferencias temporales.
     * Inicializa los atributos a los valores por defecto.
     */
	public EvaluadorTv() {
		valorHash = new Hash();
		tabla = new HashMap<String, Double>();
		ganar = VALOR_GANAR;
		perder = VALOR_PERDER;
		empatar = VALOR_EMPATAR;
		alfa = ALFA;
	}
	
	/**
	 * Crea un evaluador con tabla de valor y aprendizaje mediante diferencias temporales.
	 * Inicializa los atributos a los valores dados.
	 * 
	 * @param g	Valor en caso de ganar.
	 * @param p	Valor en caso de perder.
	 * @param e	Valor en caso de empatar.
	 * @param a	Valor de la tasa de aprendizaje alfa.
	 */
	public EvaluadorTv(double g, double p, double e, double a) {
		valorHash = new Hash();
		tabla = new HashMap<String, Double>();
		ganar = g;
		perder = p;
		empatar = e;
		alfa = a;
	}
	
	// MÉTODOS
	/**
	 * Guarda valor para estado.
	 * 
	 * @param e		Estado.
	 * @param valor	Valor.
	 */
	public void asignar(EstadoJuego e, double valor) {
		tabla.put(valorHash.getHash(e.clave()), valor);
	}
	
	/**
	 * 
	 * @param e	Estado.
	 * @return	El valor del estado e en la tabla, o 'empatar' si no existe.
	 */
	public double valor(EstadoJuego e) {
		Double v = tabla.get(valorHash.getHash(e.clave()));
		if (v == null) {
			v = empatar;
		}
		return v;
	}
	
	/**
	 * @return La tabla de valores.
	 */
	public HashMap<String, Double> getTabla() {
		return tabla;
	}

	/**
	 * 
	 * @return Número de entradas de la tabla de valores.
	 */
	public int tam() {
		return tabla.size();
	}
	
	@Override
	public double evaluacion(EstadoJuego estado, Ficha fmax, Ficha fmin) {
		return valor(estado);
	}

	@Override
	public void actualizaDt(EstadoJuego e, EstadoJuego e2) {
		double v = valor(e);
		asignar(e, v + alfa*(valor(e2)-v));
	}

	@Override
	public void estadoEmpate(EstadoJuego e) {
		asignar(e, empatar);
	}

	@Override
	public void estadoGanador(EstadoJuego e) {
		asignar(e, ganar);
	}

	@Override
	public void estadoPerdedor(EstadoJuego e) {
		asignar(e, perder);
	}
	
	@Override
	public String toString() {
		String res = "Evaluador con tabla de valor.";
		return res;
	}
	
}
