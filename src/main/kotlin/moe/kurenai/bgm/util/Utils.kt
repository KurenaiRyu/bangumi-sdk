package moe.kurenai.bgm.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author Kurenai
 * @since 2023/1/25 19:51
 */

fun getLogger(name: String = Thread.currentThread().stackTrace[2].className): Logger {
    return LoggerFactory.getILoggerFactory().getLogger(name)
}
