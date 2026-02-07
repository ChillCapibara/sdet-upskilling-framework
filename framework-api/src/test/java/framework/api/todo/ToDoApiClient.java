package framework.api.todo;

import com.szymon.common.clients.ApiClient;
import com.szymon.common.clients.RestAssuredClient;
import com.szymon.common.endpoints.ToDoEndpoints;
import io.restassured.response.Response;

public class ToDoApiClient {
    private final ApiClient client = new RestAssuredClient();

    public Response getAll() {
        return client.get(ToDoEndpoints.GET_TODOS.path());
    }

    public Response getById(long id) {
        return client.get(ToDoEndpoints.GET_TODO_BY_ID.path(id));
    }

    public Response update(long id, Object body) {
        return client.put(ToDoEndpoints.PUT_TODO.path(id), body);
    }

    public Response create(Object body) {
        return client.post(ToDoEndpoints.POST_TODO.path(), body);
    }

    public Response delete(long id) {
        return client.delete(ToDoEndpoints.DELETE_TODO.path(id));
    }
}