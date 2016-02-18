package com.melascuco.geocaching.senda;

public class Sendasolver {

	public static int FIL_CENTRO = 11;
	public static int COL_CENTRO = 11;
	
	private static long contador = 0;
	private static long linea = 0;

	int matrizValores[][] = {
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{ 0, 0, 0, 0, 0, 0,-1,-1,-1,-1, 4, 7, 7,-1,-1,-1,-1, 0, 0, 0, 0, 0, 0}, 
				{ 0, 0, 0, 0,-1,-1,-1, 5, 4, 4, 8, 3, 3, 4, 6, 3,-1,-1,-1, 0, 0, 0, 0}, 
				{ 0, 0, 0,-1,-1, 1, 4, 5, 1, 1, 1, 4, 5, 1, 7, 1, 3, 5,-1,-1, 0, 0, 0}, 
				{ 0, 0,-1,-1, 4, 9, 4, 9, 6, 7, 5, 5, 5, 8, 7, 6, 6, 8, 5,-1,-1, 0, 0}, 
				{ 0, 0,-1, 3, 7, 2, 9, 8, 3, 5, 6, 7, 3, 9, 1, 8, 7, 5, 8, 5,-1, 0, 0}, 
				{ 0,-1,-1, 1, 4, 7, 8, 4, 2, 9, 2, 7, 1, 1, 8, 2, 2, 7, 6, 3,-1,-1, 0}, 
				{ 0,-1, 7, 2, 1, 8, 5, 5, 3, 1, 1, 3, 1, 3, 3, 4, 2, 8, 6, 1, 3,-1, 0}, 
				{ 0,-1, 4, 2, 6, 7, 2, 5, 2, 4, 2, 2, 5, 4, 3, 2, 8, 1, 7, 7, 3,-1, 0}, 
				{-1,-1, 4, 1, 6, 5, 1, 1, 1, 9, 1, 4, 3, 4, 4, 3, 1, 9, 8, 2, 7,-1,-1}, 
				{-1, 4, 3, 5, 2, 3, 2, 2, 3, 2, 4, 2, 5, 3, 5, 1, 1, 3, 5, 5, 3, 7,-1}, 
				{-1, 2, 7, 1, 5, 1, 1, 3, 1, 5, 3, 3, 2, 4, 2, 3, 7, 7, 5, 4, 2, 7,-1}, 
				{-1, 2, 5, 2, 2, 6, 1, 2, 4, 4, 6, 3, 4, 4, 2, 1, 2, 6, 5, 1, 8, 8,-1}, 
				{-1,-1, 4, 3, 7, 5, 1, 9, 3, 4, 4, 5, 2, 9, 4, 1, 9, 5, 7, 4, 8,-1,-1}, 
				{ 0,-1, 4, 1, 6, 7, 8, 3, 4, 3, 4, 1, 3, 1, 2, 3, 2, 3, 6, 2, 4,-1, 0}, 
				{ 0,-1, 7, 3, 2, 6, 1, 5, 3, 9, 2, 3, 2, 1, 5, 7, 5, 8, 9, 5, 4,-1, 0}, 
				{ 0,-1,-1, 1, 6, 7, 3, 4, 8, 1, 2, 1, 2, 1, 2, 2, 8, 9, 4, 1,-1,-1, 0}, 
				{ 0, 0,-1, 2, 5, 4, 7, 8, 7, 5, 6, 1, 3, 5, 7, 8, 7, 2, 9, 3,-1, 0, 0}, 
				{ 0, 0,-1,-1, 6, 5, 6, 4, 6, 7, 2, 5, 2, 2, 6, 3, 4, 7, 4,-1,-1, 0, 0}, 
				{ 0, 0, 0,-1,-1, 2, 3, 1, 2, 3, 3, 3, 2, 1, 3, 2, 1, 1,-1,-1, 0, 0, 0}, 
				{ 0, 0, 0, 0,-1,-1,-1, 7, 4, 4, 5, 7, 3, 4, 4, 7,-1,-1,-1, 0, 0, 0, 0}, 
				{ 0, 0, 0, 0, 0, 0,-1,-1,-1,-1, 3, 3, 4,-1,-1,-1,-1, 0, 0, 0, 0, 0, 0}, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		};
	static boolean matrizInicial[][] = {
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}
	};
	
	private static long longitudCamino = 100000;
	private static String recorridoMinimo = "";
	
	
	public void moverseDesde(boolean[][] matrizYa, int fil, int col, String recorrido) {
		
		//System.out.println("Llama la " + fil + ":" + col + " \t " + "valor: " + matrizValores[fil][col]);
		
		mostrarProgreso();
		
		//Si no se había investigado aún la casilla actual
		if (! matrizYa[fil][col]) {
						
			//Construir una nueva matrizYa llamada matriz
			boolean[][] matrizVisitados = new boolean[matrizYa.length][matrizYa[0].length];
			for (int i = 0; i < matrizYa.length; i++)
				for (int j = 0; j < matrizYa[0].length; j++)
					matrizVisitados[i][j] = matrizYa[i][j];
			matrizVisitados[fil][col] = true;
			
			int avance = matrizValores[fil][col];
			
			if (moverseN(matrizVisitados, fil, col, recorrido)) {
				moverseDesde(matrizVisitados, fil-avance, col, recorrido + "(" + fil + "," + col + ")" + avance + " N ->");
			}
			if (moverseNE(matrizVisitados, fil, col, recorrido)) {
				moverseDesde(matrizVisitados, fil-avance, col+avance, recorrido + "(" + fil + "," + col + ")" + avance + " NE ->");
			}
			if (moverseE(matrizVisitados, fil, col, recorrido)) {
				moverseDesde(matrizVisitados, fil, col+avance, recorrido + "(" + fil + "," + col + ")" + avance + " E ->");
			}
			if (moverseSE(matrizVisitados, fil, col, recorrido)) {
				moverseDesde(matrizVisitados, fil+avance, col+avance, recorrido + "(" + fil + "," + col + ")" + avance + " SE ->");
			}
			if (moverseS(matrizVisitados, fil, col, recorrido)) {
				moverseDesde(matrizVisitados, fil+avance, col, recorrido + "(" + fil + "," + col + ")" + avance + " S ->");
			}
			if (moverseSW(matrizVisitados, fil, col, recorrido)) {
				moverseDesde(matrizVisitados, fil+avance, col-avance, recorrido + "(" + fil + "," + col + ")" + avance + " SW ->");
			}
			if (moverseW(matrizVisitados, fil, col, recorrido)) {
				moverseDesde(matrizVisitados, fil, col-avance, recorrido + "(" + fil + "," + col + ")" + avance + " W ->");
			}
			if (moverseNW(matrizVisitados, fil, col, recorrido)) {
				moverseDesde(matrizVisitados, fil-avance, col-avance, recorrido + "(" + fil + "," + col + ")" + avance + " NW ->");
			}
		}
		
	}
	
	private void mostrarProgreso() {
		contador ++;
		if (contador > 999999999) {
			System.out.print(".");
			contador = 0;
			linea ++;
			if (linea > 100) {
				System.out.println();
				linea = 0;
			}
		}		
	}

	public boolean moverseN(boolean[][] matrizYa, int fil, int col, String recorrido) {
		int avance = matrizValores[fil][col];
		
		//Si el destino está ya revisado
		try {
			if (matrizYa[fil-avance][col])
				return false;
		} catch (IndexOutOfBoundsException iobex) {
			return false;
		};
		
		//Revisar que no sale incorrectamente de la senda
		for (int i=fil-1; i>fil-avance; i--) 
			if (matrizValores[i][col] < 0)
				return false;
		
		//Si ha salido correctamente de la senda
		if (matrizValores[fil-avance][col] < 0) {
			if (recorrido.length() - recorrido.replace(">", "").length() < longitudCamino) {
				System.out.println(recorrido + "(" + fil + "," + col + ")" + avance + " N" + " ¡FIN!");
				longitudCamino = recorrido.length() - recorrido.replace(">", "").length();
			}
			return false;
		}
		
		//Si no ha tenido motivos para salir
		return true;
	}
	
	public boolean moverseNE(boolean[][] matrizYa, int fil, int col, String recorrido) {
		int avance = matrizValores[fil][col];
		
		//Si el destino está ya revisado
		try {
			if (matrizYa[fil-avance][col+avance])
				return false;
		} catch (IndexOutOfBoundsException iobex) {
			return false;
		};
		
		//Revisar que no sale incorrectamente de la senda
		for (int i=fil-1; i>fil-avance; i--) 
			for (int j=col+1; j<col+avance; j++)
				if (matrizValores[i][j] < 0)
					return false;
		
		//Si ha salido correctamente de la senda
		if (matrizValores[fil-avance][col+avance] < 0) {
			if (recorrido.length() - recorrido.replace(">", "").length() < longitudCamino) {
				System.out.println(recorrido + "(" + fil + "," + col + ")" + avance + " NE" + " ¡FIN!");
				longitudCamino = recorrido.length() - recorrido.replace(">", "").length();
			}
			return false;
		}
		
		//Si no ha tenido motivos para salir
		return true;
	}

	public boolean moverseE(boolean[][] matrizYa, int fil, int col, String recorrido) {
		int avance = matrizValores[fil][col];
		
		//Si el destino está ya revisado
		try {
			if (matrizYa[fil][col+avance])
				return false;
		} catch (IndexOutOfBoundsException iobex) {
			return false;
		};
			
		//Revisar que no sale incorrectamente de la senda
		for (int j=col+1; j<col+avance; j++)
			if (matrizValores[fil][j] < 0)
				return false;
	
		//Si ha salido correctamente de la senda
		if (matrizValores[fil][col+avance] < 0) {
			if (recorrido.length() - recorrido.replace(">", "").length() < longitudCamino) {
				System.out.println(recorrido + "(" + fil + "," + col + ")" + avance + " E" + " ¡FIN!");
				longitudCamino = recorrido.length() - recorrido.replace(">", "").length();
			}
			return false;
		}
		
		//Si no ha tenido motivos para salir
		return true;
	}

	public boolean moverseSE(boolean[][] matrizYa, int fil, int col, String recorrido) {
		int avance = matrizValores[fil][col];
		
		//Si el destino está ya revisado
		try {
			if (matrizYa[fil+avance][col+avance])
				return false;
		} catch (IndexOutOfBoundsException iobex) {
			return false;
		};
		
		//Revisar que no sale incorrectamente de la senda
		for (int i=fil+1; i<fil+avance; i++) 
			for (int j=col+1; j<col+avance; j++)
				if (matrizValores[i][j] < 0)
					return false;
		
		//Si ha salido correctamente de la senda
		if (matrizValores[fil+avance][col+avance] < 0) {
			if (recorrido.length() - recorrido.replace(">", "").length() < longitudCamino) {
				System.out.println(recorrido + "(" + fil + "," + col + ")" + avance + " SE" + " ¡FIN!");
				longitudCamino = recorrido.length() - recorrido.replace(">", "").length();
			}
			return false;
		}
		
		//Si no ha tenido motivos para salir
		return true;
	}
	
	public boolean moverseS(boolean[][] matrizYa, int fil, int col, String recorrido) {
		int avance = matrizValores[fil][col];
		
		//Si el destino está ya revisado
		try {
			if (matrizYa[fil+avance][col])
				return false;
		} catch (IndexOutOfBoundsException iobex) {
			return false;
		};
		
		//Revisar que no sale incorrectamente de la senda
		for (int i=fil+1; i<fil+avance; i++) 
			if (matrizValores[i][col] < 0)
				return false;
		
		//Si ha salido correctamente de la senda
		if (matrizValores[fil+avance][col] < 0) {
			if (recorrido.length() - recorrido.replace(">", "").length() < longitudCamino) {
				System.out.println(recorrido + "(" + fil + "," + col + ")" + avance + " S" + " ¡FIN!");
				longitudCamino = recorrido.length() - recorrido.replace(">", "").length();
			}
			return false;
		}
		
		//Si no ha tenido motivos para salir
		return true;
	}
	
	public boolean moverseSW(boolean[][] matrizYa, int fil, int col, String recorrido) {
		int avance = matrizValores[fil][col];
		
		//Si el destino está ya revisado
		try {
			if (matrizYa[fil+avance][col-avance])
				return false;
		} catch (IndexOutOfBoundsException iobex) {
			return false;
		};
			
		//Revisar que no sale incorrectamente de la senda
		for (int i=fil+1; i<fil+avance; i++) 
			for (int j=col-1; j>col-avance; j--)
				if (matrizValores[i][j] < 0)
					return false;
		
		//Si ha salido correctamente de la senda
		if (matrizValores[fil+avance][col-avance] < 0) {
			if (recorrido.length() - recorrido.replace(">", "").length() < longitudCamino) {
				System.out.println(recorrido + "(" + fil + "," + col + ")" + avance + " SW" + " ¡FIN!");
				longitudCamino = recorrido.length() - recorrido.replace(">", "").length();
			}
			return false;
		}
		
		//Si no ha tenido motivos para salir
		return true;
	}

	public boolean moverseW(boolean[][] matrizYa, int fil, int col, String recorrido) {
		int avance = matrizValores[fil][col];
		
		//Si el destino está ya revisado
		try {
			if (matrizYa[fil][col-avance])
				return false;
		} catch (IndexOutOfBoundsException iobex) {
			return false;
		};
		
		//Revisar que no sale incorrectamente de la senda
		for (int j=col-1; j>col-avance; j--)
			if (matrizValores[fil][j] < 0)
				return false;
		
		//Si ha salido correctamente de la senda
		if (matrizValores[fil][col-avance] < 0) {
			if (recorrido.length() - recorrido.replace(">", "").length() < longitudCamino) {
				System.out.println(recorrido + "(" + fil + "," + col + ")" + avance + " W" + " ¡FIN!");
				longitudCamino = recorrido.length() - recorrido.replace(">", "").length();
			}
			return false;
		}
		
		//Si no ha tenido motivos para salir
		return true;
	}

	public boolean moverseNW(boolean[][] matrizYa, int fil, int col, String recorrido) {
		int avance = matrizValores[fil][col];
		
		//Si el destino está ya revisado
		try { 
			if (matrizYa[fil-avance][col-avance])
				return false;
		} catch (IndexOutOfBoundsException iobex) {
			return false;
		};	
		
		//Revisar que no sale incorrectamente de la senda
		for (int i=fil-1; i>fil-avance; i--) 
			for (int j=col-1; j>col-avance; j--)
				if (matrizValores[i][j] < 0)
					return false;
		
		//Si ha salido correctamente de la senda
		if (matrizValores[fil-avance][col-avance] < 0) {
			if (recorrido.length() - recorrido.replace(">", "").length() < longitudCamino) {
				System.out.println(recorrido + "(" + fil + "," + col + ")" + avance + " NW" + " ¡FIN!");
				longitudCamino = recorrido.length() - recorrido.replace(">", "").length();
			}
			return false;
		}
		
		//Si no ha tenido motivos para salir
		return true;
	}
	

	public static void main(String[] args) {
		System.out.println("************************************************************************");
		System.out.println("**                       INICIO SENDA SOLVER                          **");
		System.out.println("************************************************************************");
		
		//boolean[][] matrizInicial = null;
				
		Sendasolver sendaSolver = new Sendasolver();
		sendaSolver.moverseDesde(matrizInicial, FIL_CENTRO, COL_CENTRO, "Camino: ");
		
		System.out.println("Recorrido mínimo (" + longitudCamino + "): " + recorridoMinimo);
		
		System.out.println("************************************************************************");
	}

}
