group = "com.gmail.aazavoykin.processor"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":common"))
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("org.apache.kafka:kafka-streams")
    implementation("org.springframework.boot:spring-boot-starter")
}
