package InstanceClass;

/**
 * 父类的初始化<clinit>
 * （1）j = method();
 * （2）父类的静态代码块
 *
 * 父类的实例化：
 * （1）super();（一定是最开始）
 * （2）i = test();
 * （3）父类的非静态代码块
 * （4）父类的无参构造器（一定是最后）
 *
 * 非静态方法前面其实有一个默认的对象this
 * this在构造器（或<init>）它表示的是正在创建的对象，因为这里是在创建Son对象，
 * 所以test()执行的是子类重写的代码（面向对象多态）
 *
 * 这里的i = test();执行的是子类重写的test()方法
 */
public class Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.print("(1)");
    }

    public Father() {
        System.out.print("(2)");
    }

    {
        System.out.print("(3)");
    }

    public int test() {
        System.out.print("(4)");
        return 1;
    }

    public static int method() {
        System.out.print("(5)");
        return 1;
    }
}
