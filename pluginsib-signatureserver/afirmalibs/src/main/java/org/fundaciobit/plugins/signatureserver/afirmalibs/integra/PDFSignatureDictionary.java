/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.lowagie.text.pdf.PdfDictionary
 */
package org.fundaciobit.plugins.signatureserver.afirmalibs.integra;

import java.io.Serializable;

import com.aowagie.text.pdf.PdfDictionary;

public class PDFSignatureDictionary implements Serializable {
    private static final long serialVersionUID = -1252456113292585014L;
    private int revision;
    private PdfDictionary dictionary;
    private String name;

    public PDFSignatureDictionary(int revisionParam, PdfDictionary dictionaryParam, String nameParam) {
        this.revision = revisionParam;
        this.dictionary = dictionaryParam;
        this.name = nameParam;
    }

    public final int getRevision() {
        return this.revision;
    }

    public final void setRevision(int revisionParam) {
        this.revision = revisionParam;
    }

    public final PdfDictionary getDictionary() {
        return this.dictionary;
    }

    public final void setDictionary(PdfDictionary dictionaryParam) {
        this.dictionary = dictionaryParam;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String nameParam) {
        this.name = nameParam;
    }
}

