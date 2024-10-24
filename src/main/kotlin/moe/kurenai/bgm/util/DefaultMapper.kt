package moe.kurenai.bgm.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import moe.kurenai.bgm.exception.BgmException
import moe.kurenai.bgm.model.error.BgmError
import moe.kurenai.bgm.model.error.NotFoundError
import moe.kurenai.bgm.model.error.ValidationError
import java.io.IOException
import java.net.http.HttpResponse


object DefaultMapper {

    private val log = getLogger()

    val MAPPER: ObjectMapper = jsonMapper {
        addModules(
            kotlinModule {
                withReflectionCacheSize(512)
                    .configure(KotlinFeature.NullToEmptyCollection, false)
                    .configure(KotlinFeature.NullToEmptyMap, false)
                    .configure(KotlinFeature.NullIsSameAsDefault, false)
                    .configure(KotlinFeature.SingletonSupport, true)
                    .configure(KotlinFeature.StrictNullChecks, false)
            },
            Jdk8Module(),
            JavaTimeModule()
        )
    }
        .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)

    fun <T> convertToString(t: T): String {
        return try {
            MAPPER.writeValueAsString(t)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    fun <T> convertToByteArray(t: T): ByteArray {
        return MAPPER.writeValueAsBytes(t)
    }

    fun <T> parseResponse(response: HttpResponse<ByteArray>, reference: TypeReference<T>): T {
        return when (response.statusCode()) {
            200 -> {
                MAPPER.readValue(response.body(), reference)
            }

            404 -> {
                MAPPER.readValue(response.body(), NotFoundError::class.java)
            }

            422 -> {
                MAPPER.readValue(response.body(), ValidationError::class.java)
            }

            else -> {
                BgmError("Unknown response type: ${response.body()}")
            }
        }.let {
            if (it is BgmError) {
                throw BgmException(it)
            } else {
                it as T
            }
        }
    }

    fun <T> convertToMap(t: T): Map<Any, Any?> {
        return MAPPER.convertValue(t, object : TypeReference<Map<Any, Any?>>() {})
    }

    fun <T> HttpResponse<ByteArray>.parse(reference: TypeReference<T>): T {
        return parseResponse(this, reference)
    }
}
