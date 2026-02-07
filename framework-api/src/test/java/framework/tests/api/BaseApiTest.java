package framework.tests.api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "http://localhost:3000";
    }

}
