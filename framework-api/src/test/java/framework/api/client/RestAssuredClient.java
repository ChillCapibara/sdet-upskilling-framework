package framework.api.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredClient implements ApiClient {
    @Override
    public Response get(String path) {
        return RestAssured.get(path);
    }

    @Override
    public Response post(String path, Object body) {
        return RestAssured.given()
                .body(body)
                .post(path);
    }

    @Override
    public Response put(String path, Object body) {
        return RestAssured.given()
                .body(body)
                .put(path);
    }

    @Override
    public Response delete(String path) {
        return RestAssured.delete(path);
    }
}
