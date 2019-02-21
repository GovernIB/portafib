package org.fundaciobit.plugins.signature.api.constants;

/**
 * <p>Class that represents the different signature forms.</p>
 * <b>Project:</b><p>Library for the integration with the services of @Firma, eVisor and TS@.</p>
 * @version 1.0, 19/11/2014.
 */
public enum SignatureTypeFormEnumForUpgrade {

    /**
     * Attribute that represents type and format of a signature.
     */
 // CMS(1,"CMS",  SignatureTypeForUpgrade.CMS, null),
 // CAdES(2,"CAdES",  SignatureTypeForUpgrade.CADES, null),
 // CAdES_BES(3,"CAdES-BES",  SignatureTypeForUpgrade.CADES, SignatureFormForUpgrade.BES),
 // CAdES_EPES(4,"CAdES_EPES",  SignatureTypeForUpgrade.CADES, SignatureFormForUpgrade.EPES),
  CAdES_T(5,"CAdES-T",  SignatureTypeForUpgrade.CADES, SignatureFormForUpgrade.T),
  CAdES_X(6,"CAdES-X",  SignatureTypeForUpgrade.CADES, SignatureFormForUpgrade.X),
  CAdES_X1(7,"CAdES-X1",  SignatureTypeForUpgrade.CADES, SignatureFormForUpgrade.X_1),
  CAdES_X2(8,"CAdES-X2",  SignatureTypeForUpgrade.CADES, SignatureFormForUpgrade.X_2),
  CAdES_XL(9,"CAdES-XL",  SignatureTypeForUpgrade.CADES, SignatureFormForUpgrade.X_L),
  CAdES_XL1(10,"CAdES-XL1",  SignatureTypeForUpgrade.CADES, SignatureFormForUpgrade.X_L_1),
  CAdES_XL2(11,"CAdES-XL2",  SignatureTypeForUpgrade.CADES, SignatureFormForUpgrade.X_L_2),
  CAdES_A(12,"CAdES-A",  SignatureTypeForUpgrade.CADES, SignatureFormForUpgrade.A),
 // CAdES_BASELINE(13,"CAdES_BASELINE",  SignatureTypeForUpgrade.CADES_BASELINE_2_2_1, null),
 // CAdES_B_LEVEL(14,"CAdES_B_LEVEL",  SignatureTypeForUpgrade.CADES_BASELINE_2_2_1, SignatureFormForUpgrade.B_LEVEL),
  CAdES_T_LEVEL(15,"CAdES T-LEVEL",  SignatureTypeForUpgrade.CADES_BASELINE_2_2_1, SignatureFormForUpgrade.T_LEVEL),
  CAdES_LT_LEVEL(16,"CAdES LT-LEVEL",  SignatureTypeForUpgrade.CADES_BASELINE_2_2_1, SignatureFormForUpgrade.LT_LEVEL),
  CAdES_LTA_LEVEL(17,"CAdES LTA-LEVEL",  SignatureTypeForUpgrade.CADES_BASELINE_2_2_1, SignatureFormForUpgrade.LTA_LEVEL),
  
