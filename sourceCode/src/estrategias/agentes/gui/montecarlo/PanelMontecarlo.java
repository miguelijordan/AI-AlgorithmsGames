package estrategias.agentes.gui.montecarlo;

import entornointeractivo.gui.estrategias.ControladorEstrategia;
import entornointeractivo.gui.interfaces.PanelConfiguracionEstrategia;
import estrategias.agentes.Jugador;
import estrategias.agentes.montecarlo.JugadorMonteCarlo;
import estrategias.agentes.montecarlo.JugadorMonteCarloEstadisticas;
import estrategias.agentes.montecarlotreesearch.JugadorMonteCarloTreeSearch;
import estrategias.agentes.montecarlotreesearch.JugadorMonteCarloTreeSearchEstadisticas;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import juegos.EstadoJuego;

public class PanelMontecarlo extends PanelConfiguracionEstrategia implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNSimulaciones;
	private JCheckBox chckbxMCTreeSearch;
	private JLabel lblNSimulaciones;
	private JPanel panel_2;
	private JLabel lblExploracion;
	private JTextField textFieldExploracion;
	private JPanel panel_3;
	private JCheckBox chckbxReutilizarArbol;

	public PanelMontecarlo(EstadoJuego e, ControladorEstrategia ctrEstrategia) {
		super(e, ctrEstrategia);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel);
		
		lblNSimulaciones = new JLabel("Nº de simulaciones:");
		panel.add(lblNSimulaciones);
		
		textFieldNSimulaciones = new JTextField();
		textFieldNSimulaciones.setText(String.valueOf(JugadorMonteCarlo.N_SIMULACIONES));
		panel.add(textFieldNSimulaciones);
		textFieldNSimulaciones.setColumns(5);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(panel_1);
		
		chckbxMCTreeSearch = new JCheckBox("Monte-Carlo Tree Search");
		panel_1.add(chckbxMCTreeSearch);
		
		panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		add(panel_2);
		
		lblExploracion = new JLabel("Constante de exploración:");
		panel_2.add(lblExploracion);
		
		textFieldExploracion = new JTextField();
		textFieldExploracion.setText(String.valueOf(JugadorMonteCarloTreeSearch.CONSTANTE_EXPLORACION));
		panel_2.add(textFieldExploracion);
		textFieldExploracion.setColumns(5);
			
		panel_3 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		add(panel_3);
		
		chckbxReutilizarArbol = new JCheckBox("Reutilizar árbol");
		panel_3.add(chckbxReutilizarArbol);
		
		activarTreeSearch();
		
		registrarControlador(this);

	}
	
	@Override
	public String infoComponente(Component c) {
		String info = null;
		if (c.equals(lblNSimulaciones) || c.equals(textFieldNSimulaciones)) {
			info = "Número de simulaciones que se realizan para determinar el mejor movimiento.";
		} else if (c.equals(chckbxMCTreeSearch)) {
			info = "Incluye un árbol de búsqueda para almacenar la información de los nodos visitados durante la simulación.";
		} else if (c.equals(lblExploracion) || c.equals(textFieldExploracion)) {
			info = "Constante de exploración. Valor entre 0 y 1.";
		} else if (c.equals(chckbxReutilizarArbol)) {
			info = "Indica si se debe reutilizar en cada movimiento el árbol generado.";
			info += " En ese caso, también se reutilizará de una partida a otra en las simulaciones.";
		}
		return info;
	}

	@Override
	public void actualizarInfo(String info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		lblNSimulaciones.addMouseListener(ctr);
		textFieldNSimulaciones.addMouseListener(ctr);
		chckbxMCTreeSearch.addMouseListener(ctr);
		chckbxReutilizarArbol.addMouseListener(ctr);
	}

	@Override
	public Jugador estrategia() {
		int nSimulaciones;
		Double exploracion = null;
		
		try {
			nSimulaciones = Integer.parseInt(textFieldNSimulaciones.getText());
			if (chckbxMCTreeSearch.isSelected()) {
				exploracion = Double.parseDouble(textFieldExploracion.getText());
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(new JFrame(), "Hay un error en los parámetros.");
			return null;
		}
		if (hayError(nSimulaciones, exploracion)) {
			return null;
		}
		
		if (chckbxMCTreeSearch.isSelected()) {
			return new JugadorMonteCarloTreeSearchEstadisticas(nSimulaciones, exploracion, chckbxReutilizarArbol.isSelected());
		} else {
			return new JugadorMonteCarloEstadisticas(nSimulaciones);
		}
	}
	
	private boolean hayError(int nSimulaciones, Double exploracion) {
		if (nSimulaciones < 0) {
			JOptionPane.showMessageDialog(new JFrame(), "El número de simulaciones no puede ser negativo.");
			return true;
		}
		if (exploracion != null && (exploracion < 0 || exploracion > 1)) {
			JOptionPane.showMessageDialog(new JFrame(), "La constante de exploración debe estar en el intervalo [0,1].");
			return true;
		}
		return false;
	}
	
	private void registrarControlador(ActionListener ctr) {
		chckbxMCTreeSearch.addActionListener(ctr);
		chckbxMCTreeSearch.setActionCommand("MCTreeSearch");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		
		if (command.equals("MCTreeSearch")) {
			activarTreeSearch();
		}
	}

	private void activarTreeSearch() {
		if (chckbxMCTreeSearch.isSelected()) {
			lblExploracion.setEnabled(true);
			textFieldExploracion.setEnabled(true);
			chckbxReutilizarArbol.setEnabled(true);
		} else {
			lblExploracion.setEnabled(false);
			textFieldExploracion.setEnabled(false);
			chckbxReutilizarArbol.setEnabled(false);
		}
	}
	
}
