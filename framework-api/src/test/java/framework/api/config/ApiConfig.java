package framework.api.config;

import framework.config.Config;

public final class ApiConfig {
    private ApiConfig() {}

    public static String baseUrl() {
        String env = System.getenv("API_BASE_URL");
        if (env != null && !env.isBlank()) return env;

        return Config.get("api.baseUrl");
    }
}