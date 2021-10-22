REM Si no volem LOBs en un altre tablespace llavors afegir la següent
REM linia despres de mvn: -Dsqlgenerator.oracle.generatelob=false o no afegir res
REM Per els lobs a un altre tablespace posar -Dsqlgenerator.oracle.generatelob=true
mvn exec:java -Dexec.mainClass="org.fundaciobit.genapp.sqlgenerator.SqlGenerator" -Dexec.args="portafib portafibPULocal %1%" -Dexec.classpathScope="compile"
