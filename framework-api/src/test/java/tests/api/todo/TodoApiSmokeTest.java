package tests.api.todo;

import framework.api.todo.ToDoApiClient;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.api.base.BaseApiTest;

import java.util.List;
import java.util.Map;

public class TodoApiSmokeTest extends BaseApiTest {

    private final ToDoApiClient toDoApiClient = new ToDoApiClient();

    @Test(groups = "smoke")
    public void getTodos_returnsList() {
        Response response = toDoApiClient.getTodos();

        List<?> todos = response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath()
                .getList("$");

        Assert.assertNotNull(todos, "Todos list should not be null");
        Assert.assertFalse(todos.isEmpty(), "Expected at least 1 todo");
    }

    @Test(groups = "smoke")
    public void postTodo_thenGetById_returnsSameTodo() {
        Map<String, Object> payload = Map.of(
                "todo", "API smoke todo",
                "completed", false,
                "userId", 10
        );

        long id = toDoApiClient.postTodo(payload)
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath()
                .getLong("id");

        String todo = toDoApiClient.getTodoById(id)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath()
                .getString("todo");

        Assert.assertEquals(todo, "API smoke todo");
    }

    @Test(groups = "smoke")
    public void postTodo_withUnexpectedField_returns400() {
        Map<String, Object> payload = Map.of(
                "todo", "bad payload",
                "completed", false,
                "userId", 10,
                "potato", "nope"
        );

        String body = toDoApiClient.postTodo(payload)
                .then()
                .statusCode(400)
                .extract()
                .asString();

        Assert.assertTrue(body.contains("Unexpected fields"),
                "Expected 'Unexpected fields' message. Body was: " + body);
    }
}