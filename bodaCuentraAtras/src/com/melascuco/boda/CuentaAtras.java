package com.melascuco.boda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

public class CuentaAtras {

  final static String FECHA_OBJETIVO = "29/08/2015";
  final static Logger log = Logger.getLogger(CuentaAtras.class.getName());
  final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 

  
  
  public CuentaAtras() throws ParseException {
    log.info("Inicio log");
    //diferencia = ( fechaAnterior.getTime() - fechaPosterior.getTime() )/ MILLSECS_PER_DAY;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date dateObjetivo = sdf.parse(FECHA_OBJETIVO); 
    log.info("Día objetivo: " + dateObjetivo);
    
    Calendar calendarObjetivo = new GregorianCalendar();
    calendarObjetivo.setTime(dateObjetivo);
    log.info("Calendar objetivo: " + sdf.format(calendarObjetivo.getTime()));
    
    Calendar calendarToday = GregorianCalendar.getInstance();
    log.info("Calendar today: " + sdf.format(calendarToday.getTime()));

    int i = 0;
    long diasDiferencia = (calendarObjetivo.getTime().getTime() - calendarToday.getTime().getTime()) / MILLSECS_PER_DAY;
    for (Calendar calendarCounter = calendarToday; calendarCounter.before(calendarObjetivo); calendarCounter.add(Calendar.DAY_OF_MONTH, 1)) {
      log.info("" + (diasDiferencia - i++ + 1) + " días faltan el " + sdf.format(calendarCounter.getTime()));
    }
    
    
  }



  public static void main(String[] args) throws ParseException {
    System.out.println("Inicio cuenta atrás: " + FECHA_OBJETIVO);
    new CuentaAtras();    
    System.out.println("Fin.");
  }

}
