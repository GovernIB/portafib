@echo off
if not exist postprocess.class (
    javac postprocess.java
)

echo XXXXXXXXX         %1  %2     XXXXXXXXXXXX >> adeu.txt
echo ----------------------------------------- >> adeu.txt
java postprocess %1 >> adeu.txt


