package es.caib.portafib.back.controller.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLDecoder;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.web.i18n.I18NDateTimeFormat;
import org.fundaciobit.plugins.utils.CertificateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import es.caib.portafib.logic.utils.I18NLogicUtils;

/**
 * 
 * @author anadal
 *
 */
@Controller
public class RubricaController {
  
  protected static Logger log = Logger.getLogger(RubricaController.class);

  
  //@RequestMapping(value = "/common/rubrica/{params:.+}")
  @RequestMapping(value = "/common/rubrica/**")
  public void generaRubrica(
      //@PathVariable("params") String params,
      @RequestParam("certificateFile") MultipartFile file,
      HttpServletRequest request,HttpServletResponse response) throws IOException {
    
    String params = (String) request.getAttribute(
        HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
    
    
    log.info("Params = " + params);
    
    if (file.isEmpty()) {
      log.error("Certificat no s'ha enviat !!!!");
      response.sendError(HttpServletResponse.SC_NO_CONTENT);
      return;
    }
    
    try {
    
      X509Certificate cert = CertificateUtils.decodeCertificate(file.getInputStream());
      
      log.info("Rubrica Certificat CN = " + CertificateUtils.getCN(cert));
      log.info(" Rubrica Certificat DNI = " + CertificateUtils.getDNI(cert));
  
    
      String dec = URLDecoder.decode(params, "UTF-8"); // , 
      log.info(" Params DEC = " + dec);
      dec = dec.replace('|', '/');
      log.info("Params DEC = " + dec);
      
      Properties prop = new Properties();
      Reader reader = new InputStreamReader(new ByteArrayInputStream(dec.getBytes()), "UTF-8");
      prop.load(reader);

      int ample = Integer.parseInt(prop.getProperty("ample"));
      int altura = Integer.parseInt(prop.getProperty("altura"));
      // TODO No s'han d'enviar els labels
      String firmatPerLabel = prop.getProperty("firmatPerLabel");
      String firmatPer = prop.getProperty("firmatPer");
      // TODO No s'han d'enviar els labels
      String dataLabel = prop.getProperty("dataLabel");
      String data = prop.getProperty("data");
      // TODO No s'han d'enviar els labels
      String motiuLabel = prop.getProperty("motiuLabel");
      String motiu = prop.getProperty("motiu");
    
      
      log.info(" ample:" + ample);
      log.info(" altura:" + altura);
      log.info(" firmatPerLabel:" + firmatPerLabel);
      log.info(" firmatPer:" + firmatPer);
      log.info(" dataLabel:" + dataLabel);
      log.info(" data:" + data);
      log.info(" motiuLabel:" + motiuLabel);
      log.info(" motiu:" + motiu);
      
      byte[] image = getImage((float)ample, (float)altura, firmatPerLabel,
          firmatPer, dataLabel, data, motiuLabel, motiu);
      
      response.getOutputStream().write(image);
      
      
    } catch (Exception e) {
      log.error("Error generant rubrica: " + e.getMessage(), e);
      response.sendError(HttpServletResponse.SC_NO_CONTENT);
    }
    
    
  }
  
  // TODO XYZ Moure a logic
  public static  byte[] getImage(X509Certificate cert, Locale locale, float ample, float altura,
      String firmatPerFormat, Date data, String motiu) throws Exception {
    
    
    String firmatPer = getFirmatPer(firmatPerFormat, cert);
    
    I18NDateTimeFormat t = new I18NDateTimeFormat();
    SimpleDateFormat sdf = t.getSimpleDateFormat(locale);
    
    return getImage(ample,altura,
        I18NLogicUtils.tradueix(locale, "rubric.firmatper") + ": ",firmatPer,
        I18NLogicUtils.tradueix(locale, "rubric.data") + ": ", sdf.format(data),
        I18NLogicUtils.tradueix(locale, "rubric.motiu") + ": ", motiu);
    
    
  }
  
  
//TODO XYZ Moure a logic  
  /**
   * 
   * {0} = NOM
   * {1} = LONGITUD NIF
   * {2} = NIF
   * {3} = EMISSOR
   * {4} = LONGITUD CARREC_CERTIFICAT
   * {5} = CARREC_CERTIFICAT
   * {6} = LONGITUD UNITAT_ADMINISTRATIVA
   * {7} = UNITAT_ADMINISTRATIVA
   * @param bundleSign
   * @param cert
   * @param certName
   * @return
   */
  protected static String getFirmatPer(String firmatPerFormat, X509Certificate cert) {
    
      String certName = CertificateUtils.getCN(cert);
    
    
      // Parche pels certificats FNMT que contenen la paraula NOMBRE al principi i el NIF del Firmant
      if (certName.startsWith("NOMBRE ")) {
        certName = certName.substring("NOMBRE ".length());
      }
      final int posNIF = certName.indexOf(" - NIF ");
      if (posNIF != -1) {
        certName = certName.substring(0, posNIF);
      }
      // Parche pels certificat DNIe (eliminar FIRMA i AUTENTICACION)
      final String[] dnie = { " (FIRMA)", " (AUTENTICACIÓN)" };
      for (String tipusDNIe : dnie) {
        int pos = certName.indexOf(tipusDNIe);
        if (pos != -1) {
          // Eliminar tipus
          certName = certName.replace(tipusDNIe, "");
          // Posar Nom davant
          pos = certName.lastIndexOf(',');
          if (pos != -1) {
            String nom = certName.substring(pos + 1).trim();
            String llinatges = certName.substring(0, pos).trim();
            certName = nom + " " + llinatges;
          }
        }
      }
    
    
    
      log.info("XYZ CertName final = " + certName);
      
      final String emissor = CertificateUtils.getCN(cert.getIssuerDN().toString());
      
      final  String nif = CertificateUtils.getDNI(cert);
      
      final Long nifLen = new Long((nif== null || nif.trim().length() == 0)? 0 : nif.length());
      

      String ua = null;
      try {
        ua = CertificateUtils.getUnitatAdministrativa(cert);
      } catch(Exception e) {
        e.printStackTrace();
      }
      // TODO ESPEFIFIC CAIB !!!!!
      if (ua != null) {
        int pos = ua.lastIndexOf('-');
        if (pos != -1) {
          ua = ua.substring(pos + 1);
        }
      }
      final Long uaLen = new Long((ua== null || ua.trim().length() == 0)? 0 : ua.length());
      
      String carrec;
      try {
        carrec = CertificateUtils.getCarrec(cert);      
      } catch(Exception e) {
        carrec = null;
        e.printStackTrace();
      }
      final Long carrecLen = new Long((carrec== null || carrec.trim().length() == 0)? 0 : carrec.length());

      final String nom = CertificateUtils.getSubjectCorrectName(cert);
      
      log.info("XYZ Firmat Per FORMAT = ]" + firmatPerFormat + "[");
      
      MessageFormat form = new MessageFormat(firmatPerFormat);
      
      Object[] args = { 
          nom,      // {0} = NOM
          nifLen,   // {1} = LONGITUD NIF
          nif,      // {2} = NIF
          emissor,  // {3} = EMISSOR
          carrecLen,// {4} = LONGITUD CARREC_CERTIFICAT
          carrec,   // {5} = CARREC_CERTIFICAT
          uaLen,    // {6} = LONGITUD UNITAT_ADMINISTRATIVA
          ua        // {7} = UNITAT_ADMINISTRATIVA
      };
      
      String firmatPer = form.format(args);
      
      //String firmatPer = certName + "  (" + bundleSign.getString("emissor") +": " + emisor+ ")";
      
      log.info("XYZ Firmat Per SUBSTITUIT = ]" + firmatPer + "[");
   
      return firmatPer;
  }
  
  
  
//TOD XYZ Moure a logic
  public static byte[] getImage(float ample, float altura, String firmatPerLabel,
         String firmatPer, String dataLabel, String data,
         String motiuLabel, String motiu) throws Exception {
    
    ByteArrayOutputStream baos =  new ByteArrayOutputStream();
    
    
    /*
    int width=900;
    int height=60;
    */
    
    float factorPdfUnitToPixel = 1f; // + (1f/3f);
    int width=(int)(6 * ample * factorPdfUnitToPixel);
    int height=(int)(6 * altura * factorPdfUnitToPixel);
    
    log.info("XYZ Ample Imatge Firma: " + width);
    log.info("XYZ Alt Imatge Firma: "+ height);
    
    BufferedImage bufferedImage = new BufferedImage(width, height,   
                 BufferedImage.TYPE_INT_RGB);  

    final int lines = 5;
    
    int fontSize = (int)(((((13.0f * height)/60.0f)*4.0f)/lines))       - 2; 
    
    //System.out.println("TAMANY DE FONT ANTIC = " + (int)(( (13.0 * height)/60.0) -2 ) );
    //System.out.println("TAMANY DE FONT NOU = " + fontSize );
    
    
    
    //Draw an oval  
    Graphics g = bufferedImage.getGraphics();
    
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);
    
    // DEBUG
    /*
    g.setColor(Color.RED);
    g.drawRect(0,0, width - 1, height -1);
    
    {
      int costat = height - 8;
      g.setColor(Color.BLUE);
      g.drawRect(width /2,4, costat, costat);
    }
    */

    g.setColor(Color.BLACK);
    // Dialog  Serif    SansSerif
    Font font = new Font("SansSerif" , java.awt.Font.PLAIN, fontSize);
           
    Font fontBolt = new Font("SansSerif" , Font.BOLD, fontSize);
    
    
     // get metrics from the graphics
    FontMetrics metrics = g.getFontMetrics(font);
    FontMetrics metricsBold = g.getFontMetrics(fontBolt);

    // get the advance of my text in this font
    // and render context
    final int factorSeparacioReduit = (fontSize * 3)/13;
    int alt = metrics.getHeight() - factorSeparacioReduit/2;
    
    // Escriu Dades del Certificat del Firmant
    int y = - factorSeparacioReduit;
    /*
    
    String label = firmatPerLabel;
    int labelW = metricsBold.stringWidth(label);
    g.setFont(fontBolt);
    g.drawString(label, 4, y);
    g.setFont(font);
    g.drawString(firmatPer, 4 + labelW , y);
    */
    y = drawStringMultipleLine(firmatPerLabel, firmatPer, width, g, font, fontBolt,
        metrics, metricsBold, factorSeparacioReduit, alt, y);
        
        
    y = y + alt;
    String label = dataLabel;
    g.setFont(fontBolt);
    g.drawString(label, 4, y);
    int labelW = metricsBold.stringWidth(label);
    g.setFont(font);
    g.drawString(data, 4 + labelW, y);

    // Escriu Motiu
    y = drawStringMultipleLine(motiuLabel, motiu, width, g, font, fontBolt, metrics, metricsBold,
        factorSeparacioReduit, alt, y);
    
    

    g.dispose();  

    //Write the image as a jpg  
    //ImageIO.write(bufferedImage, "jpg", baos);

    Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");
    ImageWriter writer = (ImageWriter)iter.next();

    ImageWriteParam iwp = writer.getDefaultWriteParam();
   // Now, we can set the compression quality:
    iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    iwp.setCompressionQuality(0.05f);   // an integer between 0 and 1
    // 1 specifies minimum compression and maximum quality
    
    writer.setOutput(new MemoryCacheImageOutputStream(baos));
    IIOImage image = new IIOImage(bufferedImage, null, null);
    writer.write(null, image, iwp);
    writer.dispose();

    baos.flush();
    baos.close();
    
    return baos.toByteArray();
    
  }

