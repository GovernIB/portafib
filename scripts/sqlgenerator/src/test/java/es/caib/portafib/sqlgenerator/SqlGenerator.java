package es.caib.portafib.sqlgenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;

import org.junit.Test;

/**
 * 
 * @author anadal
 * 
 */
public class SqlGenerator {

  @Test
  public void test() throws Exception {

    

    File f = new File("hibernate.properties");
    if (!f.exists()) {
      System.out.println("------------ NO FILE hibernate.properties");
      return;
    }

    Properties prop = new Properties();

    prop.load(new FileInputStream(f));

    String dialect = prop.getProperty("hibernate.dialect");

    if (dialect == null || dialect.indexOf("Oracle") == -1) {
      System.err.println("------------ NO Dialect ORACLE");
      return;
    }

    System.out.println(" Dialect:   " + dialect);
    String sqlBaseName = dialect + "_create_schema";
    File sql = new File(sqlBaseName + ".sql");

    if (!sql.exists()) {
      System.out.println("Exist sql = " + sql.exists());
    }

    prop.clear();
    prop.load(new FileInputStream("shortNames.properties"));

    StringBuffer out = new StringBuffer();

    BufferedReader br = new BufferedReader(new FileReader(sql));
    String table = null;

    StringBuffer allUniques = new StringBuffer();
    StringBuffer allPKs = new StringBuffer();
    StringBuffer allFKs = new StringBuffer();
    StringBuffer allIndexes = new StringBuffer();
    StringBuffer allGrants = new StringBuffer();

    final String uk = "unique (";
    final String pk = "primary key (";
    final String ct = "create table ";
    final String cs = "create sequence ";

    String projectName = prop.getProperty("_projectName_");
    // String projectPrefix = prop.getProperty("_projectPrefix_");
    // String shortName = null;

    for (String l; (l = br.readLine()) != null;) {
      String line = l.trim();

      // Project Name

      // Create table
      if (line.startsWith(ct)) {

        table = line.substring(line.indexOf(ct) + ct.length(), line.indexOf("("));

        table = table.trim();

        // shortName = prop.getProperty(table);

        allGrants.append("    grant select,insert,delete,update on " + table + " to www_"
            + projectName + ";\n");

        out.append(l).append('\n');
        continue;
      }

      // alter table STR_DOCNIV
      // add constraint STR_DNV_PK primary key (DNV_CODIGO);
      if (line.startsWith(pk)) {

        /*
         * String pkC = table + "_pk"; if (pkC.length() > 30) { pkC =
         * projectPrefix + "_"+ shortName + "_pk"; }
         */
        String pkC = prop.getProperty(table + "_PK");
        allPKs.append("    alter table " + table + " add constraint " + pkC + " ");
        if (line.endsWith(",")) {
          allPKs.append(line.substring(0, line.length() - 1));
        } else {
          allPKs.append(line);
        }
        allPKs.append(";\n\n");
        continue;
      }

      // alter table STR_DOCNIV
      // add constraint STR_DNVNIV_UNI unique (DNV_CODDOC, DNV_NIVAUT);

      if (line.startsWith(uk)) {

        String uniqueSimple;
        if (line.endsWith(",")) {
          uniqueSimple = line.substring(line.indexOf("("), line.length() - 1);
        } else {
          uniqueSimple = line.substring(line.indexOf("("));
        }

        String uniqueName = prop.getProperty(table + "_UNIQUE_" + uniqueSimple);
        // System.out.println("uniqueName = ]" + table + "_UNIQUE_" +
        // uniqueSimple + "[");

        allUniques.append("    alter table " + table + " add constraint " + uniqueName
            + " unique " + uniqueSimple + ";\n\n");
        continue;
      }

      // Unique Simple (d'una columna)
      if (line.endsWith(" unique,") || line.endsWith(" unique")) {

        out.append(l.substring(0, l.lastIndexOf(" unique")));
        if (line.endsWith(" unique,")) {
          out.append(",\n");
        } else {
          out.append("\n");
        }

        String column = line.substring(0, line.indexOf(' '));

        String uniqueName = prop.getProperty(table + "_UNIQUE_(" + column + ")");
        allUniques.append("    alter table " + table + " add constraint " + uniqueName
            + " unique (" + column + ");\n\n");

        continue;
      }

      if (line.startsWith("alter table ")) {
        allFKs.append("\n").append(l).append("\n");
        while ((l = br.readLine()) != null) {
          allFKs.append(l).append("\n");
          if (l.endsWith(";")) {
            br.readLine(); // Retorn de carro
            
            break;
          }
        }
        continue;
      }

      if (line.startsWith("create index ")) {
        allIndexes.append(l).append("\n");
        br.readLine(); // Retorn de carro
        continue;
      }

      // Final taula
      if (line.startsWith(");")) {

        out.append(l).append("\n");

        table = null;
        // shortName = null;
        continue;
      }

      // Create sequence
      if (line.startsWith(cs)) {
        String seqname = line.substring(line.indexOf(cs) + cs.length(), line.indexOf(";"));
        allGrants.append("    grant select on " + seqname + " to www_" + projectName).append(
            ";\n");

      }


      out.append(l).append("\n");
    }
    
    out.append("\n");
    out.append("\n");

    out.append(" -- INICI Indexes\n");
    out.append(allIndexes.toString());
    out.append(" -- FINAL Indexes\n\n");

    out.append(" -- INICI PK's\n");
    out.append(allPKs.toString());
    out.append(" -- FINAL PK's\n\n");

    out.append(" -- INICI FK's\n");
    out.append(allFKs.toString());
    out.append(" -- FINAL FK's\n\n");

    out.append(" -- INICI UNIQUES\n");
    out.append(allUniques.toString());
    out.append(" -- FINAL UNIQUES\n\n");

    
    {
      FileOutputStream fos = new FileOutputStream(projectName +  "_create_schema.sql");

      fos.write(out.toString().replace(",\n    );", "\n    );").getBytes());
      fos.flush();
      fos.close();
    }
    
    
    out.append(" -- INICI GRANTS\n");
    out.append(allGrants.toString());
    out.append(" -- FINAL GRANTS\n\n");
     
    {
    FileOutputStream fos = new FileOutputStream(projectName +  "_create_schema_caib.sql");

    fos.write(out.toString().replace(",\n    );", "\n    );").getBytes());
    fos.flush();
    fos.close();
    }
    

  }

}