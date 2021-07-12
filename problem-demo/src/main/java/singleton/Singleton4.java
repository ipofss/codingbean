package singleton;

/**
 * 懒汉式
 * 延迟创建这个对象
 * 1、构造器私有化
 * 2、用一个静态变量来保存这个唯一的实例
 * 3、对外提供一个静态方法，用来获取这个实例
 */
public class Singleton4 {
    private static volatile Singleton4 instance;

    private Singleton4() {

    }

    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
