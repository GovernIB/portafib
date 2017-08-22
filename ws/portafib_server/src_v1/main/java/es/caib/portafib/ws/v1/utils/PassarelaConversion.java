package es.caib.portafib.ws.v1.utils;

import java.util.ArrayList;
import java.util.List;

import es.caib.portafib.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.passarela.api.PassarelaPolicyInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSecureVerificationCodeStampInfo;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesTableHeader;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.ws.v1.impl.CustodiaInfoBean;

/**
 * 
 * @author anadal
 *
 */
public class PassarelaConversion {

  public static es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus convert(
      es.caib.portafib.ws.v1.impl.PassarelaSignatureStatusWs pssWs) {

    if (pssWs == null) {
      return null;
    }

    return new es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus(
        pssWs.getStatus(), pssWs.getErrorMessage(), pssWs.getErrorStackTrace());

  }

  public static es.caib.portafib.ws.v1.impl.PassarelaSignatureStatusWs convert(
      es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus pss) {

    if (pss == null) {
      return null;
    }

    return new es.caib.portafib.ws.v1.impl.PassarelaSignatureStatusWs(pss.getStatus(),
        pss.getErrorMessage(), pss.getErrorStackTrace());

  }

  public static es.caib.portafib.ws.v1.impl.PassarelaFullResultsWs convert(
      PassarelaFullResults pfr) {

    if (pfr == null) {
      return null;
    }

    return new es.caib.portafib.ws.v1.impl.PassarelaFullResultsWs(
        convert(pfr.getSignaturesSetStatus()), convert(pfr.getSignResults()));

  }

  public static List<es.caib.portafib.ws.v1.impl.PassarelaSignatureResultWs> convert(
      List<PassarelaSignatureResult> psrList) {

    if (psrList == null) {
      return null;
    }

    List<es.caib.portafib.ws.v1.impl.PassarelaSignatureResultWs> psrWsList;
    psrWsList = new ArrayList<es.caib.portafib.ws.v1.impl.PassarelaSignatureResultWs>();

    for (PassarelaSignatureResult passarelaSignatureResult : psrList) {
      psrWsList.add(convert(passarelaSignatureResult));
    }

    return psrWsList;

  }

  public static es.caib.portafib.ws.v1.impl.PassarelaSignatureResultWs convert(
      PassarelaSignatureResult psr) {

    if (psr == null) {
      return null;
    }

    return new es.caib.portafib.ws.v1.impl.PassarelaSignatureResultWs(psr.getStatus(),
        psr.getErrorMessage(), psr.getErrorStackTrace(), convert(psr.getSignedFile()),
        psr.getSignID());

  }

  public static PassarelaSignaturesSet convert(
      es.caib.portafib.ws.v1.impl.PassarelaSignaturesSetWs pssWs) {

    if (pssWs == null) {
      return null;
    }

    return new PassarelaSignaturesSet(pssWs.getSignaturesSetID(), pssWs.getExpiryDate(),
        convert(pssWs.getCommonInfoSignature()), convert(pssWs.getFileInfoSignatureArray()));

  }

  public static PassarelaFileInfoSignature[] convert(
      es.caib.portafib.ws.v1.impl.PassarelaFileInfoSignatureWs[] fisArrayWs) {
    if (fisArrayWs == null) {
      return null;
    }

    PassarelaFileInfoSignature[] fisArray = new PassarelaFileInfoSignature[fisArrayWs.length];

    for (int i = 0; i < fisArrayWs.length; i++) {
      fisArray[i] = convert(fisArrayWs[i]);
    }

    return fisArray;
  }

  public static PassarelaFileInfoSignature convert(
      es.caib.portafib.ws.v1.impl.PassarelaFileInfoSignatureWs fisWs) {
    if (fisWs == null) {
      return null;
    }

    return new PassarelaFileInfoSignature(convert(fisWs.getFileToSign()), fisWs.getSignID(),
        fisWs.getName(), fisWs.getReason(), fisWs.getLocation(), fisWs.getSignerEmail(),
        fisWs.getSignNumber(), fisWs.getLanguageSign(), fisWs.getSignType(),
        fisWs.getSignAlgorithm(), fisWs.getSignMode(), fisWs.getSignaturesTableLocation(),
        convert(fisWs.getSignaturesTableHeader()),
        convert(fisWs.getSecureVerificationCodeStampInfo()), fisWs.isUseTimeStamp(),
        convert(fisWs.getCustodiaInfo()));

  }

