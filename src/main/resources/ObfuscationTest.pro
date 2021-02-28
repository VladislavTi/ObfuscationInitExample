#java -jar proguard\proguard-base-5.3.1.jar @ObfuscationTest.pro

-injars       target/ObfuscationInitExample.jar
-outjars      target/ObfuscationInitExample-out.jar

-libraryjars  <java.home>/lib/rt.jar #contains all of the compiled class files for the base Java Runtime environment
-printmapping pgmapout.map
-dontwarn

-keep public class ru.vladislav.obfuscation.proj.start.ObfuscationTest {public static void main(java.lang.String[]);}