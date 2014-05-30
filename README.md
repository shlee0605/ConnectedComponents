Spark Standalone Program Example
====================

In order to create the eclipse project, run the following command:

```sh
sbt/sbt eclipse
```

In order to run on the local mode of Spark, run the following commands:

```sh
sbt/sbt assembly
```

```sh
# arguments: run <host> <iteration> <input path> <output path>
sbt/sbt "run local 4 input/test.txt output/test"
```
