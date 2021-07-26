package InstanceClass;

/**
 * 子类的初始化<clinit>
 * （1）j = method();
 * （2）子类的静态代码块
 * <p>
 * 先初始化父类：（5）（1）
 * 初始化子类：（10）（6）
 *
 * 子类的实例化<init>：
 * （1）super();（一定是最开始）      （9）（3）（2）
 * （2）i = test();                （9）
 * （3）子类的非静态代码块            （8）
 * （4）子类的无参构造器（一定是最后）  （7）
 *
 * 因为创建了2个Son对象，因为实例初始化方法执行了2遍
 * (9)(3)(2)(9)(8)(7)
 */
public class Son extends Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.print("(6)");
    }

    public Son() {
//        super();//写或不写都存在，在子类的构造器中一定存在父类的构造器
        System.out.print("(7)");
    }

    {
        System.out.print("(8)");
    }

    public int test() {
        System.out.print("(9)");
        return 1;
    }

    public static int method() {
        System.out.print("(10)");
        return 1;
    }

    public static void main(String[] args) {
        Son s1 = new Son();
        System.out.println();
        Son s2 = new Son();
    }
}
