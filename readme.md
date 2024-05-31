#### Application consists of 2 microservices communicating by Kafka:
- news-user-api - user can:
  - send request for world bitcoin news by date (GET with data as query parameter) - first Redis repository is check for news presence,
    if absent, message is send to Kafka topic 'news-request' (String format). There is also the listener of topic 'news-response',
    saving received messages to Redis database.
  - send Json Article (as body to POST endpoint), which is passed to Kafka 'json-topic'
- news-collector - listens for 2 topics ('news-request' with String format, 'json-topic' with Json format). Upon receiving messages from:
  - news requests - checks external API for collecting bitcoin news and serializes obtained results in 'news-response' topic (String format)
  - json-topic - deserializes Json and publishes it to 'avro-topic' (Avro format, based on schema AvroArticleModel.avsc)

Purpose of mixing different topics/formats was to learn Kafka as communicating system between microservices.
 Kafka cluster consists of 3 brokers, each topic is replicated and contains more than 1 partition. 

#### Data flow diagram:

![microservices-with-kafka.drawio.png](microservices-with-kafka.drawio.png)

#### Usage:
- docker-compose up (start kafka cluster/schema registry/redis containters)
- mvn compile both modules
- run both compiled microservices
- API calls:
  - GET '/news'
  - POST '/news-json' with body as: 
  ````json
  {
    "author": "Some famous reporter",
    "title": "with great article",
    "news": "and exciting news content"
  }
