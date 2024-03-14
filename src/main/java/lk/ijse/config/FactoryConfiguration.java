package lk.ijse.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;

    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();

        Properties properties = new Properties();

        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        configuration.setProperties(properties);

        /* Add classes here */
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration);
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}