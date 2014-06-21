package com.fromnibly.jinq.enumerable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.fromnibly.jinq.Gen;
import com.fromnibly.jinq.Jinq;

public class Any_Test
{
  @Test
  public void AllAre_ReturnsTrue()
  {
    Collection<Integer> list = Gen.arrayListOf10();
    boolean result = Jinq.from(list).any(x -> x >= 0);

    assertTrue(result);
  }

  @Test
  public void OneIsnt_ReturnsTrue()
  {
    Collection<Integer> list = Gen.arrayListOf10();
    boolean result = Jinq.from(list).any(x -> x > 1);

    assertTrue(result);
  }

  @Test
  public void AllArent_ReturnsFalse()
  {
    Collection<Integer> list = Gen.arrayListOf10();
    boolean result = Jinq.from(list).any(x -> x < 0);

    assertFalse(result);
  }

  @Test
  public void EmptyCollection_ReturnsFalse()
  {
    Collection<Integer> list = new ArrayList<Integer>();
    boolean result = Jinq.from(list).any(x -> true);

    assertFalse(result);
  }
}
