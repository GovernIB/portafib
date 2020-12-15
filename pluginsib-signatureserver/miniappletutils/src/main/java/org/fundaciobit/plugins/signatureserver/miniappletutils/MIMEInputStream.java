
/** 
 * Clase que transforma un InputStream cualquiera en un InputStream en formato MIME, estableciendo
 * en la cabecera el content-type que se le pasa como argumento al constructor y codificando los 
 * bytes de entrada en base64. Se utiliza la codificación de caracteres UTF-8.
 * @author  Jesús Reyes (3dígits)
 * @version 0.98
 */
package org.fundaciobit.plugins.signatureserver.miniappletutils;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;


public class MIMEInputStream extends FilterInputStream {

    /** Flag que indica si se han leido todos los bytes de la entrada */
    private boolean finished;

    private byte[] headData = null; //buffer con los bytes de la cabecera
    private int headDataOff; // Posición dentro del buffer de la cabecera
    private int headDataSize; // Tamaño del buffer de la cabecera
    protected String contentType = null; 
    private boolean inHead; //flag que indica si estamos procesando la cabecera
    private byte[] bodyData = new byte[66];//buffer de los bytes procesados
    private int bodyDataOff;//Posición dentro del buffer de los bytes procesados
    private int bodyDataSize;//Tamaño del buffer de los bytes procesados
    
// Constructor de la clase

    /** Crea un nuevo MIMEInputStream 
     * @throws UnsupportedEncodingException
     * 
     * 
     * */
    public MIMEInputStream(InputStream is, String contentType)  throws UnsupportedEncodingException{
      super(is);
      this.contentType = contentType;
      
      
      //String mime = new String("MIME-Version: 1.0\r\n");
      
      //Inicialización de la cabecera
      String mime = "";
      mime = mime + "Content-Type: " + contentType + "\r\n";
      mime = mime + "Content-Transfer-Encoding: base64\r\n\r\n";

      this.headData = mime.getBytes("UTF-8");
      this.headDataOff = 0;
      this.headDataSize = this.headData.length;
      this.inHead = true;
      
      
      
    }
     

    public synchronized void close ()
    throws IOException {
        finished = true;
        super.close();
    }

    /**
     * Lee el siguiente byte procesado
     * @return el siguiente byte de datos o bien -1 si se ha llegado al final del stream
     * @throws IOException
     */
    public synchronized int read ()
    throws IOException {
        int b;
        
        //Si estamos en la cabecera leemos los bytes del buffer de cabecera
        //Cuando se termina la cabecera se inicializa el buffer de los bytes procesados
        if (inHead)
        {
            b = headData[headDataOff];
            headDataOff++;
            if (headDataOff==headDataSize)
            {
              inHead = false;
              bodyDataSize = this.getBodyData(bodyData);
              if (bodyDataSize==-1)
                finished = true;
              else
                bodyDataOff = 0;
            }
        }
        
        else
        {
          if (finished) return -1;
          b = bodyData[bodyDataOff];
          bodyDataOff++;
          if (bodyDataOff == bodyDataSize)
          {
            bodyDataSize = this.getBodyData(bodyData);
            if (bodyDataSize==-1)
              finished = true;
            else
              bodyDataOff = 0;
          }                   
         }
        return b & 0xFF;
    }




    public synchronized int read (byte[] buffer, int offset, int length)
    throws IOException {
        for (int i = 0; i < length; ++i) {
            int c = read();
            if (c < 0) return i == 0 ? -1 : i;
            buffer[offset++] = (byte) c;
        }
        return length;
    }


  
    /**
     * Skips over and discards <i>n</i> bytes of data from the
     * input stream. 
     * @param  n    the number of bytes to be skipped.
     * @return the actual number of bytes skipped.
     * @exception IOException if an I/O error occurs.
     */
    public synchronized long skip(long n) throws IOException {
        for (long i = 0; i < n; i++)
            if (this.read() < 0) return i;
        return n;
    }

    /**
     * Devuelve el número de bytes que se pueden leer sin que se bloquee el stream
     * 
     * @throws IOException i
     */
    public synchronized int available()
    throws IOException {
        if (inHead)
          return (headDataSize - headDataOff);
        else
          return (bodyDataSize - bodyDataOff);
          
    }

    /**
     * Does nothing, since this class does not support mark/reset.
     */
    public void mark(int readlimit) {}

    /**
     * Always throws an IOException, since this class does not support mark/reset.
     */
    public void reset() throws IOException {
        throw new IOException("Base64InputStream does not support mark/reset");
    }

    /**
     * Tests if this input stream supports the <code>mark</code> and
     * <code>reset</code> methods of InputStream, which it does not.
     *
     * @return <code>false</code>, since this class does not support the
     *         <code>mark</code> and <code>reset</code> methods.
     */
    public boolean markSupported() { return false; }


// Own methods
    
    
  /**
   * 
   * @throws java.io.IOException
   * @return número de bytes procesados o -1 si se ha llegado al final del stream
   * @param bodyData buffer de bytes procesados
   */
  
    private int getBodyData(byte[] bodyData) throws IOException
    {
      int size = 0;
      // La codificación base64 codifica 3 bytes de entrada en 4 bytes de salida
      // El buffer de entrada es de 48 bytes, por lo que la codificación en base64 nos
      // producirá 64 bytes. 
      byte[] inputBuffer = new byte[48];
      size = 0;
      do
      {
    	  int read = in.read(inputBuffer, size, inputBuffer.length - size);
    	  if (read < 0 && size == 0)
    		  return -1;
    	  else if (read < 0 )
    		  break;
    	  size = size + read;
      } while (size < inputBuffer.length);
      
      if (size >= 0)
      {
        byte result[] = Base64.toBase64(inputBuffer, 0, size);
        
        size = result.length;
        System.arraycopy( result, 0, bodyData, 0, size);
      }
      return size;
    }
}
