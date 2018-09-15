package com.codefans.practicetask.projectsetup;

import java.io.File;
import java.util.Map;

/**
 * @author caishengzhi
 * @date 2018/2/11 9:40
 */
public class ProjectPomBuilder extends BasicPomBuilder implements PropertyKeyConstants {

    private Map<String, String> infoMap;

    public ProjectPomBuilder(Map<String, String> infoMap) {
        this.infoMap = infoMap;
    }

    public String build() {
        this.projectBegin().newline();
        this.modelVersionBegin().text(this.get(PROJECT_MODELVERSION)).modelVersionEnd().newline();
        this.groupIdBegin().text(this.get(PROJECT_GROUPID)).groupIdEnd().newline();
        this.artifactIdBegin().text(this.get(PROJECT_ARTIFACTID)).artifactIdEnd().newline();
        this.versionBegin().text(this.get(PROJECT_VERSION)).versionEnd().newline();
        this.packagingBegin().text(this.get(PROJECT_PACKAGING)).packagingEnd().newline();
        this.nameBegin().text(this.get(PROJECT_NAME)).nameEnd().newline();

        //set <modules>
        this.setupModules();

        //set <properties>
        this.setupProperties();

        //set <dependencyManagement>
        this.setupDependencyManagement();

        //set <build>
        this.setupBuild();

        //set <profiles>
        this.setupProfiles();

        this.projectEnd();
        return super.toString();
    }

    public void setupModules() {
        this.modulesBegin().newline();
            String moduleArtifactIds = this.get(MODULE_ARTIFACTIDS);
            String[] moduleArtifactIdArr = moduleArtifactIds.split(";");
            String moduleArtifactId;
            for(int i = 0; i < moduleArtifactIdArr.length; i ++) {
                moduleArtifactId = moduleArtifactIdArr[i];
                this.moduleBegin().text(moduleArtifactId).moduleEnd().newline();
            }
        this.modulesEnd().newline();
    }

    public void setupProperties() {

        String key = "maven.deploy.skip";
        String propKey = "project.maven.deploy.skip";
        this.propertiesBegin().newline();
            this.begin(key).text(this.get(propKey)).end(key);
        this.propertiesEnd().newline();

    }

    public void setupDependencyManagement() {

    }

    public void setupBuild() {
        this.buildBegin().newline();
//            this.finalNameBegin().text(this.get(PROJECT_ARTIFACTID)).finalNameEnd().newline();
            this.finalNameBegin().text("${project.artifactId}").finalNameEnd().newline();
            this.setupPlugins();
            this.setupResources();
        this.buildEnd().newline();
    }


    public void setupPlugins() {

        String pluginIds = this.get(PROJECT_BUILD_PLUGINS_ARTIFACTIDS);
        String[] pluginIdArr = pluginIds.split(PROPERTY_SEPERATOR);
        if(pluginIdArr != null && pluginIdArr.length > 0) {
            this.pluginsBegin().newline();
            String pluginId = "";
            for(int i = 0; i < pluginIdArr.length; i ++) {
                pluginId = pluginIdArr[i];

                //maven-compiler-plugin
                if(MAVEN_COMPILER_PLUGIN.equalsIgnoreCase(pluginId)) {
                    this.setupMavenCompilerPlugin(pluginId);
                }

                //maven-source-plugin
                if(MAVEN_SOURCE_PLUGIN.equalsIgnoreCase(pluginId)) {
                    this.setupMavenSourcePlugin(pluginId);
                }

            }
            this.pluginsEnd().newline();
        }
    }

    public void setupMavenCompilerPlugin(String pluginId) {
        this.pluginBegin().newline();
            this.groupIdBegin().text(this.get("project." + pluginId + ".groupId")).groupIdEnd().newline();
            this.artifactIdBegin().text(pluginId).artifactIdEnd().newline();
            this.versionBegin().text(this.get("project." + pluginId + ".version")).versionEnd().newline();
            this.configurationBegin();
                this.sourceBegin().text(this.get("project." + pluginId + ".config.source")).sourceEnd().newline();
                this.encodingBegin().text(this.get("project." + pluginId + ".config.encoding")).encodingEnd().newline();
                this.targetBegin().text(this.get("project." + pluginId + ".config.target")).targetEnd().newline();
            this.configurationEnd().newline();
        this.pluginEnd().newline();
    }

    public void setupMavenSourcePlugin(String pluginId) {
        this.pluginBegin().newline();
            this.artifactIdBegin().text(this.get("project." + pluginId + ".artifactId")).artifactIdEnd().newline();
            this.versionBegin().text(this.get("project." + pluginId + ".version")).versionEnd().newline();
            this.configurationBegin().newline();
                this.attachBegin().text(this.get("project." + pluginId + ".attach")).attachEnd().newline();
            this.configurationEnd().newline();
            this.executionsBegin().newline();
                this.executionBegin().newline();
                    this.phaseBegin().text(this.get("project." + pluginId + ".execution.phase")).phaseEnd().newline();
                    this.goalsBegin().newline();
                        this.goalBegin().text(this.get("project." + pluginId + ".execution.goal")).goalEnd().newline();
                    this.goalsEnd().newline();
                this.executionEnd().newline();
            this.executionsEnd().newline();
        this.pluginEnd();
    }

    public void setupResources() {

        String resourcesIds = this.get(PROJECT_RESOURCES_IDS);
        String[] resourcesIdArr = resourcesIds.split(PROPERTY_SEPERATOR);
        if(resourcesIdArr != null && resourcesIdArr.length > 0) {
            this.resourcesBegin().newline();
            String resourceId = "";
            for(int i = 0; i < resourcesIdArr.length; i ++) {
                resourceId = resourcesIdArr[i];
                this.resourceBegin().newline();
                    this.directoryBegin().text(this.get("project.resources." + resourceId + ".directory")).directoryEnd().newline();
                    this.filteringBegin().text(this.get("project.resources." + resourceId + ".filtering")).filteringEnd().newline();
                this.resourceEnd().newline();
            }
            this.resourcesEnd().newline();

        }


    }

    public void setupProfiles() {
        String profilesIds = this.get(PROJECT_PROFILES_IDS);
        String[] profilesIdArr = profilesIds.split(PROPERTY_SEPERATOR);
        if(profilesIdArr != null && profilesIdArr.length > 0) {
            this.profilesBegin().newline();
            String profileId = "";
            for(int i = 0; i < profilesIdArr.length; i ++) {
                profileId = profilesIdArr[i];
                this.profileBegin().newline();
                this.idBegin().text(profileId).idEnd().newline();
                this.propertiesBegin().newline();
                    this.begin(this.get("project.profiles." + profileId + ".property.name"));
                    this.text(profileId);
                    this.end(this.get("project.profiles." + profileId + ".property.name")).newline();
                this.propertiesEnd().newline();
                this.profileEnd().newline();
            }
            this.profilesEnd().newline();
        }
    }

    public String get(String key) {
        return infoMap.get(key);
    }


}
