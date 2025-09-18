import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.buildFeatures.gitHubAppBuildScopedToken
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.projectFeatures.githubAppConnection
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2025.03"

project {

    vcsRoot(HttpsGithubComTamaracvjetkovicCapytravelTravelAgencyRefsHeadsMain)

    buildType(Build)

    features {
        githubAppConnection {
            id = "PROJECT_EXT_4"
            displayName = "TeamCity TC2"
            appId = "1730988"
            clientId = "Iv23liF97J1LGBmBIoug"
            clientSecret = "credentialsJSON:aa291a07-a24a-41f3-8f89-cdeb14697025"
            privateKey = "credentialsJSON:bde6e7e3-1819-4580-b6e4-73b47c115cd7"
            webhookSecret = "credentialsJSON:8a88a5a0-69b3-4be3-95e9-be9c0ec18378"
            ownerUrl = "https://github.com/tamaracvjetkovic"
            useUniqueCallback = true
            allowBuildScopedTokens = true
        }
        githubAppConnection {
            id = "PROJECT_EXT_6"
            displayName = "TeamCity proba2"
            appId = "1736992"
            clientId = "Iv23liRpSwSKRlVxxMv9"
            clientSecret = "credentialsJSON:e5a17486-2c0f-4f67-b235-dfb6fc602d14"
            privateKey = "credentialsJSON:c572c700-f89f-4d9c-acce-bc3c359cb1c6"
            webhookSecret = "credentialsJSON:17850083-8d74-4c66-9c6e-6b916a2aafed"
            ownerUrl = "https://github.com/tamaracvjetkovic"
            useUniqueCallback = true
        }
    }
}

object Build : BuildType({
    name = "Build"

    params {
        param("env.TEST", "")
    }

    vcs {
        root(DslContext.settingsRoot)
        root(HttpsGithubComTamaracvjetkovicCapytravelTravelAgencyRefsHeadsMain, "+:=> .proba")
    }

    steps {
        script {
            name = "echo"
            id = "echo"
            enabled = false
            scriptContent = "echo 123"
        }
        script {
            name = "test cmd"
            id = "test_cmd"
            enabled = false
            scriptContent = """
                TOKEN=%env.TEST%
                
                echo "token=${'$'}TOKEN"
                
                echo "env_var=${'$'}TEST"
                
                curl -X POST \
                  -H "Accept: application/vnd.github+json" \
                  -H "Authorization: Bearer ${'$'}TOKEN" \
                  -H "X-GitHub-Api-Version: 2022-11-28" \
                  https://api.github.com/repos/tamaracvjetkovic/animal-shelter/releases \
                  -d '{"tag_name": "tag-%build.number%", "body": "Description of the release"}'
            """.trimIndent()
        }
        script {
            name = "tree"
            id = "tree"
            scriptContent = "tree"
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        commitStatusPublisher {
            vcsRootExtId = "${DslContext.settingsRoot.id}"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = storedToken {
                    tokenId = "tc_token_id:CID_d78fd80fd6efd91fe73df25cb32b796b:1:32f1cc58-f040-44b0-9c00-0caff47494f6"
                }
            }
        }
        gitHubAppBuildScopedToken {
            parameterName = "TEST"
            connectionId = "PROJECT_EXT_4"
            targetRepositories = "animal-shelter"
            writeAccess = false
        }
    }
})

object HttpsGithubComTamaracvjetkovicCapytravelTravelAgencyRefsHeadsMain : GitVcsRoot({
    name = "https://github.com/tamaracvjetkovic/capytravel-travel-agency#refs/heads/main"
    url = "https://github.com/tamaracvjetkovic/capytravel-travel-agency"
    branch = "refs/heads/main"
    authMethod = password {
        userName = "tamaracvjetkovic"
        password = "credentialsJSON:9ec482f2-e0b1-4932-a9b4-8dc5cb516ea4"
    }
})
