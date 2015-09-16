package entornointeractivo.gui.simular;

import entornointeractivo.gui.principal.ControladorPrincipal;
import entornointeractivo.gui.util.InformacionEstadisticas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import juegos.Juego;

public class ControladorSimulacion implements ActionListener, MouseListener {

	private ControladorPrincipal ctrPrincipal;
	private PantallaSimulacion pantallaSimulacion;
	private long tiempoEjecucion;
	private boolean finSimulacion;
	private Thread threadSimulacion;
	private ThreadSimulacion tSimulacion;
	
	public ControladorSimulacion(ControladorPrincipal ctrPrincipal) {
		this.ctrPrincipal = ctrPrincipal;
		
		pantallaSimulacion = new PantallaSimulacion();
		pantallaSimulacion.actualizarInfoJug1(ctrPrincipal.infoJugador1());
		pantallaSimulacion.actualizarInfoJug2(ctrPrincipal.infoJugador2());
		pantallaSimulacion.registrarControlador(this);
		pantallaSimulacion.registrarControladorInformacion(this);
		pantallaSimulacion.setVisible(true);
		finSimulacion = false;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		String info = pantallaSimulacion.infoComponente(arg0.getComponent());
		pantallaSimulacion.actualizarInfo(info);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		pantallaSimulacion.actualizarInfo(null);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setFinSimulacion(boolean fin) {
		finSimulacion = fin;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals("Simular")) {
			Integer nPartidas = obtenerNumPartidas();
			if (nPartidas != null) {
				actualizarInformacionAyudaSimulacion(nPartidas);
				pantallaSimulacion.desactivarControlador(this);
				
				tSimulacion = new ThreadSimulacion(nPartidas, ctrPrincipal, pantallaSimulacion, this);
				threadSimulacion = new Thread(tSimulacion);
				threadSimulacion.setPriority(Thread.MAX_PRIORITY);
				
				tiempoEjecucion = System.currentTimeMillis();
				//double[] resultados = Juego.nPartidasGUI(nPartidas, ctrPrincipal.getJugador1(), ctrPrincipal.getJugador2(), ctrPrincipal.getEstadoJuego(), pantallaSimulacion);
				threadSimulacion.start();
			}
		} else if (command.equals("Cancelar")) {
			if (threadSimulacion != null) {
				try {
					threadSimulacion.interrupt();
					tSimulacion.fin();
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
			pantallaSimulacion.dispose();
			ctrPrincipal.continuar();
		}
	}
	
	public void finSimulacion(double[] resultados, int nPartidas) {
		tiempoEjecucion = System.currentTimeMillis() - tiempoEjecucion;
		JOptionPane.showMessageDialog(new JFrame(), "Fin de la simulación.");
		pantallaSimulacion.dispose();
		InformacionEstadisticas infoEstadisticas = new InformacionEstadisticas(resultados, nPartidas, tiempoEjecucion, null);
		ctrPrincipal.finSimulacion(infoEstadisticas);
	}
	
	private Integer obtenerNumPartidas() {
		int nPartidas; 
		
		try {
			nPartidas = pantallaSimulacion.getNumPartidas();	
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Hay un error en los parámetros.");
			return null;
		}
		if (nPartidas <= 0) {
			JOptionPane.showMessageDialog(new JFrame(), "El número de partidas debe ser mayor que 0.");
			return null;
		}
		return nPartidas;
	}
	
	private void actualizarInformacionAyudaSimulacion(int nPartidas) {
		pantallaSimulacion.actualizarSimulacion(nPartidas, 0, 0);
		String info = "Simulando...";
		info += "\nTenga paciencia, ";
		info += "la simulación puede durar varios minutos o incluso horas, dependiendo del tamaño del juego y del número de partidas.";
		pantallaSimulacion.actualizarInfo(info);
		pantallaSimulacion.update(pantallaSimulacion.getGraphics());
	}
}
