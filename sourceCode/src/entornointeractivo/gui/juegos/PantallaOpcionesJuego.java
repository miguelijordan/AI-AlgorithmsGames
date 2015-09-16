package entornointeractivo.gui.juegos;

import entornointeractivo.gui.interfaces.InformacionAyuda;
import entornointeractivo.gui.interfaces.PanelOpcionesJuego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Pantalla para configurar las opciones del juego.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 1/12/2011
 *
 */
public class PantallaOpcionesJuego extends JFrame implements InformacionAyuda {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Etiqueta con el nombre del juego.
	 */
	private JLabel lblJuego;
	
	/**
	 * &Aacute;rea de texto para la informaci&oacute;n de ayuda.
	 */
	private JTextArea txtInfo;
	
	/**
	 * Botón aceptar.
	 */
	private JButton btnAceptar;
	
	/**
	 * Botón cancelar.
	 */
	private JButton btnCancelar;

	/**
	 * Panel de opciones para un juego concreto.
	 */
	private PanelOpcionesJuego panelOpciones;

	/**
	 * Crea la pantalla de opciones del juego.
	 * 
	 * @param nombreJuego		Nombre del juego.
	 * @param panelOpciones		Panel de configuración de opciones del juego en concreto.
	 */
	public PantallaOpcionesJuego(String nombreJuego, PanelOpcionesJuego panelOpciones) {
		setResizable(false);
		this.panelOpciones = panelOpciones;
		
		setTitle("Configuración del juego");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 523);
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// Agregamos el codigo a boton X para cerrar la ventana
		/*addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});*/
		
		lblJuego = new JLabel(nombreJuego);
		lblJuego.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblJuego, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelConfiguracion = new JPanel();
		panelConfiguracion.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Configuraci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));

		panel.add(panelConfiguracion);
		panelConfiguracion.setLayout(new BorderLayout(0, 0));
		
		// Insertamos el panel de opciones del juego que recibe el constructor.
		panelConfiguracion.add(panelOpciones, BorderLayout.CENTER);
		
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
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * Registra un controlador en todos los componentes activos de la pantalla.
	 * 
	 * @param ctr	Controlador.
	 */
	public void registrarControlador(ActionListener ctr) {
		btnAceptar.addActionListener(ctr);
		btnAceptar.setActionCommand("Aceptar");
		
		btnCancelar.addActionListener(ctr);
		btnCancelar.setActionCommand("Cancelar");
		
		panelOpciones.registrarControlador(ctr);
	}
	
	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		btnAceptar.addMouseListener(ctr);
		btnCancelar.addMouseListener(ctr);
		txtInfo.addMouseListener(ctr);
		panelOpciones.registrarControladorInformacion(ctr);
	}
	
	@Override
	public void actualizarInfo(String info) {
		txtInfo.setText(info);
		txtInfo.setCaretPosition(0);
	}

	@Override
	public String infoComponente(Component c) {
		String res = null;
		if (c.equals(btnAceptar)) {
			res = "Acepta y guarda los cambios.";
		} else if (c.equals(btnCancelar)) {
			res = "Cancela y no tiene en cuenta los cambios.";
		} else if (c.equals(txtInfo)) {
			res = "Aquí se muestra información de ayuda sobre los componentes de la pantalla al pasar el ratón sobre ellos.";		
		} else {
			res = panelOpciones.infoComponente(c);
		}
		return res;
	}

	/**
	 * @return the panelOpciones
	 */
	public PanelOpcionesJuego getPanelOpciones() {
		return panelOpciones;
	}
	
}
