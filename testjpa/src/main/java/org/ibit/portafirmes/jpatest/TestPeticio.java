package org.ibit.portafirmes.jpatest;

import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.PortaFIBJPADaoManagers;
import es.caib.portafib.model.PortaFIBDaoManager;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.dao.IFirmaManager;
import es.caib.portafib.model.dao.IPeticioDeFirmaManager;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.BlocDeFirmesFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
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
public class TestPeticio {

    public static void main(String[] args) {
        I18NCommonUtils.BUNDLES = new String[] {"missatges"};
        new TestPeticio().main();
    }

    public void main() {
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
            List<Fitxer> llistaFitxers = new ArrayList<Fitxer>();

            //long peticioFirmaID = 1155; // no iniciat
            long peticioFirmaID = 1147; // no iniciat
            //long peticioFirmaID = 1127; // en procés després de fer pausa al primer bloc
            //long peticioFirmaID = 1111; // finalitzat
            //long peticioFirmaID = 1086;
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
                        ? createFitxerNull("Fitxer a firmar de petició")
                        : peticio.getFitxerAFirmar() );

                llistaFitxers.add( peticio.getFitxerAdaptatID() == null
                        ? createFitxerNull("Fitxer adaptat")
                        : peticio.getFitxerAdaptat() );

                llistaFitxers.add( peticio.getFirmaOriginalDetachedID() == null
                        ? createFitxerNull("Fitxer original detached")
                        : peticio.getFirmaOriginalDetached() );

            }



            {

                System.out.println(" ========= LLISTAR FIRMES DE LA PETICIÓ FIRMA " + peticioFirmaID);

                IFirmaManager firmaManager = PortaFIBDaoManager.getDaoManagers().getFirmaManager();

                Where where = new FirmaQueryPath().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA().PETICIODEFIRMAID()
                        .equal(peticioFirmaID);
                OrderBy orderBy = new OrderBy(BlocDeFirmesFields.ORDRE, OrderType.ASC);


                List<Firma> llistaFirmes = firmaManager.select(where, orderBy);

                int counter = 0;
                for (Firma firma : llistaFirmes) {
                    counter++;

                    Hibernate.initialize(((FirmaJPA)firma).getBlocDeFirmes());

                    System.out.println(counter + ".- [" + firma.getFirmaID()
                            + "] fitxer: [" + firma.getFitxerFirmatID() + "]"
                            + ", destinatari [" + firma.getDestinatariID() + "]"
                            + ", numFirmaDocument [" + firma.getNumFirmaDocument() + "]"
                            + ", tipusestatdefirmafinalid [" +
                                    (firma.getTipusEstatDeFirmaFinalID() != null
                                            ? I18NCommonUtils.tradueix(loc, "tipusestatdefirmafinal." + firma.getTipusEstatDeFirmaFinalID())
                                            : null) + "]"
                            + "\n\t\t"
                            + ", numseriecertificat [" + firma.getNumeroSerieCertificat()  + "]"
                            + ", blocdefirmesid [" + ((FirmaJPA) firma).getBlocDeFirmes().getBlocDeFirmesID() + "]"
                            + ", datafinalitzaciobloc [" + ((FirmaJPA) firma).getBlocDeFirmes().getDataFinalitzacio() + "]"
                            + "\n\t\t"
                            + ", ordre bloc " + ((FirmaJPA) firma).getBlocDeFirmes().getOrdre());

                    llistaFitxers.add( firma.getFitxerFirmatID() == null
                            ? createFitxerNull("Firma destinatari " + firma.getDestinatariID())
                            : firma.getFitxerFirmat() );

                }
                System.out.println(" ========= FI LLISTAT FIRMES PETICIÓ FIRMA " + peticioFirmaID);
            }


            {
                System.out.println(" ========= INFORMACIÓ FITXERS PETICIÓ FIRMA " + peticioFirmaID);

                int counter = 0;
                for (Fitxer fitxer : llistaFitxers) {
                    counter++;

                    System.out.println(counter + ".-" + fitxer.getNom() + ", " + fitxer.getDescripcio());

                }
            }

            System.out.println("Good Bye!");

        } catch (I18NException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private FitxerBean createFitxerNull(String nom) {
        return new FitxerBean(-1,nom, "NULL", 0,null);
    }

}