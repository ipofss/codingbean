package chapter4;

/**
 * staticObj、instanceObj、localObj存放在那里？
 * -Xmx10M -XX:+UseSerialGC -XX:-UseCompressedOops
 */
public class JHSDBTestCase {

    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");     // 这里设置一个端点
        }
    }

    private static class ObjectHolder {
    }

    public static void main(String[] args) {
        Test test = new JHSDBTestCase.Test();
        test.foo();
    }
}
