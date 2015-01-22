REM Si no volem LOBs en un altre tablespace llavors afegir la següent
REM linia despres de mvn: -Dsqlgenerator.oracle.generatelob=false
mvn exec:java -Dsqlgenerator.oracle.generatelob=false -Dsqlgenerator.project.name=portafib -Dexec.mainClass="org.fundaciobit.genapp.gensql.SqlGenerator" -Dexec.args="portafibDB %1%"
