package es.caib.portafib.back.utils;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.SelectField;
import org.fundaciobit.genapp.common.query.StringField;

/**
 * 
 * @author anadal
 * 1 oct 2024 11:37:18
 */
public class ObfuscatedNifStringField extends Field<String> {

    public ObfuscatedNifStringField(StringField sf) {
        this(sf.table, sf.javaName, sf.sqlName);
    }

    private ObfuscatedNifStringField(String table, String javaName, String sqlName) {
        super(table, javaName, sqlName, new StringSelect(table, javaName));
    }

    private ObfuscatedNifStringField(String path, StringField field) {
        super(null, path + field.javaName, null, new StringSelect(null, path + field.javaName), field.codeLabel);
    }

    public static class StringSelect extends SelectField<String> {

        public StringSelect(String table, String sqlName) {
            super(table, sqlName);
        }

        @Override
        public String getFromObject(Object obj) throws I18NException {
            return Utils.ofuscarDNI((String) obj);
        }

    };

}
