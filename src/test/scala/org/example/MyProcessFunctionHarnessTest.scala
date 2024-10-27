package org.example

import org.apache.flink.api.common.serialization.SerializerConfigImpl
import org.apache.flink.streaming.api.functions.ProcessFunction
import org.apache.flink.streaming.api.operators.ProcessOperator
import org.apache.flink.streaming.util.OneInputStreamOperatorTestHarness
import org.apache.flink.util.Collector
import org.apache.flinkx.api.serializers._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class MyProcessFunctionHarnessTest extends AnyFunSuite with Matchers {

  private class MyProcessFunction extends ProcessFunction[Int, Option[Int]] {

    override def processElement(event: Int, ctx: ProcessFunction[Int, Option[Int]]#Context, out: Collector[Option[Int]]): Unit = {
      out.collect(None)
    }

  }

  test("Process element should output None") {
    val serializer = deriveTypeInformation[Option[Int]].createSerializer(
      new SerializerConfigImpl()
    )
    val operator = new ProcessOperator(new MyProcessFunction)
    val harness = new OneInputStreamOperatorTestHarness(operator)
    harness.setup(serializer)
    try {
      harness.open()
      harness.processElement(1, 0L)
      harness.getRecordOutput.iterator().next().getValue should be(None)
    } finally {
      harness.close()
    }
  }

}
