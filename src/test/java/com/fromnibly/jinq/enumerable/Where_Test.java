package com.fromnibly.jinq.enumerable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.fromnibly.jinq.Gen;
import com.fromnibly.jinq.Jinq;

public class Where_Test
{
  @Test
  public void greaterThan() throws InstantiationException, IllegalAccessException
  {
    Collection<Integer> ints = new ArrayList<Integer>();

    for (int i = 0; i < 10; i++)
    {
      ints.add(i);
    }

    Collection<Integer> actual = Jinq.from(ints).where((Integer x) -> {
      return x > 5;
    }).toCollection();

    for (int i = 0; i < 10; i++)
    {
      if (i > 5)
      {
        assertTrue(actual.contains(i));
      }
      else
      {
        assertFalse(actual.contains(i));
      }
    }
  }

  @Test
  public void lessThan()
  {
    Collection<Integer> ints = Gen.arrayListOf10();

    Collection<Integer> actual = Jinq.from(ints).where((Integer x) -> {
      return x < 5;
    }).toCollection();

    for (int i = 0; i < 10; i++)
    {
      if (i < 5)
      {
        assertTrue(actual.contains(i));
      }
      else
      {
        assertFalse(actual.contains(i));
      }
    }
  }

  @Test
  public void alwaysTrue()
  {
    Collection<Integer> ints = Gen.arrayListOf10();

    Collection<Integer> actual = Jinq.from(ints).where((Integer x) -> {
      return true;
    }).toCollection();

    for (int i = 0; i < 10; i++)
    {
      assertTrue(actual.contains(i));
    }
  }

}
