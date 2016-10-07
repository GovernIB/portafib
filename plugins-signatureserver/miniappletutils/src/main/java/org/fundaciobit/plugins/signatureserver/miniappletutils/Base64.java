package org.fundaciobit.plugins.signatureserver.miniappletutils;

/**
 * 
 * @author Projecte IBKey
 *
 */
public class Base64 {

    // Codificar BASE 64
    static private final byte base64Array [] = 
    	"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".getBytes();

    public static byte [] toBase64 (byte source [], int src, int len)
    {
    	int i ;
    	int max = src+len;
    	int j = 0;
    	int resultLength = 4 * ( (len + 2) / 3);
    	byte result[] = new byte [2 + resultLength];
    	
    	for ( i = src; i < max; i+=3)
    	{
    		int c1, c2, c3;
    		int index;
    		c1 = source[i];
    		if ( i+1 < max ) c2 = (char) source[i+1];
    		else c2 = 0;
    		if ( i+2 < max ) c3 = (char) source [i+2];
    		else c3 = 0;
    		index = (c1 & 0xfc) >> 2;
    		result [j++] = base64Array[ index ];
    		index = ((c1 & 0x03) << 4) | ((c2 & 0xf0) >> 4);
    		result [j++] = base64Array[ index ];
    		if ( i+1 >= max ) result [j ++ ] = '=';
    		else
    		{
    			index = ((c2 & 0x0f) << 2) | ((c3 & 0xc0) >> 6);
    			result [j++] = base64Array[ index ];
    		}
    		if ( i+2 >= max ) result [ j ++ ] = '=';
    		else
    		{
    			index = (c3 & 0x3f);
    			result [j++] = base64Array[ index ];
    		}
    	}
    	result [j++] = '\r';
    	result [j++] = '\n';
    	return result;
    }

}
