package es.caib.portafib.it.indra.helium;

import javax.activation.MimetypesFileTypeMap;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;

import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.DocumentFormatRegistry;

/**
 * Utilitats per a conversió de documents amb OpenOffice.
 * 
 * @author Limit Tecnologies <limit@limit.es>
 */
public class OpenOfficeUtils {

	private DocumentFormatRegistry documentFormatRegistry;

	public String nomArxiuConvertit(
			String arxiuNom,
			String extensioSortida) {
		DocumentFormat outputFormat = getDocumentFormatRegistry().getFormatByFileExtension(extensioSortida);
		int indexPunt = arxiuNom.lastIndexOf(".");
		if (indexPunt != -1) {
			String nom = arxiuNom.substring(0, indexPunt);
			return nom + "." + outputFormat.getFileExtension();
		} else {
			return arxiuNom + "." + outputFormat.getFileExtension();
		}
	}

	public String getArxiuMimeType(String nomArxiu) {
		DocumentFormat format = formatPerNomArxiu(nomArxiu);
		if (format == null)
			return new MimetypesFileTypeMap().getContentType(nomArxiu);
		else
			return format.getMimeType();
	}


	private DocumentFormat formatPerNomArxiu(String fileName) {
		int indexPunt = fileName.lastIndexOf(".");
		if (indexPunt != -1) {
			String extensio = fileName.substring(indexPunt + 1);
			return getDocumentFormatRegistry().getFormatByFileExtension(extensio);
		}
		return null;
	}

	private DocumentFormatRegistry getDocumentFormatRegistry() {
		if (documentFormatRegistry == null)
			documentFormatRegistry = new DefaultDocumentFormatRegistry();
		return documentFormatRegistry;
	}

}
