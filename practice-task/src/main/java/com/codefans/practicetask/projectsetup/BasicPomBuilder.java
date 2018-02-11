package com.codefans.practicetask.projectsetup;

/**
 * @author caishengzhi
 * @date 2018/2/11 9:44
 *
<groupId>com.lejr</groupId>
<artifactId>scf-risk-restapi</artifactId>
<version>0.0.1</version>
<packaging>pom</packaging>
<name>scf-risk-restapi</name>
<modules>
    <module>scf-risk-restapi-common</module>
    <module>scf-risk-restapi-service</module>
    <module>scf-risk-restapi-web</module>
</modules>

 */
public class BasicPomBuilder {

    private StringBuilder pomStr = new StringBuilder();



    public BasicPomBuilder projectBegin() {
        pomStr.append("<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "\txsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">");
        return this;
    }

    public BasicPomBuilder text(String text) {
        pomStr.append(text);
        return this;
    }

    public BasicPomBuilder modelVersionBegin() {
        pomStr.append("<modelVersion>");
        return this;
    }

    public BasicPomBuilder modelVersionEnd() {
        pomStr.append("</modelVersion>");
        return this;
    }

    public BasicPomBuilder parentBegin() {
        pomStr.append("<parent>");
        return this;
    }

    public BasicPomBuilder parentEnd() {
        pomStr.append("</parent>");
        return this;
    }

    public BasicPomBuilder modulesBegin() {
        pomStr.append("<modules>");
        return this;
    }

    public BasicPomBuilder modulesEnd() {
        pomStr.append("</modules>");
        return this;
    }

    public BasicPomBuilder moduleBegin() {
        pomStr.append("<module>");
        return this;
    }

    public BasicPomBuilder moduleEnd() {
        pomStr.append("</module>");
        return this;
    }

    public BasicPomBuilder groupIdBegin() {
        pomStr.append("<groupId>");
        return this;
    }

    public BasicPomBuilder groupIdEnd() {
        pomStr.append("</groupId>");
        return this;
    }

    public BasicPomBuilder artifactIdBegin() {
        pomStr.append("<artifactId>");
        return this;
    }

    public BasicPomBuilder artifactIdEnd() {
        pomStr.append("</artifactId>");
        return this;
    }

    public BasicPomBuilder versionBegin() {
        pomStr.append("<version>");
        return this;
    }

    public BasicPomBuilder versionEnd() {
        pomStr.append("</version>");
        return this;
    }

    public BasicPomBuilder nameBegin() {
        pomStr.append("<name>");
        return this;
    }

    public BasicPomBuilder nameEnd() {
        pomStr.append("</name>");
        return this;
    }

    public BasicPomBuilder buildPackaging(String packaging) {
        this.packagingBegin();
        pomStr.append(packaging);
        this.packagingEnd();
        return this;
    }

    public BasicPomBuilder packagingBegin() {
        pomStr.append("<packaging>");
        return this;
    }

    public BasicPomBuilder packagingEnd() {
        pomStr.append("</packaging>");
        return this;
    }

    public BasicPomBuilder propertiesBegin() {
        pomStr.append("<properties>");
        return this;
    }

    public BasicPomBuilder propertiesEnd() {
        pomStr.append("</properties>");
        return this;
    }

    public BasicPomBuilder begin(String name) {
        pomStr.append("<");
        pomStr.append(name);
        pomStr.append(">");
        return this;
    }

    public BasicPomBuilder end(String name) {
        pomStr.append("</");
        pomStr.append(name);
        pomStr.append(">");
        return this;
    }

    public BasicPomBuilder dependencyManagementBegin() {
        pomStr.append("<dependencyManagement>");
        return this;
    }

    public BasicPomBuilder dependencyManagementEnd() {
        pomStr.append("</dependencyManagement>");
        return this;
    }

    public BasicPomBuilder dependenciesBegin() {
        pomStr.append("<dependencies>");
        return this;
    }

    public BasicPomBuilder dependenciesEnd() {
        pomStr.append("</dependencies>");
        return this;
    }

    public BasicPomBuilder dependencyBegin() {
        pomStr.append("<dependency>");
        return this;
    }

