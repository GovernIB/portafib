package es.caib.portafib.logic.scheduler;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Values;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Columns;

import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 * 8 abr 2025 15:12:32
 */
@Singleton
@Startup
@RunAs(Constants.PFI_ADMIN)
public class RebuigPeticionsCaducadesScheduler extends AbstractScheduler {

    @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
    protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

    @Override
    public String getSchedulerName() {
        return "RebuigPeticionsCaducadesScheduler";
    }

    /**
     * 
     * @return Si val null significa que no s'ha d'executar
     */
    @Override
    public String getCronExpression() {
        //return "0 */2 * * * * *"; // Cada 2 minuts
        return PropietatGlobalUtil.getRebuigPeticionsCaducadesCronExpression();
    }

    @Override
    public void executeTask(ControlOfExecution coe) {
        try {
            log.info("---------------- INICI " + getSchedulerName() + " --------------");

            Long xDiesDespresDeCaducar = PropietatGlobalUtil.getRebuigPeticionsCaducadesDies();
            if (xDiesDespresDeCaducar == null) {
                xDiesDespresDeCaducar = 4L * 365L; // 4 anys;
            }

            // Data actual manco 5 anys
            long dataActualMenysXDies = System.currentTimeMillis() - (xDiesDespresDeCaducar * 24 * 60 * 60 * 1000);

            Timestamp dataActualMenysXDiesTS = new Timestamp(dataActualMenysXDies);

            Select2Columns<Long, Timestamp> select = new Select2Columns<Long, Timestamp>(
                    PeticioDeFirmaFields.PETICIODEFIRMAID.select, PeticioDeFirmaFields.DATACADUCITAT.select);

            // Peticions en marxa o pausades
            Where w1 = Where.OR(
                    PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID
                            .equal(ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES),
                    PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID.equal(ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT));
            // Peticions caducades
            Where w2 = PeticioDeFirmaFields.DATACADUCITAT.lessThan(dataActualMenysXDiesTS);

            List<Select2Values<Long, Timestamp>> peticions = peticioDeFirmaLogicaEjb.executeQuery(select,
                    Where.AND(w1, w2), new OrderBy(PeticioDeFirmaFields.DATACADUCITAT, OrderType.ASC));

            int count = 0;
            for (Select2Values<Long, Timestamp> peti : peticions) {
                Long id = peti.getValue1();
                try {

                    Timestamp dataCaducitat = peti.getValue2();

                    log.info("    (" + (count + 1) + ") Petició " + id + " caducada des del dia  " + dataCaducitat
                            + ": La rebutjam !!!");

                    peticioDeFirmaLogicaEjb.rebutjarPeticioDesDeProcesIntern(id,
                            "Programador de neteja de Peticions Caducades ha trobat aquesta petició caducada des de fa més de "
                                    + xDiesDespresDeCaducar + " dies, concretament de dia " + dataCaducitat
                                    + ": Es rebutja.");

                    count++;
                } catch (Throwable e) {
                    log.error("Scheduler " + getSchedulerName() + "::Error Rebutjant petició " + id + ": "
                            + e.getMessage(), e);
                }

                if (coe.mustExitOfMethod()) {
                    log.warn("S'ha superat el timeout de processament de Peticions Caducades, sortim del bucle.");
                    break;
                }
            }

            log.info("---------------- FINAL " + getSchedulerName() + " Processades " + count
                    + " peticions caducades. --------------");

        } catch (Throwable e) {
            log.error("Error en el Scheduler " + getSchedulerName() + ": " + e.getMessage(), e);
        }
    }

}
