import java.util.ArrayList;
import java.util.List;

/**
 * 测试代码
 *
 * @author: wangbingshuai
 * @create: 2020-06-11 14:40
 **/
public class DemoTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
    }
}
