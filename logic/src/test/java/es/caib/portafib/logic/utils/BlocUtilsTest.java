package es.caib.portafib.logic.utils;

import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.model.entity.Firma;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class BlocUtilsTest {

    @Test
    public void test_1_Si() {
        Assert.assertEquals(1, BlocUtils.minimFirmes(getFirmas(true)));
    }

    @Test
    public void test_1_No() {
        Assert.assertEquals(1, BlocUtils.minimFirmes(getFirmas(false)));
    }

    @Test
    public void test_1_Si_1_No() {
        Assert.assertEquals(2, BlocUtils.minimFirmes(getFirmas(true, false)));
    }

    @Test
    public void test_2_Si() {
        Assert.assertEquals(2, BlocUtils.minimFirmes(getFirmas(true, true)));
    }

    @Test
    public void test_2_No() {
        Assert.assertEquals(1, BlocUtils.minimFirmes(getFirmas(false, false)));
    }

    @Test
    public void test_2_Si_1_No() {
        Assert.assertEquals(3, BlocUtils.minimFirmes(getFirmas(true, false, true)));
    }

    @Test
    public void test_2_Si_2_No() {
        Assert.assertEquals(3, BlocUtils.minimFirmes(getFirmas(true, false, true, false)));
    }

    private Firma getFirma(boolean obligatoria) {
        Firma firma = new FirmaJPA();
        firma.setObligatori(obligatoria);
        return firma;
    }

    private Set<Firma> getFirmas(boolean... obligatories) {
        Set<Firma> firmes = new HashSet<Firma>(obligatories.length, 1.0f);
        for (boolean obligatoria: obligatories) {
            firmes.add(getFirma(obligatoria));
        }
        return firmes;
    }
}
