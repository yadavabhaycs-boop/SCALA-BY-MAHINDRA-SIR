from pyspark.sql import SparkSession

# Create Spark session
spark = SparkSession.builder \
    .appName("RiverDataJoin") \
    .master("local[*]") \
    .getOrCreate()

# Read first CSV
river_df = spark.read.csv(
    "River.csv",
    header=True,
    inferSchema=True
)

# Read second CSV
quality_df = spark.read.csv(
    "RiverQuality.csv",
    header=True,
    inferSchema=True
)

# Display original data
print("River Data:")
river_df.show()

print("River Quality Data:")
quality_df.show()

# Join both DataFrames using common column: River
joined_df = river_df.join(
    quality_df,
    on="River",
    how="inner"
)

# Display joined data
print("Joined River Data:")
joined_df.show()

# Write output to a file
joined_df.write \
    .mode("overwrite") \
    .option("header", True) \
    .csv("joined_output")

# Stop Spark
spark.stop()