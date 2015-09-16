package entornointeractivo.gui.estrategias;

import entornointeractivo.gui.Evaluadores;
import entornointeractivo.gui.InterfazEvaluador;
import entornointeractivo.gui.interfaces.InformacionAyuda;
import entornointeractivo.gui.interfaces.PanelConfiguracionEvaluador;
import entornointeractivo.gui.util.InformacionEntrenamiento;

import heuristicos.evaluadores.Evaluador;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import juegos.EstadoJuego;

public class PanelSeleccionEvaluador extends JPanel implements InformacionAyuda, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Componentes de la interfaz
	private JComboBox cmbEvaluador;
	private JButton btnConfigurar;
	private JTextArea txtInfoEvaluador;
	private JLabel lblEvaluadorHeurstico;
	
	// private PanelSeleccionEvaluador pSeleccionEvaluador;
	private PanelConfiguracionEvaluador pConfiguracionEvaluador; // esto será PantallaConfiguracionEvaluador
	private InterfazEvaluador iEvaluador;
	private EstadoJuego estadoJuego;
	private Evaluador evaluador;
	
	private ControladorEstrategia ctrEstrategia;
	
	
	/**
	 * Create the panel.
	 */
	public PanelSeleccionEvaluador(EstadoJuego estadoJuego, ControladorEstrategia ctrEstrategia) {
		construirVentana(filtrarEvaluadores(estadoJuego));
		this.pConfiguracionEvaluador = null;
		this.estadoJuego = estadoJuego;
		this.ctrEstrategia = ctrEstrategia;
		
		this.registrarControlador(this);
			
		iEvaluador = ((Evaluadores) cmbEvaluador.getSelectedItem()).getInterfaz();
		this.evaluador = iEvaluador.evaluador(estadoJuego);
		actualizarInfoEvaluador(iEvaluador.informacion());
		ctrEstrategia.setiEvaluador(iEvaluador);
		activarConfiguracion();
	}
	
	private List<Evaluadores> filtrarEvaluadores(EstadoJuego estadoJuego) {
		List<Evaluadores> evaluadoresDisponibles = new ArrayList<Evaluadores>();
		Evaluadores[] evaluadores = Evaluadores.values();
		for (Evaluadores e : evaluadores) {
			Class<? extends EstadoJuego> clase = e.getInterfaz().claseEstadoJuego();
			if (clase == null || clase.equals(estadoJuego.getClass())) {
				evaluadoresDisponibles.add(e);
			}
		}
		return evaluadoresDisponibles;
	}
	
	private void construirVentana(List<Evaluadores> evaluadores) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.NORTH);
		
		lblEvaluadorHeurstico = new JLabel("Evaluador heurístico:");
		panel_2.add(lblEvaluadorHeurstico);
		
		cmbEvaluador = new JComboBox(evaluadores.toArray());
		panel_2.add(cmbEvaluador);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		
		btnConfigurar = new JButton("Configurar");
		panel_3.add(btnConfigurar);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		
		txtInfoEvaluador = new JTextArea();
		txtInfoEvaluador.setWrapStyleWord(true);
		txtInfoEvaluador.setLineWrap(true);
		txtInfoEvaluador.setRows(5);
		txtInfoEvaluador.setColumns(20);
		JScrollPane pScrollInfoEv = new JScrollPane(txtInfoEvaluador, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel_1.add(pScrollInfoEv);
	}
	
	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		lblEvaluadorHeurstico.addMouseListener(ctr);
		cmbEvaluador.addMouseListener(ctr);
		btnConfigurar.addMouseListener(ctr);
		txtInfoEvaluador.addMouseListener(ctr);
	}
	
	@Override
	public String infoComponente(Component c) {
		String res = null;
		
		if (c.equals(lblEvaluadorHeurstico) || c.equals(cmbEvaluador)) {
			res = "Selecciona un evaluador heurístico.";
		} else if (c.equals(btnConfigurar)) {
			res = "Configura el evaluador heurístico.";
		} else if (c.equals(txtInfoEvaluador)) {
			res = "Información sobre el evaluador heurístico seleccionado.";
		}
		return res;
	}

	@Override
	public void actualizarInfo(String info) {
		// TODO Auto-generated method stub
	}
	
	private void registrarControlador(ActionListener ctr) {
		cmbEvaluador.addActionListener(ctr);
		cmbEvaluador.setActionCommand("Seleccionar Evaluador");
		
		btnConfigurar.addActionListener(ctr);
		btnConfigurar.setActionCommand("Configurar Evaluador");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Seleccionar Evaluador")) {
			iEvaluador = ((Evaluadores) cmbEvaluador.getSelectedItem()).getInterfaz();
			// CUIDADO QUE NO SE QUEDA GUARDADO EL EVALUADOR Y SI SE CAMBIA EN EL COMBOBOX SE CAMBIA TAMBIEN AQUI.
			this.evaluador = iEvaluador.evaluador(estadoJuego);
			actualizarInfoEvaluador(iEvaluador.informacion());
			ctrEstrategia.setiEvaluador(iEvaluador);
			actualizarInformacionEntrenamiento();
			activarConfiguracion();
		} else if (command.equals("Configurar Evaluador")) {
			ctrEstrategia.desactivarPantalla();
			new ControladorEvaluador(iEvaluador, ctrEstrategia);
			// new de la pantalla de configuracion;
			//interfazEvaluador.panelConfiguracion();
		}
		
	}
	
	private void actualizarInformacionEntrenamiento() {
		if (ctrEstrategia.isJug1()) {
			if (iEvaluador.entrenable()) {
				ctrEstrategia.setInfoEntrenamiento1(new InformacionEntrenamiento());		
			} else {
				ctrEstrategia.setInfoEntrenamiento1(null);
			}
		} else {
			if (iEvaluador.entrenable()) {
				ctrEstrategia.setInfoEntrenamiento2(new InformacionEntrenamiento());
			} else {
				ctrEstrategia.setInfoEntrenamiento2(null);
			}
		}
	}
	
	private void activarConfiguracion() {
		pConfiguracionEvaluador = iEvaluador.panelConfiguracion();
		//pConfiguracionEvaluador // poner como no visible.
		if (pConfiguracionEvaluador == null) {
			btnConfigurar.setEnabled(false);
		} else {
			btnConfigurar.setEnabled(true);
		}
	}
	
	public Evaluador getEvaluador() {
		Evaluador ev = ctrEstrategia.getEvaluador();
		if (ev == null) {
			ev = this.evaluador;
		}
		return ev;
	}
	
	public void actualizarInfoEvaluador(String msg) {
		txtInfoEvaluador.setText(msg);
		txtInfoEvaluador.setCaretPosition(0);
	}
	
	public void setEvaluador(Evaluador evaluador) {
		this.evaluador = evaluador;
	}

	public void setCtrEstrategia(ControladorEstrategia ctrEstrategia) {
		this.ctrEstrategia = ctrEstrategia;
	}
}
