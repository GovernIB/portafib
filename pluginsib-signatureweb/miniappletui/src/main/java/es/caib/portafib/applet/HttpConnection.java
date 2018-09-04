package es.caib.portafib.applet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author anadal
 *
 */
public class HttpConnection {

  public static final String charset = "UTF-8";

  public static final  String CRLF = "\r\n"; // Line separator required by multipart/form-data.
  
  public final String boundary;
  
  public final URLConnection connection;
  
  public final OutputStream output;

  
  public HttpConnection(String url) throws Exception {
    

    boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
    connection = new URL(url).openConnection();
    connection.setDoOutput(true);
    connection.setDoInput(true);
    connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
  
  
    output = connection.getOutputStream();
    
  
  }
  
  
  public void addBinaryFile(String formName, String fileName, String mime, byte[] data) throws Exception {
    // Send binary file.
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
    writer.append("--" + boundary).append(CRLF);
    writer.append("Content-Disposition: form-data; name=\"" + formName + "\"; filename=\"" + fileName+ "\"").append(CRLF);
    writer.append("Content-Type: ").append(mime).append(CRLF);
    writer.append("Content-Transfer-Encoding: binary").append(CRLF);
    writer.append(CRLF).flush();
    output.write(data);
    output.flush(); // Important before continuing with writer!
    writer.append(CRLF); // CRLF is important! It indicates end of boundary.
    // End of multipart/form-data.
    writer.append("--" + boundary + "--").append(CRLF).flush();
  
    writer.close();
      
    
  }

   public int startConnection() throws IOException {
     return ((HttpURLConnection) connection).getResponseCode(); 
   }
  
   public InputStream getInputStream() throws IOException {
     return connection.getInputStream();
   }
}
