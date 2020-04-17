package pers.gk.javastudydemo.javastudy.clazz;

/**
 * Class测试类
 *
 * @author GK
 * @date 2020-04-15
 */
public class ClassTest {

    public static void main(String[] args) {
        String str = "aaa";
        Class<? extends String> strClass = str.getClass();
        System.out.println(strClass == String.class);
        System.out.println(strClass.equals(String.class));

    }

}
