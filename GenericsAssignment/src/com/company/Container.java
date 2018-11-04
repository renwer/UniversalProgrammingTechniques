package com.company;

import java.util.ArrayList;
import java.util.List;

public class Container<TElement extends IAggregable<TElement, TResult> & IDeeplyCloneable<TElement>, TResult> implements IContainer<TElement, TResult> {

    private List<TElement> elements;

    public Container() {
        elements = new ArrayList<>();
    }

    public void addElement(TElement element) {
        elements.add(element);
    }
    public void addAllElements(List<TElement> newElements) {
        elements.addAll(newElements);
    }

    @Override
    public List<TElement> elements() {
        return elements;
    }

    @Override
    public TResult aggregateAllElements() {

        TResult result = null;
        for (TElement entry: elements) {
            result = entry.aggregate(result);
        }
        return result;
    }

    @Override
    public TElement cloneElementAtIndex(int index) {
        if (index >= elements.size() || index < 0) {
            throw new InvalidAccessException("Attempt to access non-existing index!");
        }
        return elements.get(index).deepClone();
    }
}
