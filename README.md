#JINQ
a port of linq to java because the java 8 stream API was disappointing :(.  Jinq is generic it doesnt use object so there is no boxing or unboxing.  You dont have to cast your result in order to get stuff out of an Enumerable object.

#Why?
[JinqExamples](./src/test/java/com/fromnibly/jinq/WhyJinqIsBetter.java)

##Functions (so far)

Items with a check mark have already been created. 

Items with a question mark are not part of the microsoft document but are part of the javascript linq library which this library is based on. Inclusion of these methods is in question.

- Aggregate ✓
- AggregateRight ?
- All ✓
- Ancestors
- Any ✓
- AsParallel
- Average
- BufferWithCount
- CascadeBreadthFirst
- CascadeDepthFirst
- Cast ✓
- Catch
- Choice
- Concat ✓
- Contains ✓
- Count ✓
- Cycle
- DefaultIfEmptyDistinct
- DefaultIfEmpty
- Descendants
- Distinct
- Do ?
- ElementAt ✓
- ElementAtOrDefault ✓
- Elements
- Empty ✓
- Except
- Finally ?
- First ✓
- FirstOrDefault ✓
- Flatten
- ForEach ✓
- Force
- Generate
- GroupBy
- GroupJoin
- IndexOf
- Insert
- Intersect
- iterable ✓
- Join
- Last ✓
- LastIndexOf
- LastOrDefault
- Let ?
- LongCount
- Matches ?
- Max
- MemoizeAll ?
- Min
- OfType
- OrderBy
- OrderByDescending
- Pairwise ?
- PartitionBy ?
- Range ?
- RangeDown ?
- RangeTo ?
- Repeat ?
- RepeatWithFinalize ?
- Return ?
- Reverse
- Scan ?
- Select
- SelectMany
- SequenceEqual
- Share ?
- Shuffle ?
- Single
- SingleOrDefault
- Skip
- SkipWhile
- Sum
- Take
- TakeExceptLast ?
- TakeFromLast ?
- TakeWhile
- ThenBy ?
- ThenByDescending
- ToArray
- ToMap
- ToList
- ToInfinity ?
- ToJSON ?
- ToLookup
- ToNegativeInfinity ?
- ToObject ?
- ToString ?
- Trace ?
- Unfold ?
- Union
- Where ✓
- Write ?
- WriteLine ?
- Zip