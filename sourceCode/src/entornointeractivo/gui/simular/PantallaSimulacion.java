package entornointeractivo.gui.simular;

import entornointeractivo.gui.interfaces.InformacionAyuda;

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
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Window.Type;

/**
 * Pantalla para configurar las opciones del juego.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 1/12/2011
 *
 */
public class PantallaSimulacion extends JFrame implements InformacionAyuda {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * &Aacute;rea de texto para la informacio&acute;n de ayuda.
	 */
	private JTextArea txtInfo;
	
	/**
	 * Botón aceptar.
	 */
	private JButton btnSimular;
	private JButton btnCancelar;
	private JTextField textFieldNPartidas;
	private JLabel lblPartidasJugadas;
	private JProgressBar progressBar;
	private JPanel panelSim;
	private JLabel lblNPartidas;
	private JTextArea textAreaInfoJug1;
	private JTextArea textAreaInfoJug2;

	public PantallaSimulacion() {
		setResizable(false);
		setTitle("Simulación");
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
		
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		panelSim = new JPanel();
		panel.add(panelSim, BorderLayout.CENTER);
		panelSim.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_8.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panelSim.add(panel_8, BorderLayout.NORTH);
		
		lblPartidasJugadas = new JLabel("0 de 100 partidas");
		panel_8.add(lblPartidasJugadas);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		panelSim.add(progressBar, BorderLayout.CENTER);
		
		JPanel panel_9 = new JPanel();
		panel.add(panel_9, BorderLayout.NORTH);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_9.add(panel_6, BorderLayout.NORTH);
		FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		
		lblNPartidas = new JLabel("Nº de partidas:");
		panel_6.add(lblNPartidas);
		
		textFieldNPartidas = new JTextField();
		textFieldNPartidas.setText("100");
		panel_6.add(textFieldNPartidas);
		textFieldNPartidas.setColumns(5);
		
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
		
		btnSimular = new JButton("Aceptar");
		panel_5.add(btnSimular);
		btnSimular.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btnCancelar = new JButton("Cancelar");
		panel_5.add(btnCancelar);
		
		JPanel panel_7 = new JPanel();
		contentPane.add(panel_7, BorderLayout.NORTH);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelJug1 = new JPanel();
		panelJug1.setBorder(new TitledBorder(new LineBorder(new Color(185, 209, 234)), "Jugador 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.add(panelJug1);
		panelJug1.setLayout(new BorderLayout(0, 0));
		
		textAreaInfoJug1 = new JTextArea();
		textAreaInfoJug1.setTabSize(4);
		textAreaInfoJug1.setRows(3);
		textAreaInfoJug1.setLineWrap(true);
		textAreaInfoJug1.setWrapStyleWord(true);
		textAreaInfoJug1.setEditable(false);
		JScrollPane pScrollInfoJug1 = new JScrollPane(textAreaInfoJug1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelJug1.add(pScrollInfoJug1, BorderLayout.CENTER);
		
		JPanel panelJug2 = new JPanel();
		panelJug2.setBorder(new TitledBorder(new LineBorder(new Color(185, 209, 234)), "Jugador 2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.add(panelJug2);
		panelJug2.setLayout(new BorderLayout(0, 0));
		
		textAreaInfoJug2 = new JTextArea();
		textAreaInfoJug2.setRows(3);
		textAreaInfoJug2.setTabSize(4);
		textAreaInfoJug2.setWrapStyleWord(true);
		textAreaInfoJug2.setLineWrap(true);
		textAreaInfoJug2.setEditable(false);
		JScrollPane pScrollInfoJug2 = new JScrollPane(textAreaInfoJug2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelJug2.add(pScrollInfoJug2, BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * Registra un controlador en todos los componentes activos de la pantalla.
	 * 
	 * @param ctr	Controlador.
	 */
	public void registrarControlador(ActionListener ctr) {
		btnSimular.addActionListener(ctr);
		btnSimular.setActionCommand("Simular");
		btnCancelar.addActionListener(ctr);
		btnCancelar.setActionCommand("Cancelar");
	}
	
	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		btnSimular.addMouseListener(ctr);
		btnCancelar.addMouseListener(ctr);
		txtInfo.addMouseListener(ctr);
		lblNPartidas.addMouseListener(ctr);
		textFieldNPartidas.addMouseListener(ctr);
		lblPartidasJugadas.addMouseListener(ctr);
		progressBar.addMouseListener(ctr);
	}
	
	@Override
	public void actualizarInfo(String info) {
		txtInfo.setText(info);
		txtInfo.setCaretPosition(0);
	}
	
	public void actualizarInfoJug1(String info) {
		textAreaInfoJug1.setText(info);
		textAreaInfoJug1.setCaretPosition(0);
	}
	
	public void actualizarInfoJug2(String info) {
		textAreaInfoJug2.setText(info);
		textAreaInfoJug2.setCaretPosition(0);
	}

	@Override
	public String infoComponente(Component c) {
		String res = null;
		if (c.equals(btnSimular)) {
			res = "Juega el número de partidas indicado entre los dos jugadores.\n";
			res += "ATENCIÓN: La simulación no se podrá cancelar una vez iniciada.";
		} else if (c.equals(btnCancelar)) {
			res = "Cancela y vuelve a la pantalla inicial.";
		} else if (c.equals(txtInfo)) {
			res = "Aquí se muestra información de ayuda sobre los componentes de la pantalla al pasar el ratón sobre ellos.";
		}  else if (c.equals(lblNPartidas) || c.equals(textFieldNPartidas)) {
			res = "Número de partidas que se jugarán.";
		} else if (c.equals(progressBar)) {
			res = "Muestra el progreso de la simulación.";
		} else if (c.equals(lblPartidasJugadas)) {
			res = "Número de partidas jugadas.";
		}
		return res;
	}
	
	public void actualizarSimulacion(int nTotal, int nPartida, int p) {
		lblPartidasJugadas.setText(nPartida + " de " + nTotal + " partidas");
		progressBar.setValue(p);
		try {
			panelSim.update(panelSim.getGraphics());	
		} catch(Exception e) {
			
		}
		
	}
	
	public int getNumPartidas() {
		return Integer.parseInt(textFieldNPartidas.getText());
	}
	
	public void desactivarControlador(ControladorSimulacion ctr) {
		btnSimular.removeActionListener(ctr);
		btnSimular.removeMouseListener(ctr);
		btnSimular.setEnabled(false);
		txtInfo.removeMouseListener(ctr);
		lblNPartidas.removeMouseListener(ctr);
		textFieldNPartidas.removeMouseListener(ctr);
		lblPartidasJugadas.removeMouseListener(ctr);
		progressBar.removeMouseListener(ctr);
		btnCancelar.removeMouseListener(ctr);
	}
}
