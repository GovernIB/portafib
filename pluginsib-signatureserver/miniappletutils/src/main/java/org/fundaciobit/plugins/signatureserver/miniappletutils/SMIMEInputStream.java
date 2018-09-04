
package org.fundaciobit.plugins.signatureserver.miniappletutils;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;


/** 
 * Clase que transforma un InputStream cualquiera en un InputStream en formato SMIME, estableciendo
 * en la cabecera el content-type que se le pasa como argumento al constructor y codificando los 
 * bytes de entrada en base64.
 * 
 * Modificacion introducidas por FGU para permitir generacion del SMIME a partir de un PKCS#7 que contiene una firma
 * en paralelo:
 * 
 *  * Se intoruduce nuevo constructor a partir de un array de bytes que contiene el pkcs#7 y el contentType
 *  * Se introduce el atributo contentType para en el metodo fetchContentHeader no tener que obtenerlo de la signature
 *  * El constructor que utiliza la Signature da valor al atribute contentType
 *  
 * @author  Biel Buades
 * @version 1.0
 */
public class SMIMEInputStream extends InputStream {

	private static final int CHUNK_SIZE = 48;
	String boundary;
	//Signature signature;
	InputStream content;
  String contentType;
  
  
  
    /** Flag que indica si se han leido todos los bytes de la entrada */
	private int status;
	static private final int STATUS_IDLE = 0;
	static private final int STATUS_HEADER = 1;
	static private final int STATUS_CONTENT_HEADER = 2;
	static private final int STATUS_CONTENT_BODY = 3;
	static private final int STATUS_SIGNATURE_HEADER = 4;
	static private final int STATUS_SIGNATURE_BODY = 5;
	static private final int STATUS_FOOTER = 6;
	static private final int STATUS_END = 7;
	
	//PJR 23/10/2008 hemos de calcular si el tamaño total del stream es múltiplo del 
	// CHUNK_SIZE para que no poner un salto de línea que genera un formato SMIME incorrecto. 
	private boolean contentSizeMultiple=false;
	private boolean signatureSizeMultiple=false;
	
	int bufferOffset;
	byte buffer [];

	byte signatureData[];
	int signatureDataOffset ;
// Constructor

    /** Crea un nuevo MIMEInputStream 
     * @throws UnsupportedEncodingException
     *
     * @param signature Firma electrónica válida para el documento a encapsular
     * @param content InputStream con el contenido del documento firmado. 
     * 
     */
	/*
    public SMIMEInputStream(String contentType, byte[] signatureData, InputStream content)  throws UnsupportedEncodingException{
    	//this.signature = signature;
    	this.content = content;
      this.contentType = contentType; // signature.getContentType();
    	status = STATUS_IDLE;
    	bufferOffset = 0;
    	buffer = null;
    	boundary = "----CAIB_BOUNDARY_"+System.currentTimeMillis()+"_DGTIC";
    	this.signatureData = signatureData; // signature.getPkcs7();
    	signatureDataOffset = 0;
    }
    */
    
    /**
   * Crea un nuevo SMIMEInputStream a partir de la firma en pkcs7 y el contentType del MIME
   * @param pkcs7 el array de bytes que representa el pkcs7
   * @param content El contenido que se firma.
   * @param contentType El tipo del contenido que se firma.
   */
    public SMIMEInputStream(byte[] pkcs7, InputStream content, String contentType) {
      this.content = content;
      this.contentType = contentType;
      status = STATUS_IDLE;
      bufferOffset = 0;
      buffer = null;
      boundary = "----CAIB_BOUNDARY_"+System.currentTimeMillis()+"_DGTIC";
      signatureData = pkcs7;
      signatureDataOffset = 0;
      
    }
      
   
    
    /* (non-Javadoc)
     * @see java.io.InputStream#close()
     */
    public synchronized void close ()
    throws IOException {
        status = STATUS_END;
        content.close ();
    }

    /* (non-Javadoc)
     * @see java.io.InputStream#read()
     */
    public synchronized int read ()
    throws IOException {
    	do {
    		if ( buffer != null && bufferOffset < buffer.length)
	    	{
	    		byte b = buffer [ bufferOffset ++ ];
	    		return b;
	    	} else if (status == STATUS_END) {
    			return -1;
	    	} else {
	    		fetchData();
	    	}
	    	
    	} while (true);
    }

    /* (non-Javadoc)
     * @see java.io.InputStream#read(byte[], int, int)
     */
    public synchronized int read (byte[] targetBuffer, int offset, int length)
    throws IOException {
    	do {
    		if ( buffer != null && bufferOffset < buffer.length)
	    	{
    			int actualLength = buffer.length - bufferOffset;
    			if ( actualLength > length)
    				actualLength = length;
    			System.arraycopy(buffer, bufferOffset, targetBuffer, offset, actualLength);
    			bufferOffset = bufferOffset + actualLength;
    			return actualLength;
	    	} else if (status == STATUS_END) {
        		return -1;
	    	} else {
	    		fetchData();
	    	}
	    	
    	} while (true);
    }

