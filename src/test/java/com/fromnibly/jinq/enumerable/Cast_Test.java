package com.fromnibly.jinq.enumerable;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.fromnibly.jinq.Jinq;

public class Cast_Test
{
  @Test
  public void AppropriateCast_Works()
  {
    Collection<ThingA> things = new ArrayList<Cast_Test.ThingA>();
    things.add(new ThingB());
    things.add(new ThingB());
    Jinq.from(things).cast(ThingB.class).toCollection().forEach(x -> x.equals(5));

  }

  @Test(expected = ClassCastException.class)
  public void BadCast_ThrowsException()
  {
    Collection<ThingA> things = new ArrayList<Cast_Test.ThingA>();
    things.add(new ThingA());
    things.add(new ThingA());
    Jinq.from(things).cast(Integer.class).toCollection().forEach(x -> x.equals(5));
  }

  public class ThingA
  {
    @SuppressWarnings("unused")
    private boolean test;
  }

  public class ThingB extends ThingA
  {

  }
}
