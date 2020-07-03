package es.caib.portafib.back.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;

/**
 * Classe per codificar strings de manera que es pugui garantir que la seva transformació a un encoding determinat no
 * produeix caràcters estranys. Ho fa reemplaçant els caràcters que no es poden representar amb la codificació indicada
 * per la cadena de reemplça indicada.
 *
 * Per exemple, si passam l'string: "Això té accents", i l'hem de codificar amb US-ASCII, i indicam la cadena de
 * reemplaç "_", ens retornarà "Aix_ t_ accents".
 *
 * No és thread-safe, ja que utilitza un CharsetEncoder que tampoc ho és.
 * Per tant no reutilitzar instàncies com a variables static.
 */
public class SafeCharsetEncoder {

    private static final String DEFAULT_REPLACEMENT = "_";

    final Charset charset;
    final CharsetEncoder charsetEncoder;

    public static SafeCharsetEncoder getInstance(Charset charset) {
        return new SafeCharsetEncoder(charset, DEFAULT_REPLACEMENT);
    }

    public static SafeCharsetEncoder getInstance(Charset charset, String replacement) {
        return new SafeCharsetEncoder(charset, replacement);
    }

    private SafeCharsetEncoder(Charset charset, String replacement) {
        this.charset = charset;
        this.charsetEncoder = charset.newEncoder();
        charsetEncoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
        charsetEncoder.replaceWith(replacement.getBytes(charset));
    }

    public String encode(String source) {
        if (charsetEncoder.canEncode(source)) {
            return source;
        }

        try {
            ByteBuffer buffer = charsetEncoder.encode(CharBuffer.wrap(source));
            return new String(buffer.array(), charset);
        } catch (CharacterCodingException e) {
            // Realment no es pot produir perquè hem fixat CodingErrorAction.REPLACE
            throw new IllegalStateException("charsetEncoder.onUnmappableCharacter hauria de ser REPLACE", e);
        }
    }
}
