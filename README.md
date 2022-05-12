# Yet another Spring Boot app

Simple example of Kafka message deduplication by **Kafka Streams** windowing.

---

Project consists of two applications:
- *emitter*, that generates and sends messages to some Kafka topic. Some messages are generated with duplicated `id` param;
- *processor*, that consumes messages from *emitter*'s topic, de-duplicates them by `id` param and then produces them to another topic.

*Emitter* is based on using traditional Spring's KafkaTemplate, while *Processor* uses Kafka Streams functionality.

Commonly used classes are located in subproject *common*.

---

Before running both applications, you need to start Docker containers by executing command:
```bash
docker-compose up -d
```

Then start both applications.

Then you can compare messages in source and target topics on Kafka UI (`localhost:9001`) and make sure that they are de-duplicated by `id` param.

**IMPORTANT NOTE:** Target topic may still have duplicated, but uncommitted (in terms of *Kafka transactions*) messages, for example after re-balancing. To consume only de-duplicated messages you should read committed messages only (set *Kafka Streams*' property `isolation.level = read_committed`).