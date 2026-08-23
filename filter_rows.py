from pyspark.sql import SparkSession

# Create Spark session
spark = SparkSession.builder \
    .appName("FilterRiverData") \
    .master("local[*]") \
    .getOrCreate()

# Read the CSV file
df = spark.read.csv(
    "RIVER_PH_TDS.csv",
    header=True,
    inferSchema=True
)

# Display original data
print("Original River Data:")
df.show()

# Set threshold
threshold = 300

# Filter rivers where TDS is greater than 300
filtered_df = df.filter(df["TDS"] > threshold)

# Display filtered data
print("Rivers with TDS greater than", threshold)
filtered_df.show()

# Stop Spark
spark.stop()