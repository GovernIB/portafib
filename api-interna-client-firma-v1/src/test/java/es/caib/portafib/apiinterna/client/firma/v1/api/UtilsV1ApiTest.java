/*
 * API Interna de PortaFIB de consulta de serveis d'utilitat
 * Conjunt de Serveis REST de PortaFIB per atendre consultes generiques de Portafib
 *
 * The version of the OpenAPI document: 1.0-SNAPSHOT
 * Contact: otae@fundaciobit.org
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package es.caib.portafib.apiinterna.client.firma.v1.api;


import org.junit.Test;

import es.caib.portafib.apiinterna.client.firma.v1.model.AvailableLanguagesRest;
import es.caib.portafib.apiinterna.client.firma.v1.model.AvailableProfilesRest;
import es.caib.portafib.apiinterna.client.firma.v1.model.LlistaTipusDocumentalRest;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiClient;
import es.caib.portafib.apiinterna.client.firma.v1.services.ApiException;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.jboss.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * API tests for UtilsV1Api
 */
public class UtilsV1ApiTest {
	
	protected Logger log = Logger.getLogger(getClass());


    private final UtilsV1Api api = new UtilsV1Api();

    public static void main(String[] args) {

    	UtilsV1ApiTest utilsV1ApiTest = new UtilsV1ApiTest();
		try {
			
			utilsV1ApiTest.callTipusDocumentalListTest();
			
			//utilsV1ApiTest.callPerfilsDeFirmaListTest();
			
			//utilsV1ApiTest.callAvailableLanguagesTest();

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
    
    
    @Before
	 public void beforeMethod() {
	     try {
			org.junit.Assume.assumeTrue(getApi()!= null);
		} catch (Exception e) {
			Assume.assumeFalse("Error instanciant API: "+e.getMessage(), true);
		}
	 }
    
    @Test
	public void callTipusDocumentalListTest() {
		
        String language = "ca";
		String appuser = "";
		String expectedError = null;
		//Comu
		
        internalTestTipusDocumentalList(language, appuser, expectedError);
	}
    
    @Test
	public void callTipusDocumentalListErrorUserTest() {
		
        String language = "ca";
		String appuser = "pepito";
		String expectedError = "{\"code\":400,\"errorMessage\":\"No existeix l'usuari aplicacio introduit\"}";
		//Comu
		
        internalTestTipusDocumentalList(language, appuser, expectedError);
	}
    
    
    @Test
	public void callPerfilsDeFirmaListTest() {
		
        String language = "ca";
		String expectedError = null;
		//Comu
		
        internalTestPerfilsDeFirmaList(language, expectedError);
	}
    
    @Test
	public void callAvailableLanguagesTest() {
		
        String language = "ca";
		String expectedError = null;
		
		//Comu
        internalTestAvailableLanguages(language, expectedError);
	}    
    
    private AvailableLanguagesRest internalTestAvailableLanguages(String language, String expectedError) {
    	UtilsV1Api api;
        try {
			api = getApi();
		} catch (Exception e) {
			Assert.fail("Error instanciant API => " + e.getMessage());
			return null;
		}
        
        try {
        	AvailableLanguagesRest response = api.getAvailableLanguages(language);
			if(expectedError != null) {
				Assert.fail("S'espera un error i la cridada ha funcionat.");
			}
			System.out.println(response.toString());
			return response;
		} catch (ApiException e) {
			
			if(expectedError == null) {
				Assert.fail("Error a la cridada de  TEST => " + e.getMessage());
			}
			
			Assert.assertEquals(e.getMessage(), expectedError);
			return null;
		}
    }
    
    
    private AvailableProfilesRest internalTestPerfilsDeFirmaList(String language, String expectedError) {
    	UtilsV1Api api;
        try {
			api = getApi();
		} catch (Exception e) {
			Assert.fail("Error instanciant API => " + e.getMessage());
			return null;
		}
        
        try {
        	AvailableProfilesRest response = api.getAvailableProfiles(language);
			if(expectedError != null) {
				Assert.fail("S'espera un error i la cridada ha funcionat.");
			}
			System.out.println(response.toString());
			return response;
		} catch (ApiException e) {
			
			if(expectedError == null) {
				Assert.fail("Error a la cridada de  TEST => " + e.getMessage());
			}
			
			Assert.assertEquals(e.getMessage(), expectedError);
			return null;
		}
    }
    
    private LlistaTipusDocumentalRest internalTestTipusDocumentalList(String language, String appuser, String expectedError) {
    	UtilsV1Api api;
        try {
			api = getApi();
		} catch (Exception e) {
			
			Assert.fail("Error instanciant API => " + e.getMessage());
			return null;
		}
        
	    try {
			LlistaTipusDocumentalRest response = api.tipusdocumentalslist(language, appuser);
			if(expectedError != null) {
				Assert.fail("S'espera un error i la cridada ha funcionat.");
			}
			System.out.println(response.toString());
			return response;
		} catch (ApiException e) {
			
			if(expectedError == null) {
				Assert.fail("Error a la cridada de  TEST => " + e.getMessage());
			}
			
			Assert.assertEquals(e.getMessage(), expectedError);
			return null;
		}
	}
    
    protected UtilsV1Api getApi() throws Exception {
		Properties prop = new Properties();
		File f = new File("test.properties");
		if (!f.exists()) {
			return null;
		}
        prop.load(new FileInputStream(f));

        String basePath = prop.getProperty("basePath");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        
        ApiClient client = new ApiClient();
        client.setBasePath(basePath);
        client.setUsername(username);
        client.setPassword(password);

        UtilsV1Api api = new UtilsV1Api(client);
        return api;
    }
    
}