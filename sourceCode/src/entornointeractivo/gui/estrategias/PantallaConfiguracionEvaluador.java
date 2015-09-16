package entornointeractivo.gui.estrategias;

import entornointeractivo.gui.interfaces.InformacionAyuda;
import entornointeractivo.gui.interfaces.PanelConfiguracionEvaluador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Window.Type;

public class PantallaConfiguracionEvaluador extends JFrame implements InformacionAyuda {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea txtInfo;
	private JButton btnOK;
	private JPanel panelConfiguracion;
	//private Estrategias estrategiaActual;
	//private PanelConfiguracionEstrategia panelConfiguracionEstrategia;
	private PanelEntrenamiento panelEntrenamiento;
	private PanelConfiguracionEvaluador pConfEv;
	//private JButton btnCancelar;
	
	/**
	 * Create the frame.
	 */
	public PantallaConfiguracionEvaluador(String nombreEvaluador, PanelConfiguracionEvaluador pConfEv, boolean necesitaEntrenamiento,  boolean jug1) {
		setType(Type.UTILITY);
		this.pConfEv = pConfEv;
		
		setTitle("Configuración del evaluador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 563);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblEvaluador = new JLabel(nombreEvaluador);
		lblEvaluador.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblEvaluador, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		panelConfiguracion = new JPanel();
		panelConfiguracion.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Configuraci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelConfiguracion.setLayout(new BorderLayout(0, 0));
		if (pConfEv != null) {
			panelConfiguracion.add(pConfEv, BorderLayout.CENTER);	
		}
		
		if (necesitaEntrenamiento) {
			panelEntrenamiento = new PanelEntrenamiento();
			panel.add(panelEntrenamiento, BorderLayout.CENTER);
			panel.add(panelConfiguracion, BorderLayout.NORTH);
		} else {
			panel.add(panelConfiguracion, BorderLayout.CENTER);	
			panelEntrenamiento = null;
		}
		
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
		
		btnOK = new JButton("OK");
		panel_5.add(btnOK);
		btnOK.setAlignmentX(Component.CENTER_ALIGNMENT);
		
	/*	btnCancelar = new JButton("Cancelar");
		btnCancelar.setAlignmentX(0.5f);
		panel_5.add(btnCancelar);*/
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void registrarControlador(ActionListener ctr) {
		btnOK.addActionListener(ctr);
		btnOK.setActionCommand("Aceptar");
		
	/*	btnCancelar.addActionListener(ctr);
		btnCancelar.setActionCommand("Cancelar");*/
		
		if (pConfEv != null) {
			pConfEv.registrarControlador(ctr);
		}
		if (panelEntrenamiento != null) {
			panelEntrenamiento.registrarControlador(ctr);
		}
	}
	
	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		btnOK.addMouseListener(ctr);
		txtInfo.addMouseListener(ctr);
	//	btnCancelar.addMouseListener(ctr);

		if (pConfEv != null) {
			pConfEv.registrarControladorInformacion(ctr);
		}
		if (panelEntrenamiento != null) {
			panelEntrenamiento.registrarControladorInformacion(ctr);
		}
	}
	
	@Override
	public String infoComponente(Component c) {
		String res = null;
		if (c.equals(txtInfo)) {
			res = "Aquí se muestra información de ayuda sobre los componentes de la pantalla al pasar el ratón sobre ellos.";
		} else if (c.equals(btnOK)) {
			res = "Acepta y guarda los cambios realizados.";
	/*	} else if (c.equals(btnCancelar)) {
			res = "Información sobre el botón jug2.";*/
		} else {
			res = pConfEv.infoComponente(c);
		}
		if (res == null) {
			res = panelEntrenamiento.infoComponente(c);
		}
		return res;
	}

	@Override
	public void actualizarInfo(String info) {
		txtInfo.setText(info);
		txtInfo.setCaretPosition(0);
	}

	public PanelEntrenamiento getPanelEntrenamiento() {
		return panelEntrenamiento;
	}

	public PanelConfiguracionEvaluador getpConfEv() {
		return pConfEv;
	}
}
