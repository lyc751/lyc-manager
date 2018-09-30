package org.lyc.manager.domain;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2018/9/11.
 */
public class HibernateUtil {

    private final static SessionFactory SESSION_FACTORY;

    static{
        //纯手工写入配置项，主要为了防止hibernate.cfg.xml文件污染
        Map<String,String> settings = new HashMap<String, String>();
        settings.put(Environment.DRIVER,"com.mysql.jdbc.Driver");
        settings.put(Environment.URL,"jdbc:mysql://192.168.174.128:3306/mgrdb?useUnicode=true&characterEncoding=UTF-8");
        settings.put(Environment.USER,"mgruser");
        settings.put(Environment.PASS,"mgruser");
        settings.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.SHOW_SQL,"true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");

        try{
            // 读取hibernate.cfg.xml配置文件的创建方法如下(默认情况下也是读classpath下的hibernate.cfg.xml):
            // StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();

            //使用addAnnotatedClass方法替代hibernate.cfg.xml中mapping的配置，加入映射的实体类
            Metadata metadata = new MetadataSources(serviceRegistry)
                    .addAnnotatedClass(User.class)
                    .getMetadataBuilder().build();

            SESSION_FACTORY = metadata.getSessionFactoryBuilder().build();
        }
        catch (Throwable th){
            System.err.println("Initial SessionFactory creation failed!"+th);
            throw new ExceptionInInitializerError(th);
        }

    }

    public static SessionFactory getSessionFactory(){
        return SESSION_FACTORY;
    }
}
