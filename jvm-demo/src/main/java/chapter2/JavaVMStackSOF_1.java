package chapter2;

/**
 * VM Args：-Xss128k
 *
 * @author wangbingshuai
 */
public class JavaVMStackSOF_1 {
    private int stackLength = -1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF_1 oom = new JavaVMStackSOF_1();
        try {
            oom.stackLeak();
        } catch (Exception e) {
            throw e;
        } finally {
            System.out.println("stack length:" + oom.stackLength);
        }
    }
}
