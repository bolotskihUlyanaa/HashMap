import org.junit.*;

/**
 * Класс для тестирования методов класса {@link MyHashMap}
 * @author ulyana bolotskih
 */
public class MyHashMapTest {
    /** Поле {@link MyHashMap} */
    private MyHashMap<Integer, Integer> map;

    /** Метод инициализирует поле перед каждым тестом */
    @Before
    public void init() {
        map = new MyHashMap<>();
    }

    /** Метод тестирует метод добавления элемента с помощью ключа и значения {@link MyHashMap#put(Object, Object)} */
    @Test
    public void put() {
        for (int i = 0; i < 70; i++)
            map.put(i, i);
        for (int i = 0; i < 70; i++)
            Assert.assertEquals(Integer.valueOf(i), map.get(i));
        map.put(0, 2);
        Assert.assertEquals(Integer.valueOf(2), map.get(0));
    }

    /** Метод тестирует метод добавления пары {@link MyEntry} {@link MyHashMap#put(MyEntry)} */
    @Test
    public void putEntry() {
        for (int i = 0; i < 70; i++) {
            map.put(new MyEntry<>(i, i + 100));
        }
        for (int i = 0; i < 70; i++) {
            Assert.assertEquals(Integer.valueOf(i + 100), map.get(i));
        }
    }

    /** Метод тестирует метод удаления пары по ключу {@link MyHashMap#remove(Object)} */
    @Test
    public void remove() {
        MyEntry entry = new MyEntry<>(7, 7);
        map.put(entry);
        Assert.assertEquals(entry,  map.remove(7));
        Assert.assertEquals(null, map.get(7));
        Assert.assertEquals(null, map.remove(7));
    }

    /** Метод тестирует метод который возвращает список весь пар {@link MyHashMap#values()} */
    @Test
    public void values() {
        MyLinkedList<MyEntry> expected = new MyLinkedList<>();
        for (int i = 0; i < 70; i++) {
            MyEntry entry = new MyEntry<>(i, i + 100);
            map.put(entry);
            expected.add(entry);
        }
        MyLinkedList<MyEntry> actual = map.values();
        for (int i = 0; i < 70; i++) {
            Assert.assertEquals(expected.get(i), actual.get(i));
        }
    }
}
