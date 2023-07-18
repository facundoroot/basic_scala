package analytics
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object BankingJob {
  
  val accountsSchema = StructType(Array(
    StructField("accountNumber", StringType, true),
    StructField("balance", DoubleType, true)
  ))

  val transactionsSchema = StructType(Array(
    StructField("fromAccountNumber", StringType, true),
    StructField("toAccountNumber", StringType, true),
    StructField("transferAmount", DoubleType, true)
  ))

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("Banking Data Mining")
      .master("local")
      .getOrCreate()
    
    val accountsDf = spark.read.option("header", "true").schema(accountsSchema).csv("resources/accounts.csv")
    val transactionsDf = spark.read.option("header", "true").schema(transactionsSchema).csv("resources/transactions.csv")

    val validDf = extractValidTransactions(accountsDf, transactionsDf)
    val distinctTransactionsCount = distinctTransactions(transactionsDf)
    val byAccount = transactionsByAccount(transactionsDf)

    println("Valid Transactions: ")
    validDf.show()

    println("Distinct Transactions Count: ")
    println(distinctTransactionsCount)

    println("Transactions By Account: ")
    byAccount.foreach(println)

    spark.stop()
  }
  
  def extractValidTransactions(accountsDf: DataFrame, transactionsDf: DataFrame): DataFrame = {
    val validAccountsDf = accountsDf.alias("accounts")
    val validTransactionsDf = transactionsDf.alias("transactions")

    validTransactionsDf.join(
      validAccountsDf, 
      validTransactionsDf.col("toAccountNumber") === validAccountsDf.col("accountNumber")
    )
    .filter(validTransactionsDf.col("transferAmount") <= validAccountsDf.col("balance"))
    .select("transactions.*")
  }
  
  def distinctTransactions(transactionsDf: DataFrame): Long = {
    transactionsDf.select("fromAccountNumber").distinct.count
  }
  
  def transactionsByAccount(transactionsDf: DataFrame): Map[String, Long] = {
    transactionsDf.groupBy("fromAccountNumber")
      .count
      .orderBy(desc("count"))
      .limit(10)
      .collect()
      .map(row => (row.getString(0), row.getLong(1)))
      .toMap
  }
}

