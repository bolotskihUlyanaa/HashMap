import java.util.Collection;
import java.util.Iterator;

/** Класс реализует двусвязанный список
 * @author ulyana bolotskih
 */
public class MyLinkedList<T> implements Iterable<T> {
    /** Поле первый элемент списка */
    private MyNode first;
    /** Поле последний элемент списка */
    private MyNode last;
    /** Поле размер списка */
    private int size;

    /** Класс сущности узел */
    public class MyNode {
        /** Поле значение узла */
        private T value;
        /** Поле ссылка на предыдущий узел */
        private MyNode prev;
        /** Поле ссылка на следующий узел */
        private MyNode next;

        /**
         * Конструктор - создает узел со значениями
         * @param value - значение, которое хранит узел
         * @param prev - ссылка на предыдущий узел
         * @param next - ссылка на следующий узел
         */
        public MyNode(T value, MyNode prev, MyNode next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        /**
         * Метод определения предыдущего узла
         * @param prev - ссылка на узел
         */
        public void setPrev(MyNode prev){
            this.prev = prev;
        }

        /**
         * Метод определения следующего узла
         * @param next - ссылка на узел
         */
        public void setNext(MyNode next) {
            this.next = next;
        }

        /**
         * Метод получения значения поля {@link MyNode#prev}
         * @return возвращает ссылку на предыдущий узел
         */
        public MyNode getPrev(){
            return prev;
        }

        /**
         * Метод получения значения поля {@link MyNode#next}
         * @return возвращает ссылку на следующий узел
         */
        public MyNode getNext() {
            return next;
        }

        /**
         * Метод получения значения поля {@link MyNode#value}
         * @return возвращает значение узла
         */
        public T getValue() {
            return value;
        }
    }

    /**
     * Конструктор - создает пустой список
     * @see MyLinkedList#MyLinkedList(Collection<? extends T>)
     */
    public MyLinkedList() {
        size = 0;
        first = null;
        last = null;
    }

    /**
     * Конструктор - создает список, содержащий элементы указанной коллекции
     * @param collection - коллекция, содержащая элементы, которые будут добавлены в этот список
     */
    public MyLinkedList(Collection<? extends T> collection) {
        super();
        addAll(collection);
    }

    /**
     * Класс итератор для {@link MyLinkedList}
     * Чтобы можно было перебирать узлы
     */
    public class MyIterator implements Iterator<T> {
        /** Поле текущий узел */
        private MyNode node;

        /** Конструктор - {@link MyIterator#next()} вернет первый узел списка
         * @see MyIterator#MyIterator(MyNode)
         */
        public MyIterator() {
            node = new MyNode(null, null, first);
        }

        /** Конструктор
         * @param node - @link MyIterator#MyNode} узел на который будет указывать
         */
        public MyIterator(MyNode node) {
            this.node = node;
        }

        /**
         * Метод для проверки наличия следующего узла
         * @return true если в списке есть ещё узел
         */
        @Override
        public boolean hasNext() {
            return node.getNext() != null;
        }

        /**
         * Метод с помощью которого можно перемещаться вперед по списку
         * @return следующий узел в списке
         */
        @Override
        public T next() {
            node = node.getNext();
            return node.getValue();
        }

        /**
         * Метод удаляет узел, на который ссылается
         */
        @Override
        public void remove() {
            if (node.getPrev() == null) {
                removeFirst();
            } else {
                if (node.getNext() == null) {
                    removeLast();
                } else {
                    removeMiddle();
                }
            }
            size--;
        }

        /**
         * Метод удаляет элемент если он является первым в списке
         */
        private void removeFirst() {
            if (node.getNext() != null) {
                node.getNext().setPrev(null);
                node = node.getNext();
                first = node;
            } else {
                first = null;
                last = null;
            }
        }

        /**
         * Метод удаляет элемент если он последний в списке
         */
        private void removeLast() {
            node.getPrev().setNext(null);
            node = node.getPrev();
            last = node;
        }

        /**
         * Метод удаляет элемент из середины
         */
        private void removeMiddle() {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
            node = node.getNext();
        }
    }

    /**
     * Метод создает итератор
     * @return возвращает итератор {@link MyIterator}
     */
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    /**
     * Метод для добавления первого элемента
     * @param value - значение элемента
     */
    private boolean init(T value) {
        if (size == 0) {
            MyNode newNode = new MyNode(value, null, null);
            first = newNode;
            last = newNode;
            return true;
        }
        return false;
    }

    /**
     * Метод добавления элемента в конец списка
     * @param value - элемент
     * @return возвращает всегда true
     */
    public boolean add(T value) {
        if (!init(value)) {
            MyNode newNode = new MyNode(value, last, null);
            last.setNext(newNode);
            last = newNode;
        }
        size++;
        return true;
    }

    /**
     * Метод добавления элемента в начало списка
     * @param value - элемент
     * @return возвращает всегда true
     */
    public boolean addFirst(T value) {
        if (!init(value)) {
            MyNode newNode = new MyNode(value, null, first);
            first.setPrev(newNode);
            first = newNode;
        }
        size++;
        return true;
    }

    /**
     * Метод добавления элемента на определенное место в списке
     * @param index - номер в списке
     * @param value - элемент
     */
    public boolean add(int index, T value) {
        if (index == size) return add(value);
        if (index == 0) return addFirst(value);
        MyNode node = search(index);
        MyNode newNode = new MyNode(value, node.getPrev(), node);
        node.getPrev().setNext(newNode);
        node.setPrev(newNode);
        size++;
        return true;
    }

    /**
     * Метод получения значения элемента на определенной позиции
     * @param index - номер в списке
     * @return возвращает значение элемента
     */
    public T get(int index) throws IndexOutOfBoundsException {
        return search(index).getValue();
    }

    /**
     * Метод получения значения длины списка
     * @return возвращает количество элементов
     */
    public int size(){
        return size;
    }

    /**
     * Метод добавляет все элементы из переданной коллекции в конец списка
     * @param collection - коллекция
     */
    public void addAll(Collection<? extends T> collection) {
        for(T i : collection) {
            add(i);
        }
    }

    /**
     * Метод поиска элемента по его индексу
     * @param index - индекс
     * @return возвращает ссылку на элемент
     */
    public MyNode search(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        MyNode node = first;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    /** Метод удаления первого элемента в списке */
    public void remove() {
        MyIterator it = new MyIterator(first);
        it.remove();
    }

    /**
     * Метод удаления узла в списке по определенному индексу
     * @param index - индекс
     */
    public void remove(int index) throws IndexOutOfBoundsException {
        MyIterator it = new MyIterator(search(index));
        it.remove();
    }

    /**
     * Метод удаления элемента из списка по его значению
     * @param value - узел
     */
    public void removeValue(T value) {
        MyIterator it = new MyIterator();
        while (it.hasNext()) {
            if (it.next().equals(value)) {
                it.remove();
            }
        }
    }

    /** Метод удаления последнего узла в списке */
    public void removeLast() {
        MyIterator it = new MyIterator(last);
        it.remove();
    }

    /**
     * Метод представления содержимого списка в виде @link{String}
     * @return возвращает {@link String}
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        MyIterator it = new MyIterator();
        while (it.hasNext()) {
            str.append(it.next());
            str.append(' ');
        }
        return str.toString();
    }
}
