# DuplicateNoneBug

This minimal project allows to reproduce https://github.com/flink-extended/flink-scala-api/issues/106 and https://github.com/flink-extended/flink-scala-api/issues/148 bug.

Somehow, there is 2 instances of `scala.None` case object in the classloader, causing `scala.MatchError: None (of class scala.None$)` error.

A simple ProcessFunction outputting `None`, given to a harness test is able to reproduce the issue.
