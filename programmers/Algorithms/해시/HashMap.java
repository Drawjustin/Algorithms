package Algorithms.해시;

public class HashMap {
    class CustomHashMap<K, V> {
        Node<K, V>[] buckets;
        int size;

        @SuppressWarnings("unchecked")
        CustomHashMap(int capacity) {
            buckets = (Node<K, V>[]) new Node[tableSizeFor(capacity)];
        }

        CustomHashMap() {
            this(16);
        }

        V put(K key, V value) {
            int index = index(key);
            Node<K, V> current = buckets[index];

            while (current != null) {
                if (equalsKey(current.key, key)) {
                    V oldValue = current.value;
                    current.value = value;
                    return oldValue;
                }
                current = current.next;
            }

            buckets[index] = new Node<>(key, value, buckets[index]);
            size++;
            return null;
        }

        V get(K key) {
            Node<K, V> current = buckets[index(key)];
            while (current != null) {
                if (equalsKey(current.key, key)) {
                    return current.value;
                }
                current = current.next;
            }
            return null;
        }

        boolean containsKey(K key) {
            Node<K, V> current = buckets[index(key)];
            while (current != null) {
                if (equalsKey(current.key, key)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }

        V remove(K key) {
            int index = index(key);
            Node<K, V> current = buckets[index];
            Node<K, V> previous = null;

            while (current != null) {
                if (equalsKey(current.key, key)) {
                    if (previous == null) {
                        buckets[index] = current.next;
                    } else {
                        previous.next = current.next;
                    }
                    size--;
                    return current.value;
                }
                previous = current;
                current = current.next;
            }
            return null;
        }

        boolean isEmpty() {
            return size == 0;
        }

        int size() {
            return size;
        }

        int index(K key) {
            return hash(key) & (buckets.length - 1);
        }

        int hash(K key) {
            int h = (key == null) ? 0 : key.hashCode();
            return h ^ (h >>> 16);
        }

        int tableSizeFor(int capacity) {
            int n = 1;
            int target = Math.max(1, capacity);
            while (n < target) {
                n <<= 1;
            }
            return n;
        }

        /**
         * int hash(K key) {
         *     int h = (key == null) ? 0 : key.hashCode();
         *     return h ^ (h >>> 16);
         * }
         *
         * int index(K key) {
         *     return hash(key) & (buckets.length - 1);
         * }
         */
        boolean equalsKey(K a, K b) {
            if (a == b) {
                return true;
            }
            if (a == null || b == null) {
                return false;
            }
            return a.equals(b);
        }

        static class Node<K, V> {
            K key;
            V value;
            Node<K, V> next;

            Node(K key, V value, Node<K, V> next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }
    }

}
