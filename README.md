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

                              +----------------------------+
                              |                            |
                              |  Tfidf.java                |
                              |                            |         +------------------------------+
+-------------------+         |                            |         |                              |
|                   +-------->+                            +<--------+                              |
|FileWatcher.java   |         +----------------------------+         |                              |
|                   |                                                |                              |
|                   |                                                |  Notifier.java               |
|                   +-----+   +----------------------------+         |                              |
+--------+----------+     |   |                            |         |                              |
         |                +-->+  TfidfExperimental.java    |         |                              |
         |                    |                            +<--------+                              |
         |                    |                            |         +------------------------------+
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

Multiple Tfidf implementations can be added by just implementing Tfidf and Listener interfaces

### Comments
* Enable/Disable Tfidf implementations by just commenting @Component annotation

### Things to do
* Some more unit tests

### Alternatives considered

