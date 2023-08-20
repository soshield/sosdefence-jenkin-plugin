package org.jenkinsci.plugins.sosdefence;

import hudson.util.VersionNumber;

public class Messages {

    public static String ApiClient_Error_Connection(int status, String reasonPhrase) {
        return "Error connecting to the server: " + reasonPhrase + " (Status: " + status + ")";
    }

    public static String ApiClient_Error_Canceled() {
        return "Operation was canceled.";
    }

    public static String ApiClient_Error_ProjectLookup(String projectName, String projectVersion, int status, String reasonPhrase) {
        return "Error looking up project '" + projectName + "' with version '" + projectVersion + "': " + reasonPhrase + " (Status: " + status + ")";
    }

    public static String ApiClient_Error_RetrieveFindings(int status, String reasonPhrase) {
        return "Error retrieving findings: " + reasonPhrase + " (Status: " + status + ")";
    }

    public static String Builder_Error_Processing(String remote, String localizedMessage) {
        return "Error processing artifact at '" + remote + "': " + localizedMessage;
    }

    public static String Builder_Payload_Invalid() {
        return "Invalid payload.";
    }

    public static String Builder_Unauthorized() {
        return "Unauthorized access.";
    }

    public static String Builder_Project_NotFound() {
        return "Project not found.";
    }

    public static String ApiClient_Error_ProjectUpdate(String projectUuid, int status, String reasonPhrase) {
        return "Error updating project with UUID '" + projectUuid + "': " + reasonPhrase + " (Status: " + status + ")";
    }

    public static String ApiClient_Error_TokenProcessing(int status, String reasonPhrase) {
        return "Error processing token: " + reasonPhrase + " (Status: " + status + ")";
    }

    public static String Builder_Upload_Failed() {
        return "Artifact upload failed.";
    }

    public static String Builder_Artifact_Unspecified() {
        return "Artifact path is unspecified.";
    }

    public static String Builder_Result_InvalidArguments() {
        return "Invalid arguments provided.";
    }

    public static String Builder_Result_ProjectIdMissing() {
        return "Project ID is missing.";
    }

    public static String Builder_Artifact_NonExist(String effectiveArtifact) {
        return "Artifact does not exist: " + effectiveArtifact;
    }

    public static String Builder_Publishing(String effectiveUrl) {
        return "Publishing to: " + effectiveUrl;
    }

    public static String Builder_Success(String format) {
        return "Success: " + format;
    }

    public static String Builder_Polling() {
        return "Polling...";
    }

    public static String Builder_Polling_Timeout_Exceeded() {
        return "Polling timeout exceeded.";
    }

    public static String Builder_Findings_Processing() {
        return "Processing findings...";
    }

    public static String Builder_Threshold_ComparingTo(int buildNumber) {
        return "Comparing to build number: " + buildNumber;
    }

    public static String Builder_Threshold_Exceed() {
        return "Threshold exceeded.";
    }

    public static String Builder_Project_Update() {
        return "Updating project...";
    }

    public static String Builder_Project_Lookup(String effectiveProjectName, String effectiveProjectVersion) {
        return "Looking up project: " + effectiveProjectName + " (Version: " + effectiveProjectVersion + ")";
    }

    public static String Publisher_ProjectList_Placeholder() {
        return "Select a project...";
    }

    public static String Builder_Error_Projects(String localizedMessage) {
        return "Error fetching projects: " + localizedMessage;
    }

    public static String Publisher_ConnectionTest_Error(String poweredBy) {
        return "Error testing connection: " + poweredBy;
    }

    public static String Publisher_ConnectionTest_VersionWarning(VersionNumber version, VersionNumber requiredVersion) {
        return "Dependency-Track version " + version + " is older than the required version " + requiredVersion + ". Update Dependency-Track to a newer version.";
    }

    public static String Publisher_ConnectionTest_InputError() {
        return "Invalid input for connection test.";
    }

    public static String Publisher_PermissionTest_Team(String escape) {
        return "Team: " + escape;
    }

    public static String Publisher_PermissionTest_Optional(String escape) {
        return "Optional permission: " + escape;
    }

    public static String Publisher_PermissionTest_Okay(String escape) {
        return "Permission granted: " + escape;
    }

    public static String Publisher_PermissionTest_Missing(String escape) {
        return "Missing permission: " + escape;
    }

    public static String Publisher_PermissionTest_Warning(String escape) {
        return "Permission warning: " + escape;
    }

    public static String Publisher_ConnectionTest_Success(String poweredBy) {
        return "Connection test successful. Powered by: " + poweredBy;
    }

    public static String Publisher_ConnectionTest_Warning(String poweredBy) {
        return "Connection test warning. Powered by: " + poweredBy;
    }

    public static String Publisher_DependencyTrack_Name() {
        return "SOSdefence Publisher";
    }

    public static String Publisher_ConnectionTest_InvalidProtocols() {
        return "Only HTTP and HTTPS protocols are supported.";
    }

    public static String Publisher_ConnectionTest_UrlMalformed() {
        return "Malformed URL.";
    }

    public static String Result_DT_Report() {
        return "Dependency-Track Report";
    }

    public static String Result_DT_Project() {
        return "Dependency-Track Project";
    }

    public static String Publisher_ConnectionTest_VersionWarning(String s, String s1) {
        return "Version Warning: Current version " + s + " is older than expected " + s1;
    }
}
