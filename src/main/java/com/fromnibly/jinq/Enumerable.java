package com.fromnibly.jinq;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class Enumerable<T>
{
  private final Collection<T> from;

  public Enumerable(Collection<T> from)
  {
    this.from = from;
  }

  public Collection<T> toCollection()
  {
    return from;
  }

  /**
   * Returns a new instance of a collection containing the elements in the Enumerable
   * 
   * @param toClass
   *          The type of collection to be returned
   * @return a new instance of a collection containing the elements in the Enumerable
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  public <O extends Collection<T>> O toCollection(Class<O> toClass)
  {
    O rtn;
    try
    {
      rtn = toClass.newInstance();
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
    Iterator<T> iterator = iterator();
    while (iterator.hasNext())
    {
      rtn.add(iterator.next());
    }
    return rtn;
  }

  /**
   * Applies an accumulator over a collection.
   * 
   * @param seed
   *          the initial value
   * @param func
   *          the accumulator to apply to the collection
   * @return the accumulated value, This uses the same instance given as the given <code>seed</code>
   */
  public <O> O aggregate(O seed, Accumulator<O, T> func)
  {
    Iterator<T> iterator = iterator();

    while (iterator.hasNext())
    {
      seed = func.op(seed, iterator.next());
    }
    return seed;
  }

  /**
   * Applies an accumulator over a collection and then applies a selector on the result.
   * 
   * @param seed
   *          the initial value
   * @param func
   *          the accumulator to apply to the collection
   * @param selector
   *          transforms the result after the aggregation has occurred
   * @return the accumulated result after the <code>selector</code> has been applied
   */
  public <T1, T2> T1 aggregate(T2 seed, Accumulator<T2, T> func, Selector<T1, T2> selector)
  {
    return selector.op(aggregate(seed, func));
  }

  /**
   * Determines whether all of the elements satisfy the <code>predicate</code>.
   * 
   * @param predicate
   *          the function used to check each element
   * @return true if all elements satisfy the <code>predicate</code> false if at least one element
   *         does not satisfy the <code>predicate</code>.
   */
  public boolean all(Predicate<T> predicate)
  {
    if (count() == 0)
    {
      return false;
    }
    Iterator<T> iterator = iterator();
    while (iterator.hasNext())
    {
      if (!predicate.op(iterator.next()))
      {
        return false;
      }
    }
    return true;
  }

  /**
   * Determines whether or not a collection contains any elements.
   * 
   * @return true if the size is > 0 otherwise false
   */
  public boolean any()
  {
    return count() > 0;
  }

  /**
   * Determines whether or not any element satisfies a condition
   * 
   * @param predicate
   *          tests an element for a condition
   * @return true if any element satisfies the condition
   */
  public boolean any(Predicate<T> predicate)
  {
    Iterator<T> iterator = iterator();
    while (iterator.hasNext())
    {
      if (predicate.op(iterator.next()))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Casts each element in a collection to another class
   * 
   * @param castTo
   *          the class that should be cast to
   * @return a new Enumerable containing a new collection of the casted objects.
   */
  @SuppressWarnings("unchecked")
  public <O> Enumerable<O> cast(Class<O> castTo)
  {
    Collection<O> rtn;
    try
    {
      rtn = from.getClass().newInstance();
    }
    catch (Exception e)
    {
      rtn = new ArrayList<O>();
    }
    Iterator<T> iterator = iterator();
    while (iterator.hasNext())
    {
      rtn.add((O) iterator.next());
    }
    return Jinq.from(rtn);
  }

  /**
   * Concatenates two sequences.
   * 
   * @param enumerable
   *          the enumerable to concatenate to the end of <code>this</code>
   * @return a new Enumerable containing the the results of both <code>this</code> and
   *         <code>enumerable</code>
   */
  public Enumerable<T> concat(Enumerable<T> enumerable)
  {
    return concat(enumerable.from);
  }

  /**
   * Concatenates two sequences.
   * 
   * @param collection
   *          the enumerable to concatenate to the end of <code>this</code>
   * @return a new Enumerable containing the the results of both <code>this</code> and
   *         <code>collection</code>
   */
  public Enumerable<T> concat(Collection<T> collection)
  {
    Collection<T> rtn = tryGetCollection();
    Iterator<T> iterator1 = iterator();
    while (iterator1.hasNext())
    {
      rtn.add(iterator1.next());
    }

    Iterator<T> iterator2 = collection.iterator();
    while (iterator2.hasNext())
    {
      rtn.add(iterator2.next());
    }
    return Jinq.from(rtn);
  }

  /**
   * Checks to see whether or not an Enumerable contains the Object <code>object</code>.
   * 
   * @param object
   *          The Object to check the Enumerable for.
   * @return true if the list contains <code>object</code> otherwise false.
   */
  public boolean contains(T object)
  {
    return from.contains(object);
  }

  /**
   * Checks to see whether or not an Enumerable contains the Set <code>set</code>
   * 
   * @param set
   *          The Set to check the Enumerable for.
   * @return true if the list contains <code>set</code> otherwise false.
   */
  public boolean contains(Set<T> set)
  {
    return from.containsAll(set);
  }

  /**
   * Returns the number of elements in a sequence.
   * 
   * @return the number of elements in a sequence.
   */
  public int count()
  {
    return from.size();
  }

  /**
   * Returns the number of elements in a sequence that satisfies a condition.
   * 
   * @param predicate
   *          tests an element for a condition.
   * @return the number of elements in a sequence that satisfies a condition.
   */
  public int count(Predicate<T> predicate)
  {
    return where(predicate).from.size();
  }

  /**
   * Returns the element at an index.
   * 
   * @param index
   *          The index to get the element
   * @return the element at an index.
   * @throws IndexOutOfBoundsException
   *           if the index is larger than the collection.
   */
  public T elementAt(int index)
  {
    if (index >= count() || index < 0)
    {
      throw new IndexOutOfBoundsException();
    }
    Iterator<T> iterator = iterator();
    for (int i = 0; i < count(); i++)
    {
      if (i == index)
      {
        return iterator.next();
      }
      iterator.next();
    }
    throw new IndexOutOfBoundsException();
  }

  /**
   * Returns an element at an index or a default object.
   * 
   * @param index
   *          the index to get the element.
   * @param defaultObject
   *          the default object to return if there is no object at the index.
   * @return an element at an index or a default object
   */
  public T elementAtOrDefault(int index, T defaultObject)
  {
    if (index >= count() || index < 0)
    {
      return defaultObject;
    }
    Iterator<T> iterator = iterator();
    for (int i = 0; i < count(); i++)
    {
      if (i == index)
      {
        return iterator.next();
      }
      iterator.next();
    }
    return defaultObject;
  }

  /**
   * Checks to see if a collection is empty
   * 
   * @return returns true if the Enumerable is empty otherwise false.
   */
  public boolean empty()
  {
    return count() == 0;
  }

  /**
   * Returns the first element of collection
   * 
   * @return the first element of a collection
   * @throws NoSuchElementException
   *           if the collection has no elements.
   */
  public T first()
  {
    return iterator().next();
  }

  /**
   * Returns the first element of a collection that satisfies a condition.
   * 
   * @param predicate
   *          checks an element for a condition
   * @return the first element of a collection that sacrifices a condition.
   * @throws NoSuchElementException
   *           if the collection has no elements.
   */
  public T first(Predicate<T> predicate)
  {
    Iterator<T> iterator = iterator();
    while (iterator.hasNext())
    {
      T object = iterator.next();
      if (predicate.op(object))
      {
        return object;
      }
    }
    throw new NoSuchElementException();
  }

  /**
   * Returns the first element of a collection or a defaultObject if there are no elements
   * 
   * @param defaultObject
   *          The default object to be returned if there are no elements in the collection.
   * @return The first element or the default object if there are no elements in the collection.
   */
  public T firstOrDefault(T defaultObject)
  {
    if (count() != 0)
    {
      return iterator().next();
    }
    else
    {
      return defaultObject;
    }
  }

  /**
   * Returns the first element of a collection that satisfies a condition or a
   * <code>defaultObject</code>
   * 
   * @param predicate
   *          checks an element for a condition
   * @param defaultObject
   *          The default object to return if no element satisfies a condition.
   * @return The first element of a collection that satisfies a condition or a
   *         <code>defaultObject</code>
   */
  public T firstOrDefault(Predicate<T> predicate, T defaultObject)
  {
    Iterator<T> iterator = iterator();
    while (iterator.hasNext())
    {
      T object = iterator.next();
      if (predicate.op(object))
      {
        return object;
      }
    }
    return defaultObject;
  }

  /**
   * Applies an action on each element
   * 
   * @param action
   */
  public void forEach(Action<T> action)
  {
    for (T object : from)
    {
      action.op(object);
    }
  }

  /**
   * Returns the iterator of the collection.
   * 
   * @return the iterator of the collection.
   */
  public Iterator<T> iterator()
  {
    return from.iterator();
  }

  /**
   * Returns the last element in a collection if there are any.
   * 
   * @return the last element in a collection if there are any.
   */
  public T last()
  {
    final Iterator<T> itr = iterator();
    T lastElement = itr.next();
    while (itr.hasNext())
    {
      lastElement = itr.next();
    }
    return lastElement;
  }

  /**
   * Returns the last element in a collection that satisfies a condition.
   * 
   * @param predicate
   *          checks an element for a condition
   * @return Returns
   */
  public T last(Predicate<T> predicate)
  {
    return where(predicate).last();
  }

  /**
   * Returns an Enumerable that satisfies a condition
   * 
   * @param predicate
   *          checks an element for a condition
   * @return an Enumerable that satisfies a conditio
   */
  public Enumerable<T> where(Predicate<T> predicate)
  {
    Collection<T> rtn = tryGetCollection();
    for (T item : from)
    {
      if (predicate.op(item))
      {
        rtn.add(item);
      }
    }
    return Jinq.from(rtn);
  }

  @SuppressWarnings("unchecked")
  private Collection<T> tryGetCollection()
  {
    Collection<T> rtn;
    try
    {
      rtn = from.getClass().newInstance();
    }
    catch (Exception ex)
    {
      rtn = new ArrayList<T>();
    }
    return rtn;
  }

}
