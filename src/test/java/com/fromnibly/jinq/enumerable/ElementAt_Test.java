package com.fromnibly.jinq.enumerable;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.fromnibly.jinq.Gen;
import com.fromnibly.jinq.Jinq;

public class ElementAt_Test
{

  @Test
  public void elementAt_ReturnsCorrectElement()
  {
    ArrayList<Integer> list = Gen.arrayListOf10();

    assertEquals(new Integer(5), Jinq.from(list).elementAt(5));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void invalidIndex_ThrowsException()
  {
    ArrayList<Integer> list = Gen.arrayListOf10();
    Jinq.from(list).elementAt(10);
  }

  @Test
  public void invalidIndex_OrDefault_ReturnsDefault()
  {
    ArrayList<Integer> list = Gen.arrayListOf10();
    Integer result = Jinq.from(list).elementAtOrDefault(10, 20);

    assertEquals(new Integer(20), result);
  }

}
