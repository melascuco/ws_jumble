package com.melascuco.jumble.probabilidadesEn3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * En un bombo con 100 bolas numeradas. 
 * Sacando 3, que probabilidad hay de que salgan en orden ascendente, la siguiente más alta que la anterior?
 * 
 * @author melascuco
 *
 */
public class TresBolas {

	private static final int BOLAS = 100;
	private static final int REPETICIONES = 9999;
	private static final int EXTRACCIONES = 3;
	
	public TresBolas() {
		boolean[] bolas = new boolean[BOLAS];
		Integer[] extraidas;
		int casosExito = 0;
		
		for (int experimento = 1; experimento <= REPETICIONES; experimento++) {
			System.out.print(String.format("Experimento %0" + Integer.toString(REPETICIONES).length() +"d: ", experimento));
			extraidas = new Integer[EXTRACCIONES];
			for (int extraccion = 0; extraccion < EXTRACCIONES; extraccion++) {
				extraidas[extraccion] = randomBetweenExcluding(1, BOLAS, extraidas);
				System.out.print(String.format("%0" + Integer.toString(BOLAS).length() +"d", extraidas[extraccion]) + " ");
			}
			if (isArrayOrdenAscendente(extraidas)) {
				casosExito++;
				System.out.println(" ¡CUMPLE!");
			} else {
				System.out.println("");
			}
		}		
		System.out.println("Porcentaje de éxito: " + (100 * casosExito / REPETICIONES) + "%");
	}
	
	private int randomBetweenExcluding(final int min, final int max, final Integer[] excluding) {
		int randomNum;
		Set<Integer> excludingSet = new HashSet<Integer>(Arrays.asList(excluding));
		do {
		     randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		} while (excludingSet.contains(randomNum));
		return randomNum;
	}
	
	private boolean isArrayOrdenAscendente(final Integer[] array) {
		boolean ordenAscendente = true;
		if (array.length > 0) {
			int valorPrevio = array[0];
			for (int i = 1; i < array.length; i++) {
				if (valorPrevio > array[i]) {
					ordenAscendente = false;
				}
				valorPrevio = array[i];
			}
		}		
		return ordenAscendente;
	}
	
	public static void main(String[] args) {
		System.out.println(" *** INICIO ***");
		new TresBolas();
		System.out.println(" *** FIN ***");
	}

}
