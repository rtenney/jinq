package com.fromnibly.jinq;

import java.util.ArrayList;

import org.junit.Test;

public class WhyJinqIsBetter
{
  @Test
  public void filteringAndGoingBackToSameCollection_StreamApi()
  {
    ArrayList<Integer> list = Gen.arrayListOf10();

    Object[] results = list.stream().filter(x -> x > 4).toArray();

    ArrayList<Integer> finals = new ArrayList<Integer>();
    for (Object i : results)
    {
      finals.add((Integer) i);
    }

    finals.forEach(x -> System.out.println(x));

  }

  @Test
  public void filteringAndGoingBackToSameCollection_Jinq()
  {
    ArrayList<Integer> list = Gen.arrayListOf10();

    @SuppressWarnings("unchecked")
    ArrayList<Integer> finals = Jinq.from(list).where(x -> x > 4).toCollection(ArrayList.class);
    finals.forEach(x -> System.out.println(x));
  }

  @Test
  public void GrabingTheFirstItemThatSatisfiesACondition_StreamApi()
  {
    ArrayList<Integer> list = Gen.arrayListOf10();

    Integer fin = (Integer) list.stream().filter(x -> x > 5).toArray()[0];
    System.out.println(fin);
  }

  @Test
  public void GrabingTheFirstItemThatSatisfiesACondition_Jinq()
  {
    ArrayList<Integer> list = Gen.arrayListOf10();

    Integer fin = Jinq.from(list).first(x -> x > 5);
    System.out.println(fin);

  }

}
