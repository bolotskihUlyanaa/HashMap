import org.junit.*;
import java.util.*;

/**
 * Класс для тестирования методов класса {@link MyLinkedList}
 * @author ulyana bolotskih
 */
public class MyLinkedListTest {
    /** Поле {@link MyLinkedList} */
    private MyLinkedList<Integer> list;

    /** Метод инициализирует поле перед каждым тестом */
    @Before
    public void init() {
        list = new MyLinkedList<>();
    }

    /** Метод тестирует метод добавления элемента {@link MyLinkedList#add(Object)} */
    @Test
    public void add() {
        for (int i = 0; i < 100; i++)
            list.add(i);
        for (int i = 0; i < 100; i++)
            Assert.assertEquals(Integer.valueOf(i), list.get(i));
    }

    /** Метод тестирует метод добавления элемента в начало {@link MyLinkedList#addFirst(Object)} */
    @Test
    public void addFirst() {
        for (int i = 0; i < 100; i++)
            list.addFirst(i);
        for (int i = 0; i < 100; i++)
            Assert.assertEquals(Integer.valueOf(99 - i), list.get(i));
    }

    /** Метод тестирует метод добавления элемента на определенную позицию {@link MyLinkedList#add(int, Object)} */
    @Test
    public void addIndex() {
        for (int i = 0; i < 100; i++)
            list.add(i);
        list.add(0, 999);
        Assert.assertEquals(Integer.valueOf(999), list.get(0));
        list.add(50, 888);
        Assert.assertEquals(Integer.valueOf(888), list.get(50));
        list.add(102, 777);
        Assert.assertEquals(Integer.valueOf(777), list.get(102));
    }

    /** Метод тестирует метод удаления последнего элемента {@link MyLinkedList#removeLast()} */
    @Test
    public void removeLast() {
        //когда один элемент в списке
        list.add(0);
        list.removeLast();
        Assert.assertEquals("", list.toString());
        //когда несколько элементов в списке
        list.addAll(Arrays.asList(1, 2, 3, 4));
        list.removeLast();
        Assert.assertEquals("1 2 3 ", list.toString());
    }

    /** Метод тестирует метод удаления первого элемента {@link MyLinkedList#addFirst(Object)} */
    @Test
    public void removeFirst() {
        //когда один элемент в списке
        list.add(0);
        list.remove();
        Assert.assertEquals("", list.toString());
        list.addAll(Arrays.asList(1, 2, 3, 4));
        list.remove();
        Assert.assertEquals("2 3 4 ", list.toString());
    }

    /** Метод удаления элемента на определенной позиции {@link MyLinkedList#remove(int)} */
    @Test
    public void removeIndex() {
        //когда один элемент в списке
        list.add(0);
        list.remove(0);
        Assert.assertEquals("", list.toString());
        //когда несколько элементов в списке
        list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        //удаление из начала
        list.remove(0);
        Assert.assertEquals("2 3 4 5 6 7 ", list.toString());
        //удаление из середины
        list.remove(2);
        Assert.assertEquals("2 3 5 6 7 ", list.toString());
        //удаление из конца
        list.remove(4);
        Assert.assertEquals("2 3 5 6 ", list.toString());
    }

    /** Метод тестирует метод удаления элемента по его значению {@link MyLinkedList#removeValue(Object)} */
    @Test
    public void removeValue() {
        //когда один элемент в списке
        list.removeValue(0);
        Assert.assertEquals("", list.toString());
        //когда несколько элементов в списке
        list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        //удаление из начала
        list.removeValue(1);
        Assert.assertEquals("2 3 4 5 6 7 ", list.toString());
        //удаление из середины
        list.removeValue(4);
        Assert.assertEquals("2 3 5 6 7 ", list.toString());
        //удаление из конца
        list.removeValue(7);
        Assert.assertEquals("2 3 5 6 ", list.toString());
    }

    /**
     * Метод тестирует методы внутреннего класса {@link MyLinkedList.MyIterator}
     * {@link MyLinkedList.MyIterator#hasNext()}
     * {@link MyLinkedList.MyIterator#next()}
     */
    @Test
    public void iterator() {
        for (int i = 0; i < 10; i++)
            list.add(i);
        int i = 0;
        Iterator<Integer> it = list.iterator();
        while (it.hasNext())
            Assert.assertEquals(Integer.valueOf(i++), it.next());
    }

    /** Метод тестирует метод {@link MyLinkedList.MyIterator#remove()} когда несколько элементов */
    @Test
    public void iteratorRemove() {
        list.addAll(Arrays.asList(10, 11, 12, 13, 14, 15));
        Iterator<Integer> it = list.iterator();
        it.next();
        //удаление первого элемента
        it.remove();
        Assert.assertEquals("11 12 13 14 15 ", list.toString());
        it.next();
        it.next();
        //удаление из середины
        it.remove();
        Assert.assertEquals("11 12 14 15 ", list.toString());
        it.next();
        //удаление последнего элемента
        it.remove();
        Assert.assertEquals("11 12 14 ", list.toString());
        Assert.assertEquals(false, it.hasNext());
    }

    /** Метод тестирует метод {@link MyLinkedList.MyIterator#remove()} когда только один элемент */
    @Test
    public void iteratorRemoveOnlyOne() {
        list.add(8);
        Iterator<Integer> it = list.iterator();
        it.next();
        it.remove();
        Assert.assertEquals(0 , list.size());
    }

    /** Метод тестирует метод вставки элементов из коллекции {@link MyLinkedList#addAll(Collection)} */
    @Test
    public void addAll() {
        for (int i = 0; i < 10; i++)
            list.add(i);
        list.addAll(Arrays.asList(10, 11, 12, 13, 14, 15, 16, 17, 18));
        int i = 0;
        for (Integer j : list)
            Assert.assertEquals(Integer.valueOf(i++), j);
    }
}
