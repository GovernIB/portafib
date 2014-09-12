package es.caib.portafib.applet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 * 
 */
public class PanelSign extends BasePanel implements Runnable {
  
  List<ProgressPanel> progressPanels = new ArrayList<ProgressPanel>();

  /**
   * @param signaturaProperties
   * @param signer
   */
  public PanelSign(ParentPanel parentPanel) {
    super(parentPanel);

    init();
  }

  public void init() {
    List<ProcessDeFirma> processos = parentPanel.getFirmes();

    JPanel allFirmes = new JPanel();
    //allFirmes.setLayout(new GridLayout(processos.size(), 1));
    //allFirmes.setLayout(new BoxLayout(allFirmes, BoxLayout.PAGE_AXIS));

    allFirmes.setLayout(new GridBagLayout());
    
    allFirmes.setBackground(Color.white);
    allFirmes.setAlignmentY(TOP_ALIGNMENT);
    allFirmes.setBorder(null);

    int y = 0;
    for (ProcessDeFirma processDeFirma : processos) {
      ProgressPanel pp = new ProgressPanel(processDeFirma);
      progressPanels.add(pp);

      GridBagConstraints c = new GridBagConstraints();      
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = y;
      c.gridwidth = 3;
      c.weightx = 1.0;
      c.anchor = GridBagConstraints.NORTH;
      y++;
      
      allFirmes.add(pp, c);
    }

    JScrollPane scroll = new JScrollPane(allFirmes);
    scroll.setBorder(null);
    
    this.setLayout(new GridLayout());
    this.add(scroll);

  }

  @Override
  public boolean next() {
    return false;
  }

  @Override
  public void preShow() throws Exception {
    parentPanel.hideNextButton();
    Thread t = new Thread(this);
    t.start();
    
  }

  public void run() {

    System.out.println("Arranca thread de PanelSign");

    int finalitzats;
    int errors;
    do {
      // TODO millorar l'eficiència

      finalitzats = 0;
      errors = 0;
      for (ProgressPanel pp : this.progressPanels) {

        ProcessDeFirma pdf = pp.getProcessFirma();
        
        pp.setEstat(tradueix(pdf.getEstat().code));

        switch (pdf.getEstat()) {

        case FINAL:
          finalitzats++;
          pp.setImageEstat(IconEstat.FINAL);
          break;

        case ERROR:
          pp.setErrorButton();          
          errors++;
          break;

        case NO_INICIAT:
        case LLEGINT_PDF:
        case ESPERA_SELECCIO_CERTIFICAT:
        case FIRMANT:
        case GUARDANT_PDF:
          try {
            Thread.sleep(250);
          } catch (InterruptedException e) {
          }
        }

      }

    } while ((finalitzats + errors) != this.progressPanels.size());

    if (errors == 0) {
      String jump = parentPanel.signerContext.getContextParameter(Constants.APPLET_REDIRECT);
      jump = parentPanel.signerContext.checkURL(jump);
      if (jump != null) {
        try {
          parentPanel.signerContext.showURL(new URL(jump));

        } catch (MalformedURLException e) {
          e.printStackTrace();
        }
      } else {
        JOptionPane.showMessageDialog(null, tradueix("firmes.ok"), tradueix("informacio"),
            JOptionPane.INFORMATION_MESSAGE);
      }
    } else {
      JOptionPane.showMessageDialog(null, tradueix("firmes.error"),
          tradueix("error"), JOptionPane.ERROR_MESSAGE);
    }
    


  }
  
  public enum IconEstat {
    ESPERA("wait.gif"),FINAL("success.gif"), ERROR("error.gif");
    
    String file;
    
    IconEstat(String file) {
      this.file = file;
    }
  }

  public class ProgressPanel extends JPanel {

    JProgressBar lectura;

    JProgressBar escriptura;

    final ProcessDeFirma processFirma;

    PortaFIBAppletException error = null;

    JButton botoFinal = null;

    JLabel estat = new JLabel();
    
    
    JLabel estatIcon = new JLabel();

    public ProgressPanel(ProcessDeFirma processFirma) {
      this.processFirma = processFirma;
      this.setBackground(Color.white);
      
      Border border = BorderFactory.createCompoundBorder(
          BorderFactory.createEmptyBorder(2, 4, 1, 4),
          BorderFactory.createLineBorder(Color.black));
      
      lectura = new JProgressBar(processFirma.getLecturaProgress());
      lectura.setStringPainted(true);
      lectura.setString(tradueix("lectura"));      
      lectura.setBackground(Color.white);      
      lectura.setBorder(border);

      escriptura = new JProgressBar(processFirma.getEscripturaProgress());
      escriptura.setStringPainted(true);
      escriptura.setString(tradueix("firmaescriptura"));      
      escriptura.setBackground(Color.white);
      escriptura.setBorder(border);

      JPanel allProgress = new JPanel();
      allProgress.setBackground(Color.white);
      allProgress.setLayout(new BoxLayout(allProgress, BoxLayout.PAGE_AXIS));
      allProgress.add(lectura);
      allProgress.add(escriptura);
      
      Dimension d = new Dimension(60, 16);
      estat.setPreferredSize(d);
      estat.setMaximumSize(d);

      JPanel panelEstat = new JPanel();
      panelEstat.setLayout(new BoxLayout(panelEstat, BoxLayout.PAGE_AXIS));
      panelEstat.add(estat);
      panelEstat.setBackground(Color.white);

      this.setBorder(BorderFactory.createTitledBorder(processFirma.getIdName()));
      this.setLayout(new BorderLayout(4, 2));
      this.add(allProgress, BorderLayout.CENTER);
      this.add(panelEstat, BorderLayout.EAST);
      
      setImageEstat(IconEstat.ESPERA);
      this.add(this.estatIcon, BorderLayout.WEST);
      
    }
    
    
    
    public void setImageEstat(IconEstat estat) {      
      this.estatIcon.setIcon(new ImageIcon(this.getClass().getResource(estat.file)));
    }
    
    

    public void setErrorButton() {
      if (this.error == null) {
        PortaFIBAppletException excepcio = this.processFirma.getError();

        JButton botoError = new JButton(new ImageIcon(this.getClass().getResource(IconEstat.ERROR.file)));

        botoError.setToolTipText(excepcio.getMessage());

        botoError.addActionListener(new ActionListener() {

          public void actionPerformed(ActionEvent e) {
            
            boolean showStackTrace = false;
            
            while(true) {
            PanelError pe = new PanelError(showStackTrace);
            
            Object[] options;
            if (showStackTrace) {
              options = new Object[] { tradueix("acceptar") };
            } else {
              options = new Object[] {
                tradueix("acceptar"),
                tradueix("mostrardetallserror")
              };
            }
            
            pe.init(error, parentPanel.signerContext);
            int result = JOptionPane.showOptionDialog(null, pe, tradueix("error"),
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE,
                null, options, options[0]);
            
            System.out.println("result = " + result);
            if (result == 0) {
              break;
            }
            showStackTrace = true;
          }

          }
        });

        error = excepcio;

        /*
        JPanel panelEstat = new JPanel();
        panelEstat.setLayout(new BoxLayout(panelEstat, BoxLayout.PAGE_AXIS));
        panelEstat.add(botoError);
        */

        this.estatIcon.setVisible(false); 
        
        add(botoError, BorderLayout.WEST);
        

        this.repaint();
      }
    }

    public void setEstat(String estat) {
      this.estat.setText(estat);
      this.estat.repaint();
      this.repaint();
    }

    public ProcessDeFirma getProcessFirma() {
      return processFirma;
    }

  }

}
