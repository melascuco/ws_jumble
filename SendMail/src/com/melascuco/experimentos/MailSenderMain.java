package com.melascuco.experimentos;

public class MailSenderMain {

	public static void main(String[] args) {
		System.out.println("INICIO ENV�A EMAIL");
		
		try {
			MailSender.Send("tugozoenunpozo@hotmail.com", "Texto del correo");
			System.out.println("Todo correcto");
		} catch (Exception e) {
			System.out.println("Ha habido un error, veamos a ver qu� ha pasado.");
			e.printStackTrace();
		}
		
		System.out.println("*FIN ENV�A EMAIL*");
	}

}
