package it.unibo.inner.test.impl;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** 
 * this class implements the interface {@link IterableWithPolicy} 
 */
public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
    
    private final T[] iterableArray;

    public IterableWithPolicyImpl(final T[] array) {
        this.iterableArray = array;
    }

    @Override 
    public void setIterationPolicy(final Predicate<T> filter) {

    }

    @Override 
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<T> {
        private int arrayIndex = 0;

        @Override 
        public boolean hasNext() {
            if (arrayIndex < iterableArray.length) {
                return true;
            }
            return false;
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
