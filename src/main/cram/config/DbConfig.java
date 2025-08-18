package main.cram.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbConfig {
    private final Properties prop = new Properties();
    public DbConfig() {
        try(InputStream in = getClass().getResourceAsStream("application.properties")) {
            if(in != null) prop.load(in);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String url() {return prop.getProperty("db.url", "");}
    public String username() {return prop.getProperty("db.user", "");}
    public String password() {return prop.getProperty("db.password", "");}
}
