package es.caib.portafib.api.interna.all.exemplepublic;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 *
 */
@Schema(name = "ExempleDeByteArray")
public class ExempleDeByteArray {

    protected String nom;
    protected String mime;
    protected int length;
    @Schema(name = "bytes", required = true, type = "string", format = "byte")
    private byte[] bytes = null;

    public ExempleDeByteArray() {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}
