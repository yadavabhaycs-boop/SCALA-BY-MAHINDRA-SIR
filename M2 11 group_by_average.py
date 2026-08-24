from pyspark.sql import SparkSession
from pyspark.sql.functions import avg

# Create Spark session
spark = SparkSession.builder \
    .appName("RiverGroupByAverage") \
    .master("local[*]") \
    .getOrCreate()

# Read the river CSV file
df = spark.read.csv(
    "River.csv",
    header=True,
    inferSchema=True
)

# Display the original data
print("Original River Data:")
df.show()

# Group by pH and calculate average TDS
result = df.groupBy("pH").agg(
    avg("TDS").alias("Average_TDS")
)

# Display the result
print("Average TDS by pH:")
result.show()

# Stop Spark
spark.stop()
