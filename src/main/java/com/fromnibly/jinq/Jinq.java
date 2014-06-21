package com.fromnibly.jinq;

import java.util.Collection;

public class Jinq
{
  public static <T> Enumerable<T> from(Collection<T> from)
  {
    return new Enumerable<T>(from);
  }
}
