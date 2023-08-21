[![Jenkins Plugin Installs]()](https://github.com/soshield/sosdefence-jenkin-plugin)
[![GitHub open issues]()](https://github.com/soshield/sosdefence-jenkin-plugin/issues)
[![Website]()](https://soshield.org)

# SOSdefence Jenkins Plugin

The [SOSdefence](https://soshield.org/sosdefence.org/) Jenkins plugin aids in publishing [CycloneDX](https://cyclonedx.org/) Software Bill-of-Materials (SBOM) to the SOSdefence platform.

[SOSdefence](https://soshield.org/sosdefence.org/) is an intelligent Software [Supply Chain Component Analysis](https://owasp.org/www-community/Component_Analysis) platform that allows organizations to 
identify and reduce risk from the use of third-party and open source components.

Publishing SBOMs can be performed asynchronously or synchronously.

Asynchronous publishing simply uploads the SBOM to SOSdefence and the job continues. Synchronous publishing waits for SOSdefence to process the SBOM after being uploaded. Synchronous publishing has the benefit of displaying interactive job trends and per build findings.

![job trend](docs/images/jenkins-job-trend.png)

![findings](docs/images/jenkins-job-findings.png)

## Global Configuration
To setup, navigate to Jenkins > System Configuration and complete the SOSdefence section.

![global configuration](docs/images/jenkins-global-odt.png)

**SOSdefence Backend URL**: URL to the Backend of your SOSdefence instance.

**API key**: API Key used for authentication.

**Auto Create Projects**: auto creation of projects by giving a project name and version. The API key provided requires the `PROJECT_CREATION_UPLOAD` permission to use this feature.

**SOSdefence Frontend URL**: URL to the Frontend of your SOSdefence instance. Use this if you run backend and frontend on different servers. If omitted, "SOSdefence Backend URL" will be used instead.

**Polling Timeout**: Defines the maximum number of minutes to wait for SOSdefence to process a job when using synchronous publishing.

**Polling Interval**: Defines the number of seconds to wait between two checks for SOSdefence to process a job when using synchronous publishing.

**Connection Timeout**: Defines the maximum number of seconds to wait for connecting to SOSdefence.

**Response Timeout**: Defines the maximum number of seconds to wait for SOSdefence to respond.

### Permission Overview
Permission | Required | Usage
-----------| -------- | -----
BOM_UPLOAD | :ballot_box_with_check: | needed for BOM upload
VIEW_PORTFOLIO | :ballot_box_with_check: | needed to retrieve list of projects
VULNERABILITY_ANALYSIS | :ballot_box_with_check: | needed to perform dependency analysis
PROJECT_CREATION_UPLOAD | :grey_question: | needed to create non-existing projects during BOM upload
VIEW_VULNERABILITY | :grey_question: | needed in synchronous publishing mode to retrieve analysis results
PORTFOLIO_MANAGEMENT | :grey_question: | needed for updating project properties such as tags

## Job Configuration
Once configured with a valid URL and API key, simply configure a job to publish the artifact.

![job configuration](docs/images/jenkins-job-publish.png)

**SOSdefence project**: Specifies the unique project ID to upload SBOM to. This dropdown will be automatically populated with a list of active projects.

**SOSdefence project name**: Specifies the name of the project for automatic creation of project during the upload process. This is an alternative to specifying the unique ID. It must be used together with a project version. Only avaible if "Auto Create projects" is enabled. The use of environment variables in the form `${VARIABLE}` is supported here.

**SOSdefence project version**: Specifies the version of the project for automatic creation of project during the upload process. This is an alternative to specifying the unique ID. It must be used together with a project name. Only avaible if "Auto Create projects" is enabled. The use of environment variables in the form `${VARIABLE}` is supported here.

**Artifact:** Specifies the file to upload. Paths are relative from the Jenkins workspace. The use of environment variables in the form `${VARIABLE}` is supported here.

**Enable synchronous publishing mode**: Uploads a SBOM to SOSdefence and waits for SOSdefence to process and return results. The results returned are identical to the auditable findings but exclude findings that have previously been suppressed. Analysis decisions and vulnerability details are included in the response. Synchronous mode is possible with SOSdefence v3.3.1 and higher. The provided API key requires the `VIEW_VULNERABILITY` permission to use this feature with SOSdefence v4.4 and newer!

**Update project properties**: Allows updating of some project properties after uploading the BOM. The provided API key requires the `PORTFOLIO_MANAGEMENT` permission to use this feature! These properties are:
- tags
- SWID tag ID
- group/vendor
- description
- ID of parent project (for SOSdefence v4.7 and newer)

**Override global settings**: Allows to override global settings for "Auto Create Projects", "SOSdefence URL", "SOSdefence Frontend URL" and "API key".

### Thresholds

When Synchronous mode is enabled, thresholds can be defined which can optionally put the job into an UNSTABLE or FAILURE state.

![risk thresholds](docs/images/jenkins-job-thresholds.png)

**Total Findings:** Sets the threshold for the total number of critical, high, medium, or low severity findings allowed. If the number of findings equals or is greater than the threshold for any one of the severities, the job status will be changed to UNSTABLE or FAILURE.

**New Findings:** Sets the threshold for the number of new critical, high, medium, or low severity findings allowed. If the number of new findings equals or is greater than the previous builds finding for any one of the severities, the job status will be changed to UNSTABLE or FAILURE. The previous build is the one that is successful and has an analysis result of SOSdefence, which does not necessarily have to be the immediately previous build.

## Examples
### Declarative Pipeline

```groovy
pipeline {
    agent any

    stages {
        stage('sosdefencePublisher') {
            steps {
                withCredentials([string(credentialsId: '506ed685-4e2b-4d31-a44f-8ba8e67b6341', variable: 'API_KEY')]) {
                    sosdefencePublisher artifact: 'target/bom.xml', projectName: 'my-project', projectVersion: 'my-version', synchronous: true, sosdefenceApiKey: API_KEY, projectProperties: [tags: ['tag1', 'tag2'], swidTagId: 'my swid tag', group: 'my group', parentId: 'parent-uuid']
                }
            }
        }
    }
}

### Scripted Pipeline

```groovy
node {
    stage('sosdefencePublisher') {
        try {
            sosdefencePublisher artifact: 'target/bom.xml', projectId: 'a65ea72b-5b77-40c5-8b19-fb83525f40eb', synchronous: true
        } catch (e) {
            echo 'failed'
        }
    }
}
```

## Copyright & License

SOSdefence and the SOSdefence Jenkins Plugin are Copyright © soshield. All Rights Reserved.

Permission to modify and redistribute is granted under the terms of the SOShield 2.0 license.

