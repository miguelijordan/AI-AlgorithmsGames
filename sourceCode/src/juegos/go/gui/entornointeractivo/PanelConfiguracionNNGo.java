package juegos.go.gui.entornointeractivo;

import entornointeractivo.gui.interfaces.PanelConfiguracionEvaluador;

import heuristicos.evaluadores.Evaluador;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import juegos.EstadoJuego;
import juegos.go.heuristicos.EvaluadorNNGo;
import juegos.go.juego.Go;

public class PanelConfiguracionNNGo extends PanelConfiguracionEvaluador {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNNeuronasIntermedias;
	private JLabel lblNneuronasIntermedias;
	private JPanel panel_1;
	private JLabel lblTasaAprendizaje;
	private JTextField textFieldTasaAprendizaje;
	private JPanel panel_2;
	private JLabel lblMomento;
	private JTextField textFieldMomento;

	/**
	 * Create the panel.
	 */
	public PanelConfiguracionNNGo() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel);
		
		lblNneuronasIntermedias = new JLabel("Nº de neuronas intermedias:");
		panel.add(lblNneuronasIntermedias);
		
		textFieldNNeuronasIntermedias = new JTextField();
		textFieldNNeuronasIntermedias.setText(String.valueOf(InterfazEvaluadorNNGo.N_NEURONAS_INTERMEDIAS));
		panel.add(textFieldNNeuronasIntermedias);
		textFieldNNeuronasIntermedias.setColumns(5);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(panel_1);
		
		lblTasaAprendizaje = new JLabel("Tasa de aprendizaje:");
		panel_1.add(lblTasaAprendizaje);
		
		textFieldTasaAprendizaje = new JTextField();
		textFieldTasaAprendizaje.setText(String.valueOf(InterfazEvaluadorNNGo.TASA_APRENDIZAJE));
		panel_1.add(textFieldTasaAprendizaje);
		textFieldTasaAprendizaje.setColumns(5);
		
		panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		add(panel_2);
		
		lblMomento = new JLabel("Momento:");
		panel_2.add(lblMomento);
		
		textFieldMomento = new JTextField();
		textFieldMomento.setText(String.valueOf(InterfazEvaluadorNNGo.MOMENTO));
		panel_2.add(textFieldMomento);
		textFieldMomento.setColumns(5);

	}

	@Override
	public String infoComponente(Component c) {
		String res = null;
		if (c.equals(lblNneuronasIntermedias) || c.equals(textFieldNNeuronasIntermedias)) {
			res = "Número de neuronas de la capa intermedia de la red neuronal.";
		} else if (c.equals(lblTasaAprendizaje) || c.equals(textFieldTasaAprendizaje)) {
			res = "Tasa de aprendizaje: Ajusta la velocidad a la que aprende la red.";
			res += " 0 < alfa < 1";
		} else if (c.equals(lblMomento) || c.equals(textFieldMomento)) {
			res = "Momento de entrenamiento: Indica el grado de influencia que tendrá la iteración anterior sobre la actual.";
			res += " Es un valor entre 0 y 1. Puede ser útil para salir de un mínimo local.";
		}
		return res;
	}

	@Override
	public void actualizarInfo(String info) {
		// TODO Auto-generated method stub
	}

	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		textFieldNNeuronasIntermedias.addMouseListener(ctr);
		lblNneuronasIntermedias.addMouseListener(ctr);
		textFieldTasaAprendizaje.addMouseListener(ctr);
		lblTasaAprendizaje.addMouseListener(ctr);
		textFieldMomento.addMouseListener(ctr);
		lblMomento.addMouseListener(ctr);
	}

	@Override
	public Evaluador evaluador(EstadoJuego e) {
		int neuronas = InterfazEvaluadorNNGo.N_NEURONAS_INTERMEDIAS;
		double tasaAprendizaje = InterfazEvaluadorNNGo.TASA_APRENDIZAJE;
		double momento = InterfazEvaluadorNNGo.MOMENTO;
		
		try {
			neuronas = Integer.parseInt(this.textFieldNNeuronasIntermedias.getText());
			tasaAprendizaje = Double.parseDouble(this.textFieldTasaAprendizaje.getText());
			momento = Double.parseDouble(this.textFieldMomento.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(new JFrame(), "Hay un error en los parámetros.");
			return null;
		}
		if (error(neuronas, tasaAprendizaje, momento)) {
			return null;
		}		
		Go go = (Go) e;
		return new EvaluadorNNGo(go.tablero().nFilas(), go.tablero().nColumnas(), neuronas, tasaAprendizaje, momento);
	}

	/**
	 * Control de errores para los parámetros.
	 * 
	 * @param n		Número de neuronas intermedias.
	 * @param t		Tasa de aprendizaje.
	 * @param m		Momento.
	 * @return		Verdadero si hay algun error, falso en sino.
	 */
	private boolean error(int n, double t, double m) {
		if (n <= 0) {
			JOptionPane.showMessageDialog(new JFrame(), "El número mínimo de neuronas intermedias es 1.");
			return true;
		}
		if (t <= 0 || t >= 1) {
			JOptionPane.showMessageDialog(new JFrame(), "El valor de la tasa de aprendizaje es incorrecto, debe ser 0 < alfa < 1.");
			return true;
		}
		if (m < 0 || m > 1) {
			JOptionPane.showMessageDialog(new JFrame(), "El valor del momento es incorrecto, debe estar en el intervalo [0,1].");
			return true;
		}
		return false;
	}
	
	@Override
	public void registrarControlador(ActionListener ctr) {
		// TODO Auto-generated method stub
	}

}
