package singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * 饿汉式3
 * 静态代码块声明单例
 */
public class Singleton3 {
    public static final Singleton3 instance;

    static {
        // todo：需要初始化一些properties文件时会用到
        Properties properties = new Properties();
        try {
            properties.load(Singleton3.class.getClassLoader().getResourceAsStream("demo.properties"));
            String demo = properties.getProperty("demo");
            System.out.println(demo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        instance = new Singleton3();
    }

    private Singleton3() {

    }
}
