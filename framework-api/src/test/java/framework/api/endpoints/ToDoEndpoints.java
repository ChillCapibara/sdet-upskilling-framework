package framework.api.endpoints;

public enum ToDoEndpoints {
    GET_TODOS("/todos"),
    GET_TODO_BY_ID("/todos/%d"),
    POST_TODO("/todos/add"),
    PUT_TODO("/todos/%d"),
    DELETE_TODO("/todos/%d");

    private final String path;

    ToDoEndpoints(String path){this.path = path;}

    public String path(Object... args){
        return args.length == 0 ? path : String.format(path, args);
    }

}
