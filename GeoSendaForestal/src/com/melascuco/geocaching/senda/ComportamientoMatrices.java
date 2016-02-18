package com.melascuco.geocaching.senda;

public class ComportamientoMatrices {

	int matrizValores[][] = {
			{ 0, 0}, 
			{ 0, 0},
	};
	boolean matrizInicial[][] = {
		{false,false},
		{false,false}
	};

	
	
	public ComportamientoMatrices() {
		cambiaValores(matrizValores);
		cambiaBooleanos(matrizInicial);
		
		System.out.println(matrizValores[0][0] + " " + matrizValores[0][1]);
		System.out.println(matrizValores[1][0] + " " + matrizValores[1][1]);

		System.out.println(matrizInicial[0][0] + " " + matrizInicial[0][1]);
		System.out.println(matrizInicial[1][0] + " " + matrizInicial[1][1]);
	}

	public void cambiaValores(int[][] matriz) {
		matriz[0][0] = 8;
	}
	
	public void cambiaBooleanos(boolean[][] matriz) {
		matriz[0][0] = true;
	}


	public static void main(String[] args) {
		System.out.println(">>>> INICIO");

		new ComportamientoMatrices();
		
		System.out.println("<<<< FIN.");
	}

}
