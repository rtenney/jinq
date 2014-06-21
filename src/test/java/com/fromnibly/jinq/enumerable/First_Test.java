package com.fromnibly.jinq.enumerable;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.Test;

import com.fromnibly.jinq.Gen;
import com.fromnibly.jinq.Jinq;

public class First_Test
{

  @Test
  public void NoPredicate()
  {
    Collection<Integer> ints = Gen.arrayListOf10();

    assertEquals((Integer) 0, Jinq.from(ints).first());
  }

  @Test
  public void WithGreaterThan()
  {
    Collection<Integer> ints = Gen.arrayListOf10();

    assertEquals((Integer) 5, Jinq.from(ints).first(x -> x > 4));
  }

  @Test(expected = NoSuchElementException.class)
  public void NoElements_ThrowsException()
  {
    Collection<Integer> ints = new ArrayList<Integer>();

    assertEquals((Integer) 5, Jinq.from(ints).first(x -> x > 4));
  }
}
