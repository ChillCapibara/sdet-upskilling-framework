package framework.api.endpoints;

public enum ToDoEndpoints {
    GET_TODOS("/todos"),
    GET_TODO_BY_ID("/todos/%d"),
    POST_TODO("/todos/add"),
    PUT_TODO("/todos/%d"),
    DELETE_TODO("/todos/%d");

    private final String path;

    ToDoEndpoints(String path) { this.path = path; }

    public String path() { return path; }

    public String path(long id) {
        return String.format(path, id);
    }
}
