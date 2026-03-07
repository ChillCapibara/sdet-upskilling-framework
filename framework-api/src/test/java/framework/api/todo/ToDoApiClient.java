package framework.api.todo;

import framework.api.client.ApiClient;
import framework.api.client.RestAssuredClient;
import framework.api.endpoints.ToDoEndpoints;
import io.restassured.response.Response;

public class ToDoApiClient {
    private final ApiClient client = new RestAssuredClient();

    public Response getTodos() {
        return client.get(ToDoEndpoints.GET_TODOS.path());
    }

    public Response getTodoById(long id) {
        return client.get(ToDoEndpoints.GET_TODO_BY_ID.path(id));
    }

    public Response postTodo(Object payload) {
        return client.post(ToDoEndpoints.POST_TODO.path(), payload);
    }

    public Response putTodo(long id, Object payload) {
        return client.put(ToDoEndpoints.PUT_TODO.path(id), payload);
    }

    public Response deleteTodo(long id) {
        return client.delete(ToDoEndpoints.DELETE_TODO.path(id));
    }
}