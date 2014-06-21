package com.fromnibly.jinq.enumerable;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

import com.fromnibly.jinq.Gen;
import com.fromnibly.jinq.Jinq;

public class Aggregate_Test
{

  @Test
  public void stringConcatenation_Works()
  {
    Collection<Integer> list = Gen.arrayListOf10();
    String result = Jinq.from(list).aggregate("", (workingSet, next) -> workingSet + next);

    assertEquals("0123456789", result);
  }

  @Test
  public void stringConcatenation_Reverse_Works()
  {
    Collection<Integer> list = Gen.arrayListOf10();
    String result = Jinq.from(list).aggregate("", (workingSet, next) -> next + workingSet);

    assertEquals("9876543210", result);
  }

  @Test
  public void IntegerSum_Works()
  {
    Collection<Integer> list = Gen.arrayListOf10();
    int result = Jinq.from(list).aggregate(0, (workingSet, next) -> next + workingSet);

    assertEquals(45, result);
  }

  @Test
  public void IntegerSubtraction_Works()
  {
    Collection<Integer> list = Gen.arrayListOf10();
    int result = Jinq.from(list).aggregate(0, (workingSet, next) -> next - workingSet);

    assertEquals(5, result);
  }

}
