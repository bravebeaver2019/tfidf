# Getting Started

### Building application
You need to have JDK installed (tested with jdk10)

Please, run the following command specifying the arguments
```bash
./build
```
### Running application
Please, run the following command specifying the arguments
```bash
./tfidf --tfidf.search.term="abc" --tfidf.search.limit=5 --tfidf.search.period=5000 --tfidf.populator.enabled=true --tfidf.search.dir=/tmp
or
./tfidf --tfidf.search.term="abc some common" --tfidf.search.limit=5 --tfidf.search.period=5000 --tfidf.populator.enabled=true --tfidf.search.dir=/tmp
```
--tfidf.populator.enabled=true in case you want the app to populate the filesystem
--tfidf.populator.enabled=false in case you want to add the files by yourself

### Design document

```
                              +----------------------------+
                              |                            |
                              |  Tfidf.java                |
                              |                            |         +------------------------------+        +----------------+
+-------------------+         |                            |         |                              |        |                |
|                   +-------->+                            +<--------+                              |        |                |
|FileWatcher.java   |         +----------------------------+         |                              |        |                |
|                   |                                                |                              |        |                |
|                   |                                                |  Search.java                 +<-------+  Notifier.java |
|                   +-----+   +----------------------------+         |                              |        |                |
+--------+----------+     |   |                            |         |                              |        |                |
         |                +-->+  TfidfExperimental.java    |         |                              |        |                |
         |                    |                            |<- - - - +                              |        |                |
         |                    |                            |         +------------------------------+        +----------------+
         v                    |                            |
+--------+----------+         +----------------------------+
|                   |
|                   |
|  Filesystem       |
|                   |
|                   |
|                   |
+--------+----------+
         ^
         |
         |
+--------+----------+
|                   |
| FilePopulator.java|
|                   |
|                   |
|                   |
+-------------------+
```

Multiple Tfidf implementations can be added by just implementing Tfidf and Listener interfaces

### Comments
* Switch between Tfidf implementations by just adding @Primary annotation to one of them

### Things to do
* Some more unit tests

### Complexity
Current system complexity would be
Write (doc scanning): O(1) or O(log n)
Read (querying): O(n log n)

### Alternatives considered
Current system implementation is just a basic demonstration of the algorithm. Here the calculation of the term score is
done upon read which would be very inefficient in a real environment. In order to do it properly I should split the document
after scanning, perform the calculations and keep up to date a pre calculated table with the terms scores.
This would have two significan impacts:
* The complexity of the R/W operations would be inverted
* The system will have to implement some synchronization mechanism to avoid serveral threads updating the same terms scores.
Now, since write is very fast we didnt have to implement multithreading on it but if we switch to a slow write scenario,
we would have to implement some way to parallelize this task. Another interesting feature to implement would be partitioning,
we could split the terms into several sets and let subsystems deal with them, on this way we would reduce the number of
threads blocked.
