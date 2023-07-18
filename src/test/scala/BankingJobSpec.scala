import analytics.BankingJob
import org.apache.spark.sql.{SparkSession, Dataset, Row}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BankingJobSpec extends AnyFlatSpec with Matchers {
  val spark: SparkSession = {
    SparkSession.builder()
      .appName("BankingJobSpec")
      .master("local")
      .getOrCreate()
  }

  import spark.implicits._

  "transactionsByAccount" should "return top transacting accounts correctly" in {
    val input = Seq(
      ("1", "2", 100),
      ("1", "3", 200),
      ("2", "3", 300),
      ("3", "1", 400),
      ("3", "2", 500)
    ).toDF("fromAccountNumber", "toAccountNumber", "transferAmount")

    val actualOutput = BankingJob.transactionsByAccount(input)

    val expectedOutput = Map("3" -> 2L, "1" -> 2L, "2" -> 1L)
    actualOutput shouldBe expectedOutput
  }
}

