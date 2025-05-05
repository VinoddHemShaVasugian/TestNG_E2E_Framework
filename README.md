# TestNG_E2E_Framework

TestNG framework for Java Selenium developers integrated with Allure Report and Logger. This framework is designed to support efficient and scalable test automation with the following features:

## Key Features
- **Parallel Execution**: Run multiple tests simultaneously to save time.
- **Random Data Generator**: Generate dynamic test data for robust testing.
- **Excel Data Integration**: Read test data from Excel files for data-driven testing.
- **TestNG Data Provider**: Implement data-driven testing using TestNG's `@DataProvider`.
- **Allure Reporting**: Generate detailed and interactive test execution reports.
- **Logging**: Integrated logging for better debugging and traceability.

## Prerequisites
- **Java**: Ensure Java is installed and configured in your system.
- **Maven**: Install Maven for dependency management.
- **Selenium WebDriver**: Add the required WebDriver binaries to your system path.
- **TestNG**: Ensure TestNG is included in your project dependencies.
- **Allure Report**: Ensure Allure Report is included in your project dependencies.

## Installation
1. Clone the repository:
   git clone <repository-url>
2. Navigate to the project directory:
	cd TestNG_E2E_Framework
3. Install dependencies:
mvn clean install
4. Install Allure
iex (new-object net.webclient).downloadstring('https://get.scoop.sh'), 
scoop install allure, 
Verify the installation by running below cmd: 
allure --version

## Usage
1. Configure the test data in the Excel file located in the `resources` folder.
2. Update the `testng.xml` file to include your test classes and configure parallel execution if needed.
3. Run the tests using Maven:
mvn clean test -DsuiteFile=/TestNG_E2E_Framework/testng.xml or ran with bat file (RunTest.bat). 
If need to run test parallel based on methods level - use ParallelMethodLevel.xml. 
If need to run test parallel based on tests level - use ParallelTestsLevel.xml.
4. View the test results in the Allure Report:
allure serve

## Project Structure
- `src/main/java`: Contains the main framework code and project specific libraries.
- `src/test/java`: Contains the test cases.
- `com/test/resources`: Contains configuration files, Utilities and test data (e.g., Excel files and RandomStringGenerator).
- `testng.xml`: TestNG configuration file.

## Reporting
- **Allure Report**: After test execution, generate and view the Allure report for detailed insights into the test results using below command.
allure serve