  //XAdES(18,"XAdES",  SignatureTypeForUpgrade.XADES_V_1_3_2, null),
  //XAdES_BES(19,"XAdES_BES",  SignatureTypeForUpgrade.XADES_V_1_3_2, SignatureFormForUpgrade.BES),
  //XAdES_EPES(20,"XAdES_EPES",  SignatureTypeForUpgrade.XADES_V_1_3_2, SignatureFormForUpgrade.EPES),
  XAdES_T(21,"XAdES-T",  SignatureTypeForUpgrade.XADES_V_1_3_2, SignatureFormForUpgrade.T),
  XAdES_C(22,"XAdES-C",  SignatureTypeForUpgrade.XADES_V_1_3_2, SignatureFormForUpgrade.C),
  XAdES_X(23,"XAdES-X",  SignatureTypeForUpgrade.XADES_V_1_3_2, SignatureFormForUpgrade.X),
  XAdES_X1(24,"XAdES-X1",  SignatureTypeForUpgrade.XADES_V_1_3_2, SignatureFormForUpgrade.X_1),
  XAdES_X2(25,"XAdES-X2",  SignatureTypeForUpgrade.XADES_V_1_3_2, SignatureFormForUpgrade.X_2),
  XAdES_XL(26,"XAdES-XL",  SignatureTypeForUpgrade.XADES_V_1_3_2, SignatureFormForUpgrade.X_L),
  XAdES_XL1(27,"XAdES-XL1",  SignatureTypeForUpgrade.XADES_V_1_3_2, SignatureFormForUpgrade.X_L_1),
  XAdES_XL2(28,"XAdES-XL2",  SignatureTypeForUpgrade.XADES_V_1_3_2, SignatureFormForUpgrade.X_L_2),
  XAdES_A(29,"XAdES-A",  SignatureTypeForUpgrade.XADES_V_1_3_2, SignatureFormForUpgrade.A),
 // XAdES_BASELINE(30,"XAdES_BASELINE",  SignatureTypeForUpgrade.XADES_BASELINE_2_1_1, null),
 // XAdES_B_LEVEL(31,"XAdES_B_LEVEL",  SignatureTypeForUpgrade.XADES_BASELINE_2_1_1, SignatureFormForUpgrade.B_LEVEL),
  XAdES_T_LEVEL(32,"XAdES T-LEVEL",  SignatureTypeForUpgrade.XADES_BASELINE_2_1_1, SignatureFormForUpgrade.T_LEVEL),
  XAdES_LT_LEVEL(33,"XAdES LT-LEVEL",  SignatureTypeForUpgrade.XADES_BASELINE_2_1_1, SignatureFormForUpgrade.LT_LEVEL),
  XAdES_LTA_LEVEL(34,"XAdES LTA-LEVEL",  SignatureTypeForUpgrade.XADES_BASELINE_2_1_1, SignatureFormForUpgrade.LTA_LEVEL),
  
  //ODF(35,"ODF",  SignatureTypeForUpgrade.ODF, null),
  
  //PDF(36,"PDF",  SignatureTypeForUpgrade.PDF, null),
  //PAdES(37,"PAdES",  SignatureTypeForUpgrade.PADES, SignatureFormForUpgrade.PADES_BASIC),
  //PAdES_BES(38,"PAdES_BES",  SignatureTypeForUpgrade.PADES, SignatureFormForUpgrade.PADES_BES),
  //PAdES_EPES(39,"PAdES_EPES",  SignatureTypeForUpgrade.PADES, SignatureFormForUpgrade.PADES_EPES),
  PAdES_LTV(40,"PAdES-LTV",  SignatureTypeForUpgrade.PADES, SignatureFormForUpgrade.PADES_LTV),
  //PAdES_BASELINE(41,"PAdES_BASELINE",  SignatureTypeForUpgrade.PADES_BASELINE_2_1_1, null),
  //PAdES_B_LEVEL(42,"PAdES_B_LEVEL",  SignatureTypeForUpgrade.PADES_BASELINE_2_1_1, SignatureFormForUpgrade.B_LEVEL),
  PAdES_T_LEVEL(43,"PAdES T-LEVEL",  SignatureTypeForUpgrade.PADES_BASELINE_2_1_1, SignatureFormForUpgrade.T_LEVEL),
  PAdES_LT_LEVEL(44,"PAdES LT-LEVEL",  SignatureTypeForUpgrade.PADES_BASELINE_2_1_1, SignatureFormForUpgrade.LT_LEVEL),
  PAdES_LTA_LEVEL(45,"PAdES LTA-LEVEL",  SignatureTypeForUpgrade.PADES_BASELINE_2_1_1, SignatureFormForUpgrade.LTA_LEVEL);

  
  private final int id;
  
  private final String name;
  
  /**
   * Attribute that represents the URI of the signature type.
   */
  private final String type;

  /**
   * Attribute that represents the URI of the signature format.
   */
  private final String format;

  /**
   * Constructor method for the class HashAlgorithmEnum.java.
   * 
   * @param uriTypeParam
   *          Parameter that represents the URI of the signature type.
   * @param uriFormatParam
   *          Parameter that represents the URI of the signature format.
   */
  private SignatureTypeFormEnumForUpgrade(int id, String name, String uriTypeParam, String uriFormatParam) {
    this.id = id;
    this.name = name;
    this.type = uriTypeParam;
    this.format = uriFormatParam;
  }

  /**
   * Gets the value of the attribute {@link #type}.
   * 
   * @return the value of the attribute {@link #type}.
   */
  public String getType() {
    return type;
  }

  /**
   * Gets the value of the attribute {@link #format}.
   * 
   * @return the value of the attribute {@link #format}.
   */
  public String getFormat() {
    return format;
  }

  /**
   * 
   * @return
   */
  public int getId() {
    return id;
  }

  /**
   * 
   * @return
   */
  public String getName() {
    return name;
  }

}
