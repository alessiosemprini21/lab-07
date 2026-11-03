package it.unibo.inner.test.impl;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** 
 * array implmentation of {@link IterableWithPolicy}: it iterates
 * the elements of the given array in their original order, returning only
 * those accepted by the current iteration policy
 * @param <T> the type of the elements of the array
 */
public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
    
    private final T[] iterableArray;
    private Predicate<T> predicate;

    public IterableWithPolicyImpl(final T[] array, final Predicate<T> predicate) {
        this.iterableArray = array;
        this.predicate = predicate;
    }

    public IterableWithPolicyImpl(final T[] array) {
        this(array, new Predicate<T>() {
            @Override   
            public boolean test (T elem) {
                return true;
            }
        });
    }

    @Override 
    public void setIterationPolicy(final Predicate<T> filter) {
        this.predicate = filter;
    }

    @Override 
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<T> {
        private int arrayIndex = 0;

        @Override 
        public boolean hasNext() {
            while (arrayIndex < iterableArray.length && !predicate.test(iterableArray[arrayIndex])) {
                arrayIndex++;
            }
            return arrayIndex < iterableArray.length; 
        }

        @Override 
        public T next() {
            if (hasNext()) {
                arrayIndex++;
                return iterableArray[arrayIndex - 1];
            }
            throw new NoSuchElementException();
        }
    }
}
