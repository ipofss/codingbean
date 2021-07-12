package singleton;

/**
 * 饿汉式1
 * 直接创建实例对象，不管是否需要使用这个对象都会创建，不存在线程安全的问题
 * 1、构造器私有化
 * 2、自行创建，并用静态变量保存
 * 3、向外提供这个实例
 * 4、强调这是一个单例，用final修饰
 */
public class Singleton1 {
    public static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {

    }
}
