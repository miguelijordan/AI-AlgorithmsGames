package estrategias.agentes.estadisticas;

public interface EstadisticasJugador {

	/**
	 * Proporciona estadísticas propias de la estrategia.
	 * 
	 * @return	Estadísticas en forma de texto formateado.
	 */
	public String getEstadisticas();
	
	/**
	 * Tiempo medio por movimiento en milisegundos.
	 * 
	 * @return	Tiempo medio por movimiento en milisegundos.
	 */
	public long tiempoMedioPorMovimiento();
	
	/**
	 * Número total de movimientos realizados por el jugador.
	 * Este número es independiente del número de partidas jugadas por el jugador.
	 * Es el número de veces que se ha llamado al método "mueve";
	 * 
	 * @return	Número total de movimientos.
	 */
	public int numTotalMovimientos();
	
	/**
	 * Inicializa las estadísticas del jugador.
	 */
	public void inicializarEstadisticas();
}
