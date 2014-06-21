package com.fromnibly.jinq.enumerable;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.fromnibly.jinq.Gen;
import com.fromnibly.jinq.Jinq;

public class Count_Test
{

  @Test
  public void getsSize()
  {
    ArrayList<Integer> list = Gen.arrayListOf10();
    assertEquals(10, Jinq.from(list).count());
  }

  @Test
  public void getsSize_WithPredicate()
  {
    ArrayList<Integer> list = Gen.arrayListOf10();
    assertEquals(5, Jinq.from(list).count(x -> x > 4));
  }

  @Test
  public void emptyList_Returns0()
  {
    ArrayList<Integer> list = new ArrayList<Integer>();
    assertEquals(0, Jinq.from(list).count());
  }

  @Test
  public void emptyList_WithPredicate_Returns0()
  {
    ArrayList<Integer> list = Gen.arrayListOf10();
    assertEquals(0, Jinq.from(list).count(x -> x < 0));
  }

}
