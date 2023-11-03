package es.caib.portafib.ws.v1.example;

import java.net.HttpURLConnection;

/**
 * 
 * @author anadal
 *
 */
public class DirectGetVersion {

  public static void main(String[] args) {
    try {
      String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:impl=\"http://impl.v1.ws.portafib.caib.es/\">"
          + "\n"
          + "<soapenv:Header/>"
          + "\n"
          + "<soapenv:Body>"
          + "\n"
          + "<impl:getVersion/>"
          + "\n" + "</soapenv:Body>" + "\n" + "</soapenv:Envelope>" + "\n";

      //String urlStr = "http://localhost:8080/portafib/ws/v1/PortaFIBHelloWorld";
      //String urlStr = "https://dev.caib.es/portafib/ws/v1/PortaFIBHelloWorld";
      String urlStr = "https://dev.caib.es/portafib21/ws/v1/PortaFIBHelloWorld";
      java.net.URL url = new java.net.URL(urlStr);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      // Set the necessary header fields
      conn.setRequestProperty("SOAPAction", urlStr);
      conn.setDoOutput(true);

      // Send the request
      java.io.OutputStreamWriter wr = new java.io.OutputStreamWriter(conn.getOutputStream());
      wr.write(request);
      wr.flush();

      // Check error
      if (conn.getResponseCode() != 200) {
        throw new Exception("Error " + conn.getResponseMessage() + "(Error code = "
            + conn.getResponseCode() + ")");
      }

      StringBuffer str = new StringBuffer();
      // Read the response
      java.io.BufferedReader rd = new java.io.BufferedReader(new java.io.InputStreamReader(
          conn.getInputStream()));
      String line;
      while ((line = rd.readLine()) != null) {
        str.append(line).append("\n");
      }

      String versio = getBetweenStrings(str.toString(), "<return>", "</return>");

      System.out.println(versio);
      
      if (versio.indexOf('-') != -1) {
          versio = versio.substring(0, versio.indexOf('-')); 
      }
      

      System.out.println("1.0.0  ? " + compareVersions(versio, "1.0.0"));
      System.out.println("1.1.3? " + compareVersions(versio, "1.1.3"));
      System.out.println("1.1.4? " + compareVersions(versio, "1.1.4"));
      System.out.println("2.0.0? " + compareVersions(versio, "2.0.0"));

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static int compareVersions(String str1, String str2) {
    String[] vals1 = str1.split("\\.");
    String[] vals2 = str2.split("\\.");
    int i = 0;
    // set index to first non-equal ordinal or length of shortest version string
    while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
      i++;
    }
    // compare first non-equal ordinal number
    if (i < vals1.length && i < vals2.length) {
      int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
      return Integer.signum(diff);
    }
    // the strings are equal or one string is a substring of the other
    // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
    return Integer.signum(vals1.length - vals2.length);
  }

  public static String getBetweenStrings(String text, String textFrom, String textTo) {

    String result = "";

    // Cut the beginning of the text to not occasionally meet a
    // 'textTo' value in it:
    result = text.substring(text.indexOf(textFrom) + textFrom.length(), text.length());

    // Cut the excessive ending of the text:
    result = result.substring(0, result.indexOf(textTo));

    return result;
  }

}
