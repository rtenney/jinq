package com.fromnibly.jinq.enumerable;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

import com.fromnibly.jinq.Gen;
import com.fromnibly.jinq.Jinq;

public class Concat_Test
{

  @Test
  public void ArrayList_TwoInstances()
  {
    ArrayList<Integer> list1 = Gen.arrayListOf10();
    ArrayList<Integer> list2 = Gen.arrayListOf10();

    @SuppressWarnings("unchecked")
    ArrayList<Integer> result = Jinq.from(list1).concat(list2).toCollection(ArrayList.class);

    for (int i = 0; i < 20; i++)
    {
      assertEquals(new Integer(i % 10), result.get(i));
    }
  }

  @Test
  public void Set_OneInstance()
  {
    HashSet<Integer> set = Gen.hashSetOf10();
    ArrayList<Integer> list2 = Gen.arrayListOf10();

    @SuppressWarnings("unchecked")
    ArrayList<Integer> result = Jinq.from(set).concat(list2).toCollection(ArrayList.class);

    assertEquals(10, result.size());
    for (int i = 0; i < 10; i++)
    {
      assertEquals(new Integer(i), result.get(i));
    }
  }

}
