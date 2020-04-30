package array;

/**
 * 数组常见操作
 *
 * @author: wangbingshuai
 * @create: 2020-04-30 17:28
 **/
public class Array {
    /**
     * 定义整形数组保存数据
     */
    private int[] data;
    /**
     * 定义数组长度
     */
    private int n;
    /**
     * 定义数组中实际的长度
     */
    private int count;

    /**
     * 默认构造函数，定义数组长度为10
     */
    public Array() {
        this.data = new int[10];
        this.n = 10;
        this.count = 0;
    }

    /**
     * 初始化数组定义数组长度
     *
     * @param n 数组长度
     */
    public Array(int n) {
        this.data = new int[n];
        this.n = n;
        this.count = 0;
    }

    /**
     * 向数组尾部添加一个数
     *
     * @param num 数
     * @return
     */
    public boolean add(int num) {
        if (count == n) {
            System.out.println("数组已满");
            return false;
        }
        this.data[count] = num;
        count++;
        return true;
    }

    /**
     * 向数组指定位置插入一个数
     *
     * @param index 指定下标
     * @param num   数
     * @return
     */
    public boolean insert(int index, int num) {
        if (count == n) {
            System.out.println("数组已满");
            return false;
        }
        if (index < 0 || index > count) {
            System.out.println("位置不合法");
            return false;
        }
        for (int i = count; i > index; i--) {
            this.data[i] = this.data[i - 1];
        }
        this.data[index] = num;
        count++;
        return true;
    }

    /**
     * 查找指定下标的数
     *
     * @param index 下标
     * @return
     */
    public int find(int index) {
        if (index < 0 || index >= count) {
            System.out.println("位置不合法");
            return -1;
        }
        return this.data[index];
    }

    /**
     * 删除指定下标的数
     *
     * @param index 下标
     * @return
     */
    public boolean delete(int index) {
        if (index < 0 || index >= count) {
            System.out.println("位置不合法");
            return false;
        }
        for (int i = index; i < count; i++) {
            this.data[i] = this.data[i + 1];
        }
        count--;
        return true;
    }

    /**
     * 打印数组
     */
    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.print(this.data[i] + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Array array1 = new Array();
        array1.print();
        array1.add(8);
        array1.add(9);
        array1.insert(0, 7);
        array1.insert(1, 5);
        System.out.println(array1.find(3));
        array1.delete(1);
        array1.print();
    }
}
