package es.caib.portafib.logic;


import java.util.ArrayList;
import java.util.List;

import es.caib.portafib.model.entity.Plugin;


import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

/**
 *
 * @author anadal
 *
 */
public abstract class AbstractCommonPluginLogicaEJB<I> extends PluginLogicaEJB
        implements AbstractCommonPluginLogicaLocal<I> {

    protected abstract int getTipusDePlugin();

    protected abstract String getName();

    @Override
    public List<Plugin> getAllPlugins(String entitatID) throws I18NException {
        Where where = getWhere(entitatID);
        return select(where);
    }

    @Override
    public List<Plugin> getAllPluginsSenseEntitat() throws I18NException {
        Where where = Where.AND(TIPUS.equal(getTipusDePlugin()), ACTIU.equal(true), ENTITATID.isNull());
        return select(where);
    }

    public Where getWhere(String entitatID) {
        return Where.AND(TIPUS.equal(getTipusDePlugin()), ACTIU.equal(true), ENTITATID.equal(entitatID)
        // TODO Elegim plugin entre les genèriques o entre els específics per l'entitat
        // Where.OR(ENTITATID.isNull(), ENTITATID.equal(entitatID))
        );
    }

    //@SuppressWarnings("unchecked")
    @Override
    public abstract I getInstanceByPluginID(long pluginID) throws I18NException;

    @Override
    public List<I> getPluginInstancesByEntitatID(String entitatID) throws I18NException {

        return getPluginInstancesBy(entitatID, null, null);

    }

    @Override
    public List<I> getPluginInstancesBy(String entitatID, List<Long> filterByPluginID, List<String> filterByPluginCode)
            throws I18NException {

        List<I> plugins = new ArrayList<I>();

        Where where = Where.AND(TIPUS.equal(getTipusDePlugin()), ACTIU.equal(true), ENTITATID.equal(entitatID));

        if (filterByPluginID != null && filterByPluginID.size() != 0) {
            where = Where.AND(where, PLUGINID.in(filterByPluginID));
        }

        // TODO XYZ pendent afegir camp codi dins plugin
        //    if (filterByPluginCode != null && filterByPluginCode.size() != 0) {
        //      where = Where.AND(where, CODI.in(filterByPluginID));
        //    }
        List<Plugin> modulsdefirma = select(where);

        for (Plugin mf : modulsdefirma) {
            plugins.add(getInstanceByPluginID(mf.getPluginID()));
        }

        return plugins;

    }

}
