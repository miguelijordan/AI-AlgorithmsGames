package estrategias.agentes.gui.evaluar;

import entornointeractivo.gui.estrategias.ControladorEstrategia;
import entornointeractivo.gui.estrategias.PanelSeleccionEvaluador;
import entornointeractivo.gui.interfaces.PanelConfiguracionEstrategia;
import estrategias.agentes.Jugador;
import estrategias.agentes.evaluar.JugadorEvaluarEstadisticas;

import heuristicos.evaluadores.Evaluador;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseListener;

import juegos.EstadoJuego;

public class PanelEvaluar extends PanelConfiguracionEstrategia {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PanelSeleccionEvaluador panelSeleccionEvaluador;
	
	public PanelEvaluar(EstadoJuego e, ControladorEstrategia ctrEstrategia) {
		super(e, ctrEstrategia);
		
		setLayout(new BorderLayout(0, 0));
		panelSeleccionEvaluador = new PanelSeleccionEvaluador(e, ctrEstrategia);
		add(panelSeleccionEvaluador, BorderLayout.CENTER);
	}
	
	@Override
	public String infoComponente(Component c) {
		return panelSeleccionEvaluador.infoComponente(c);
	}

	@Override
	public void actualizarInfo(String info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		panelSeleccionEvaluador.registrarControladorInformacion(ctr);	
	}

	@Override
	public Jugador estrategia() {
		Evaluador evaluador = panelSeleccionEvaluador.getEvaluador();
		return new JugadorEvaluarEstadisticas(evaluador);
	}

}
