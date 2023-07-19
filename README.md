# My Scala Project

This is a Scala project built with SBT. It contains [brief description of your project].

## Prerequisites

To compile and run this project, you need to have the following prerequisites installed on your system:

- Java Development Kit (JDK) 8 or above
- SBT (Scala Build Tool) [Installation instructions](https://www.scala-sbt.org/download.html)

## Getting Started

1. Clone the repository:

   ```shell
   git clone https://github.com/your-username/your-scala-project.git
2. Go to project directory
3. Compile using sbt:
   ```shell
    sbt compile
4. Run using sbt
   ```shell
    sbt run
5. Basic test:
   ```shell
    sbt test

To check the SQL solution, you can directly spin up a basic postgres

docker compose up

The docker compose file will contain required csv and sql files to solve the challenge:
  *  csv file containing sample data
  *  SQL query to create staging table containing data
  *  SQL query to make necessary transformations on data
  *  SQL query to take value from transformed data (solution of challenge)

* You can explore the tables inside the database container:

      docker exec -it <postgres_container_name> -U username -W database
* Enter the password
* You can check and change this values on the .env file
Now you can explore tha tables on the database, the result of the query should be on:

      transactions.balance
    
Note: I've never written code on scala and didnt have much time to get familiar with it, so it will probably not be up to production standars






