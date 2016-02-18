package com.melascuco.faketrafficevents.JAX;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.melascuco.faketrafficevents.data.Coordinate;
import com.melascuco.faketrafficevents.data.TrafficEvent;

//       http://localhost:8080/TrafficEventsFake/rest/trafficEvents?lat=45.9989&long=3.221445&r=20

@Path("trafficEvents")
public class TrafficEvents {

	private final static Logger LOGGER = Logger.getLogger(TrafficEvents.class.getName());
	
	@GET
	//@Produces(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	//public GenericEntity<List<TrafficEvent>> getEvents(@QueryParam("lat") String lat, @QueryParam("long") String log, @QueryParam("r") String radius) {
	public Response getEvents(@QueryParam("lat") Double lat, @QueryParam("long") Double log, @QueryParam("r") int radius) {
		List<TrafficEvent> trafficEvents = new ArrayList<TrafficEvent>();
		/*//PRUEBA A PIÑÓN
		TrafficEvent te1 = new TrafficEvent();
		Coordinate coord1 = new Coordinate();
		coord1.setLat(40.12333);
		coord1.setLog(3.54544);
		te1.setCoordinate(coord1);
		te1.setDescription("Aglomeración grande de camiones");
		te1.setDurationMins(120);
		te1.setSeverity(5);
		te1.setTime(new Date());
		te1.setTitle("AGLOMERACIÓN");
		
		TrafficEvent te2 = new TrafficEvent();
		Coordinate coord2 = new Coordinate();
		coord2.setLat(40.12482);
		coord2.setLog(3.54569);
		te2.setCoordinate(coord2);
		te2.setDescription("Obras en el carril izquierdo");
		te2.setDurationMins(120);
		te2.setSeverity(5);
		te2.setTime(new Date());
		te2.setTitle("OBRAS");
		
		trafficEvents.add(te1);
		trafficEvents.add(te2);
		*/
		
		/*
		 * ALEATORIO EN UN RANGO
		 *  int cantidadNumsRango = 2;
		 *  int terminoInicialRango = -1;
		 *  int aleatorioEnRango = (int) (rnd.nextDouble() * cantidadNumsRango + terminoInicialRango);
		 *  double aleatorioEnRango = (rnd.nextDouble() * cantidadNumsRango + terminoInicialRango);
		 */
		Random rnd = new Random();
		
		int cuantosEventos = (int) (rnd.nextDouble() * (2 + (radius/10)));
		//int cuantosEventos = (int) (rnd.nextDouble()*10) ;
		
		LOGGER.info("Cuantos eventos en " + lat+","+log + " con radio " + radius + "kms " + ": " + cuantosEventos);
		
		for (int i=0; i<cuantosEventos; i++) {
			TrafficEvent te = new TrafficEvent();
			Coordinate coord = new Coordinate();
			coord.setLat(lat + ((rnd.nextDouble() * 2 + (-1))/10));
			coord.setLog(log + ((rnd.nextDouble() * 2 + (-1))/10));
			te.setCoordinate(coord);
			te.setDescription("Descripción " + i + ". Esto provocará bastante jaleo.");
			te.setDurationMins(120);
			te.setSeverity(5);
			te.setTime(new Date());
			te.setTitle("TÍTULO DEL EVENTO " + i + ".");
			LOGGER.info("Evento insertado " + i + ": " + te.toString());
			trafficEvents.add(te);
		}
		
		//return new GenericEntity<List<TrafficEvent>>(trafficEvents) {};
		return Response.ok(new GenericEntity<List<TrafficEvent>>(trafficEvents) {}).build();
	}
	
}
