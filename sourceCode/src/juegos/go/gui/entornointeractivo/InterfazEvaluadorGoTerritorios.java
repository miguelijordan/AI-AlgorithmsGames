package juegos.go.gui.entornointeractivo;

import heuristicos.evaluadores.Evaluador;
import entornointeractivo.gui.InterfazEvaluador;
import entornointeractivo.gui.interfaces.PanelConfiguracionEvaluador;
import juegos.EstadoJuego;
import juegos.conectak.heuristicos.EvaluadorCK;
import juegos.conectak.juego.ConectaK;
import juegos.go.heuristicos.EvaluadorGoPuntosJp;
import juegos.go.heuristicos.EvaluadorGoTerritorios;
import juegos.go.juego.Go;


public class InterfazEvaluadorGoTerritorios implements InterfazEvaluador {

	// CONSTANTES
	private static final String NOMBRE_EVALUADOR = "Evaluador Go Territorios";
		
	@Override
	public String nombre() {
		return NOMBRE_EVALUADOR;
	}

	@Override
	public PanelConfiguracionEvaluador panelConfiguracion() {
		return null;
	}

	@Override
	public Evaluador evaluador(EstadoJuego e) {
		return new EvaluadorGoTerritorios();
	}

	@Override
	public Class<? extends EstadoJuego> claseEstadoJuego() {
		return Go.class;
	}

	@Override
	public boolean entrenable() {
		return false;
	}

	@Override
	public String informacion() {
		String i = "Evaluador heurístico para el juego del Go.";
		i += "\nEl valor heurístico de un estado es la diferencia entre el número de territorios de MAX y de MIN.";
		return i;
	}
}
