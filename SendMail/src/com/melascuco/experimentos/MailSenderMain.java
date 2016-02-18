package com.melascuco.experimentos;

public class MailSenderMain {

	public static void main(String[] args) {
		System.out.println("INICIO ENVÍA EMAIL");
		
		try {
			MailSender.Send("tugozoenunpozo@hotmail.com", "Texto del correo");
			System.out.println("Todo correcto");
		} catch (Exception e) {
			System.out.println("Ha habido un error, veamos a ver qué ha pasado.");
			e.printStackTrace();
		}
		
		System.out.println("*FIN ENVÍA EMAIL*");
	}

}
