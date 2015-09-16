package juegos.conectak.gui.entornointeractivo;

import entornointeractivo.gui.interfaces.PanelOpcionesJuego;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import juegos.EstadoJuego;
import juegos.conectak.juego.ConectaK;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class PanelOpcionesConectaK extends PanelOpcionesJuego {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldFilas;
	private JTextField textFieldColumnas;
	private JTextField textFieldLongitudGanadora;
	private JLabel lblNFilas;
	private JLabel lblNColumnas;
	private JLabel lblLongitudGanadora;
	
	/**
	 * Create the panel.
	 */
	public PanelOpcionesConectaK() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel);
		
		lblNFilas = new JLabel("Nº de filas:");
		panel.add(lblNFilas);
		
		textFieldFilas = new JTextField();
		textFieldFilas.setText(String.valueOf(InterfazJuegoCK.N_FILAS));
		panel.add(textFieldFilas);
		textFieldFilas.setColumns(4);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(panel_1);
		
		lblNColumnas = new JLabel("Nº de columnas:");
		panel_1.add(lblNColumnas);
		
		textFieldColumnas = new JTextField();
		textFieldColumnas.setText(String.valueOf(InterfazJuegoCK.N_COLUMNAS));
		panel_1.add(textFieldColumnas);
		textFieldColumnas.setColumns(4);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		add(panel_2);
		
		lblLongitudGanadora = new JLabel("Longitud ganadora:");
		panel_2.add(lblLongitudGanadora);
		
		textFieldLongitudGanadora = new JTextField();
		textFieldLongitudGanadora.setText(String.valueOf(InterfazJuegoCK.LONGITUD_GANADORA));
		panel_2.add(textFieldLongitudGanadora);
		textFieldLongitudGanadora.setColumns(4);

	}

	@Override
	public void registrarControlador(ActionListener ctr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		lblNFilas.addMouseListener(ctr);
		lblNColumnas.addMouseListener(ctr);
		lblLongitudGanadora.addMouseListener(ctr);
		textFieldFilas.addMouseListener(ctr);
		textFieldColumnas.addMouseListener(ctr);
		textFieldLongitudGanadora.addMouseListener(ctr);
	}

	@Override
	public EstadoJuego estadoJuego() {
		int nFilas = InterfazJuegoCK.N_FILAS;
		int nColumnas = InterfazJuegoCK.N_COLUMNAS;
		int longGanadora = InterfazJuegoCK.LONGITUD_GANADORA;
		
		try {
			nFilas = Integer.parseInt(this.textFieldFilas.getText());
			nColumnas = Integer.parseInt(this.textFieldColumnas.getText());
			longGanadora = Integer.parseInt(this.textFieldLongitudGanadora.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(new JFrame(), "Hay un error en los parámetros.");
			return null;
		}
		if (!hayError(nFilas, nColumnas, longGanadora)) {
			return null;
		}
		return new ConectaK(nFilas, nColumnas, longGanadora);
	}
	
	/**
	 * Control de errores para los parámetros.
	 * 
	 * @param nFilas			Número de filas.
	 * @param nColumnas			Número de columnas.
	 * @param longGanadora		Longitud ganadora.
	 * @return					Verdadero si todos los parámetros son correctos, falso en caso contrario.
	 */
	private boolean hayError(int nFilas, int nColumnas, int longGanadora) {
		boolean ok = true;
		if (nFilas <= 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Número de filas incorrecto. El mínimo es 1.");
			ok = false;
		} else if (nColumnas <= 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Número de columnas incorrecto. El mínimo es 1.");
			ok = false;
		} else if (longGanadora <= 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Valor no válido para la longitud ganadora. El mínimo es 1.");
			ok = false;
		} else if (longGanadora > nFilas || longGanadora > nColumnas) {
			JOptionPane.showMessageDialog(new JFrame(), "La longitud ganadora no puede ser mayor que las dimensiones del tablero.");
			ok = false;
		}
		return ok;
	}

	@Override
	public String infoComponente(Component c) {
		String res = null;
		
		if (c.equals(lblNFilas) || c.equals(textFieldFilas)) {
			res = "Número de filas del tablero de juego.";
		} else if (c.equals(lblNColumnas) || c.equals(textFieldColumnas)) {
			res = "Número de columnas del tablero de juego.";
		} else if (c.equals(lblLongitudGanadora) || c.equals(textFieldLongitudGanadora)) {
			res = "Longitud ganadora del juego.";
		}
		return res;		
	}

	@Override
	public void actualizarInfo(String info) {
		// TODO Auto-generated method stub
		
	}

}
