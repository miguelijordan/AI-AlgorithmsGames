package entornointeractivo.gui.estrategias;

import entornointeractivo.gui.Estrategias;
import entornointeractivo.gui.interfaces.InformacionAyuda;
import entornointeractivo.gui.interfaces.PanelConfiguracionEstrategia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class PantallaEstrategiaJugador extends JFrame implements InformacionAyuda {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea txtInfo;
	private JButton btnAceptar;
	private JComboBox<Estrategias> cmbEstrategia;
	private JTextArea txtInfoEstrategia;
	private JPanel panelConfiguracion;
	private JButton btnCancelar;
	//private Estrategias estrategiaActual;
	private PanelConfiguracionEstrategia panelConfiguracionEstrategia;
	private JLabel lblEstrategia;
	
	/**
	 * Create the frame.
	 */
	public PantallaEstrategiaJugador(boolean jug1) {
		setTitle("Estrategia del jugador " + (jug1 ? "1" : "2"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 523);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		String strLabelJugador = jug1 ? "JUGADOR 1" : "JUGADOR 2";
		JLabel lblJugador = new JLabel(strLabelJugador);
		lblJugador.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblJugador, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_6.setToolTipText("");
		panel.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new BorderLayout(5, 0));
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.WEST);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9, BorderLayout.CENTER);
		
		lblEstrategia = new JLabel("Estrategia:");
		panel_9.add(lblEstrategia);
		
		cmbEstrategia = new JComboBox<Estrategias>(Estrategias.values());
		//cmbEstrategia = new JComboBox();
		panel_9.add(cmbEstrategia);
		
		txtInfoEstrategia = new JTextArea();
		txtInfoEstrategia.setRows(5);
		txtInfoEstrategia.setLineWrap(true);
		txtInfoEstrategia.setWrapStyleWord(true);
		txtInfoEstrategia.setTabSize(4);
		txtInfoEstrategia.setEditable(false);
		JScrollPane pScroll1 = new JScrollPane(txtInfoEstrategia, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel_6.add(pScroll1, BorderLayout.CENTER);
		
		panelConfiguracion = new JPanel();
		panelConfiguracion.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Configuraci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel.add(panelConfiguracion, BorderLayout.CENTER);
		panelConfiguracion.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 5));
		
		txtInfo = new JTextArea();
		txtInfo.setRows(3);
		txtInfo.setEditable(false);
		txtInfo.setLineWrap(true);
		txtInfo.setTabSize(4);
		txtInfo.setWrapStyleWord(true);
		JScrollPane pScrollInfo = new JScrollPane(txtInfo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel_1.add(pScrollInfo, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setHgap(75);
		panel_2.add(panel_3, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setHgap(75);
		panel_2.add(panel_4, BorderLayout.EAST);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnAceptar = new JButton("Aceptar");
		panel_5.add(btnAceptar);
		btnAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btnCancelar = new JButton("Cancelar");
		panel_5.add(btnCancelar);
		
		cargarPanelConfiguracion(null);
		setLocationRelativeTo(null);
	}
	
	public void registrarControlador(ActionListener ctr) {
		btnAceptar.addActionListener(ctr);
		btnAceptar.setActionCommand("Aceptar");
		
		btnCancelar.addActionListener(ctr);
		btnCancelar.setActionCommand("Cancelar");
		
		cmbEstrategia.addActionListener(ctr);
		cmbEstrategia.setActionCommand("Estrategia");
	}
	
	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		btnAceptar.addMouseListener(ctr);
		btnCancelar.addMouseListener(ctr);
		cmbEstrategia.addMouseListener(ctr);
		lblEstrategia.addMouseListener(ctr);
		txtInfoEstrategia.addMouseListener(ctr);
		txtInfo.addMouseListener(ctr);
	}
	
	public void cargarPanelConfiguracion(PanelConfiguracionEstrategia p) {
		this.panelConfiguracionEstrategia = p;
		panelConfiguracion.removeAll();
		if (p != null) {
			this.panelConfiguracion.add(p, BorderLayout.CENTER);
			this.pack();
		} else {
			panelConfiguracion.add(new JLabel("No hay nada que configurar."), BorderLayout.CENTER);
			this.pack();
		}
		update(getGraphics());
		// panelConfiguracion.registrarControlar(ctr);
	}
	
	@Override
	public String infoComponente(Component c) {
		String res = null;
		if (c.equals(btnAceptar)) {
			res = "Acepta y guarda los cambios.";
		} else if (c.equals(btnCancelar)) {
			res = "Cancela y no tiene en cuenta los cambios.";
		} else if (c.equals(cmbEstrategia) || c.equals(lblEstrategia)) {
			res = "Selecciona la estrategia que usará el jugador.";
		} else if (c.equals(txtInfoEstrategia)) {
			res = "Información de la estrategia seleccionada.";
		} else if (c.equals(txtInfo)) {
			res = "Aquí se muestra información de ayuda sobre los componentes de la pantalla al pasar el ratón sobre ellos.";		
		}
		return res;
	}

	@Override
	public void actualizarInfo(String info) {
		txtInfo.setText(info);
		txtInfo.setCaretPosition(0);
	}

	/**
	 * @return the cmbEstrategia
	 */
	public JComboBox<Estrategias> getCmbEstrategia() {
		return cmbEstrategia;
	}
	
	public void actualizarInfoEstrategia(String msg) {
		txtInfoEstrategia.setText(msg);
		txtInfoEstrategia.setCaretPosition(0);
	}
	
	
}
