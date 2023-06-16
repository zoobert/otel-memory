package com.example.client

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure

@Serializable(with = TokenResponseSerializer::class)
data class TokenResponse(
    val accessToken: String,
    val expiresIn: Long,
    val refreshExpiresIn: Long,
    val tokenType: String,
    val notBeforePolicy: Long,
    val scope: String,
    val tenantId: String,
)

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = TokenResponse::class)
class TokenResponseSerializer : KSerializer<TokenResponse> {

    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("TokenResponse") {
            element<String>("access_token")
            element<Long>("expires_in")
            element<Long>("refresh_expires_in")
            element<String>("token_type")
            element<Long>("not-before-policy")
            element<String>("scope")
            element<String>("tenant_id")
        }

    override fun deserialize(decoder: Decoder): TokenResponse =
        decoder.decodeStructure(descriptor) {
            var accessToken = ""
            var expiresIn = 0L
            var refreshExpiresIn = 0L
            var tokenType = ""
            var notBeforePolicy = -1L
            var scope = ""
            var tenantId = ""

            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> accessToken = decodeStringElement(descriptor, index)
                    1 -> expiresIn = decodeLongElement(descriptor, index)
                    2 -> refreshExpiresIn = decodeLongElement(descriptor, index)
                    3 -> tokenType = decodeStringElement(descriptor, index)
                    4 -> notBeforePolicy = decodeLongElement(descriptor, index)
                    5 -> scope = decodeStringElement(descriptor, index)
                    6 -> tenantId = decodeStringElement(descriptor, index)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index while deserializing ${TokenResponse::class}: $index")
                }
            }

            TokenResponse(
                accessToken = accessToken,
                expiresIn = expiresIn,
                refreshExpiresIn = refreshExpiresIn,
                tokenType = tokenType,
                notBeforePolicy = notBeforePolicy,
                scope = scope,
                tenantId = tenantId,
            )
        }

    override fun serialize(encoder: Encoder, value: TokenResponse) {
        encoder.encodeStructure(descriptor = descriptor) {
            encodeStringElement(descriptor = descriptor, index = 0, value = value.accessToken)
            encodeLongElement(descriptor = descriptor, index = 1, value = value.expiresIn)
            encodeLongElement(descriptor = descriptor, index = 2, value = value.refreshExpiresIn)
            encodeStringElement(descriptor = descriptor, index = 3, value = value.tokenType)
            encodeLongElement(descriptor = descriptor, index = 4, value = value.notBeforePolicy)
            encodeStringElement(descriptor = descriptor, index = 5, value = value.scope)
            encodeStringElement(descriptor = descriptor, index = 6, value = value.tenantId)
        }
    }
}
