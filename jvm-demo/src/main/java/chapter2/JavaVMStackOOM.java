package chapter2;

/**
 * VM Args：-Xss2M （这时候不妨设大些，请在32位系统下运行）
 * 这段代码不要轻易尝试，如果要尝试请先保存好现有的所有工作进度
 *
 * @author wangbingshuai
 */
public class JavaVMStackOOM {

    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            new Thread(() -> dontStop()).start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
