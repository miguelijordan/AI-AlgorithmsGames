/**
 * 
 */
package Pruebas;

import juegos.go.juego.Go;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Migueli
 *
 */
public class TestGo {

	private static final int N_FILAS = 9;
	private static final int N_COLUMNAS = 9;
	private static final int REGLAS = Go.REGLAS_JAPONESAS; 
	
	private static Go estadoInicial;
	private static Go e;
	private static Go estadoFinal;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//estadoInicial = new Go(9, 9, 0, null, true, Go.REGLAS_JAPONESAS);
		estadoInicial = new Go(N_FILAS, N_COLUMNAS);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		estadoInicial = null;
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	public void setUp() throws Exception {

	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	public void tearDown() throws Exception {

	}

	/**
	 * Test method for {@link juegos.go.juego.Go#Go(int, int)}.
	 */
	@Test
	public void testGoIntInt() {
		Assert.assertEquals(estadoInicial, new Go(N_FILAS, N_COLUMNAS));
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#hijos()}.
	 */
	@Test
	public void testHijos() {
		Assert.assertEquals(N_FILAS*N_COLUMNAS+1, estadoInicial.hijos().size());
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#ganador()}.
	 */
	@Test
	public void testGanador() {
		Assert.assertNull(estadoInicial.ganador());
		Go go = (Go) estadoInicial.elegirSuc(0, 1);
		go = (Go) go.pasarTurno();
		go = (Go) go.elegirSuc(1, 0);
		go = (Go) go.pasarTurno();
		go = (Go) go.elegirSuc(4, 6);
		go = (Go) go.pasarTurno();
		go = (Go) go.elegirSuc(2, 1);
		go = (Go) go.pasarTurno();
		go = (Go) go.pasarTurno();
		Assert.assertEquals(Go.getFicha1(), go.ganador());
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#agotado()}.
	 */
	@Test
	public void testAgotado() {
		Assert.assertFalse(estadoInicial.agotado());
		//Go go = rellenarTablero(estadoInicial);
		//Assert.assertTrue(go.agotado());
		
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#jug1()}.
	 */
	@Test
	public void testJug1() {
		Assert.assertTrue(estadoInicial.jug1());
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#clave()}.
	 */
	@Test
	public void testClave() {
		
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#fichaActual()}.
	 */
	@Test
	public void testFichaActual() {
		Assert.assertEquals(Go.getFicha1(), estadoInicial.fichaActual());
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#fichaOtro()}.
	 */
	@Test
	public void testFichaOtro() {
		Assert.assertEquals(Go.getFicha2(), estadoInicial.fichaOtro());
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#toString()}.
	 */
	@Test
	public void testToString() {
		
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#tablero()}.
	 */
	@Test
	public void testTablero() {
		
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#movimientoValido(int, int)}.
	 */
	@Test
	public void testMovimientoValido() {
		Assert.assertTrue(estadoInicial.movimientoValido(3, 3));
	}
	
	/**
	 * Test method for {@link juegos.go.juego.Go#elegirSuc(int, int)}.
	 */
	@Test
	public void testElegirSuc() {
		
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#pasarTurno()}.
	 */
	@Test
	public void testPasarTurno() {
		
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#puntuacionReglasChinas(juegos.util.Ficha)}.
	 */
	@Test
	public void testPuntuacionReglasChinas() {
		
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#puntuacionReglasJaponesas(juegos.util.Ficha)}.
	 */
	@Test
	public void testPuntuacionReglasJaponesas() {
		
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#getUltimoMov()}.
	 */
	@Test
	public void testGetUltimoMov() {
		
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#getReglas()}.
	 */
	@Test
	public void testGetReglas() {
		
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#getKomi()}.
	 */
	@Test
	public void testGetKomi() {
		Assert.assertEquals(0, estadoInicial.getHandicap());
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#getHandicap()}.
	 */
	@Test
	public void testGetHandicap() {
		Assert.assertEquals(0, estadoInicial.getHandicap());
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#getnFichasJug1()}.
	 */
	@Test
	public void testGetnFichasJug1() {
		Assert.assertEquals(N_FILAS*N_COLUMNAS/2 + 1, estadoInicial.getnFichasJug1());
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#getnFichasJug2()}.
	 */
	@Test
	public void testGetnFichasJug2() {
		Assert.assertEquals(N_FILAS*N_COLUMNAS/2, estadoInicial.getnFichasJug2());
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#getnFichasCapturadasJug1()}.
	 */
	@Test
	public void testGetnFichasCapturadasJug1() {
		Assert.assertEquals(0, estadoInicial.getnFichasCapturadasJug1());
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#getnFichasCapturadasJug2()}.
	 */
	@Test
	public void testGetnFichasCapturadasJug2() {
		Assert.assertEquals(0, estadoInicial.getnFichasCapturadasJug2());
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#getFicha1()}.
	 */
	@Test
	public void testGetFicha1() {
		Assert.assertEquals(Go.ficha1, estadoInicial.getFicha1());
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#getFicha2()}.
	 */
	@Test
	public void testGetFicha2() {
		Assert.assertEquals(Go.ficha2, estadoInicial.getFicha2());
	}

	/**
	 * Test method for {@link juegos.go.juego.Go#getUltimasFichasCapturadas()}.
	 */
	@Test
	public void testGetUltimasFichasCapturadas() {
		Assert.assertEquals(null, estadoInicial.getUltimasFichasCapturadas());
	}
}
