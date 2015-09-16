package entornointeractivo.gui.resultados;

import entornointeractivo.gui.principal.ControladorPrincipal;
import entornointeractivo.gui.util.InformacionEstadisticas;
import estrategias.agentes.Jugador;
import estrategias.agentes.estadisticas.EstadisticasJugador;
import estrategias.agentes.estadisticas.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ControladorEstadisticas implements ActionListener, MouseListener {

	private static final String SEPARADOR_FICHERO_LARGO = "\n--------------------------------------------------------------------------------\n";
	private static final String SEPARADOR_FICHERO_CORTO = "\n--------------------\n";
	
	private ControladorPrincipal ctrPrincipal;
	private JPanel pResultados;
	private PantallaEstadisticas pantallaEstadisticas;
	private InformacionEstadisticas infoEstadisticas;
	
	public ControladorEstadisticas(InformacionEstadisticas infoEstadisticas, JPanel pResultados, ControladorPrincipal ctrPrincipal) {
		this.ctrPrincipal = ctrPrincipal;
		this.pResultados = pResultados;
		this.infoEstadisticas = infoEstadisticas;
		
		pantallaEstadisticas = new PantallaEstadisticas(this.pResultados);
		pantallaEstadisticas.registrarControlador(this);
		pantallaEstadisticas.registrarControladorInformacion(this);
		
		pantallaEstadisticas.setTextoJuego(ctrPrincipal.getiJuego().informacion(ctrPrincipal.getEstadoJuego()));
		pantallaEstadisticas.setTextoJug1(ctrPrincipal.infoJugador1());
		pantallaEstadisticas.setTextoJug2(ctrPrincipal.infoJugador2());
		mostrarEstadisticas();
		pantallaEstadisticas.setVisible(true);
	}
	
	private void mostrarEstadisticas() {
		Jugador jug1 = ctrPrincipal.getJugador1();
		Jugador jug2 = ctrPrincipal.getJugador2();
		
		if (this.pResultados instanceof PanelResultadosSimulacion) {
			((PanelResultadosSimulacion) pResultados).actualizarResultados(infoEstadisticas.getResultados(), infoEstadisticas.getnPartidas(), infoEstadisticas.getTiempo());
			pantallaEstadisticas.setTextoEstadisticasJug1(obtenerEstadisticasJugSimulacion(jug1));
			pantallaEstadisticas.setTextoEstadisticasJug2(obtenerEstadisticasJugSimulacion(jug2));
		} else if (this.pResultados instanceof PanelResultadosJugar) {
			((PanelResultadosJugar) pResultados).actualizarResultados(infoEstadisticas.getEstadoFinal(), infoEstadisticas.getGanador(), infoEstadisticas.getTiempo());
			((PanelResultadosJugar) pResultados).actualizarInfoPartida(ctrPrincipal.getiJuego().informacionDetalle(infoEstadisticas.getEstadoJuegoFinal()));
			pantallaEstadisticas.setTextoEstadisticasJug1(obtenerEstadisticasJugJugar(jug1));
			pantallaEstadisticas.setTextoEstadisticasJug2(obtenerEstadisticasJugJugar(jug2));
		} else if (this.pResultados instanceof PanelResultadosAnalizar) {
			((PanelResultadosAnalizar) pResultados).actualizarResultados(infoEstadisticas.getEstadoInicial(), infoEstadisticas.getEstadoMovJ1(), infoEstadisticas.getEstadoMovJ2());
			((PanelResultadosAnalizar) pResultados).actualizarInfoEstadoInicial(ctrPrincipal.getiJuego().informacionDetalle(infoEstadisticas.getEstadoJuegoInicial()));
			((PanelResultadosAnalizar) pResultados).actualizarInfoEstadoMovJug1(ctrPrincipal.getiJuego().informacionDetalle(infoEstadisticas.getEstadoJuegoMov1()));
			((PanelResultadosAnalizar) pResultados).actualizarInfoEstadoMovJug2(ctrPrincipal.getiJuego().informacionDetalle(infoEstadisticas.getEstadoJuegoMov2()));
			pantallaEstadisticas.setTextoEstadisticasJug1(obtenerEstadisticasJugAnalisis(jug1));
			pantallaEstadisticas.setTextoEstadisticasJug2(obtenerEstadisticasJugAnalisis(jug2));
		}
	}
	
	private String obtenerEstadisticasJugJugar(Jugador j) {
		String res = null;
		
		try {
			res = ((EstadisticasJugador) j).getEstadisticas();
			int nMovimientos = ((EstadisticasJugador) j).numTotalMovimientos();
			long tiempo = ((EstadisticasJugador) j).tiempoMedioPorMovimiento();
			res = (res == null) ? "" : res + "\n\n";
			res += "Nº movimientos: " + nMovimientos;
			res += "\nTiempo medio por movimiento: " + Util.formatearTiempo(tiempo);	
		} catch (Exception e) {
			res = "Este jugador no proporciona estadísticas.";
		}
		return res;
	}
	
	private String obtenerEstadisticasJugSimulacion(Jugador j) {
		String res = null;
		
		try {
			res = ((EstadisticasJugador) j).getEstadisticas();
			int nMovimientos = ((EstadisticasJugador) j).numTotalMovimientos();
			int nPartidas = infoEstadisticas.getnPartidas();
			long tiempo = ((EstadisticasJugador) j).tiempoMedioPorMovimiento();
			res = (res == null) ? "" : res + "\n\n";
			res += "Nº movimientos por partida: " + nMovimientos/nPartidas;
			res += "\nTiempo medio por movimiento: " + Util.formatearTiempo(tiempo);	
		} catch (Exception e) {
			res = "Este jugador no proporciona estadísticas.";
		}
		return res;
	}
	
	private String obtenerEstadisticasJugAnalisis(Jugador j) {
		String res = null;
		
		try {
			res = ((EstadisticasJugador) j).getEstadisticas();	
			long tiempo = ((EstadisticasJugador) j).tiempoMedioPorMovimiento();
			res = (res == null) ? "" : res + "\n\n";
			res += "Tiempo medio por movimiento: " + Util.formatearTiempo(tiempo);
		} catch (Exception e) {
			res = "Este jugador no proporciona estadísticas.";
		}
		return res;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		String info = pantallaEstadisticas.infoComponente(arg0.getComponent());
		pantallaEstadisticas.actualizarInfo(info);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		pantallaEstadisticas.actualizarInfo(null);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		
		if (command.equals("OK")) {
			pantallaEstadisticas.dispose();
			System.gc();
			ctrPrincipal.continuar();
		} else if (command.equals("Generar informe")) {
			generarInforme();
		}
	}
	
	private void generarInforme() {
		JFileChooser fileChooser = new JFileChooser();
		int seleccion = fileChooser.showSaveDialog(pantallaEstadisticas);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			   File fichero = fileChooser.getSelectedFile();
			   // y a trabajar con fichero ....
			  
			try {
				PrintStream f = new PrintStream(fichero);
				
				// Escribimos la fecha
				Date fechaActual = new Date();
				SimpleDateFormat fechaF = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
				escribirln(f, fechaF.format(fechaActual));
				
				escribir(f, SEPARADOR_FICHERO_CORTO);
				escribirln(f, "JUEGO:\n");
				escribirln(f, ctrPrincipal.getiJuego().informacion(ctrPrincipal.getEstadoJuego()));
				escribir(f, SEPARADOR_FICHERO_LARGO);
				escribirln(f, "ESTRATEGIAS DE LOS JUGADORES:\n");
				escribirln(f, "Estrategia jugador 1:");
				escribir(f, ctrPrincipal.infoJugador1());
				escribir(f, SEPARADOR_FICHERO_CORTO);
				escribirln(f, "Estrategia jugador 2:");
				escribir(f, ctrPrincipal.infoJugador2());
				escribir(f, SEPARADOR_FICHERO_LARGO);
				escribirln(f, "ESTADÍSTICAS:\n");
				escribir(f, "Modo: ");
				if (this.pResultados instanceof PanelResultadosJugar) {
					escribirJugar(f);
				} else if (this.pResultados instanceof PanelResultadosSimulacion) {
					escribirSimular(f);
				} else if (this.pResultados instanceof PanelResultadosAnalizar) {
					escribirAnalizar(f);
				}
				f.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(new JFrame(), "Ha ocurrido un error en la generación del informe.");
				e.printStackTrace();
			}
		}
	}
	
	private void escribirJugar(PrintStream f) {
		escribirln(f, "Partida simple");
		escribirln(f, "Estado final:");
		escribir(f, infoEstadisticas.getEstadoJuegoFinal().toString());
		escribir(f, SEPARADOR_FICHERO_CORTO);
		escribirln(f, ctrPrincipal.getiJuego().informacionDetalle(infoEstadisticas.getEstadoJuegoFinal()));
		escribirln(f, infoEstadisticas.getGanador());
		escribir(f, "Tiempo de la partida: ");
		escribirln(f, Util.formatearTiempo(infoEstadisticas.getTiempo()));
		escribir(f, SEPARADOR_FICHERO_CORTO);
		escribirln(f, "Estadísticas Jugador 1:");
		escribirln(f, obtenerEstadisticasJugJugar(ctrPrincipal.getJugador1()));
		escribir(f, SEPARADOR_FICHERO_CORTO);
		escribirln(f, "Estadísticas Jugador 2:");
		escribirln(f, obtenerEstadisticasJugJugar(ctrPrincipal.getJugador2()));
	}
	
	private void escribirSimular(PrintStream f) {
		escribirln(f, "Simulación de partidas");
		escribir(f, "Nº de partidas: ");
		escribirln(f, String.valueOf(infoEstadisticas.getnPartidas()));
		escribirln(f, "Resultados:");
		escribirln(f, resultadosFormateados(infoEstadisticas.getResultados()));
		escribir(f, "Tiempo total: ");
		escribirln(f, Util.formatearTiempo(infoEstadisticas.getTiempo()));
		escribir(f, "Tiempo medio por partida: ");
		escribir(f, Util.formatearTiempo(infoEstadisticas.getTiempo()/infoEstadisticas.getnPartidas()));
		escribir(f, SEPARADOR_FICHERO_CORTO);
		escribirln(f, "Estadísticas Jugador 1:");
		escribirln(f, obtenerEstadisticasJugJugar(ctrPrincipal.getJugador1()));
		escribir(f, SEPARADOR_FICHERO_CORTO);
		escribirln(f, "Estadísticas Jugador 2:");
		escribirln(f, obtenerEstadisticasJugJugar(ctrPrincipal.getJugador2()));
	}
	
	private String resultadosFormateados(double[] resultados) {
		String res = "Gana el jugador 1: " + (int)resultados[0] + "% ";
	//	res += escribirPorcentajes((int)resultados[0]);
		res += "\nEmpate: " + (int)resultados[1] + "% ";
	//	res += escribirPorcentajes((int)resultados[1]);
		res += "\nGana el jugador 2: " + (int)resultados[2] + "% ";
	//	res += escribirPorcentajes((int)resultados[2]);
		res += "\n";
		return res;
	}
	
	private String escribirPorcentajes(int n) {
		String res = "|";
		for (int i = 1; i <= n; i++) {
			res += "#";
		}
		return res;
	}

	private void escribirAnalizar(PrintStream f) {
		escribirln(f, "Análisis de estado y movimientos");
		escribirln(f, "Estado inicial (analizado):");
		escribir(f, infoEstadisticas.getEstadoJuegoInicial().toString());
		escribir(f, SEPARADOR_FICHERO_CORTO);
		escribirln(f, ctrPrincipal.getiJuego().informacionDetalle(infoEstadisticas.getEstadoJuegoInicial()));
		escribir(f, SEPARADOR_FICHERO_CORTO);
		escribirln(f, "Movimiento del jugador 1:");
		escribir(f, infoEstadisticas.getEstadoJuegoMov1().toString());
		escribir(f, SEPARADOR_FICHERO_CORTO);
		escribirln(f, ctrPrincipal.getiJuego().informacionDetalle(infoEstadisticas.getEstadoJuegoMov1()));
		escribir(f, SEPARADOR_FICHERO_CORTO);
		escribirln(f, "Movimiento del jugador 2:");
		escribir(f, infoEstadisticas.getEstadoJuegoMov2().toString());
		escribir(f, SEPARADOR_FICHERO_CORTO);
		escribirln(f, ctrPrincipal.getiJuego().informacionDetalle(infoEstadisticas.getEstadoJuegoMov2()));
		escribir(f, SEPARADOR_FICHERO_CORTO);
		escribirln(f, "Estadísticas Jugador 1:");
		escribirln(f, obtenerEstadisticasJugJugar(ctrPrincipal.getJugador1()));
		escribir(f, SEPARADOR_FICHERO_CORTO);
		escribirln(f, "Estadísticas Jugador 2:");
		escribirln(f, obtenerEstadisticasJugJugar(ctrPrincipal.getJugador2()));
	}
	
	private void escribir(PrintStream f, String s) {
		s = s.replaceAll("\n", System.lineSeparator());
		f.print(s);
	}
	
	private void escribirln(PrintStream f, String s) {
		s = s.replaceAll("\n", System.lineSeparator()) + System.lineSeparator();;
		f.print(s);
	}

}
