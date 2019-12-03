

cd .\scripts\genapp

call mvn exec:java -Dgenapp_run=true -Dexec.mainClass="org.fundaciobit.genapp.traductor.Traductor" 

cd ..\..