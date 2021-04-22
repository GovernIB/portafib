package es.caib.portafib.logic.utils;

import es.caib.portafib.jpa.RevisorDeFirmaJPA;
import es.caib.portafib.model.entity.RevisorDeFirma;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class FirmaUtilsTest {

    @Test
    public void test_Buid() {
        Assert.assertEquals(0, FirmaUtils.minimRevisors(getRevisors()));
    }

    @Test
    public void test_1_Si() {
        Assert.assertEquals(1, FirmaUtils.minimRevisors(getRevisors(true)));
    }

    @Test
    public void test_1_No() {
        Assert.assertEquals(1, FirmaUtils.minimRevisors(getRevisors(false)));
    }

    @Test
    public void test_1_Si_1_No() {
        Assert.assertEquals(2, FirmaUtils.minimRevisors(getRevisors(true, false)));
    }

    @Test
    public void test_2_Si() {
        Assert.assertEquals(2, FirmaUtils.minimRevisors(getRevisors(true, true)));
    }

    @Test
    public void test_2_No() {
        Assert.assertEquals(1, FirmaUtils.minimRevisors(getRevisors(false, false)));
    }

    @Test
    public void test_2_Si_1_No() {
        Assert.assertEquals(3, FirmaUtils.minimRevisors(getRevisors(true, false, true)));
    }

    @Test
    public void test_2_Si_2_No() {
        Assert.assertEquals(3, FirmaUtils.minimRevisors(getRevisors(true, false, true, false)));
    }

    private RevisorDeFirma getRevisor(boolean obligatoria) {
        RevisorDeFirma revisor = new RevisorDeFirmaJPA();
        revisor.setObligatori(obligatoria);
        return revisor;
    }

    private Set<RevisorDeFirma> getRevisors(boolean... obligatories) {
        Set<RevisorDeFirma> revisors = new HashSet<RevisorDeFirma>(obligatories.length, 1.0f);
        for (boolean obligatoria: obligatories) {
            revisors.add(getRevisor(obligatoria));
        }
        return revisors;
    }
}