    public BasicPomBuilder dependencyEnd() {
        pomStr.append("</dependency>");
        return this;
    }

    public BasicPomBuilder scopeBegin() {
        pomStr.append("<scope>");
        return this;
    }

    public BasicPomBuilder scopeEnd() {
        pomStr.append("</scope>");
        return this;
    }

    public BasicPomBuilder buildBegin() {
        pomStr.append("<build>");
        return this;
    }

    public BasicPomBuilder buildEnd() {
        pomStr.append("</build>");
        return this;
    }

    public BasicPomBuilder finalNameBegin() {
        pomStr.append("<finalName>");
        return this;
    }

    public BasicPomBuilder finalNameEnd() {
        pomStr.append("</finalName>");
        return this;
    }

    public BasicPomBuilder pluginsBegin() {
        pomStr.append("<plugins>");
        return this;
    }

    public BasicPomBuilder pluginsEnd() {
        pomStr.append("</plugins>");
        return this;
    }

    public BasicPomBuilder pluginBegin() {
        pomStr.append("<plugin>");
        return this;
    }

    public BasicPomBuilder pluginEnd() {
        pomStr.append("</plugin>");
        return this;
    }

    public BasicPomBuilder configurationBegin() {
        pomStr.append("<configuration>");
        return this;
    }

    public BasicPomBuilder configurationEnd() {
        pomStr.append("</configuration>");
        return this;
    }

    public BasicPomBuilder sourceBegin() {
        pomStr.append("<source>");
        return this;
    }

    public BasicPomBuilder sourceEnd() {
        pomStr.append("</source>");
        return this;
    }

    public BasicPomBuilder encodingBegin() {
        pomStr.append("<encoding>");
        return this;
    }

    public BasicPomBuilder encodingEnd() {
        pomStr.append("</encoding>");
        return this;
    }

    public BasicPomBuilder targetBegin() {
        pomStr.append("<target>");
        return this;
    }

    public BasicPomBuilder targetEnd() {
        pomStr.append("</target>");
        return this;
    }

    public BasicPomBuilder resourcesBegin() {
        pomStr.append("<resources>");
        return this;
    }

    public BasicPomBuilder resourcesEnd() {
        pomStr.append("</resources>");
        return this;
    }

    public BasicPomBuilder resourceBegin() {
        pomStr.append("<resource>");
        return this;
    }

    public BasicPomBuilder resourceEnd() {
        pomStr.append("</resource>");
        return this;
    }

    public BasicPomBuilder directoryBegin() {
        pomStr.append("<directory>");
        return this;
    }

    public BasicPomBuilder directoryEnd() {
        pomStr.append("</directory>");
        return this;
    }

    public BasicPomBuilder filteringBegin() {
        pomStr.append("<filtering>");
        return this;
    }

    public BasicPomBuilder filteringEnd() {
        pomStr.append("</filtering>");
        return this;
    }

    public BasicPomBuilder profilesBegin() {
        pomStr.append("<profiles>");
        return this;
    }

    public BasicPomBuilder profilesEnd() {
        pomStr.append("</profiles>");
        return this;
    }

    public BasicPomBuilder profileBegin() {
        pomStr.append("<profile>");
        return this;
    }

    public BasicPomBuilder profileEnd() {
        pomStr.append("</profile>");
        return this;
    }

    public BasicPomBuilder idBegin() {
        pomStr.append("<id>");
        return this;
    }

    public BasicPomBuilder idEnd() {
        pomStr.append("</id>");
        return this;
    }

    public BasicPomBuilder activationBegin() {
        pomStr.append("<activation>");
        return this;
    }

    public BasicPomBuilder activationEnd() {
        pomStr.append("</activation>");
        return this;
    }

    public BasicPomBuilder activeByDefaultBegin() {
        pomStr.append("<activeByDefault>");
        return this;
    }

    public BasicPomBuilder activeByDefaultEnd() {
        pomStr.append("</activeByDefault>");
        return this;
    }





    public BasicPomBuilder projectEnd() {
        pomStr.append("</project>");
        return this;
    }

    public BasicPomBuilder newline() {
        pomStr.append("\r\n");
        return this;
    }

    public String toString() {
        return pomStr.toString();
    }

}
