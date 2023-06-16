package com.example.service

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.net.NetworkInterface
import java.util.concurrent.atomic.AtomicLong

/**
 * Unique ID generator, inspired by https://www.callicoder.com/distributed-unique-id-sequence-number-generator/
 *
 * A few notes:
 * * The ID values need to be unique for the domain in which they are generated, not across all domains. ID values generated
 *   for a 'Foo' data type will not collide with a 'Bar' data type. This is important with regards to the NODE_ID_BITS
 *   value - that value does not need to represent ALL nodes in a system, but rather represents the number of nodes
 *   for that data type.
 * * The SEQUENCE_BITS value allows for 16384 values before rolling over to zero.  In theory duplicate ID values can
 *   be generated if 16384 values are generated in a single millisecond for the same data type.  The likelyhood of this
 *   is very low as data for this system will not be generated at that rate. That would be 16384 create requests for
 *   the same data type being handled in a single millisecond.
 */
class IdGenerator(private val nodeId: Int) {

    private var lastTimestamp: AtomicLong = AtomicLong(getTimestamp())
    private var lastSequence: AtomicLong = AtomicLong(0L)

    internal fun getNextTimestamp(): Long {
        val nextTimestamp = getTimestamp()
        assert(nextTimestamp >= lastTimestamp.get()) { "Timestamp generation error, next is less than last, check system clock!" }

        lastTimestamp.set(nextTimestamp)

        return nextTimestamp
    }

    internal fun getNextSequence(): Long {
        return lastSequence.incrementAndGet() and maxSequence
    }

    fun getNextIdLong(): Long {
        val sequence = getNextSequence()
        val timestamp = getNextTimestamp()

        val nextId = timestamp shl (NODE_ID_BITS + SEQUENCE_BITS) or nodeId.toLong() shl (SEQUENCE_BITS) or sequence

        return nextId
    }

    fun getNextIdULong(): ULong = getNextIdLong().toULong()

    fun getNextId(): String = getNextIdULong().toString(36).uppercase()

    companion object {
        private const val UNUSED_BITS = 1 // Sign bit, Unused (always set to 0)

        private const val EPOCH_BITS = 41

        // handles a maximum of 256 machines/nodes per domain
        private const val NODE_ID_BITS = 8

        // 16384 sequence values - essentially limits the generation of that many unique IDs per millisecond
        private const val SEQUENCE_BITS = 14

        private val maxNodeId = (Math.pow(2.0, NODE_ID_BITS.toDouble()) - 1).toInt()
        private val maxSequence = (Math.pow(2.0, SEQUENCE_BITS.toDouble()) - 1).toLong()

        private val customEpoch = Instant
            .parse("2020-06-01T00:00:00.00Z")
            .toEpochMilliseconds()

        internal fun getTimestamp(): Long {
            return Clock.System.now().toEpochMilliseconds() - customEpoch
        }

        internal fun getNodeId(): Int {
            return NetworkInterface
                .getNetworkInterfaces()
                .asSequence()
                .map {
                    when (it.hardwareAddress) {
                        null -> ""
                        else -> it.hardwareAddress.map { "%02X".format(it) }.joinToString { "" }
                    }
                }.joinToString { "" }
                .hashCode() and maxNodeId
        }

        val instance = IdGenerator(getNodeId())
    }
}
