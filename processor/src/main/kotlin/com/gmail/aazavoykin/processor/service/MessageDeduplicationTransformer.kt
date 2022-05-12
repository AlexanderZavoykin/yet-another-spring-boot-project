package com.gmail.aazavoykin.processor.service

import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.kstream.Transformer
import org.apache.kafka.streams.processor.ProcessorContext
import org.apache.kafka.streams.state.WindowStore

class MessageDeduplicationTransformer<K, V, T>(
    private val storeName: String,
    retentionMs: Long,
    private val storeKeyExtractor: (K, V) -> T,
) : Transformer<K, V, KeyValue<K, V>> {

    private lateinit var store: WindowStore<T, Int>
    private lateinit var context: ProcessorContext
    private val leftDurationMs = retentionMs / 2
    private val rightDurationMs = retentionMs - leftDurationMs

    override fun init(context: ProcessorContext?) {
        this.context = context!!
        store = context.getStateStore(storeName) as WindowStore<T, Int>
    }

    override fun transform(key: K, value: V): KeyValue<K, V>? {
        val timestamp = context.timestamp()
        val storeKey = storeKeyExtractor(key, value)

        val storeIterator = store.fetch(storeKey, timestamp - leftDurationMs, timestamp + rightDurationMs)

        return if (storeIterator.hasNext()) {
            null
        } else {
            store.put(storeKey, 1, timestamp)
            KeyValue(key, value)
        }
    }

    override fun close() {
        // do nothing
    }

}