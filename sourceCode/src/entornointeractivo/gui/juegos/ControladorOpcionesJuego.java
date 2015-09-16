package entornointeractivo.gui.juegos;

import entornointeractivo.gui.InterfazJuego;
import entornointeractivo.gui.principal.ControladorPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import juegos.EstadoJuego;

public class ControladorOpcionesJuego implements ActionListener, MouseListener {

	private PantallaOpcionesJuego pOpcionesJuego;
	private ControladorPrincipal ctrPrincipal;
	
	
	public ControladorOpcionesJuego(InterfazJuego juego, ControladorPrincipal ctrPrincipal) {
		this.ctrPrincipal = ctrPrincipal;
		
		pOpcionesJuego = new PantallaOpcionesJuego(juego.nombre(), juego.panelOpciones());
		pOpcionesJuego.registrarControlador(this);
		pOpcionesJuego.registrarControladorInformacion(this);
		pOpcionesJuego.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		String info = pOpcionesJuego.infoComponente(e.getComponent());
		actualizarInfo(info);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		actualizarInfo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals("Aceptar")) {
			EstadoJuego estado = pOpcionesJuego.getPanelOpciones().estadoJuego();
			if (estado != null) {
				ctrPrincipal.setEstadoJuego(estado);
				pOpcionesJuego.dispose();
				ctrPrincipal.inicializarJugadores();
				ctrPrincipal.continuar();	
			}
		} else if (command.equals("Cancelar")) {
			pOpcionesJuego.dispose();
			ctrPrincipal.continuar();
		}
	}

	/**
	 * Actualiza la información de ayuda.
	 * 
	 * @param info	Información.
	 */
	private void actualizarInfo(String info) {
		pOpcionesJuego.actualizarInfo(info);
	}
	
	public EstadoJuego estadoJuego() {
		return pOpcionesJuego.getPanelOpciones().estadoJuego();
	}
}
