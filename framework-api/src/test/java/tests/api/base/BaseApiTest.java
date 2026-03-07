package tests.api.base;

import framework.api.http.Specs;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;


public abstract class BaseApiTest {

    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() {
        RestAssured.requestSpecification = Specs.requestSpec();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
