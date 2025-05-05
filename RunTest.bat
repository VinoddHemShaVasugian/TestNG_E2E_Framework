rmdir /s /q "allure-results"

rmdir /s /q "test-output"

mvn clean test -DsuiteFile=/TestNG_E2E_Framework/testng.xml

allure serve