  protected static int drawStringMultipleLine(String textLabel, String text, int width, Graphics g,
      Font font, Font fontBolt, FontMetrics metrics, FontMetrics metricsBold,
      final int factorSeparacioReduit, int alt, int y) {
    String label;
    
    y = y + alt;
    label = textLabel;
    g.setFont(fontBolt);
    g.drawString(label, 4, y);
    int labelLen = metricsBold.stringWidth(label);
    //String motiu = "Per que si 0 sdf sdfg sdfg sdfg dherty tjy rtuyjty uj rtuyrty utyu 12345 12345 12345 12345 12345 12345 12345 12345 12345 12345 12345 12345 AAAAA BBBB CCCCC DDDDD EEEEEE FFFFFFF";
    g.setFont(font);
    int textLen = metrics.stringWidth(text);
    
    log.info("XYZ  textLen(" + textLen + ") + labelLen(" + labelLen + ") + " + (5 * factorSeparacioReduit) + " > width (" + width + ")");
    
    if ( (textLen + labelLen + factorSeparacioReduit) > width ) {
      
      int index = text.lastIndexOf(' ');
      while((metrics.stringWidth(text.substring(0,index)) + labelLen + factorSeparacioReduit) > width) {
        index = text.lastIndexOf(' ', index - 1);
      }
      
      g.drawString(text.substring(0, index), 4 + labelLen, y);
      
      y = y + alt;
      g.drawString(text.substring(index + 1), 4, y);
      
      
    } else {

      g.drawString(text, 4 + labelLen, y);
    }
    return y;
  }
  
  
  
}
