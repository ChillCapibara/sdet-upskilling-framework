package framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = Config.class.getClassLoader().getResourceAsStream("framework.properties")) {
            if (is == null){
                throw new RuntimeException("framework.properties not found in classpath!");
            }
            props.load(is);
        } catch (IOException e){
            throw new RuntimeException("Failed to load framework.properties", e);
        }
    }

    public static String get(String key) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.isBlank()) return sys;

        String env = System.getenv(key.replace('.', '_').toUpperCase());
        if (env != null && !env.isBlank()) return env;

        return props.getProperty(key);
    }

    public static Integer getInt(String key){
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
