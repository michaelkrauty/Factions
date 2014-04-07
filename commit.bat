cd C:\Users\Michael\Desktop\test_server
rmdir /Q /S git\FirstPvPFactions\src
rmdir /Q /S git\FirstPvPFactions\target
mkdir git\FirstPvPFactions\src
mkdir git\FirstPvPFactions\target
copy plugins\FirstPvPFactions.jar git\FirstPvPFactions\src
cd git\FirstPvPFactions\src
jar xf FirstPvPFactions.jar
rmdir /Q /S META-INF
del .project
del .classpath
move FirstPvPFactions.jar ../target
cd me\michaelkrauty\FirstPvPFactions
del *.class
del commands\*.class
del util\*.class
cd ../../../../
git add --all
git commit -m update
git push