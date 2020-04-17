package pers.gk.javastudydemo.javastudy.conllection.list;

import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * List<E>的测试
 *
 * @author GK
 * @date 2020-04-15
 */
public class ListTest {

    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>(16);
            map.put("num", String.valueOf(i));
            list.add(map);
        }
        //""不符合int格式报错，必须过滤
        Map<String, Object> map = new HashMap<>(16);
        map.put("num", "");
        list.add(map);
        //null不符合int格式报错，必须过滤
        Map<String, Object> map1 = new HashMap<>(16);
        map1.put("num", null);
        list.add(map1);
        //"null"不符合int格式报错，必须过滤
        Map<String, Object> map2 = new HashMap<>(16);
        map2.put("num", "null");
        list.add(map2);
        System.out.println(list.toString());

        //根据某属性求最大值的Map
        long startMS = System.currentTimeMillis();
        Optional<Map<String, Object>> maxnumStr = list.stream()
                .filter(m -> null != m.get("num")
                        && StringUtils.isNotBlank(m.get("num").toString())
                        && !"null".equals(m.get("num").toString()))
                .max(Comparator.comparing(m -> Integer.valueOf(m.get("num").toString())));
        long endMS = System.currentTimeMillis();
        System.out.println("stream---" + (endMS - startMS));
        //Optional的值不为null，即Map<String, Object> != null
        if (maxnumStr.isPresent()) {
            Map<String, Object> maxMap = maxnumStr.get();
            System.out.println(maxMap.get("num").toString());
        }

        long startMS1 = System.currentTimeMillis();
        Optional<Map<String, Object>> max = list.parallelStream()
                .filter(m -> null != m.get("num")
                        && StringUtils.isNotBlank(m.get("num").toString())
                        && !"null".equals(m.get("num").toString()))
                .max(Comparator.comparing(m -> Integer.valueOf(m.get("num").toString())));
        long endMS1 = System.currentTimeMillis();
        System.out.println("parallelStream---" + (endMS1 - startMS1));
        if (max.isPresent()) {
            Map<String, Object> maxMap = max.get();
            System.out.println(maxMap.get("num").toString());
        }

        //根据指定属性值筛选Map
        long startMS2 = System.currentTimeMillis();
        List<Map<String, Object>> list1 = list.stream()
                .filter(m -> null != m.get("num")
                        && StringUtils.isNotBlank(m.get("num").toString())
                        && !"null".equals(m.get("num").toString())
                        && 3 <= Integer.parseInt(m.get("num").toString()))
                .collect(Collectors.toList());
        long endMS2 = System.currentTimeMillis();
        System.out.println("stream---" + (endMS2 - startMS2));
        System.out.println(list1.toString());

        long startMS3 = System.currentTimeMillis();
        List<Map<String, Object>> list2 = list.parallelStream()
                .filter(m -> null != m.get("num")
                        && StringUtils.isNotBlank(m.get("num").toString())
                        && !"null".equals(m.get("num").toString())
                        && 3 <= Integer.parseInt(m.get("num").toString()))
                .collect(Collectors.toList());
        long endMS3 = System.currentTimeMillis();
        System.out.println("stream---" + (endMS3 - startMS3));
        System.out.println(list2.toString());


    }


}
