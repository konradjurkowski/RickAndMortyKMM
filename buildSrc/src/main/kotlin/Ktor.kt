object Ktor {
    private const val ktorVersion = "2.0.3"
    const val core = "io.ktor:ktor-client-core:${ktorVersion}"
    const val clientSerialization = "io.ktor:ktor-client-serialization:${ktorVersion}"
    const val json = "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion"
    const val logging = "io.ktor:ktor-client-logging:$ktorVersion"
    const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${ktorVersion}"
    const val android = "io.ktor:ktor-client-okhttp:${ktorVersion}"
    const val ios = "io.ktor:ktor-client-darwin:${ktorVersion}"
}