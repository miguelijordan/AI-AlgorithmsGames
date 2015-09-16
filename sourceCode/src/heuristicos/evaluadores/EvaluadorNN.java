package heuristicos.evaluadores;

import heuristicos.aprendizaje.DiferenciasTemporales;
import juegos.EstadoJuego;
import juegos.util.Ficha;

import org.encog.neural.activation.ActivationSigmoid;
import org.encog.neural.data.NeuralData;
import org.encog.neural.data.NeuralDataSet;
import org.encog.neural.data.basic.BasicNeuralData;
import org.encog.neural.data.basic.BasicNeuralDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.logic.FeedforwardLogic;
import org.encog.neural.networks.training.Train;
import org.encog.neural.networks.training.propagation.back.Backpropagation;


/**
 * Evaluador heurístico que emplea una red neuronal entrenable empleando aprendizaje con refuerzo mediante diferencias temporales.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 18/08/2011
 *
 */
abstract public class EvaluadorNN implements Evaluador, DiferenciasTemporales {

	// CONSTANTES
	private static final double[] VALOR_GANAR = {1, 0};
	private static final double[] VALOR_PERDER = {0, 1};
	private static final double[] VALOR_EMPATAR = {0, 0};
	
	/**
	 * Tasa de aprendizaje.
	 * Ajusta la velocidad a la que aprende la red.
	 * (The learning rate, this is value is essentially a percent. 
	 * It is the degree to which the gradients are applied to the weight matrix to allow learning.)
	 */
	private static final double TASA_APRENDIZAJE = 0.25;
	
	/**
	 * Momento de entrenamiento.
	 * Indica la influencia que tendrá la iteración anterior sobre la actual.
	 * (The momentum for training.
	 * This is the degree to which changes from which the previous training iteration will affect this training iteration.
	 * This can be useful to overcome local minima.)
	 */
	private static final double MOMENTO = 0.8;
	
	// ATRIBUTOS
	/**
	 * Red neuronal.
	 */
	private BasicNetwork nn;
	
	private double[] ganar;
	private double[] perder;
	private double[] empatar;
	
	private int nEntradas;
	private int nSalidas;
	private int nNeuronasIntermedias;
	private double momento;
	private double tasa_aprendizaje;
	
	
	/**
	 * Crea una evaluador con una red neuronal con los parámetros indicados.
	 * 
	 * @param nEntradas				Número de entradas.
	 * @param nSalidas				Número de salidas.
	 * @param nNeuronasIntermedias	Número de neuronas en la capa intermedia.
	 */
	public EvaluadorNN(int nEntradas, int nSalidas, int nNeuronasIntermedias) {
		this.nEntradas = nEntradas;
		this.nSalidas = nSalidas;
		this.nNeuronasIntermedias = nNeuronasIntermedias;
		this.momento = MOMENTO;
		this.tasa_aprendizaje = TASA_APRENDIZAJE;
		
		ganar = VALOR_GANAR;
		perder = VALOR_PERDER;
		empatar = VALOR_EMPATAR;
		nn = crearRed(nEntradas, nSalidas, nNeuronasIntermedias);
	}
	
	/**
	 * Crea una evaluador con una red neuronal con los parámetros indicados.
	 * 
	 * @param nEntradas				Número de entradas.
	 * @param nSalidas				Número de salidas.
	 * @param nNeuronasIntermedias	Número de neuronas en la capa intermedia.
	 */
	public EvaluadorNN(int nEntradas, int nSalidas, int nNeuronasIntermedias, double tasa_aprendizaje, double momento) {
		this.nEntradas = nEntradas;
		this.nSalidas = nSalidas;
		this.nNeuronasIntermedias = nNeuronasIntermedias;
		this.momento = momento;
		this.tasa_aprendizaje = tasa_aprendizaje;
		
		ganar = VALOR_GANAR;
		perder = VALOR_PERDER;
		empatar = VALOR_EMPATAR;
		nn = crearRed(nEntradas, nSalidas, nNeuronasIntermedias);
	}
	
	/**
	 * Devuelve una codificación del estado para alimentar una red neuronal.
	 * 
	 * @param e		Estado a codificar.
	 * @return		Codificación del estado (entrada de la red neuronal).
	 */
	abstract public double[] codifica(EstadoJuego e);
	
	/**
	 * Crea la estructura de la red neuronal.
	 * 
	 * @param nEntradas				Número de entradas.
	 * @param nSalidas				Número de salidas.
	 * @param nNeuronasIntermedias	Número de neuronas de la capa intermedia.
	 * @return						La red.
	 */
	private BasicNetwork crearRed(int nEntradas, int nSalidas, int nNeuronasIntermedias) {
		BasicNetwork red = new BasicNetwork();
		red.addLayer(new BasicLayer(new ActivationSigmoid(), true, nEntradas));
		red.addLayer(new BasicLayer(new ActivationSigmoid(), true, nNeuronasIntermedias));
		red.addLayer(new BasicLayer(new ActivationSigmoid(), true, nSalidas));
		red.setLogic(new FeedforwardLogic());
		red.getStructure().finalizeStructure();
		red.reset();
		
		return red;
	}

	@Override
	public double evaluacion(EstadoJuego estado, Ficha fmax, Ficha fmin) {
		NeuralData salida = nn.compute(new BasicNeuralData(codifica(estado)));
		return salida.getData(0) - salida.getData(1);
	}

	@Override
	public void actualizaDt(EstadoJuego e, EstadoJuego e2) {
		NeuralData entrada = new BasicNeuralData(codifica(e));
		NeuralData entrada2 = new BasicNeuralData(codifica(e2));
		NeuralData salidaDeseada = nn.compute(entrada2);
		entrenarRed(entrada, salidaDeseada);
	}

	@Override
	public void estadoEmpate(EstadoJuego e) {
		NeuralData entrada = new BasicNeuralData(codifica(e));
		NeuralData salida = new BasicNeuralData(empatar);
		entrenarRed(entrada, salida);
	}

	@Override
	public void estadoGanador(EstadoJuego e) {
		NeuralData entrada = new BasicNeuralData(codifica(e));
		NeuralData salida = new BasicNeuralData(ganar);
		entrenarRed(entrada, salida);
	}

	@Override
	public void estadoPerdedor(EstadoJuego e) {
		NeuralData entrada = new BasicNeuralData(codifica(e));
		NeuralData salida = new BasicNeuralData(perder);
		entrenarRed(entrada, salida);
	}
	
	/**
	 * Realiza una iteración de entrenamiento de la red.
	 * 
	 * @param entrada	Datos de entrada para el entrenamiento.
	 * @param salida	Salida deseada.
	 */
	private void entrenarRed(NeuralData entrada, NeuralData salida) {
		NeuralDataSet conjuntoEntrenamiento = new BasicNeuralDataSet();
		conjuntoEntrenamiento.add(entrada, salida);
		Train entrenamiento = new Backpropagation(nn, conjuntoEntrenamiento, tasa_aprendizaje, momento);
		entrenamiento.iteration();
		nn = entrenamiento.getNetwork();
	}

	@Override
	public String toString() {
		String res = "Evaluador con red neuronal.";
		res += " " + nEntradas + " neuronas de entrada,";
		res += " " + nNeuronasIntermedias + " neuronas en la capa intermedia,";
		res += " " + nSalidas + " neuronas de salida,";
		res += " tasa de aprendizaje = " + tasa_aprendizaje;
		res += " momento = " + momento;
		return res;
	}

}
