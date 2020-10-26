# RestAssured-TestNG

Framework de pruebas sobre API Openbrewerydb

Tecnologias utilizadas:
- Lenguaje: Java
- Building Tool: Maven
- Librerias: RestAssured - TestNG - ApachePOI - Allure - Slf4j - Json
- Reporte: TestNG y Allure

Configuración de ambiente:
1. Descargar y configurar variables de entorno de JAVA (JAVA_HOME y PATH)
2. Instalar TestNG en el editor
3. Para instalar Allure Report:<br>
  a. Descargar scoop <br>
  b. Instalar Allure: scoop install allure

Ejecutar pruebas:
1. mvn clean test 

Reporte TestNG:
1. \target\surefire-reports

Ejecutar reporte Allure desde la raiz de proyecto:
1. allure serve -h 127.0.0.1 -p 8087 <br>--> -h 127.0.0.1 -p 8087 son opcionales

