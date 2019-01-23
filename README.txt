BACKGROUND:

- Used Java 8, Spring Boot, and Maven.
- IDE: IntelliJ
- Main class that contains conversion logic is FxCalculator.
- Did not create a "cross-via" matrix table/class. Implemented cross currency identification through code logic.
- Exposes a /convert API endpoint.
- Created a simple web page where user can convert currencies.
- Had to put the classes in a package because Spring Boot won't work if the classes are not in a package.

RUNNING THE WEBAPP:

- Web application is already packaged as a single JAR file. To run the webapp, open a command terminal. Go to the
directory where you unzip fxcalculator. Run:

java -jar fxcalculator-0.0.1-SNAPSHOT.jar

- When the webapp starts, open a browser and go to http://localhost:8080

API ENDPOINT:

- Endpoint is at /convert. Sample request:

http://localhost:8080/convert?amount=100&fromCurrency=AUD&toCurrency=USD