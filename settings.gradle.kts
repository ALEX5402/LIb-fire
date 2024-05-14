pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
     
        gradlePluginPortal()

        maven {
            url = uri("https://jitpack.io/")
        }
        maven {
            url = uri("https://api.xposed.info/")
        }
        maven {
            url = uri("https://s01.oss.sonatype.org/content/repositories/releases/")
        }

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        jcenter()
        maven {
            url = uri("https://jitpack.io/")
        }

        maven {
            url = uri("https://api.xposed.info/")
        }
        maven {
            url = uri("https://s01.oss.sonatype.org/content/repositories/releases/")
        }
    }
}

rootProject.name = "Lib-Fire"
include(":app")
include(":modmenu")
