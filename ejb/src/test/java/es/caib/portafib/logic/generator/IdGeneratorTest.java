package es.caib.portafib.logic.generator;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class IdGeneratorTest {

    /**
     * Genera 1.000.000 d'identificadors i comprova que són únics.
     */
    @Test
    public void generate() {
        int NUMBER_IDS = 1000000;
        IdGenerator generator = IdGeneratorFactory.getGenerator();
        Set<String> ids = new HashSet<String>(NUMBER_IDS);

        for (int i = 0; i < NUMBER_IDS; i++) {
            String id = generator.generate();
            Assert.assertNotNull(id);
            Assert.assertTrue(ids.add(id));
        }

        Assert.assertEquals(NUMBER_IDS, ids.size());
    }
}