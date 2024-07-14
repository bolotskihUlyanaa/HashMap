import java.util.Objects;

/**
 * Класс реализует пару ключ-значение
 * @param <K> тип данных ключа
 * @param <V> тип данных значения
 * @author ulyana bolotskih
 */
public class MyEntry<K, V> {
    /** Поле хэш-значение ключа */
    private final int hash;
    /** Поле ключ */
    private final K key;
    /** Поле значение */
    private V value;

    /**
     * Конструктор - создает пару ключ-значение с заданными параметрами
     * @param key - ключ
     * @param value - значение
     */
    public MyEntry(K key, V value) {
        this.hash = key.hashCode();
        this.key = key;
        this.value = value;
    }

    /**
     * Метод получения значения поля {@link MyEntry#hash}
     * @return возвращает хэш
     */
    public int getHash() {
        return hash;
    }

    /**
     * Метод получения значения поля {@link MyEntry#value}
     * @return возвращает значение
     */
    public V getValue() {
        return value;
    }

    /**
     * Метод получения значения поля {@link MyEntry#key}
     * @return возвращает ключ
     */
    public K getKey() {
        return key;
    }

    /**
     * Метод для изменения значения
     * @param value - значение
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Метод для представления пары ключ-значение в виде строки {@link String}
     * @return возвращает {@link String}
     */
    @Override
    public String toString() {
        return key + " = " + value;
    }

    /**
     * Метод сравнивает объекты на равенство
     * @param o - объект для сравнения
     * @return возвращает true если содержимое объектов одинаковое, иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyEntry<?, ?> myEntry = (MyEntry<?, ?>) o;
        return hash == myEntry.hash && Objects.equals(key, myEntry.key) && Objects.equals(value, myEntry.value);
    }

    /**
     * Метод вычисляет хэш для данного объекта
     * @return возвращает значение хэша
     */
    @Override
    public int hashCode() {
        return Objects.hash(hash, key, value);
    }
}