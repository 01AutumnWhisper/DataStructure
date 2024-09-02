package MyInterface;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @param <K> K - 该映射维护的键的类型
 * @param <V> v - 值的类型
 */
public interface IMap<K,V> {
    int size();//返回键值对的数量
    boolean isEmpty();//判断是否为空
    boolean containsKey(Object key);//判断键是否存在
    boolean containsValue(Object value);//判断值是否存在
    V get(Object key);//根据键获取值
    V put(K key, V value);//添加键值对
    V remove(Object key);//移除键值对

    void clear();//移除所有的键值对
    Set<K> keySet();// Map中包含的键Set集合
    Collection<V> values();//获取Map中包含的值Collection集合
    Set<IMap.Entry<K, V>> entrySet();//获取Map中包含的键值对Set集合
    interface Entry<K,V>{
        K getKey();//获取键
        V getValue();//获取值
        V setValue(V value);//设置值
        boolean equals(Object o);//比较两个Entry是否相等
        int hashCode();//获取哈希值
    }
}
