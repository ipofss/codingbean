package chapter2;

import java.util.HashSet;
import java.util.Set;

/**
 * VM Args：-XX:PermSize=6M -XX:MaxPermSize=6M
 * -Xmx6M
 * 本地：-XX:MetaspaceSize=9m -XX:MaxMetaspaceSize=9m -Xmx9m
 *
 * @author wangbingshuai
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        // 使用set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<>();
        // 在short范围内足以让6MB的常量池产品OOM了，jdk6及以前的常量池实现是永久代
        int i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
            System.out.println(i);
        }
    }
}
