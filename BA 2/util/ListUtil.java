package util;

import java.util.LinkedList;

public class ListUtil {

    public static <T> java.util.List<T> singleton(T element){
        assert(element != null);
        java.util.List<T> singleton = new LinkedList<>();
        singleton.add(element);
        return singleton;
    }

}
