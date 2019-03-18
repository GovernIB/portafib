package es.caib.portafib.applet.standalone;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.StringReader;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;

import es.caib.portafib.applet.SignApplet;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class JnlpApp {

  /**
   * 
   * @param args
   */
  public static void main(String[] args) {
    try {
      Properties prop = new Properties();

      {

        StringBuffer str = new StringBuffer();

        final String newline = System.getProperty("line.separator");

        for (String arg : args) {
          str.append(arg).append(newline);
        }

        prop.load(new StringReader(str.toString()));

      }

      SignApplet applet = new SignApplet(new StandaloneSignerContext(prop));
      applet.init();

      // TODO Emprar applet.setStub(stub); enloc de SignerContext

      JFrame frame = new JFrame("Applet de Firma 2.0");

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JRootPane panell = frame.getRootPane();

      panell.setLayout(new BorderLayout());
      panell.add(applet, BorderLayout.CENTER);

      applet.start();

      final int WIDTH = 475;
      final int HEIGHT = 300;
      applet.setSize(WIDTH, HEIGHT);
      applet.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      applet.setMinimumSize(new Dimension(WIDTH, HEIGHT));

      // Get the size of the screen
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

      frame.pack();

      // Determine the new location of the window
      int w = frame.getSize().width;
      int h = frame.getSize().height;
      int x = (dim.width - w) / 2;
      int y = (dim.height - h) / 2;

      // Move the window
      frame.setLocation(x, y);

      frame.setVisible(true);
    } catch (Throwable e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      System.exit(-1);
    }
  }

}
