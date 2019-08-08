package es.caib.portafib.testjpa;

import es.caib.portafib.jpa.BlocDeFirmesJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.PortaFIBJPADaoManagers;
import es.caib.portafib.model.PortaFIBDaoManager;
import es.caib.portafib.model.dao.IFirmaManager;
import es.caib.portafib.model.dao.IPeticioDeFirmaManager;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.BlocDeFirmesFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 * @author areus
 */
public class TestFitxersPeticioFirma {

    public static void main(String[] args) {
        I18NCommonUtils.BUNDLES = new String[] {"missatges"};
        new TestFitxersPeticioFirma().main();
    }

    private void main() {
        try {
            System.out.println("Test petició");


            // USING GENAPP
            // ============

            Properties prop = new Properties();

            prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            //prop.put("hibernate.show_sql", "true");
            prop.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
            prop.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/portafib2");
            prop.put("javax.persistence.jdbc.user", "portafib");
            prop.put("javax.persistence.jdbc.password", "portafib");


            prop.put("hibernate.connection.driver_class", "org.postgresql.Driver");
            prop.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/portafib2");
            prop.put("hibernate.connection.username", "portafib");
            prop.put("hibernate.connection.password", "portafib");


            EntityManagerFactory emf;

            // Veure persistence.xml
            emf = Persistence.createEntityManagerFactory("portafibDBStandalone", prop);

            EntityManager em = emf.createEntityManager();
            PortaFIBDaoManager.setDaoManagers(new PortaFIBJPADaoManagers(em));

            Locale loc = new Locale("ca");
            List<FitxerPeticioFirma> llistaFitxers = new ArrayList<FitxerPeticioFirma>();

            //long peticioFirmaID = 1155; // no iniciat
            //long peticioFirmaID = 1147; // no iniciat
            //long peticioFirmaID = 1127; // en procés després de fer pausa al primer bloc
            long peticioFirmaID = 1111; // finalitzat
            //long peticioFirmaID = 1086;
            //long peticioFirmaID = 1046; // flux senzill de dos blocs amb una sola firma obligatoria finalitzat


            {
                /*
                Estat 0, no iniciat
                    - fitxer a firmar contendrà l'objecte de la petició.
                    - adaptat serà null pq no està iniciat
                    - orig deatched serà null pq no està iniciat
                    Firma/Bloc els fitxers seran tots null perquè no s'ha iniciat
                        - tipusestatdefirmafinalid serà null
                        - numFirmadocument serà null
                        - datafinalitzacio del bloc serà null

                Estat 1, en procés
                    - fitxer a firmar contendrà l'objecte de la petició.
                    - adaptat serà contendrà el fitxer adaptat
                    - orig deatched serà null TODO no sé pq

                 */


                System.out.println(" ========= TROBAR LA PETICIÓ FIRMA " + peticioFirmaID);
                IPeticioDeFirmaManager peticioFirmaManager = PortaFIBDaoManager.getDaoManagers().getPeticioDeFirmaManager();

                PeticioDeFirma peticio = peticioFirmaManager.findByPrimaryKey(peticioFirmaID);

                System.out.println("Petició [" + peticio.getPeticioDeFirmaID() + "]"
                        + ", estat [" + I18NCommonUtils.tradueix(loc, "tipusestatpeticiodefirma." + peticio.getTipusEstatPeticioDeFirmaID()) + "]");


                System.out.println("Fitxer a firmar [" + peticio.getFitxerAFirmarID() + "]");
                System.out.println("Fitxer adaptat [" + peticio.getFitxerAdaptatID() + "]");
                System.out.println("Fitxer orig detached [" + peticio.getFirmaOriginalDetachedID() + "]");

                llistaFitxers.add( peticio.getFitxerAFirmarID() == null
                        ? new FitxerPeticioFirma("Fitxer a firmar. No hauria de ser null", null)
                        : new FitxerPeticioFirma(peticio.getFitxerAFirmar(), "Fitxer a firmar", null ));


                llistaFitxers.add( peticio.getFitxerAdaptatID() == null
                        //TODO en funció de l'estat dir si encara no s'ha creat o ja s'h descartat
                        ? new FitxerPeticioFirma("Fitxer adaptat encara no s'ha creat o ja s'ha descartat", null)
                        : new FitxerPeticioFirma(peticio.getFitxerAFirmar(), "Fitxer adaptat", null ));

                llistaFitxers.add( peticio.getFirmaOriginalDetachedID() == null
                        ? new FitxerPeticioFirma("Fitxer original detached no s'ha creat", null)
                        : new FitxerPeticioFirma(peticio.getFirmaOriginalDetached(), "Fitxer adaptat", null ));
            }

            {

                System.out.println(" ========= LLISTAR FIRMES DE LA PETICIÓ FIRMA " + peticioFirmaID);

                IFirmaManager firmaManager = PortaFIBDaoManager.getDaoManagers().getFirmaManager();

                Where where = new FirmaQueryPath().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA().PETICIODEFIRMAID()
                        .equal(peticioFirmaID);
                OrderBy orderBy = new OrderBy(BlocDeFirmesFields.ORDRE, OrderType.ASC);


                List<Firma> llistaFirmes = firmaManager.select(where, orderBy);

                int maxFirma = 0;
                for (Firma firma : llistaFirmes) {
                    if (firma.getNumFirmaDocument() != null && firma.getNumFirmaDocument() > maxFirma) {
                        maxFirma = firma.getNumFirmaDocument();
                    }
                }

                int counter = 0;
                for (Firma firma : llistaFirmes) {
                    counter++;

                    Hibernate.initialize(((FirmaJPA)firma).getBlocDeFirmes());
                    Hibernate.initialize(((FirmaJPA)firma).getEstatDeFirmas());

                    System.out.println(counter + ".- [" + firma.getFirmaID()
                            + "] fitxer[" + firma.getFitxerFirmatID() + "]"
                            + ", destinatari[" + firma.getDestinatariID() + "]"
                            + ", numFirmaDocument[" + firma.getNumFirmaDocument() + "]"
                            + ", tipusestatdefirmafinalid[" +
                                    (firma.getTipusEstatDeFirmaFinalID() != null
                                            ? I18NCommonUtils.tradueix(loc, "tipusestatdefirmafinal." + firma.getTipusEstatDeFirmaFinalID())
                                            : null) + "]"
                            + "\n\t\t"
                            + ", numseriecertificat[" + firma.getNumeroSerieCertificat()  + "]"
                            + ", blocdefirmesid[" + ((FirmaJPA) firma).getBlocDeFirmes().getBlocDeFirmesID() + "]"
                            + ", datafinalitzaciobloc[" + ((FirmaJPA) firma).getBlocDeFirmes().getDataFinalitzacio() + "]"
                            + "\n\t\t"
                            + ", ordrebloc[" + ((FirmaJPA) firma).getBlocDeFirmes().getOrdre() + "]");

                    for (EstatDeFirma estatDeFirma : ((FirmaJPA)firma).getEstatDeFirmas()) {
                        System.out.println("\t\t\t == ESTATS DE FIRMA ==");
                        System.out.println("\t\t\t - Usuari[" + estatDeFirma.getUsuariEntitatID() + "]" +
                                ", descripció[" + estatDeFirma.getDescripcio() + "]" +
                                ", tipusestatdefirmafinalid[" +
                                        (estatDeFirma.getTipusEstatDeFirmaFinalID() != null
                                                ? I18NCommonUtils.tradueix(loc, "tipusestatdefirmafinal." + estatDeFirma.getTipusEstatDeFirmaFinalID())
                                                : null) + "]" +
                                ", tipusestatdefirmainicialid[" +
                                I18NCommonUtils.tradueix(loc, "tipusestatdefirmainicial." + estatDeFirma.getTipusEstatDeFirmaInicialID()) + "]"
                        );
                    }


                    BlocDeFirmesJPA blocDeFirmes = ((FirmaJPA) firma).getBlocDeFirmes();
                    if (firma.getNumFirmaDocument() == null) {
                        if (firma.getTipusEstatDeFirmaFinalID() == null) {
                            llistaFitxers.add( new FitxerPeticioFirma("La firma encara no s'ha realitzat", blocDeFirmes.getOrdre()));
                        } else if (firma.getTipusEstatDeFirmaFinalID() == ConstantsV2.TIPUSESTATDEFIRMAFINAL_DESCARTAT){
                            llistaFitxers.add( new FitxerPeticioFirma("La firma no s'ha realitzat perque no calia", blocDeFirmes.getOrdre()));
                        }
                    } else {
                        if (firma.getFitxerFirmat() == null) {
                            if (firma.getNumFirmaDocument() < maxFirma) {
                                llistaFitxers.add( new FitxerPeticioFirma("La firma s'ha descartat perquè no és la darrera", blocDeFirmes.getOrdre()));
                            } else {
                                llistaFitxers.add( new FitxerPeticioFirma("La firma s'ha descartat i això no hauria de passar perquè és la darrera", blocDeFirmes.getOrdre()));
                            }
                        } else {
                            llistaFitxers.add( new FitxerPeticioFirma(firma.getFitxerFirmat(), "Fitxer firmat", blocDeFirmes.getOrdre()));
                        }
                    }

                }
                System.out.println(" ========= FI LLISTAT FIRMES PETICIÓ FIRMA " + peticioFirmaID);
            }


            {
                System.out.println(" ========= ===================================== =========");
                System.out.println(" ========= INFORMACIÓ FITXERS PETICIÓ FIRMA " + peticioFirmaID);
                System.out.println(" ========= ===================================== =========");

                int counter = 0;
                for (FitxerPeticioFirma fitxer : llistaFitxers) {
                    counter++;

                    System.out.println(counter + "." +
                            " id[" + fitxer.getFitxerID() + "]" +
                            ", nom[" + fitxer.getNom() + "]" +
                            ", descripcio[" + fitxer.getDescripcio() + "]" +
                            ", explicacio[" + fitxer.getExplicacio() + "]" +
                            ", ordreBloc[" + fitxer.getOrdreBloc() + "]"
                    );

                }
            }

            System.out.println("Good Bye!");

        } catch (I18NException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}