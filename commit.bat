cd C:\Users\Michael\Desktop\test_server
rmdir /Q /S git\Factions\src
rmdir /Q /S git\Factions\target
mkdir git\Factions\src
mkdir git\Factions\target
copy plugins\Factions.jar git\Factions\src
cd git\Factions\src
jar xf Factions.jar
rmdir /Q /S META-INF
del .project
del .classpath
move Factions.jar ../target
cd me\michaelkrauty\Factions
del *.class
del commands\*.class
del util\*.class
cd ../../../../
git add --all
git commit -m update
git push
PAUSE