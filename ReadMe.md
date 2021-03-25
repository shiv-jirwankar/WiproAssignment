# Project key points
* It is a Maven project
* TestNG is used as Test Framework
* Excel sheet is used to handle the test data
* Page Object Model design pattern is used along with PageFactory class
* Execution video is present in the 'ExecutionVideo'

## Project structure
* All the driver related methods are handled in 'infra' package
* All page classes are defined in 'pageObjects' package
* All the utilities are present in 'utility' package
* All the project dependencies are defined in pom.xml file
* Test classes are present in 'testSuite' package

## Steps to run tests
* Make sure Appium server is turned on before triggering the tests
* On 'testng.xml' file, update the udid, platformVersion according to the device on which tests are to be run
* Run 'testng.xml' in order to trigger the tests.

## NOTE
* Faced issues while installing the .apk on the emulator. So prefer to run the tests on any real device.