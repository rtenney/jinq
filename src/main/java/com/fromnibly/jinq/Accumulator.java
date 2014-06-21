package com.fromnibly.jinq;

public interface Accumulator<T1, T2>
{
  public T1 op(T1 workingSet, T2 next);
}
