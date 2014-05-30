Spark Standalone Program Example
====================

In order to create the eclipse project, run the following command:

```sh
sbt/sbt eclipse
```

In order to run on local mode of Spark, run following commands:

```sh
sbt/sbt assembly
```

```sh
sbt/sbt run "local 4 input/test.txt output/test"
```