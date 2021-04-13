package modelo;

import utiles.RespuestaColocacion;

public class Tablero {

	private int dimension = 3;
	private Tipo matriz[][] = new Tipo[dimension][dimension];
	private String errorActual = "";

	public Tipo getValorCasilla(int x, int y) {
		return matriz[x][y];
	}

	public Tablero() {
		super();
		inicializarMatriz();
	}

	private void inicializarMatriz() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				matriz[i][j] = Tipo.blanco;
			}
		}
	}

	public boolean limpiarCasilla(Coordenada coordenada) {
		if (getPosicion(coordenada) != Tipo.blanco) {
			setPosicion(coordenada, Tipo.blanco);
			return true;
		}
		return false;
	}

	private void setPosicion(Coordenada coordenada, Tipo blanco) {
		matriz[coordenada.getX()][coordenada.getY()] = blanco;

	}

	public boolean borrarCasilla(Coordenada coordenada, Tipo tipo) {
		// primero hay que borrar una casilla no bloqueada de tu turno
		if (isPropiedad(coordenada, tipo) && !comprobarCasillaBloqueada(coordenada)) {
			limpiarCasilla(coordenada);
			return true;
		}
		return false;
	}

	public boolean colocarFicha(Coordenada coordenada, Tipo tipoActual) {

		if (matriz[coordenada.getX()][coordenada.getY()] == Tipo.blanco) {
			matriz[coordenada.getX()][coordenada.getY()] = tipoActual;
//			System.out.println("coordenada puesta");
			return true;
		}
		return false;
	}

	public Tipo getPosicion(Coordenada coordenada) {
		return matriz[coordenada.getX()][coordenada.getY()];
	}

	private boolean isLibre(Coordenada coordenada) {
		return getPosicion(coordenada) == Tipo.blanco;
	}

	/**
	 * 
	 * @param coordenada
	 * @return false si encuentra una libre y true en caso contrario
	 */
	public boolean comprobarCasillaBloqueada(Coordenada coordenada) {
		for (int x = coordenada.getX() - 1; x <= coordenada.getX() + 1; x++)
			for (int y = coordenada.getY() - 1; y <= coordenada.getY() + 1; y++)
				if (x >= 0 && x < this.dimension && y >= 0 && y < this.dimension)
					if (this.isLibre(new Coordenada(x, y)))
						return false;
		return true;
	}

	public String getErrorActual() {
		return this.errorActual;
	}

	public boolean isPropiedad(Coordenada coordenada, Tipo tipo) {
		return getPosicion(coordenada) == tipo;
	}

	public boolean comprobarLinea() {

		Tipo simbolo;
		boolean coincidencia;

		for (int i = 0; i < matriz.length; i++) {
			coincidencia = true;
			simbolo = matriz[i][0];
			if (simbolo != Tipo.blanco) {
				for (int j = 1; j < matriz[0].length; j++) {
					if (simbolo != matriz[i][j]) {
						coincidencia = false;
					}
				}
				if (coincidencia) {
					return coincidencia;
				}

			}

		}
		return false;

	}

	public boolean comprobarColumna() {

		Tipo simbolo;
		boolean coincidencia;

		for (int j = 0; j < matriz.length; j++) {
			coincidencia = true;
			simbolo = matriz[0][j];
			if (simbolo != Tipo.blanco) {
				for (int i = 1; i < matriz[0].length; i++) {
					if (simbolo != matriz[i][j]) {
						coincidencia = false;
					}
				}
				if (coincidencia) {
					return coincidencia;
				}

			}

		}
		return false;

	}

	public boolean comprobarDiagonal() {

		Tipo simbolo;
		boolean coincidencia = true;

		simbolo = matriz[0][0];
		if (simbolo != Tipo.blanco) {
			for (int i = 1; i < matriz.length; i++) {
				if (simbolo != matriz[i][i]) {
					coincidencia = false;
				}
			}
			if (coincidencia) {
				return coincidencia;
			}

		}

		coincidencia = true;

		simbolo = matriz[0][2];
		if (simbolo != Tipo.blanco) {
			for (int i = 1, j = 1; i < matriz.length; i++, j--) {
				if (simbolo != matriz[i][j]) {
					coincidencia = false;
				}
			}
			if (coincidencia) {
				return coincidencia;
			}
		}
		return false;

	}

}
