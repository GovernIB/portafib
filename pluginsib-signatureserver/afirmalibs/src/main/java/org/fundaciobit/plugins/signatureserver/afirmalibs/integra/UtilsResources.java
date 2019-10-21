package org.fundaciobit.plugins.signatureserver.afirmalibs.integra;



import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.aowagie.text.pdf.PdfStamper;

public class UtilsResources {

  public static Logger LOGGER = Logger.getLogger(UtilsResources.class);
  
  
  public static void safeCloseOutputStream(OutputStream os) {
    if (os != null) {
        try {
            os.close();
        }
        catch (IOException e) {
            LOGGER.error("ERROR UR001", (Throwable)e);
        }
    }
}
  
  
  public static void safeClosePDFStamper(PdfStamper stamper) {
    if (stamper != null) {
        try {
            stamper.close(Calendar.getInstance());
        }
        catch (Exception e) {
            LOGGER.error("Error UR001", (Throwable)e);
        }
    }
}
  
}
