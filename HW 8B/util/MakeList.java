package util;

import java.util.LinkedList;

public class MakeList {

    public static <T> java.util.List<T> one(T element){
        assert(element != null);
        java.util.List<T> singleton = new LinkedList<>();
        singleton.add(element);
        return singleton;
    }

    public static <T> java.util.List<T> two(T first, T second){
        assert(first != null && second != null);
        java.util.List<T> singleton = new LinkedList<>();
        singleton.add(first);
        singleton.add(second);
        return singleton;
    }

}
