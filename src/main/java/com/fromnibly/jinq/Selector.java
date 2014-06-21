package com.fromnibly.jinq;

public interface Selector<T1, T2>
{
  T1 op(T2 object);
}
