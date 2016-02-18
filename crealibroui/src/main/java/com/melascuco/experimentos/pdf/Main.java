package com.melascuco.experimentos.pdf;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Logger;

public class Main {
  
  private Logger log = Logger.getGlobal();

  private JFrame frmCreaLibro;
  private JTextField textFieldOrigen;
  private JTextField textFieldDestino;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Main window = new Main();
          window.frmCreaLibro.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public Main() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmCreaLibro = new JFrame();
    frmCreaLibro.setTitle("CREA LIBRO");
    frmCreaLibro.setBounds(100, 100, 900, 150);
    frmCreaLibro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmCreaLibro.getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][]"));
    
    JLabel lblEligeUnPdf = new JLabel("Elige un PDF y obtendrás una copia formateada para imprimir a dos caras por hoja a doble página, es decir, en FORMATO LIBRO");
    frmCreaLibro.getContentPane().add(lblEligeUnPdf, "cell 1 0");
    
    JButton btnFicheroOrigen = new JButton("Fichero origen");
    frmCreaLibro.getContentPane().add(btnFicheroOrigen, "cell 0 1");
        
    textFieldOrigen = new JTextField();
    textFieldOrigen.setEditable(false);
    frmCreaLibro.getContentPane().add(textFieldOrigen, "cell 1 1,growx");
    textFieldOrigen.setColumns(10);
    
    JLabel lblFicheroDestino = new JLabel("Fichero destino: ");
    frmCreaLibro.getContentPane().add(lblFicheroDestino, "cell 0 2,alignx trailing");
    
    textFieldDestino = new JTextField();
    frmCreaLibro.getContentPane().add(textFieldDestino, "cell 1 2,growx");
    textFieldDestino.setColumns(10);
    
    btnFicheroOrigen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        //fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF", "pdf"));
        int result = fileChooser.showOpenDialog(frmCreaLibro);
        if (result == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          textFieldOrigen.setText(selectedFile.getAbsolutePath());
          textFieldDestino.setText(selectedFile.getAbsolutePath().substring(0,selectedFile.getAbsolutePath().length()-4) + "_book.pdf");
        }
      }
    });
    
    JButton btnCrearLibro = new JButton("Crear libro!");
    frmCreaLibro.getContentPane().add(btnCrearLibro, "flowx,cell 1 3");
    
    final JLabel label = new JLabel("");
    frmCreaLibro.getContentPane().add(label, "cell 1 3");
    
    btnCrearLibro.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        log.info("Iniciando...");
        log.info("Crea libro iniciando");
        try {
          log.info("--> Reading " + textFieldOrigen.getText());
          PdfReader reader = new PdfReader(textFieldOrigen.getText());
          int nOriginal = reader.getNumberOfPages();
          log.info("--> Number of pages : " + nOriginal);

          String outFile = textFieldDestino.getText();
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
        } catch (Exception ex) {
          ex.printStackTrace();
          label.setText("Crea libro falló");
        }
        log.info("Crea libro finalizado");
        label.setText("Crea libro finalizado");
      }
    });
  }

}
