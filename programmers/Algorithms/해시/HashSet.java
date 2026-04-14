package Algorithms.해시;

public class HashSet {
    class CustomHashSet<T> {
        private static final Object PRESENT = new Object();

        private final HashMap.CustomHashMap<T, Object> map;

        CustomHashSet(int capacity) {
            map = new HashMap().new CustomHashMap<>(capacity);
        }

        CustomHashSet() {
            this(16);
        }

        boolean add(T value) {
            return map.put(value, PRESENT) == null;
        }

        boolean contains(T value) {
            return map.containsKey(value);
        }

        boolean remove(T value) {
            return map.remove(value) != null;
        }

        int size() {
            return map.size();
        }

        boolean isEmpty() {
            return map.isEmpty();
        }
    }
}
