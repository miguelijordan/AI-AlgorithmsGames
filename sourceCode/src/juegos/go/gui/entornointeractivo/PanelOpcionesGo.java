package juegos.go.gui.entornointeractivo;

import entornointeractivo.gui.interfaces.PanelOpcionesJuego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import juegos.EstadoJuego;
import juegos.go.juego.Go;

public class PanelOpcionesGo extends PanelOpcionesJuego {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldFilas;
	private JTextField textFieldColumnas;
	private JLabel lblNFilas;
	private JLabel lblNColumnas;
	private JPanel panelKomi;
	private JPanel panelDimTablero;
	private JPanel panel_4;
	private JPanel panelReglas;
	private JPanel panel_6;
	private JPanel panel_7;
	private JRadioButton rdbtnJaponesas;
	private JRadioButton rdbtnChinas;
	private JLabel lblKomi;
	private JTextField textFieldKomi;
	private ButtonGroup bGroupReglas;
	
	/**
	 * Create the panel.
	 */
	public PanelOpcionesGo() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		panelDimTablero = new JPanel();
		panelDimTablero.setBorder(new TitledBorder(new LineBorder(new Color(185, 209, 234)), "Dimensiones del tablero", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panelDimTablero, BorderLayout.NORTH);
		panelDimTablero.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelDimTablero.add(panel_4);
		
		lblNFilas = new JLabel("Nº de filas:");
		panel_4.add(lblNFilas);
		
		textFieldFilas = new JTextField();
		panel_4.add(textFieldFilas);
		textFieldFilas.setText(String.valueOf(InterfazJuegoGo.N_FILAS));
		textFieldFilas.setColumns(4);
		
		JPanel panel_1 = new JPanel();
		panelDimTablero.add(panel_1);
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		
		lblNColumnas = new JLabel("Nº de columnas:");
		panel_1.add(lblNColumnas);
		
		textFieldColumnas = new JTextField();
		textFieldColumnas.setText(String.valueOf(InterfazJuegoGo.N_COLUMNAS));
		panel_1.add(textFieldColumnas);
		textFieldColumnas.setColumns(4);
		
		panelReglas = new JPanel();
		panelReglas.setBorder(new TitledBorder(new LineBorder(new Color(185, 209, 234)), "Reglas de puntuaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panelReglas, BorderLayout.SOUTH);
		panelReglas.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel_6 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panelReglas.add(panel_6);
		
		rdbtnJaponesas = new JRadioButton("Reglas japonesas");
		rdbtnJaponesas.setSelected(true);
		panel_6.add(rdbtnJaponesas);
		
		panel_7 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_7.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panelReglas.add(panel_7);
		
		rdbtnChinas = new JRadioButton("Reglas chinas");
		panel_7.add(rdbtnChinas);
		
		bGroupReglas = new ButtonGroup();
		bGroupReglas.add(rdbtnJaponesas);
		bGroupReglas.add(rdbtnChinas);
		
		JPanel panelVentaja = new JPanel();
		panelVentaja.setBorder(new TitledBorder(new LineBorder(new Color(185, 209, 234)), "Compensaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelVentaja);
		panelVentaja.setLayout(new BorderLayout(0, 0));
		
		panelKomi = new JPanel();
		FlowLayout fl_panelKomi = (FlowLayout) panelKomi.getLayout();
		fl_panelKomi.setAlignment(FlowLayout.LEFT);
		panelVentaja.add(panelKomi, BorderLayout.NORTH);
		
		lblKomi = new JLabel("Komi:");
		panelKomi.add(lblKomi);
		
		textFieldKomi = new JTextField();
		textFieldKomi.setText(String.valueOf(Go.KOMI_9x9));
		panelKomi.add(textFieldKomi);
		textFieldKomi.setColumns(5);

	}

	@Override
	public void registrarControlador(ActionListener ctr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarControladorInformacion(MouseListener ctr) {
		lblNFilas.addMouseListener(ctr);
		lblNColumnas.addMouseListener(ctr);
		textFieldFilas.addMouseListener(ctr);
		textFieldColumnas.addMouseListener(ctr);
		rdbtnJaponesas.addMouseListener(ctr);
		rdbtnChinas.addMouseListener(ctr);
		lblKomi.addMouseListener(ctr);
		textFieldKomi.addMouseListener(ctr);
	}

	@Override
	public EstadoJuego estadoJuego() {
		int nFilas = InterfazJuegoGo.N_FILAS;
		int nColumnas = InterfazJuegoGo.N_COLUMNAS;
		double komi = Go.KOMI_9x9;
		int reglas = Go.REGLAS_JAPONESAS;
		
		try {
			nFilas = Integer.parseInt(this.textFieldFilas.getText());
			nColumnas = Integer.parseInt(this.textFieldColumnas.getText());
			komi = Double.parseDouble(this.textFieldKomi.getText());
			reglas = rdbtnJaponesas.isSelected() ? Go.REGLAS_JAPONESAS : Go.REGLAS_CHINAS;
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(new JFrame(), "Hay un error en los parámetros.");
			return null;
		}
		if (hayError(nFilas, nColumnas, komi)) {
			return null;
		}
		// El Handicap no se implementa.
		
		int nFichasJug1 = nFilas*nColumnas/2 + nFilas*nColumnas%2;
		int nFichasJug2 = nFilas*nColumnas/2;
		return new Go(nFilas, nColumnas, komi, null, true, reglas, nFichasJug1, nFichasJug2);
	}
	
	private boolean hayError(int nFilas, int nColumnas, double komi) {
		if (nFilas <= 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Número de filas incorrecto. El mínimo es 1.");
			return true;
		} else if (nColumnas <= 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Número de columnas incorrecto. El mínimo es 1.");
			return true;
		} else if (komi < 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Valor no válido para el Komi. Debe ser mayor o igual a 0.");
			return true;
		}
		return false;
	}

	@Override
	public String infoComponente(Component c) {
		String res = null;
		
		if (c.equals(lblNFilas) || c.equals(textFieldFilas)) {
			res = "Número de filas del tablero de juego.";
		} else if (c.equals(lblNColumnas) || c.equals(textFieldColumnas)) {
			res = "Número de columnas del tablero de juego.";
		} else if (c.equals(rdbtnJaponesas)) {
			res = "Reglas de puntuación japonesas. ";
			res += "Las fichas capturadas se colocan en el territorio del jugador rival y ";
			res += "se cuentan el número de intersecciones (posiciones) vacías que un jugador ha encerrado (territorio).";
		} else if (c.equals(rdbtnChinas)) {
			res = "Reglas de puntuación chinas. ";
			res += "Se cuentan el número de fichas del jugador sobre el tablero y ";
			res += "y el número de intersecciones (posiciones) vacías que un jugador ha encerrado (territorio)."; 
		} else if (c.equals(lblKomi) || c.equals(textFieldKomi)) {
			res = "Valor de compensación (Komi) para el jugador blanco (jugador 2) por el hecho de jugar en segundo lugar.";
			res += " Tablero 9x9 -> Komi = 3.5 puntos.";
			res += " Tablero 13x13 -> Komi = 4.5 puntos.";
			res += " Tablero 19x19 -> Komi = 5.5 puntos.";
		}
		return res;		
	}

	@Override
	public void actualizarInfo(String info) {
		// TODO Auto-generated method stub
		
	}

}
