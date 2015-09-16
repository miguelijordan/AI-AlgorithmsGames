package entornointeractivo.gui.util;

public class InformacionEntrenamiento {

	private int nPartidas;
	//private int pausaCada;
	//private int kPruebas;
	private boolean entrenado;
	
	public InformacionEntrenamiento() {
		entrenado = false;
	}
	
	public InformacionEntrenamiento(int n) {
		entrenado = true;
		nPartidas = n;
	}
	
	public String toString() {
		String res = "No entrenado.";
		if (entrenado) {
			res = "Entrenamiento: " + nPartidas + " partidas.";
		}
		return res;
	}

	public int getnPartidas() {
		return nPartidas;
	}

	public void setnPartidas(int nPartidas) {
		this.nPartidas = nPartidas;
	}
	
	
	
}
