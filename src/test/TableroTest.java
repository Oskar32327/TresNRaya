package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Coordenada;
import modelo.Tablero;
import modelo.Tipo;

class TableroTest {

	Tablero tablero;

	@BeforeEach
	void before() {
		tablero = new Tablero();
	}

	@Test
	void test() {
		int dimension = 3;
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				assertEquals(Tipo.blanco, tablero.getValorCasilla(i, j));
			}
		}
	}

	@Test
	void testcomprobarBloqueada() {
		int posX[] = { 0, 0, 1, 1, 2, 2 };
		int posY[] = { 1, 2, 2, 1, 0, 1 };
		Tipo tipos[] = { Tipo.X, Tipo.O, Tipo.X, Tipo.O, Tipo.X, Tipo.O };
		for (int i = 0; i < tipos.length; i++) {
			tablero.colocarFicha(new Coordenada(posX[i], posY[i]), tipos[i]);
		}
		assertTrue(tablero.comprobarCasillaBloqueada(new Coordenada(0, 2)));
		assertFalse(tablero.comprobarCasillaBloqueada(new Coordenada(1, 1)));
	}

	@Test
	void testcomprobarFila() {
		int posX[] = { 0, 0, 0, 1, 2, 2 };
		int posY[] = { 0, 1, 2, 1, 0, 2 };
		Tipo tipos[] = { Tipo.X, Tipo.X, Tipo.X, Tipo.O, Tipo.O, Tipo.O };
		for (int i = 0; i < tipos.length; i++) {
			tablero.colocarFicha(new Coordenada(posX[i], posY[i]), tipos[i]);
		}
		assertTrue(tablero.comprobarLinea());
	}

	@Test
	void testcomprobarFila1() {
		int posX[] = { 0, 0, 0, 1, 2, 2 };
		int posY[] = { 0, 1, 2, 1, 0, 2 };
		Tipo tipos[] = { Tipo.X, Tipo.O, Tipo.X, Tipo.X, Tipo.O, Tipo.O };
		for (int i = 0; i < tipos.length; i++) {
			tablero.colocarFicha(new Coordenada(posX[i], posY[i]), tipos[i]);
		}
		assertFalse(tablero.comprobarLinea());
	}

	@Test
	void testcomprobarColumna() {
		int posX[] = { 0, 0, 0, 1, 1, 2 };
		int posY[] = { 0, 1, 2, 1, 2, 1 };
		Tipo tipos[] = { Tipo.X, Tipo.O, Tipo.X, Tipo.O, Tipo.X, Tipo.O };
		for (int i = 0; i < tipos.length; i++) {
			tablero.colocarFicha(new Coordenada(posX[i], posY[i]), tipos[i]);
		}
		assertTrue(tablero.comprobarColumna());
	}

	@Test
	void testcomprobarColumna1() {
		int posX[] = { 0, 0, 0, 1, 1, 2 };
		int posY[] = { 0, 1, 2, 1, 2, 1 };
		Tipo tipos[] = { Tipo.X, Tipo.X, Tipo.X, Tipo.O, Tipo.O, Tipo.O };
		for (int i = 0; i < tipos.length; i++) {
			tablero.colocarFicha(new Coordenada(posX[i], posY[i]), tipos[i]);
		}
		assertFalse(tablero.comprobarColumna());
	}

	@Test
	void testcomprobarDiagonal() {
		int posX[] = { 0, 0, 1, 2, 1, 2 };
		int posY[] = { 0, 2, 1, 0, 2, 2 };
		Tipo tipos[] = { Tipo.X, Tipo.O, Tipo.O, Tipo.O, Tipo.O, Tipo.X };
		for (int i = 0; i < tipos.length; i++) {
			tablero.colocarFicha(new Coordenada(posX[i], posY[i]), tipos[i]);
		}
		assertTrue(tablero.comprobarDiagonal());
	}

	@Test
	void testcomprobarDiagonal1() {
		int posX[] = { 0, 0, 1, 2, 1, 2 };
		int posY[] = { 0, 2, 1, 0, 2, 2 };
		Tipo tipos[] = { Tipo.X, Tipo.X, Tipo.O, Tipo.O, Tipo.O, Tipo.X };
		for (int i = 0; i < tipos.length; i++) {
			tablero.colocarFicha(new Coordenada(posX[i], posY[i]), tipos[i]);
		}
		assertFalse(tablero.comprobarDiagonal());
	}

}
