package entornointeractivo.gui.resultados;

import entornointeractivo.gui.interfaces.InformacionAyuda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class PanelResultadosAnalizar extends JPanel implements InformacionAyuda {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel_1;
	private JPanel panelEstadoInicial;
	private JPanel panelEstadoMovJug1;
	private JPanel panelEstadoMovJug2;
	private JLabel lblEstadoInicial;
	private JLabel lblMovJug1;
	private JLabel lblMovJug2;
	private JPanel panelInfoEstadoInicial;
	private JTextArea textAreaInfoEstadoInicial;
	private JPanel panelInfoEstadoMovJug1;
	private JTextArea textAreaInfoEstadoMovJug1;
	private JPanel panelInfoEstadoMovJug2;
	private JTextArea textAreaInfoEstadoMovJug2;
	
	/**
	 * Create the panel.
	 */
	public PanelResultadosAnalizar() {
		setBorder(new TitledBorder(new LineBorder(new Color(185, 209, 234)), "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lblEstadoInicial = new JLabel("Estado inicial:");
		lblEstadoInicial.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblEstadoInicial, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		
		panelEstadoInicial = new JPanel();
		panel_2.add(panelEstadoInicial);
		panelEstadoInicial.setPreferredSize(new Dimension(100, 100));
		panelEstadoInicial.setLayout(new BorderLayout(0, 0));
		
		panelInfoEstadoInicial = new JPanel();
		panelInfoEstadoInicial.setPreferredSize(new Dimension(200, 100));
		panel_2.add(panelInfoEstadoInicial);
		panelInfoEstadoInicial.setLayout(new BorderLayout(0, 0));
		
		textAreaInfoEstadoInicial = new JTextArea();
		textAreaInfoEstadoInicial.setWrapStyleWord(true);
		textAreaInfoEstadoInicial.setLineWrap(true);
		textAreaInfoEstadoInicial.setEditable(false);
		JScrollPane pScrollInfoEstadoInicial = new JScrollPane(textAreaInfoEstadoInicial, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelInfoEstadoInicial.add(pScrollInfoEstadoInicial, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelMovimientoJug1 = new JPanel();
		panel.add(panelMovimientoJug1);
		panelMovimientoJug1.setLayout(new BorderLayout(0, 0));
		
		lblMovJug1 = new JLabel("Movimiento jugador 1:");
		lblMovJug1.setHorizontalAlignment(SwingConstants.CENTER);
		panelMovimientoJug1.add(lblMovJug1, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panelMovimientoJug1.add(panel_3, BorderLayout.CENTER);
		
		panelEstadoMovJug1 = new JPanel();
		panel_3.add(panelEstadoMovJug1);
		panelEstadoMovJug1.setPreferredSize(new Dimension(100, 100));
		panelEstadoMovJug1.setLayout(new BorderLayout(0, 0));
		
		panelInfoEstadoMovJug1 = new JPanel();
		panelInfoEstadoMovJug1.setPreferredSize(new Dimension(200, 100));
		panel_3.add(panelInfoEstadoMovJug1);
		panelInfoEstadoMovJug1.setLayout(new BorderLayout(0, 0));
		
		textAreaInfoEstadoMovJug1 = new JTextArea();
		textAreaInfoEstadoMovJug1.setWrapStyleWord(true);
		textAreaInfoEstadoMovJug1.setLineWrap(true);
		textAreaInfoEstadoMovJug1.setEditable(false);
		JScrollPane pScrollInfoEstadoMovJug1 = new JScrollPane(textAreaInfoEstadoMovJug1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelInfoEstadoMovJug1.add(pScrollInfoEstadoMovJug1, BorderLayout.CENTER);
		
		JPanel panelMovimientoJug2 = new JPanel();
		panel.add(panelMovimientoJug2);
		panelMovimientoJug2.setLayout(new BorderLayout(0, 0));
		
		lblMovJug2 = new JLabel("Movimiento jugador 2:");
		lblMovJug2.setHorizontalAlignment(SwingConstants.CENTER);
		panelMovimientoJug2.add(lblMovJug2, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		panelMovimientoJug2.add(panel_4, BorderLayout.CENTER);
		
		panelEstadoMovJug2 = new JPanel();
		panel_4.add(panelEstadoMovJug2);
		panelEstadoMovJug2.setPreferredSize(new Dimension(100, 100));
		panelEstadoMovJug2.setLayout(new BorderLayout(0, 0));
		
		panelInfoEstadoMovJug2 = new JPanel();
		panelInfoEstadoMovJug2.setPreferredSize(new Dimension(200, 100));
		panel_4.add(panelInfoEstadoMovJug2);
		panelInfoEstadoMovJug2.setLayout(new BorderLayout(0, 0));
		
		textAreaInfoEstadoMovJug2 = new JTextArea();
		textAreaInfoEstadoMovJug2.setWrapStyleWord(true);
		textAreaInfoEstadoMovJug2.setLineWrap(true);
		textAreaInfoEstadoMovJug2.setEditable(false);
		JScrollPane pScrollInfoEstadoMovJug2 = new JScrollPane(textAreaInfoEstadoMovJug2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelInfoEstadoMovJug2.add(pScrollInfoEstadoMovJug2, BorderLayout.CENTER);
		
	}
	
	public void actualizarResultados(JPanel estadoPre, JPanel estadoMovJ1, JPanel estadoMovJ2) {
		panelEstadoInicial.add(estadoPre, BorderLayout.CENTER);
		panelEstadoMovJug1.add(estadoMovJ1, BorderLayout.CENTER);
		panelEstadoMovJug2.add(estadoMovJ2, BorderLayout.CENTER);
		update(getGraphics());
	}

	@Override
	public String infoComponente(Component c) {
		String res = null;
		if (c.equals(lblEstadoInicial) || c.equals(panelEstadoInicial)) {
			res = "Estado inicial analizado.";
		} else if (c.equals(lblMovJug1) || c.equals(panelEstadoMovJug1)) {
			res = "Movimiento devuelto por la estrategia del jugador 1.";
		} else if (c.equals(lblMovJug2) || c.equals(panelEstadoMovJug2)) {
			res = "Movimiento devuelto por la estrategia del jugador 2.";
		} else if (c.equals(textAreaInfoEstadoInicial)) {
			res = "Proporciona información detallada del estado inicial analizado.";
		} else if (c.equals(textAreaInfoEstadoMovJug1)) {
			res = "Proporciona información detallada del estado resultado del movimiento realizado por el jugador 1.";
		} else if (c.equals(textAreaInfoEstadoMovJug2)) {
			res = "Proporciona información detallada del estado resultado del movimiento realizado por el jugador 2.";
		}
		return res;
	}

	@Override
	public void actualizarInfo(String info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		panelEstadoInicial.addMouseListener(ctr);
		panelEstadoMovJug1.addMouseListener(ctr);
		panelEstadoMovJug2.addMouseListener(ctr);
		textAreaInfoEstadoInicial.addMouseListener(ctr);
		textAreaInfoEstadoMovJug1.addMouseListener(ctr);
		textAreaInfoEstadoMovJug2.addMouseListener(ctr);
		lblEstadoInicial.addMouseListener(ctr);
		lblMovJug1.addMouseListener(ctr);
		lblMovJug2.addMouseListener(ctr);
	}
	
	public void actualizarInfoEstadoInicial(String info) {
		textAreaInfoEstadoInicial.setText(info);
		textAreaInfoEstadoInicial.setCaretPosition(0);
	}
	
	public void actualizarInfoEstadoMovJug1(String info) {
		textAreaInfoEstadoMovJug1.setText(info);
		textAreaInfoEstadoMovJug1.setCaretPosition(0);
	}
	
	public void actualizarInfoEstadoMovJug2(String info) {
		textAreaInfoEstadoMovJug2.setText(info);
		textAreaInfoEstadoMovJug2.setCaretPosition(0);
	}
	
	
}
