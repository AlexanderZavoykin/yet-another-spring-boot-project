package com.gmail.aazavoykin.processor.config

import com.gmail.aazavoykin.common.dto.Message
import com.gmail.aazavoykin.processor.config.properties.KafkaProperties
import com.gmail.aazavoykin.processor.service.MessageDeduplicationTransformer
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.Produced
import org.apache.kafka.streams.state.Stores
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer
import java.time.Duration
import java.time.temporal.ChronoUnit


@Configuration
class KafkaStreamsConfig(
    private val kafkaProperties: KafkaProperties,
) {

    val keySerde = Serdes.String()
    val valueSerde = Serdes.WrapperSerde(JsonSerializer(), JsonDeserializer(Message::class.java))

    @Bean
    fun kStream(streamsBuilder: StreamsBuilder): KStream<String, Message> {
        val storeName = "id-store"
        val storeBuilder = storeBuilder(storeName)

        streamsBuilder.addStateStore(
            storeBuilder
        )

        val idExtractor = { _: String, value: Message -> value.id }
        val transformer = MessageDeduplicationTransformer(storeName, kafkaProperties.window.sizeMs, idExtractor)

        val stream = streamsBuilder.stream(kafkaProperties.topic.input, Consumed.with(keySerde, valueSerde))
            .transform({ transformer }, storeBuilder.name())
            .filter { _, v -> v != null }
        stream.to(kafkaProperties.topic.output, Produced.with(keySerde, valueSerde))
        return stream
    }

    fun storeBuilder(storeName: String) = Stores.windowStoreBuilder(
        Stores.inMemoryWindowStore(
            storeName,
            Duration.of(kafkaProperties.window.sizeMs, ChronoUnit.MILLIS),
            Duration.of(kafkaProperties.window.sizeMs, ChronoUnit.MILLIS),
            false
        ),
        Serdes.String(),
        Serdes.Integer()
    )
}