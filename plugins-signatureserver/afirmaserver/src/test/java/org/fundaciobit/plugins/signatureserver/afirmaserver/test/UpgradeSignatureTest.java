package org.fundaciobit.plugins.signatureserver.afirmaserver.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.signatureserver.afirmaserver.AfirmaServerSignatureServerPlugin;
import org.fundaciobit.plugins.signatureserver.afirmaserver.ValidateSignatureConstants;
import org.fundaciobit.plugins.signatureserver.api.ISignatureServerPlugin;
import org.fundaciobit.plugins.utils.FileUtils;
import org.fundaciobit.plugins.utils.XTrustProvider;


import es.gob.afirma.integraFacade.pojo.ServerSignerResponse;
import es.gob.afirma.integraFacade.pojo.SignatureFormatEnum;
import es.gob.afirma.integraFacade.pojo.UpgradeSignatureRequest;


/**
 * Tiquet (a.10) Afegir mètodes d'extensió de firma: upgradeSignature() i supportUpgradeSignature() #167
   * https://github.com/GovernIB/portafib/issues/167
 * 
 * @author anadal
 *
 */
public class UpgradeSignatureTest implements ValidateSignatureConstants {

	Logger log = Logger.getLogger(UpgradeSignatureTest.class);
	
	
	/*

	  public static final Map<String, String> localSignProfile2PluginSignProfile = new HashMap<String, String>();

	  public static final Map<String, String> localSignType2PluginSignType = new HashMap<String, String>();

	  public static final Map<String, String> localAlgorithm2PluginAlgorithm = new HashMap<String, String>();

	  public static final Map<String, String> localAlgorithmEnc2PluginAlgorithm = new HashMap<String, String>();
	  
	  

	  static {
	    
	    
	    localSignType2PluginSignType.put(DSSConstants.SignTypesURIs.XADES_V_1_3_2,
	        SIGNTYPE_XAdES); // = "http://uri.etsi.org/01903/v1.3.2#";
	    localSignType2PluginSignType.put(DSSConstants.SignTypesURIs.XADES_V_1_2_2,
	        SIGNTYPE_XAdES); // = "http://uri.etsi.org/01903/v1.2.2#";
	    localSignType2PluginSignType.put(DSSConstants.SignTypesURIs.XADES_V_1_1_1,
	        SIGNTYPE_XAdES); // = "http://uri.etsi.org/01903/v1.1.1#";
	    localSignType2PluginSignType.put(DSSConstants.SignTypesURIs.CADES,
	        SIGNTYPE_CAdES); // = "http://uri.etsi.org/01733/v1.7.3#";
	    localSignType2PluginSignType.put(DSSConstants.SignTypesURIs.CMS,
	        SIGNTYPE_CMS); // = "urn:ietf:rfc:3369";
	    localSignType2PluginSignType.put(DSSConstants.SignTypesURIs.CMS_TST,
	        SIGNTYPE_CMS); // = "urn:afirma:dss:1.0:profile:XSS:forms:CMSWithTST";
	    localSignType2PluginSignType.put(DSSConstants.SignTypesURIs.ODF,
	        SIGNTYPE_ODF); // = "urn:afirma:dss:1.0:profile:XSS:forms:ODF";
	    localSignType2PluginSignType.put(DSSConstants.SignTypesURIs.PDF,
	        SIGNTYPE_PDF); // = "urn:afirma:dss:1.0:profile:XSS:forms:PDF";
	    localSignType2PluginSignType.put(DSSConstants.SignTypesURIs.PADES,
	        SIGNTYPE_PAdES); // = "urn:afirma:dss:1.0:profile:XSS:forms:PAdES";
	    localSignType2PluginSignType.put(DSSConstants.SignTypesURIs.XML_DSIG,
	        SIGNTYPE_XML_DSIG); // = "urn:ietf:rfc:3275";
	    localSignType2PluginSignType.put(DSSConstants.SignTypesURIs.PKCS7,
	        SIGNTYPE_PKCS7); // = "urn:ietf:rfc:2315";
	    localSignType2PluginSignType.put(DSSConstants.SignTypesURIs.XML_TST,
	        SIGNTYPE_XML_TST); // = "urn:oasis:names:tc:dss:1.0:core:schema:XMLTimeStampToken";

	    //  Attribute that represents identifier for CAdES Baseline.
	    // Definit a DSSConstants.SignTypesURIs.CADES_BASELINE_2_2_1,
	    localSignType2PluginSignType.put("http://uri.etsi.org/103173/v2.2.1#",
	        SIGNTYPE_CAdES);

	    // Attribute that represents identifier for PAdES Baseline.
	    // Definit a DSSConstants.SignTypesURIs.PADES_BASELINE_2_1_1,
	    localSignType2PluginSignType.put("http://uri.etsi.org/103172/v2.1.1#",
	        SIGNTYPE_PAdES);
	 
	    // Attribute that represents identifier for XAdES Baseline.
	    // Definit a DSSConstants.SignTypesURIs.XADES_BASELINE_2_1_1,
	    localSignType2PluginSignType.put("http://uri.etsi.org/103171/v2.1.1#",
	        SIGNTYPE_XAdES);

	    
	      localSignProfile2PluginSignProfile.put(DSSConstants.SignatureForm.BES,
	          SIGNPROFILE_BES); //= "urn:oasis:names:tc:dss:1.0:profiles:AdES:forms:BES";
	      localSignProfile2PluginSignProfile.put(DSSConstants.SignatureForm.EPES,
	          SIGNPROFILE_EPES); // = "urn:oasis:names:tc:dss:1.0:profiles:AdES:forms:EPES";
	      localSignProfile2PluginSignProfile.put(DSSConstants.SignatureForm.T,
	          SIGNPROFILE_T); // = "urn:oasis:names:tc:dss:1.0:profiles:AdES:forms:ES-T";
	      localSignProfile2PluginSignProfile.put(DSSConstants.SignatureForm.C,
	          SIGNPROFILE_C); // = "urn:oasis:names:tc:dss:1.0:profiles:AdES:forms:ES-C";
	      localSignProfile2PluginSignProfile.put(DSSConstants.SignatureForm.X,
	          SIGNPROFILE_X); // = "urn:oasis:names:tc:dss:1.0:profiles:AdES:forms:ES-X";
	      localSignProfile2PluginSignProfile.put(DSSConstants.SignatureForm.X_1,
	          SIGNPROFILE_X1); // = "urn:oasis:names:tc:dss:1.0:profiles:AdES:forms:ES-X-1";
	      localSignProfile2PluginSignProfile.put(DSSConstants.SignatureForm.X_2,
	          SIGNPROFILE_X2); // = "urn:oasis:names:tc:dss:1.0:profiles:AdES:forms:ES-X-2";
	      localSignProfile2PluginSignProfile.put(DSSConstants.SignatureForm.X_L,
	          SIGNPROFILE_XL); // = "urn:oasis:names:tc:dss:1.0:profiles:AdES:forms:ES-X-L";
	      localSignProfile2PluginSignProfile.put(DSSConstants.SignatureForm.X_L_1,
	          SIGNPROFILE_XL1); // = "urn:oasis:names:tc:dss:1.0:profiles:AdES:forms:ES-X-L-1";
	      localSignProfile2PluginSignProfile.put(DSSConstants.SignatureForm.X_L_2,
	          SIGNPROFILE_XL2); // = "urn:oasis:names:tc:dss:1.0:profiles:AdES:forms:ES-X-L-2";
	      localSignProfile2PluginSignProfile.put(DSSConstants.SignatureForm.A,
	          SIGNPROFILE_A); // = "urn:oasis:names:tc:dss:1.0:profiles:AdES:forms:ES-A";
	      localSignProfile2PluginSignProfile.put(DSSConstants.SignatureForm.PADES_BASIC,
	          SIGNPROFILE_PADES_BASIC); // = "urn:afirma:dss:1.0:profile:XSS:PAdES:1.2.1:forms:Basico";
	      localSignProfile2PluginSignProfile.put(DSSConstants.SignatureForm.PADES_BES,
	          SIGNPROFILE_BES); // = "urn:afirma:dss:1.0:profile:XSS:PAdES:1.1.2:forms:BES";
	      localSignProfile2PluginSignProfile.put(DSSConstants.SignatureForm.PADES_EPES,
	          SIGNPROFILE_EPES); // = "urn:afirma:dss:1.0:profile:XSS:PAdES:1.1.2:forms:EPES";
	      localSignProfile2PluginSignProfile.put(DSSConstants.SignatureForm.PADES_LTV,
	          SIGNPROFILE_PADES_LTV); // = "urn:afirma:dss:1.0:profile:XSS:PAdES:1.1.2:forms:LTV";


	     
	       // TODO Constant DSSConstants.SignatureForm.B_LEVEL
	       //  Attribute that represents B_LEVEL identifier form.
	       
	      localSignProfile2PluginSignProfile.put("urn:afirma:dss:1.0:profile:XSS:AdES:forms:B-Level",
	          SIGNPROFILE_BES); 

	      // TODO Constant DSSConstants.SignatureForm.T_LEVEL
	      //  Attribute that represents T_LEVEL identifier form.
	       
	      localSignProfile2PluginSignProfile.put("urn:afirma:dss:1.0:profile:XSS:AdES:forms:T-Level",
	          SIGNPROFILE_T); 

	      // TODO Constant DSSConstants.SignatureForm.LT_LEVEL
	      //  Attribute that represents LT_LEVEL identifier form..
	     
	      localSignProfile2PluginSignProfile.put("urn:afirma:dss:1.0:profile:XSS:AdES:forms:LT-Level",
	          SIGNPROFILE_XL); 

	      // TODO Constant DSSConstants.SignatureForm.LTA_LEVEL
	      //  Attribute that represents LT_LEVEL identifier form.
	      localSignProfile2PluginSignProfile.put("urn:afirma:dss:1.0:profile:XSS:AdES:forms:LTA-Level",
	          SIGNPROFILE_A); 
	      
	    
	  }
	
	*/
	
	
	public static void main(String[] args) {
		try {
			
		
		InputStream is = FileUtils.readResource(UpgradeSignatureTest.class, "testfiles/hola_signat.pdf");
       
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        FileUtils.copy(is, baos);
        
        byte[] signature = baos.toByteArray();
        
        String upgradeSignFormat = ValidateSignatureConstants.SIGNTYPE_PAdES;
        String upgradeSignProfile = ValidateSignatureConstants.SIGNPROFILE_PADES_LTV;
        
        UpgradeSignatureTest test = new UpgradeSignatureTest();
        byte[] upgradeData = test.upgradeSignature(signature, upgradeSignFormat, upgradeSignProfile);
        
        FileOutputStream fos = new FileOutputStream(new File("hola_upgraded.pdf"));
        fos.write(upgradeData);
        fos.flush();
        fos.close();
        
        System.out.println("Final OK");
        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	

	public byte[] upgradeSignature(byte[] signature, String upgradeSignFormat, String upgradeSignProfile) throws Exception {
		
		
		Properties pluginProperties = new Properties();
	      pluginProperties.load(new FileInputStream(new File("./config/plugin.properties.caib")));

	      String propertyKeyBase = "org.fundaciobit.exemple.signatureserverplugins.3.";
		
		
	      ISignatureServerPlugin plugin;
	      plugin = new org.fundaciobit.plugins.signatureserver.afirmaserver.AfirmaServerSignatureServerPlugin(
	          propertyKeyBase, pluginProperties);
	      
	      
	      XTrustProvider.install();
		
		
		byte[] res;
		SignatureFormatEnum dssSignatureFormat;
		UpgradeSignatureRequest upgSigReq;

		res = null;
		
		
		if(signature == null || signature.length == 0){
			throw new Exception("Evolución de firma electrónica: Firma electrónica nula o vacía.");
		}
		
		/*
		dssSignatureFormat = translateSignatureFormat(upgradedSignatureFormat);
		*/
		// XYZ ZZZ
		dssSignatureFormat = SignatureFormatEnum.PAdES_LTV;
		
		String upgradedSignatureFormat = upgradeSignFormat + " - " + upgradeSignProfile;
		
		if(dssSignatureFormat == null){
			throw new Exception("Formato de firma electrónica " + upgradeSignFormat + " - " + upgradeSignProfile + " no soportado  para evolucionar una firma electrónica.");
		}
		
		String appID = ((AfirmaServerSignatureServerPlugin)plugin).getPropertyRequired(AfirmaServerSignatureServerPlugin.APPLICATIONID_SENSE_SEGELLLAT_DE_TEMPS_PROPERTY);
		
		
		upgSigReq = new UpgradeSignatureRequest();
		upgSigReq.setSignature(signature);
		upgSigReq.setApplicationId(appID);
		upgSigReq.setSignatureFormat(dssSignatureFormat);
		
		// XYZ ZZZ 
		ServerSignerResponse serSigRes = ((AfirmaServerSignatureServerPlugin)plugin).upgradeSignature(upgSigReq);

		log.debug("Resultado evolución firma:");
		if (serSigRes == null) {
			throw new Exception("No se obtuvo respuesta en la invocación del servicio DSSAfirmaVerify de la plataforma @firma "
					+ "para evolucionar una firma electrónica al formato " + upgradedSignatureFormat + ".");
		}
		log.debug("SersigRes asyncResponse: " + serSigRes.getAsyncResponse());
		log.debug("SersigRes transactionId: " + serSigRes.getIdTransaction());
		log.debug("SersigRes signatureFormat: " + serSigRes.getSignatureFormat());
		if (serSigRes.getResult() == null) {
			throw new Exception("La respuesta retornada por el servicio DSSAfirmaVerify de la plataforma @firma no incluye el resultado de la operación para "
					+ "evolucionar una firma electrónica al formato " + upgradedSignatureFormat + ".");
		}
		
		log.debug("SersigRes Result major: " + serSigRes.getResult().getResultMajor());
	    log.debug("SersigRes Result minor: " + serSigRes.getResult().getResultMinor());
	    log.debug("SersigRes Result message: " + serSigRes.getResult().getResultMessage());
		
		if (!"urn:oasis:names:tc:dss:1.0:resultmajor:Success".equals(serSigRes.getResult().getResultMajor())) {
			throw new Exception("Se obtuvo una respuesta erréonea en la invocación del servicio DSSAfirmaSign de la plataforma @firma para evolucionar una firma electrónica al formato " + 
					upgradedSignatureFormat + ". \n\t Código (Major): " + serSigRes.getResult().getResultMajor() + " \n\t Código (Minor): " + 
					serSigRes.getResult().getResultMinor() + "\n\t Observaciones: " + serSigRes.getResult().getResultMessage() + ".");
		}

		if (serSigRes.getSignature() == null) {
			throw new Exception("La respuesta retornada por el servicio DSSAfirmaVerify de la plataforma @firma no incluye la firma electrónica evolucionada al "
					+ "formato " + upgradedSignatureFormat + ".");
		}

		res = serSigRes.getSignature();

		return res;
	}
	
	
	
	
	
	
	/**
	 * Obtiene el identificador DSS del formato de firma electrónica requerido por el API Integr@ de @firma.
	 * @param signatureFormat formato de firma para el que se desea obtener el identificador DSS del formato de firma electrónica.
	 * @return formato de firma electrónica requerido por el API Integr@ de @firma.
	 */
	/*
	private static SignatureFormatEnum translateSignatureFormat(SignatureFormat signatureFormat) {
		SignatureFormatEnum res;
		
		res = null;
		
		switch(signatureFormat){
			case CAdES_BES:
				res = SignatureFormatEnum.CAdES_BES;
				break;
			case CAdES_EPES:
				res = SignatureFormatEnum.CAdES_EPES;
				break;
			case CAdES_T:
				res = SignatureFormatEnum.CAdES_T;
				break;
			case CAdES_X:
				res = SignatureFormatEnum.CAdES_X;
				break;
			case CAdES_X1:
				res = SignatureFormatEnum.CAdES_X1;
				break;
			case CAdES_X2:
				res = SignatureFormatEnum.CAdES_X2;
				break;
			case CAdES_XL:
				res = SignatureFormatEnum.CAdes_XL;
				break;
			case CAdES_XL1:
				res = SignatureFormatEnum.CAdES_XL1;
				break;
			case CAdES_XL2:
				res = SignatureFormatEnum.CAdES_XL2;
				break;
			case CAdES_A:
				res = SignatureFormatEnum.CAdES_A;
				break;
			case XAdES_BES:
				res = SignatureFormatEnum.XAdES_BES;
				break;
			case XAdES_EPES:
				res = SignatureFormatEnum.XAdES_EPES;
				break;
			case XAdES_T:
				res = SignatureFormatEnum.XAdES_T;
				break;
			case XAdES_X:
				res = SignatureFormatEnum.XAdES_X;
				break;
			case XAdES_X1:
				res = SignatureFormatEnum.XAdES_X1;
				break;
			case XAdES_X2:
				res = SignatureFormatEnum.XAdES_X2;
				break;
			case XAdES_XL:
				res = SignatureFormatEnum.XAdES_XL;
				break;
			case XAdES_XL1:
				res = SignatureFormatEnum.XAdES_XL1;
				break;
			case XAdES_XL2:
				res = SignatureFormatEnum.XAdES_XL2;
				break;
			case XAdES_A:
				res = SignatureFormatEnum.XAdES_A;
				break;
			case PAdES_Basic:
				res = SignatureFormatEnum.PAdES;
				break;
			case PAdES_BES:
				res = SignatureFormatEnum.PAdES_BES;
				break;
			case PAdES_EPES:
				res = SignatureFormatEnum.PAdES_EPES;
				break;
			case PAdES_LTV:
				res = SignatureFormatEnum.PAdES_LTV;
				break;
		}

		return res;
	}
	*/
	
}
