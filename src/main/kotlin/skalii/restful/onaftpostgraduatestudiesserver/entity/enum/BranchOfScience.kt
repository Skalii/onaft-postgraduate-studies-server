package skalii.restful.onaftpostgraduatestudiesserver.entity.enum


import com.fasterxml.jackson.annotation.JsonValue

import javax.persistence.AttributeConverter
import javax.persistence.Converter


enum class BranchOfScience(@get:JsonValue val value: String) {

    EMPTY(""),
    UNKNOWN("Невідома галузь науки"),
    PHYSICAL_MATHEMATICAL("фізико-математичних наук"),
    TECHNICAL("технічних наук"),
    CHEMICAL("хімічних наук"),
    BIOLOGICAL("біологічних наук"),
    MEDICAL("медичних наук"),
    AGRICULTURAL("сільськогосподарських наук"),
    VETERINARY("ветеринарних наук"),
    GEOLOGICAL("геологічних наук"),
    ECONOMICAL("економічних наук"),
    PSYCHOLOGICAL("психологічних наук"),
    HISTORICAL("історичних наук"),
    PHILOLOGICAL("філологічних наук"),
    PEDAGOGICAL("педагогічних наук"),
    PHILOSOPHICAL("філософських наук"),
    SOCIOLOGICAL("соціологічних наук"),
    JURIDICAL("юридичних наук");

    companion object {

        @Converter
        class EnumConverter : AttributeConverter<BranchOfScience, String> {

            override fun convertToDatabaseColumn(attribute: BranchOfScience?) =
                    attribute?.value ?: UNKNOWN.value

            override fun convertToEntityAttribute(dbData: String?): BranchOfScience {
                BranchOfScience.values().forEach { if (it.value == dbData) return it }
                return UNKNOWN
            }

        }

    }

}