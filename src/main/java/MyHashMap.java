import java.util.Iterator;

/**
 * Класс реализует ассоциативный контейнер
 * @param <K> - тип данных ключа
 * @param <V> - тип данных значения
 * @author ulyana bolotskih
 */
public class MyHashMap <K, V> {
    /** Поле массив списков {@link MyLinkedList} пар {@link MyEntry}*/
    private MyLinkedList<MyEntry>[] map;
    /** Поле коэффициент загрузки */
    private float loadFactor;
    /** Поле размер массива списков {@link MyLinkedList} пар {@link MyEntry} */
    private int capacity;
    /** Поле количество сохраненных пар {@link MyEntry} */
    private int size;

    /**
     * Конструктор
     * Длина массива списков {@link MyHashMap#capacity} - 16
     * Коэффициент загрузки {@link MyHashMap#loadFactor} - 0.75
     * @see MyHashMap#MyHashMap(int)
     * @see MyHashMap#MyHashMap(int, float)
     */
    public MyHashMap() {
        this(16);
    }

    /**
     * Конструктор
     * Длина массива списков {@link MyHashMap#capacity} будет имееть заданное значение
     * Коэффициент загрузки {@link MyHashMap#loadFactor} - 0.75
     * @param capacity - размер массива списков {@link MyHashMap#capacity}
     */
    public MyHashMap(int capacity) {
        this(capacity, 0.75f);
    }

    /**
     * Конструктор
     * Длина массива списков {@link MyHashMap#capacity} будет имееть заданное значение
     * Коэффициент загрузки {@link MyHashMap#loadFactor} будет имееть заданное значение
     * @param capacity - размер массива списков {@link MyHashMap#capacity}
     * @param loadFactor - коэффициент загрузки {@link MyHashMap#loadFactor}
     */
    public MyHashMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        size = 0;
        this.loadFactor = loadFactor;
        map = new MyLinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            map[i] = new MyLinkedList();
        }
    }

    /**
     * Метод для сохранения пары {@link MyEntry}
     * @param key - ключ
     * @param value - значение
     */
    public void put(K key, V value) {
        if (size + 1 > capacity * loadFactor) {
            resize();
        }
        int i = hash(key);
        if (!rewrite(i, key, value)) {
            MyEntry entry = new MyEntry<>(key, value);
            map[i].add(entry);
            size++;
        }
    }

    /**
     * Метод для сохранения пары {@link MyEntry}
     * @param entry - пара
     */
    public void put(MyEntry<K, V> entry) {
        put(entry.getKey(), entry.getValue());
    }

    /**
     * Метод вызывается когда контейнер заполняется больше чем на {@link MyHashMap#loadFactor}
     * {@link MyHashMap#capacity} увеличивается в 2 раза и все пары перераспределяются
     */
    private void resize() {
        MyLinkedList<MyEntry> array = values();
        capacity = capacity * 2;
        map = new MyLinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            map[i] = new MyLinkedList<>();
        }
        for (MyEntry entry : array)
            put(entry);
    }

    /**
     * Метод удаления пары {@link MyEntry} по ключу
     * @param key - ключ
     * @return возвращает удаленную пару
     */
    public MyEntry remove(K key) {
        int i = hash(key);
        Iterator<MyEntry> it = map[i].iterator();
        while (it.hasNext()) {
            MyEntry entry = it.next();
            if (entry.getHash() == key.hashCode() && entry.getKey().equals(key)) {
                it.remove();
                return entry;
            }
        }
        return null;
    }

    /**
     * Метод представляет все пары {@link MyEntry} в виде списка {@link MyLinkedList}
     * @return возвращает список {@link MyLinkedList} всех пар {@link MyEntry}
     */
    public MyLinkedList<MyEntry> values() {
        MyLinkedList<MyEntry> array = new MyLinkedList<>();
        for (int i = 0; i < capacity; i++) {
            for (MyEntry entry : map[i]) {
                array.add(entry);
            }
        }
        return array;
    }

    /**
     * Метод получения пары {@link MyEntry} по ключу
     * @param key - ключ
     * @return возвращает пару {@link MyEntry}
     */
    public V get(K key) {
        int i = hash(key);
        for (MyEntry<K, V> entry : map[i]) {
            if (entry.getHash() == key.hashCode() && entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Метод вычисления по ключу в каком конкретно списке {@link MyLinkedList} из массива {@link MyHashMap#map}
     * должна лежать пара
     * @param key - ключ
     * @return возвращает индекс
     */
    private int hash(K key) {
        if (key == null) return 0;
        else return key.hashCode() % capacity;
    }

    /**
     * Метод для перезаписи значения пары {@link MyEntry}, если пара с таким ключом уже хранится
     * @param i - номер списка в котором лежит пара
     * @param key - ключ
     * @param value - значение
     * @return возвращает true если значение перезаписалось, иначе - false
     */
    private boolean rewrite(int i, K key, V value) {
        int hash = key.hashCode();
        for (MyEntry entry : map[i]) {
            if (entry.getHash() == hash && entry.getKey().equals(key) && !entry.getValue().equals(value)) {
                entry.setValue(value);
                return true;
            }
        }
        return false;
    }

    /**
     * Метод для представления содержимого {@link MyHashMap#map} в виде {@link String}
     * @return возвращает {@link String}
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (MyEntry entry : values()) {
            str.append(entry);
        }
        return str.toString();
    }
}
