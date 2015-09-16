package heuristicos.evaluadores.gui.tablavalor;

import entornointeractivo.gui.interfaces.PanelConfiguracionEvaluador;

import heuristicos.evaluadores.Evaluador;
import heuristicos.evaluadores.EvaluadorTv;

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

public class PanelConfiguracionTV extends PanelConfiguracionEvaluador {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldGanar;
	private JLabel lblGanar;
	private JPanel panel_1;
	private JLabel lblTasaAprendizaje;
	private JTextField textFieldTasaAprendizaje;
	private JPanel panel_2;
	private JLabel lblEmpate;
	private JTextField textFieldEmpate;
	private JPanel panel_3;
	private JLabel lblPerder;
	private JTextField textFieldPerder;

	/**
	 * Create the panel.
	 */
	public PanelConfiguracionTV() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel);
		
		lblGanar = new JLabel("Recompensa por ganar:");
		panel.add(lblGanar);
		
		textFieldGanar = new JTextField();
		textFieldGanar.setText(String.valueOf(EvaluadorTv.VALOR_GANAR));
		panel.add(textFieldGanar);
		textFieldGanar.setColumns(5);
		
		panel_3 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		add(panel_3);
		
		lblPerder = new JLabel("Recompensa por perder:");
		panel_3.add(lblPerder);
		
		textFieldPerder = new JTextField();
		textFieldPerder.setText(String.valueOf(EvaluadorTv.VALOR_PERDER));
		textFieldPerder.setColumns(5);
		panel_3.add(textFieldPerder);
		
		panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		add(panel_2);
		
		lblEmpate = new JLabel("Recompensa por empatar:");
		panel_2.add(lblEmpate);
		
		textFieldEmpate = new JTextField();
		textFieldEmpate.setText(String.valueOf(EvaluadorTv.VALOR_EMPATAR));
		panel_2.add(textFieldEmpate);
		textFieldEmpate.setColumns(5);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(panel_1);
		
		lblTasaAprendizaje = new JLabel("Tasa de aprendizaje (alfa):");
		panel_1.add(lblTasaAprendizaje);
		
		textFieldTasaAprendizaje = new JTextField();
		textFieldTasaAprendizaje.setText(String.valueOf(EvaluadorTv.ALFA));
		panel_1.add(textFieldTasaAprendizaje);
		textFieldTasaAprendizaje.setColumns(5);

	}

	@Override
	public String infoComponente(Component c) {
		String res = null;
		if (c.equals(lblGanar) || c.equals(textFieldGanar)) {
			res = "Valor de recompensa que se usará para los estados ganadores.";
		} else if (c.equals(lblEmpate) || c.equals(textFieldEmpate)) {
			res = "Valor de recompensa que se usará para los estados de empate.";
		} else if (c.equals(lblPerder) || c.equals(textFieldPerder)) {
			res = "Valor de recompensa que se usará para los estados perdedores.";
		} else if (c.equals(lblTasaAprendizaje) || c.equals(textFieldTasaAprendizaje)) {
			res = "Tasa de aprendizaje (alfa).";
			res += " 0 < alfa < 1";
		}
		return res;
	}

	@Override
	public void actualizarInfo(String info) {
		// TODO Auto-generated method stub
	}

	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		textFieldGanar.addMouseListener(ctr);
		lblGanar.addMouseListener(ctr);
		textFieldEmpate.addMouseListener(ctr);
		lblEmpate.addMouseListener(ctr);
		textFieldPerder.addMouseListener(ctr);
		lblPerder.addMouseListener(ctr);
		textFieldTasaAprendizaje.addMouseListener(ctr);
		lblTasaAprendizaje.addMouseListener(ctr);
	}

	@Override
	public Evaluador evaluador(EstadoJuego e) {
		double ganar;
		double empatar;
		double perder;
		double alfa;
		
		try {
			ganar = Double.parseDouble(this.textFieldGanar.getText());
			empatar = Double.parseDouble(this.textFieldEmpate.getText());
			perder = Double.parseDouble(this.textFieldPerder.getText());
			alfa = Double.parseDouble(this.textFieldTasaAprendizaje.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(new JFrame(), "Hay un error en los parámetros.");
			return null;
		}
		if (error(alfa)) {
			return null;
		}		
		return new EvaluadorTv(ganar, perder, empatar, alfa);
	}

	/**
	 * Control de errores para los parámetros.
	 * 
	 * @param alfa	Alfa (tasa de aprendizaje).
	 * @return		Verdadero si hay algun error, falso en sino.
	 */
	private boolean error(double alfa) {
		if (alfa <= 0 || alfa >= 1) {
			JOptionPane.showMessageDialog(new JFrame(), "El valor alfa de la tasa de aprendizaje es incorrecto, debe ser 0 < alfa < 1.");
			return true;
		}
		return false;
	}
	
	@Override
	public void registrarControlador(ActionListener ctr) {
		// TODO Auto-generated method stub
	}

}