  public static PassarelaSecureVerificationCodeStampInfo convert(
      es.caib.portafib.ws.v1.impl.PassarelaSecureVerificationCodeStampInfoWs svcsiWs) {
    if (svcsiWs == null) {
      return null;
    }

    return new PassarelaSecureVerificationCodeStampInfo(svcsiWs.getPages(),
        svcsiWs.getMessage(), svcsiWs.getMessagePosition(), svcsiWs.getBarCodeType(),
        svcsiWs.getBarCodePosition(), svcsiWs.getBarCodeText());
  }

  public static es.caib.portafib.model.bean.CustodiaInfoBean convert(CustodiaInfoBean __bean) {
    if (__bean == null) {
      return null;
    }
    es.caib.portafib.model.bean.CustodiaInfoBean __tmp;
    __tmp = new es.caib.portafib.model.bean.CustodiaInfoBean();

    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setNomPlantilla(__bean.getNomPlantilla());
    __tmp.setCustodiaDocumentID(__bean.getCustodiaDocumentID());
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setCustodiaPluginParameters(__bean.getCustodiaPluginParameters());
    __tmp.setCustodiar(__bean.isCustodiar());
    __tmp.setUrlFitxerCustodiat(__bean.getUrlFitxerCustodiat());
    __tmp.setPagines(__bean.getPagines());
    __tmp.setMissatge(__bean.getMissatge());
    __tmp.setMissatgePosicioPaginaID(__bean.getMissatgePosicioPaginaID());
    __tmp.setCodiBarresID(__bean.getCodiBarresID());
    __tmp.setCodiBarresPosicioPaginaID(__bean.getCodiBarresPosicioPaginaID());
    __tmp.setCodiBarresText(__bean.getCodiBarresText());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setTitolPeticio(__bean.getTitolPeticio());
    __tmp.setDataCustodia(__bean.getDataCustodia());
    __tmp.setEditable(__bean.isEditable());
    return __tmp;
  }

  public static PassarelaSignaturesTableHeader convert(
      es.caib.portafib.ws.v1.impl.PassarelaSignaturesTableHeaderWs sthWs) {
    if (sthWs == null) {
      return null;
    }

    return new PassarelaSignaturesTableHeader(sthWs.getLogoJpeg(), sthWs.getTitle(),
        sthWs.getTitleFieldLabel(), sthWs.getTitleFieldValue(),
        sthWs.getDescriptionFieldLabel(), sthWs.getDescriptionFieldValue(),
        sthWs.getSignatureLabel());

  }

  public static FitxerBean convert(es.caib.portafib.ws.v1.impl.FitxerBean __bean) {
    if (__bean == null) {
      return null;
    }
    FitxerBean __tmp = new FitxerBean();
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setTamany(__bean.getTamany());
    __tmp.setMime(__bean.getMime());
    // DataHandler
    __tmp.setData(__bean.getData());
    // EncryptedFileID
    __tmp.setEncryptedFileID(__bean.getEncryptedFileID());
    return __tmp;
  }

  public static es.caib.portafib.ws.v1.impl.FitxerBean convert(FitxerBean __bean) {
    if (__bean == null) {
      return null;
    }
    es.caib.portafib.ws.v1.impl.FitxerBean __tmp = new es.caib.portafib.ws.v1.impl.FitxerBean();
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setTamany(__bean.getTamany());
    __tmp.setMime(__bean.getMime());
    // DataHandler
    __tmp.setData(__bean.getData());
    // EncryptedFileID
    __tmp.setEncryptedFileID(__bean.getEncryptedFileID());
    return __tmp;
  }

  public static PassarelaCommonInfoSignature convert(
      es.caib.portafib.ws.v1.impl.PassarelaCommonInfoSignatureWs cisWs) {

    if (cisWs == null) {
      return null;
    }

    return new PassarelaCommonInfoSignature(cisWs.getLanguageUI(),
        cisWs.getFiltreCertificats(), cisWs.getUsername(), cisWs.getAdministrationID(),
        cisWs.getUrlFinal(), convert(cisWs.getPolicyInfoSignature()));

  }

  public static PassarelaPolicyInfoSignature convert(
      es.caib.portafib.ws.v1.impl.PassarelaPolicyInfoSignatureWs pisWs) {

    if (pisWs == null) {
      return null;
    }

    return new PassarelaPolicyInfoSignature(pisWs.getPolicyIdentifier(),
        pisWs.getPolicyIdentifierHash(), pisWs.getPolicyIdentifierHashAlgorithm(),
        pisWs.getPolicyUrlDocument());
  }
}
