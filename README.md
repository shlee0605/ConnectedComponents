Connected Components
====================
This is an implementation of 'Hash-To-Min' algorithm that finds out the connected components in a graph. The detail of algorithm can be found at the following paper:

[Finding Connected Components in Map-Reduce in Logarithmic Rounds](http://arxiv.org/pdf/1203.5387.pdf)

The visualization can be found here: 
[http://connectedcomponents.herokuapp.com](http://connectedcomponents.herokuapp.com)

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
