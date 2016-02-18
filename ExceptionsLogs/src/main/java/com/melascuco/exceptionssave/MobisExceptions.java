package com.melascuco.exceptionssave;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class MobisExceptions extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  
  static Logger logger = Logger.getLogger(MobisExceptions.class);

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   
    String token = req.getParameter("token");
    if ("4to5".equalsIgnoreCase(token)) {
      String mac = req.getParameter("mac");
      String message = req.getParameter("message");
      logger.info("MAC: " + mac + " ::: " + message);
    }
      

  }
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    doGet(req,res);
  }
}
