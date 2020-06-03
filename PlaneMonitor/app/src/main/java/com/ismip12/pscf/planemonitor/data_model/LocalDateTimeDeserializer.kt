package com.ismip12.pscf.planemonitor.data_model

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime>() {

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDateTime {
        return LocalDateTime.parse(p?.text).atZone(ZoneOffset.UTC)
            .withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()
    }


}