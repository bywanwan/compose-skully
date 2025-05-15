plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}