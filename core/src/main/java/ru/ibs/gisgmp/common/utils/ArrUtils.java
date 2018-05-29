package ru.ibs.gisgmp.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class ArrUtils {

    public static<T> List<T> arrToList(T... arr){
       List<T> ret = new ArrayList<>();
       for(T t: arr) ret.add(t);
       return ret;
    }

    public static<I,O> List<O> map(List<I> src, Function<I, O> mapper){
       List<O>  ret = new ArrayList<>();
       for(I i: src) ret.add(mapper.apply(i));
       return ret;
    }

    public static<T> List<T> filter(List<T> src, Predicate<T> cond){
        List<T> ret = new ArrayList<>();
        for(T t: src)
            if(cond.test(t))
                ret.add(t);
        return ret;
    }

    public static<E,A> A reduce(List<E> src, A initial, Function<E, A> mapper, BiFunction<A, A, A> combiner){
        A acc = initial;
        for(E e: src){
            acc = combiner.apply(acc, mapper.apply(e));
        }
        return acc;
    }

    public static<T> List<T> concat(List<List<T>> lists){
       List<T> ret = new ArrayList<>();
       for(List<T> list: lists)
           ret.addAll(list);
       return ret;
    }

    public static<T> boolean anyMatch(List<T> src, Predicate<T> pred){
        for(T t: src)
            if(pred.test(t))
                return true;
        return false;
    }

    public static<T> boolean allMatch(List<T> src, Predicate<T> pred){
        return anyMatch(src, t ->!pred.test(t));
    }
}
