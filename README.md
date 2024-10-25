# DuplicateNoneBug

## Description

This minimal project allows to reproduce https://github.com/flink-extended/flink-scala-api/issues/106 and https://github.com/flink-extended/flink-scala-api/issues/148 bug.

Somehow, there is 2 instances of `scala.None` case object in the classloader, causing `scala.MatchError: None (of class scala.None$)` error.

A simple ProcessFunction outputting `None`, given to a harness test is able to reproduce the issue.

## Usage

Reproduced with JDK 11, Scala 2.12.20
- flink-scala-api 1.1.7 and 1.2.0
- Flink 1.19.1 and 1.20.0
- IntelliJ test execution and `sbt test`
