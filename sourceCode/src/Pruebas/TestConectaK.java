/**
 * 
 */
package Pruebas;

import juegos.conectak.juego.ConectaK;
import juegos.util.Movimiento;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author José Miguel Horcas Aguilera
 *
 */
public class TestConectaK {

	private static final int N_FILAS = 6;
	private static final int N_COLUMNAS = 7;
	private static final int LONG_GANADORA = 4; 
	
	private static ConectaK estadoInicial;
	private static ConectaK e;
	private static ConectaK ck;
	private static ConectaK estadoFinal;
	
	/*
	@Test(timeout = 5000)
	public void testHijos() {
		Assert.assertEquals(7, estadoInicial.hijos().size());
		Assert.assertEquals(3, e.hijos().size());
	}
	
	@Test(timeout = 5000, expected = InvalidAssignmentException.class)
	public void setCellInvalidAssignmentException1() throws IllegalArgumentException, InvalidAssignmentException{
		board.setCell(1, 1, Signs.Cross);
		board.setCell(1, 1, Signs.Circle);
	}
*/
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		estadoInicial = new ConectaK(N_FILAS, N_COLUMNAS, LONG_GANADORA);
		//e = new ConectaK(3, 3, 3);
		//ck = (ConectaK) estadoInicial.elegirSucNth(3);
		//estadoFinal = rellenarTablero(e);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		estadoInicial = null;
		//e = null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link juegos.conectak.juego.ConectaK#ConectaK(int, int, int)}.
	 */
	@Test
	public void testConectaK() {
		Assert.assertEquals(estadoInicial, new ConectaK(N_FILAS, N_COLUMNAS, LONG_GANADORA));
		//Assert.assertEquals(e, new ConectaK(3, 3, 3));
	}

	/**
	 * Test method for {@link juegos.conectak.juego.ConectaK#longGanadora()}.
	 */
	@Test
	public void testLongGanadora() {
		Assert.assertEquals(4, estadoInicial.longGanadora());
		//Assert.assertEquals(3, e.longGanadora());
	}

	/**
	 * Test method for {@link juegos.conectak.juego.ConectaK#ultimoMovimiento()}.
	 */
	@Test
	public void testUltimoMovimiento() {
		Assert.assertNull(estadoInicial.ultimoMovimiento());
		//Assert.assertEquals(new Movimiento(6,3), ck.ultimoMovimiento());
	}

	/**
	 * Test method for {@link juegos.conectak.juego.ConectaK#fichaActual()}.
	 */
	@Test
	public void testFichaActual() {
		Assert.assertEquals(ConectaK.ficha1, estadoInicial.fichaActual());
	}

	/**
	 * Test method for {@link juegos.conectak.juego.ConectaK#fichaOtro()}.
	 */
	@Test
	public void testFichaOtro() {
		Assert.assertEquals(ConectaK.ficha2, estadoInicial.fichaOtro());
	}

	/**
	 * Test method for {@link juegos.conectak.juego.ConectaK#posibleP(int)}.
	 */
	@Test
	public void testPosibleP() {
		for (int c = 0; c < estadoInicial.tablero().nColumnas(); c++) {
			Assert.assertTrue(estadoInicial.posibleP(c));
		}
		ConectaK ck = rellenarTablero(estadoInicial);
		Assert.assertFalse(ck.posibleP(3));
	}

	/**
	 * Test method for {@link juegos.conectak.juego.ConectaK#elegirSucNth(int)}.
	 */
	@Test
	public void testElegirSucNth() {
		Assert.assertEquals(ConectaK.ficha2, estadoInicial.elegirSucNth(3).fichaActual());
		Assert.assertEquals(ConectaK.ficha1, estadoInicial.elegirSucNth(3).fichaOtro());
		Assert.assertEquals(new Movimiento(5,3).toString(), ((ConectaK) estadoInicial.elegirSucNth(3)).ultimoMovimiento().toString());
		//Assert.assertEquals(ConectaK.ficha2, ck.fichaActual());
		//Assert.assertEquals(ConectaK.ficha1, ck.fichaOtro());
		//Assert.assertEquals(new Movimiento(6,3), ck.ultimoMovimiento());
		
	}

