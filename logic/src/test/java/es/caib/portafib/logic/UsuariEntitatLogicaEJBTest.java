package es.caib.portafib.logic;

import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UsuariEntitatLogicaEJBTest {

    private UsuariEntitatLogicaEJB usuariEntitatLogicaEJB;
    private EntityManager mockEntityManager;

    @Before
    public void setup() {
        mockEntityManager = Mockito.mock(EntityManager.class);
        usuariEntitatLogicaEJB = new UsuariEntitatLogicaEJB();
        usuariEntitatLogicaEJB.roleUsuariEntitatLogicaEjb = new RoleUsuariEntitatLogicaEJB() {
            @Override
            protected EntityManager getEntityManager() {
                return mockEntityManager;
            }
        };
    }

    @Test
    public void testGetEmailsOfAdministradorsEntitatByEntitat() throws I18NException {

        List<String> resultList = new ArrayList<String>();
        resultList.add("aden1");
        resultList.add("aden2");

        Query mockQuery = Mockito.mock(Query.class);
        Mockito.when(mockQuery.getResultList()).thenReturn(resultList);

        Mockito.when(mockEntityManager.createQuery(Mockito.anyString())).thenReturn(mockQuery);

        List<String> emails = usuariEntitatLogicaEJB.getEmailsOfAdministradorsEntitatByEntitat("fundaciobit");

        Mockito.verify(mockEntityManager).createQuery("select roleUsuariEntitat.usuariEntitat.usuariPersona.email " +
                "from RoleUsuariEntitatJPA roleUsuariEntitat " +
                "where (  ( ( roleUsuariEntitat.roleID = ?1 ) ) " +
                " AND  ( ( roleUsuariEntitat.usuariEntitat.entitatID = ?2 ) ) " +
                " AND  ( ( roleUsuariEntitat.usuariEntitat.actiu = ?3 ) ) " +
                " AND  ( ( roleUsuariEntitat.usuariEntitat.rebreTotsElsAvisos = ?4 ) )  )");

        Mockito.verify(mockQuery).setParameter(1, ConstantsV2.ROLE_ADEN);
        Mockito.verify(mockQuery).setParameter(2, "fundaciobit");
        Mockito.verify(mockQuery).setParameter(3, true);
        Mockito.verify(mockQuery).setParameter(4, true);

        Assert.assertEquals(resultList.size(), emails.size());
        Assert.assertTrue(emails.containsAll(resultList));
    }


}
