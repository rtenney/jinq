package com.fromnibly.jinq;

import java.util.ArrayList;
import java.util.HashSet;

public class Gen
{
  public static ArrayList<Integer> arrayListOf10()
  {
    ArrayList<Integer> ints = new ArrayList<Integer>();
    for (int i = 0; i < 10; i++)
    {
      ints.add(i);
    }
    return ints;
  }

  public static HashSet<Integer> hashSetOf10()
  {
    HashSet<Integer> ints = new HashSet<Integer>();
    for (int i = 0; i < 10; i++)
    {
      ints.add(i);
    }
    return ints;
  }
}
