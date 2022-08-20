package yr2021.common

import org.scalatest.flatspec.AnyFlatSpec

class InputDataTest extends AnyFlatSpec {

  it should "do multi line" in {
    val blocks = InputData.fromFile("yr2021/common/input1.txt").multiLineRecordParse(InputData.emptyLineSeparater)
    assert (blocks == 2)
  }

}
