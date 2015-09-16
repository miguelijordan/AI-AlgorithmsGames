package estrategias.agentes.estadisticas;

import java.util.List;

public class Util {

	public static double media(List<Integer> l) {
		double suma = 0;
		for (int i : l) {
			suma += i;
		}
		return suma / l.size();
	}
	
	public static String formatearTiempo(long tiempo) {
		String res = "";
		long t = tiempo%1000;
		long ms = t;
		t = tiempo/1000;
		long s = t%60;
		t = t/60;
		long m = t%60;
		t = t/60;
		long h = (int)t;
		if (h > 0) {
			res = h + "h " + m + "m " + s + "s " + ms + "ms";
		} else if (m > 0) {
			res = m + "m " + s + "s " + ms + "ms";
		} else if (s > 0) {
			res = s + "s " + ms + "ms";
		} else {
			res = ms + "ms";
		}
		return res;
	}
	
	/**
	 * Redondea un valor real a dos decimales.
	 * 
	 * @param d	Valor.
	 * @return	Valor redondeado a dos decimales.
	 */
	public static double redondear(double d) {
		return (Math.rint(d*100)/100);		// Redondea a dos decimales.
	}
	
	/**
	 * Formatea los resultados convirtiendolos en porcentajes de 2 decimales.
	 * 
	 * @param res	Array con los resultados.
	 * @return		Array con los resultados formateados.
	 */
	public static double[] formatoResultados(double[] res) {
		for (int i = 0; i < res.length; i++) {
			res[i] = Util.redondear(res[i]*100);
		}
		return res;
	}
}
