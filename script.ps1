# Set JAVA_HOME environment variable
$env:JAVA_HOME = "D:\GPC\jdk-17.0.11"

# Update PATH environment variable
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

# Navigate to the project directory
Set-Location -Path "D:\GPC\Backend\28.10.2024"

# Run the Spring Boot application
mvn clean install
mvn spring-boot:run