    /* (non-Javadoc)
     * @see java.io.InputStream#available()
     */
    public synchronized int available()
    throws IOException {
    	do {
    		 if ( buffer != null && bufferOffset < buffer.length)
	    	{
    			return buffer.length - bufferOffset;
	    	} else if (status == STATUS_END) {
	    		return -1;
	    	} else if (status == STATUS_CONTENT_BODY) {
	    		return content.available();
	    	} else {
	    		fetchData ();
	    	}
    	} while (true);
    }

	private void fetchData() throws IOException {
		switch (status)
		{
		case STATUS_IDLE:
			buffer = fetchIdle ();
			break;
		case STATUS_HEADER:
			buffer = fetchHeader ();
			break;
		case STATUS_CONTENT_HEADER:
			buffer = fetchContentHeader ();
			break;
		case STATUS_CONTENT_BODY:
			buffer = fetchContentBody ();
			break;
		case STATUS_SIGNATURE_HEADER:
			buffer = fetchSignatureHeader ();
			break;
		case STATUS_SIGNATURE_BODY:
			buffer = fetchSignatureBody ();
			break;
		case STATUS_FOOTER:
			buffer = fetchFooter ();
			break;
		default:
			buffer = null;
			break;
		}
		bufferOffset = 0;
	}




    private byte [] fetchFooter() {
    	try {
        	status = STATUS_END;
        	
        	String outString=(signatureSizeMultiple)?"":"\r\n"; //si el tamaño es divisible entre CHUNK_SIZE no ponemos salto de línea
			outString+="--"+boundary+"--\r\n";
			
			return outString.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException (e);
		}
	}


	private byte [] fetchContentBody() throws IOException {
		byte b[] = new byte[CHUNK_SIZE];
		int offset = 0;
		int read;
		contentSizeMultiple=false;
		
		while (true)
		{
			read = content.read(b, offset, b.length-offset);
			if ( read <= 0)
			{
				//si el tamaño final es divisible entre CHUNK_SIZE no ponemos salto de línea
				if((offset % CHUNK_SIZE)==0) 
					contentSizeMultiple=true;
				
				status = STATUS_SIGNATURE_HEADER;
				break;
			}
			offset = offset + read;
			if ( offset == b.length)
				break;
		}
		return Base64.toBase64(b, 0, offset);
	}


	private byte [] fetchContentHeader() {
    	try {
        	status = STATUS_CONTENT_BODY;
			return ("--"+boundary+"\r\n"+
					"Content-Type: "+contentType+"\r\n"+
					"Content-Transfer-Encoding: base64\r\n" +          
					"\r\n").getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException (e);
		}
	}



	private byte [] fetchSignatureBody() {
		int len = signatureData.length - signatureDataOffset;
		signatureSizeMultiple=false;
		
		if ( len <= 0)
		{
			//si el tamaño final es divisible entre CHUNK_SIZE no ponemos salto de línea
			if((signatureDataOffset % CHUNK_SIZE)==0)
				signatureSizeMultiple=true;
			
			status = STATUS_FOOTER;
			return null;
		}
		else if ( len > CHUNK_SIZE )
		{
			len = CHUNK_SIZE;
		}
		
		byte result []= Base64.toBase64(signatureData, signatureDataOffset, len);
		signatureDataOffset = signatureDataOffset + len;
		return result;
	}


	private byte [] fetchSignatureHeader() {
    	try {
        	status = STATUS_SIGNATURE_BODY;
			
        	String outString=(contentSizeMultiple)?"":"\r\n";	//si el tamaño es divisible entre CHUNK_SIZE no ponemos salto de línea
			outString+="--"+boundary+"\r\n"+
					"Content-Type: application/pkcs7-signature; name=smime.p7s; smime-type=signed-data\r\n"+
					"Content-Transfer-Encoding: base64\r\n"+
					"Content-Disposition: attachment; filename=\"smime.p7s\"\r\n"+
					"Content-Description: S/MIME Cryptographic Signature\r\n"+
					"\r\n";
        	return outString.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException (e);
		}
	}


	private byte [] fetchHeader() {
    	try {
        	status = STATUS_CONTENT_HEADER;
			return ("Content-Type: multipart/signed; " +
					"protocol=\"application/pkcs7-signature\"; " +
					"micalg=\"sha1\";" +
					"boundary=\""+boundary+"\"\r\n"+
					"\r\n").getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException (e);
		}
	}


	private byte [] fetchIdle() {
		status = STATUS_HEADER;
		return null;
	}


	

  
    /* (non-Javadoc)
     * @see java.io.InputStream#skip(long)
     */
    public synchronized long skip(long n) throws IOException {
        for (long i = 0; i < n; i++)
            if (this.read() < 0) return i;
        return n;
    }

    /* (non-Javadoc)
     * @see java.io.InputStream#mark(int)
     */
    public void mark(int readlimit) {}

    /* (non-Javadoc)
     * @see java.io.InputStream#reset()
     */
    public void reset() throws IOException {
        throw new IOException("Base64InputStream does not support mark/reset");
    }

    /* (non-Javadoc)
     * @see java.io.InputStream#markSupported()
     */
    public boolean markSupported() { return false; }


// Own methods
    
    
}
