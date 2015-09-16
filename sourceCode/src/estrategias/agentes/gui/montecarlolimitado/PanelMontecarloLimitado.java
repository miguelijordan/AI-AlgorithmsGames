package estrategias.agentes.gui.montecarlolimitado;

import entornointeractivo.gui.estrategias.ControladorEstrategia;
import entornointeractivo.gui.interfaces.PanelConfiguracionEstrategia;
import estrategias.agentes.Jugador;
import estrategias.agentes.montecarlo.JugadorMonteCarloLimitado;
import estrategias.agentes.montecarlo.JugadorMonteCarloLimitadoEstadisticas;
import estrategias.agentes.montecarlotreesearch.JugadorMonteCarloTreeSearchLimitado;
import estrategias.agentes.montecarlotreesearch.JugadorMonteCarloTreeSearchLimitadoEstadisticas;

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

public class PanelMontecarloLimitado extends PanelConfiguracionEstrategia implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldTLimite;
	private JCheckBox chckbxMCTreeSearch;
	private JLabel lblTLimite;
	private JPanel panel_2;
	private JLabel lblExploracion;
	private JTextField textFieldExploracion;
	private JLabel lblSegundos;
	private JPanel panel_3;
	private JCheckBox chckbxReutilizarArbol;

	public PanelMontecarloLimitado(EstadoJuego e, ControladorEstrategia ctrEstrategia) {
		super(e, ctrEstrategia);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel);
		
		lblTLimite = new JLabel("Tiempo límite:");
		panel.add(lblTLimite);
		
		textFieldTLimite = new JTextField();
		textFieldTLimite.setText(String.valueOf(JugadorMonteCarloLimitado.TIEMPO_LIMITE));
		panel.add(textFieldTLimite);
		textFieldTLimite.setColumns(5);
		
		lblSegundos = new JLabel("segundos.");
		panel.add(lblSegundos);
		
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
		textFieldExploracion.setText(String.valueOf(JugadorMonteCarloTreeSearchLimitado.CONSTANTE_EXPLORACION));
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
		if (c.equals(lblTLimite) || c.equals(textFieldTLimite) || c.equals(lblSegundos)) {
			info = "Tiempo límite (en segundos) disponible para realizar las simulaciones para cada movimiento.";
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
		lblTLimite.addMouseListener(ctr);
		textFieldTLimite.addMouseListener(ctr);
		chckbxMCTreeSearch.addMouseListener(ctr);
		chckbxReutilizarArbol.addMouseListener(ctr);
	}

	@Override
	public Jugador estrategia() {
		long tiempo;
		Double exploracion = null;
		
		try {
			tiempo = Long.parseLong(textFieldTLimite.getText());
			if (chckbxMCTreeSearch.isSelected()) {
				exploracion = Double.parseDouble(textFieldExploracion.getText());
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(new JFrame(), "Hay un error en los parámetros.");
			return null;
		}
		if (hayError(tiempo, exploracion)) {
			return null;
		}
		
		if (chckbxMCTreeSearch.isSelected()) {
			return new JugadorMonteCarloTreeSearchLimitadoEstadisticas(tiempo, exploracion, chckbxReutilizarArbol.isSelected());
		} else {
			return new JugadorMonteCarloLimitadoEstadisticas(tiempo);
		}
	}
	
	private boolean hayError(long tiempo, Double exploracion) {
		if (tiempo <= 0) {
			JOptionPane.showMessageDialog(new JFrame(), "El tiempo límite debe ser mayor o igual a 1 segundo.");
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
