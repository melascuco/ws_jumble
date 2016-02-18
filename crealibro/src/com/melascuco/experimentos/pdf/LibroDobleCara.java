package com.melascuco.experimentos.pdf;

import java.io.FileOutputStream;
import java.util.logging.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;

//Prepara un pdf para ser imprimido a doble cara a dos páginas por hoja para doblar y convertirlo en libro.
//Preparar previamente páginas pares (múltiplo de 4) o si no el programa añade páginas en blanco al final

public class LibroDobleCara {
  final static String SUFIJO_FICHERO = "-LIBRO.pdf";
  private Logger log = Logger.getGlobal();

  public LibroDobleCara(String arg) {
    System.out.println("Iniciando...");
    log.info("Crea libro iniciando");

    try {
      String inFile = arg;
      log.info("--> Reading " + inFile);
      PdfReader reader = new PdfReader(inFile);
      int nOriginal = reader.getNumberOfPages();
      log.info("--> Number of pages : " + nOriginal);

      String outFile = inFile.substring(0, inFile.indexOf(".pdf")) + SUFIJO_FICHERO;
      log.info("Writing " + outFile);
      Document document = new Document(reader.getPageSizeWithRotation(nOriginal));
      PdfCopy writer = new PdfCopy(document, new FileOutputStream(outFile));
      document.open();

      int nPages = nOriginal;
      if (nPages % 4 != 0) {
        log.info("Se aumentará el número de páginas para que sea múltiplo de 4 porque " + nPages + " no es múltiplo de 4");
        for (; nPages % 4 != 0; nPages++) {
          log.info("   --> Página en blanco será añadida al final. Number of pages: " + (nPages + 1));
        }
      }

      int semilla = nPages / 2;
      int modificador = semilla + 1;
      int conteoPaginas = 0;
      while (semilla > 1) {

        PdfImportedPage page1 = writer.getImportedPage(reader, semilla);
        writer.addPage(page1);
        log.info("añadida página " + ++conteoPaginas + " a partir de " + semilla);
        if (modificador <= nOriginal) {
          PdfImportedPage page2 = writer.getImportedPage(reader, modificador);
          writer.addPage(page2);
          log.info("añadida página " + ++conteoPaginas + " a partir de " + (modificador));
        } else {
          // Inserta página en blanco si fuera necesario
          writer.addPage(reader.getPageSizeWithRotation(nOriginal), 0);
          log.info("añadida página " + ++conteoPaginas + " vacía");
        }
        if (modificador + 1 <= nOriginal) {
          PdfImportedPage page3 = writer.getImportedPage(reader, modificador + 1);
          writer.addPage(page3);
          log.info("añadida página " + ++conteoPaginas + " a partir de " + (modificador + 1));
        } else {
          // Inserta página en blanco si fuera necesario
          writer.addPage(reader.getPageSizeWithRotation(nOriginal), 0);
          log.info("añadida página " + ++conteoPaginas + " vacía");
        }
        PdfImportedPage page4 = writer.getImportedPage(reader, semilla - 1);
        writer.addPage(page4);
        log.info("añadida página " + ++conteoPaginas + " a partir de " + (semilla - 1));

        semilla = semilla - 2;
        modificador = modificador + 2;
      }
      document.close();
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    log.info("Crea libro finalizado");
  }

  public static void main(String[] args) {
    if (args.length < 1)
      System.out.println("Introduce el nombre del fichero pdf a convertir como argumento.");
    else {
      System.out.println("LibroDobleCara: " + args[0]);
      new LibroDobleCara(args[0]);
    }
    System.out.println("Fin del programa.");
  }

}