	/**
	 * Test method for {@link juegos.conectak.juego.ConectaK#agotado()}.
	 */
	@Test
	public void testAgotado() {
		ConectaK ck = rellenarTablero(estadoInicial);
		Assert.assertTrue(ck.agotado());
		Assert.assertFalse(estadoInicial.agotado());
	}

	/**
	 * Test method for {@link juegos.conectak.juego.ConectaK#clave()}.
	 */
	@Test
	public void testClave() {
		Assert.assertEquals(estadoInicial.clave(), new ConectaK(N_FILAS, N_COLUMNAS, LONG_GANADORA).clave());
		ConectaK ck = (ConectaK) estadoInicial.elegirSucNth(3);
		Assert.assertFalse(estadoInicial.clave().equals(ck.clave()));
		ck = new ConectaK(3,3,3);
		Assert.assertFalse(estadoInicial.clave().equals(ck.clave()));
	}

	/**
	 * Test method for {@link juegos.conectak.juego.ConectaK#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Assert.assertTrue(estadoInicial.equals(new ConectaK(N_FILAS, N_COLUMNAS, LONG_GANADORA)));
		ConectaK ck = (ConectaK) estadoInicial.elegirSucNth(3);
		Assert.assertFalse(estadoInicial.equals(ck));
		ck = new ConectaK(3,3,3);
		Assert.assertFalse(estadoInicial.equals(ck));
	}

	/**
	 * Test method for {@link juegos.conectak.juego.ConectaK#ganador()}.
	 */
	@Test
	public void testGanador() {
		Assert.assertNull(estadoInicial.ganador());
		ConectaK ck = (ConectaK) estadoInicial.elegirSucNth(0);
		ck = (ConectaK) ck.elegirSucNth(1);
		ck = (ConectaK) ck.elegirSucNth(0);
		ck = (ConectaK) ck.elegirSucNth(1);
		ck = (ConectaK) ck.elegirSucNth(0);
		ck = (ConectaK) ck.elegirSucNth(1);
		ck = (ConectaK) ck.elegirSucNth(0);
		Assert.assertEquals(ConectaK.ficha1, ck.ganador());
	}

	/**
	 * Test method for {@link juegos.conectak.juego.ConectaK#hijos()}.
	 */
	@Test
	public void testHijos() {
		Assert.assertEquals(7, estadoInicial.hijos().size());
		//Assert.assertEquals(3, e.hijos().size());
	}

	/**
	 * Test method for {@link juegos.conectak.juego.ConectaK#jug1()}.
	 */
	@Test
	public void testJug1() {
		Assert.assertTrue(estadoInicial.jug1());
		ConectaK ck = (ConectaK) estadoInicial.elegirSucNth(3);
		Assert.assertFalse(ck.jug1());
	}

	/**
	 * Test method for {@link juegos.conectak.juego.ConectaK#toString()}.
	 */
	@Test
	public void testToString() {
		ConectaK ck = (ConectaK) estadoInicial.elegirSucNth(3);
		Assert.assertFalse(estadoInicial.toString().equals(ck.toString()));
		ck = new ConectaK(3,3,3);
		Assert.assertFalse(estadoInicial.toString().equals(ck.toString()));
	}

	/**
	 * Test method for {@link juegos.conectak.juego.ConectaK#tablero()}.
	 */
	@Test
	public void testTablero() {
		ConectaK ck = (ConectaK) estadoInicial.elegirSucNth(3);
		Assert.assertFalse(estadoInicial.tablero().equals(ck.tablero()));
		ck = new ConectaK(3,3,3);
		Assert.assertFalse(estadoInicial.tablero().equals(ck.tablero()));
	}

	private static ConectaK rellenarTablero(ConectaK ck) {
		ConectaK e = ck;
		
		for (int f = 0; f < ck.tablero().nFilas(); f++) {
			for (int c = 0; c < ck.tablero().nColumnas(); c++) {
				e = (ConectaK) e.elegirSucNth(c);
			}
		}
		return e;
	}
}
