package heuristicos.evaluadores.gui.tablavalor;

import heuristicos.evaluadores.Evaluador;
import heuristicos.evaluadores.EvaluadorTv;
import entornointeractivo.gui.InterfazEvaluador;
import entornointeractivo.gui.interfaces.PanelConfiguracionEvaluador;
import juegos.EstadoJuego;

public class InterfazEvaluadorTablaValor implements InterfazEvaluador {

	// CONSTANTES
	private static final String NOMBRE_EVALUADOR = "Tabla de valor";
		
	@Override
	public String nombre() {
		return NOMBRE_EVALUADOR;
	}

	@Override
	public PanelConfiguracionEvaluador panelConfiguracion() {
		return new PanelConfiguracionTV();
	}

	@Override
	public Evaluador evaluador(EstadoJuego e) {
		return new EvaluadorTv();
	}

	@Override
	public Class<? extends EstadoJuego> claseEstadoJuego() {
		return null;
	}

	@Override
	public boolean entrenable() {
		return true;
	}

	@Override
	public String informacion() {
		String i = "Tabla para almacenar el valor de la evaluación de cada estado,";
		i += " entrenable empleando aprendizaje con refuerzo mediante el método de las diferencias temporales.";
		i += "\ntabla(estado) = tabla(estado) + alfa*(tabla(sucesor) - tabla(estado))";
		return i;
	}
}
