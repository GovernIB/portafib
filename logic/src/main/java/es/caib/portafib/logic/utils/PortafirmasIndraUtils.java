package es.caib.portafib.logic.utils;

import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
public final class PortafirmasIndraUtils {

  /** circuito de firmas bloqueado */
  public static final int DOCUMENTO_BLOQUEADO = 0;

  /** circuito de firmas incompleto */
  public static final int DOCUMENTO_PENDIENTE = 1;

  /** circuito de firmas finalizado */
  public static final int DOCUMENTO_FINALIZADO = 2;

  /** circuito de firmas rechazado */
  public static final int DOCUMENTO_RECHAZADO = 3;


  
  public static int peticioEstat2IndraEstat(int estatPeticio,
      String estatDeFirmaUsuariEntitatID ) {

    //int estatPeticio = fe.getTipusEstatPeticioDeFirmaID();

    int estatIndra;

    switch ( estatPeticio) {

    case (int) Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES:
      estatIndra = DOCUMENTO_PENDIENTE;
      break;
    case (int) Constants.TIPUSESTATPETICIODEFIRMA_REBUTJAT:
      estatIndra = DOCUMENTO_RECHAZADO;
      break;
    case (int) Constants.TIPUSESTATPETICIODEFIRMA_FIRMAT:
      if (estatDeFirmaUsuariEntitatID == null) {
        estatIndra = DOCUMENTO_FINALIZADO;
      } else {
        estatIndra = DOCUMENTO_PENDIENTE;
      }
      break;

    default:
    case (int) Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT:
    case (int) Constants.TIPUSESTATPETICIODEFIRMA_PAUSAT:
      estatIndra = DOCUMENTO_BLOQUEADO;
      break;
    }

    return estatIndra;

  }
  
  
  
}
