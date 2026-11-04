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

    /**
     * Creates an iterable over the given array,
     * selecting the elements with the given policy
     * 
     * @param array the elements to iterate over
     * @param predicate the policy wich decide what element is accepted
     */
    public IterableWithPolicyImpl(final T[] array, final Predicate<T> predicate) {
        this.iterableArray = array;
        this.predicate = predicate;
    }

    /**
     * Create an iterable over the given array, because the 
     * policy is not given it's always true
     * 
     * @param array the elements to iterate over
     */
    public IterableWithPolicyImpl(final T[] array) {
        this(array, new Predicate<T>() {
            @Override   
            public boolean test (T elem) {
                return true;
            }
        });
    }

    /**  
     * Replaces the policy used to select elements of the iteration.
     * The new policy also applies to the iterator already created, because it consult
     * the policy each time the next element is looked up.
     * 
     * @param filter is the new policy
     */
    @Override 
    public void setIterationPolicy(final Predicate<T> filter) {
        this.predicate = filter;
    }

    /** 
     * Return a new iterator over the elements accepted by the current policy,
     * srtarting from the beginning of the array.
     * 
     * Every call return an indipendent iterator
     * 
     * @return a new iterator over the accepted elements
     */
    @Override 
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<T> {
        private int arrayIndex = 0;

        /**
         * Tell if the iteration has at least one more element accepted by 
         * the current policy
         * 
         * @return if there is one more element to iterate
         * 
         */
        @Override 
        public boolean hasNext() {
            while (arrayIndex < iterableArray.length && !predicate.test(iterableArray[arrayIndex])) {
                arrayIndex++;
            }
            return arrayIndex < iterableArray.length; 
        }

        /**
         * Return the next element accepted by the current policy
         * and moves the iteration past it
         * 
         * @return the next accepted element
         * @throws NoSuchElementException if no further element satisfy the policy
         * 
        */
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
