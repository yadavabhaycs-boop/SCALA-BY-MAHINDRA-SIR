import os
import sys

# Use the current virtual environment for Spark Python workers
os.environ["PYSPARK_PYTHON"] = sys.executable
os.environ["PYSPARK_DRIVER_PYTHON"] = sys.executable

from pyspark.sql import SparkSession
from pyspark.ml import Pipeline
from pyspark.ml.feature import VectorAssembler
from pyspark.ml.classification import LogisticRegression
from pyspark.ml.evaluation import MulticlassClassificationEvaluator

# Create Spark session
spark = SparkSession.builder \
    .appName("RiverTimeSeriesLogisticRegression") \
    .master("local[*]") \
    .getOrCreate()

# Read the Time Series CSV file
df = spark.read.csv(
    "RiverTimeSeries.csv",
    header=True,
    inferSchema=True
)

# Display original data
print("Original Time Series Data:")
df.show()

# Prepare features
assembler = VectorAssembler(
    inputCols=["Temperature", "Humidity"],
    outputCol="features"
)

# Create Logistic Regression classifier
lr = LogisticRegression(
    featuresCol="features",
    labelCol="Status"
)

# Create ML pipeline
pipeline = Pipeline(
    stages=[assembler, lr]
)

# Split data into training and testing
train_data = df.orderBy("Date").limit(21)

test_data = df.orderBy("Date").tail(9)

# Convert test rows back to DataFrame
test_data = spark.createDataFrame(
    test_data,
    df.schema
)

# Train the model
model = pipeline.fit(train_data)

# Make predictions
predictions = model.transform(test_data)

# Display predictions
print("Logistic Regression Classification Results:")

predictions.select(
    "Date",
    "Temperature",
    "Humidity",
    "Status",
    "prediction"
).show(9, truncate=False)

# Calculate accuracy
evaluator = MulticlassClassificationEvaluator(
    labelCol="Status",
    predictionCol="prediction",
    metricName="accuracy"
)

accuracy = evaluator.evaluate(predictions)

print("Accuracy:", accuracy)
print("Accuracy Percentage:", accuracy * 100, "%")

# Stop Spark
spark.stop()
