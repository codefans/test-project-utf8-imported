package com.codefans.practicetask.projectsetup;

import java.util.Map;

/**
 * @author caishengzhi
 * @date 2018/2/11 9:45
 */
public class ModulePomBuilder extends BasicPomBuilder implements PropertyKeyConstants {

    private Map<String, String> infoMap;
    private String moduleArtifactId;

    public ModulePomBuilder(Map<String, String> infoMap, String moduleArtifactId) {
        this.infoMap = infoMap;
        this.moduleArtifactId = moduleArtifactId;
    }

    public String build() {

        this.projectBegin().newline();
            this.modelVersionBegin().text(this.get(PROJECT_MODELVERSION)).modelVersionEnd().newline();
            this.parentBegin().newline();
                this.groupIdBegin().text(this.get(PROJECT_GROUPID)).groupIdEnd().newline();
                this.artifactIdBegin().text(this.get(PROJECT_ARTIFACTID)).artifactIdEnd().newline();
                this.versionBegin().text(this.get(PROJECT_VERSION)).versionEnd().newline();
            this.parentEnd().newline();

            this.artifactIdBegin().text(moduleArtifactId).artifactIdEnd().newline();
            this.packagingBegin().text(this.get("module." + moduleArtifactId + ".packaging")).packagingEnd().newline();

//            <maven.deploy.skip>false</maven.deploy.skip>
//        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
//        <maven.compiler.source>1.7</maven.compiler.source>
//        <maven.compiler.target>1.7</maven.compiler.target>

        String deploySkipKey = "maven.deploy.skip";
        String deploySkipPropKey = "module." + moduleArtifactId + "." + deploySkipKey;

        this.propertiesBegin().newline();
            this.begin(deploySkipKey).text(this.get(deploySkipPropKey)).end(deploySkipKey);
        this.propertiesEnd().newline();

        this.projectEnd().newline();

        return super.toString();
    }

    public String get(String key) {
        return infoMap.get(key);
    }


}