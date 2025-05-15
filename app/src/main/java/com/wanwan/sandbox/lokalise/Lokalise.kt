//package com.wanwan.sandbox.lokalise
//
//import android.icu.text.PluralRules
//import androidx.annotation.PluralsRes
//import androidx.annotation.StringRes
//import kotlinx.serialization.*
//import kotlinx.serialization.json.*
//import java.util.Locale
//
//interface Lokalise {
//    fun stringResource(id: StringId, vararg formatArgs: Any): String?
//    fun pluralStringResource(id: PluralId, count: Double, vararg formatArgs: Any): String?
//}
//
//enum class StringId(val key: String) {
//    SHARE_COUNT("share_count")
//}
//
//enum class PluralId(val key: String) {
//    SHARE_COUNT("share_count")
//}
//
//object LokaliseString {
//    object Home {
//        const val shareCount: String = ""
//        val shareCountPlural: PluralString = PluralString(
//            other = ""
//        )
//    }
//}
//
//@Serializable
//data class PluralString(
//    private val one: String? = null,
//    private val many: String? = null,
//    private val other: String? = null,
//    private val zero: String? = null,
//    private val few: String? = null,
//    private val two: String? = null,
//) {
//    private fun pluralStringResource(count: Int): String? = when (count) {
//        0 -> zero
//        1 -> one
//        else -> many
//    }
//
//}
//
//class LokaliseImpl(private val json: String) : Lokalise {
//    private val strings: Map<String, PluralEntry> = Json.decodeFromString(json)
//
//    override fun stringResource(id: StringId, vararg formatArgs: Any): String? {
//        TODO("Not yet implemented")
//    }
//
//    override fun pluralStringResource(id: PluralId, count: Double, vararg formatArgs: Any): String? {
//        val keyword = PluralRules.forLocale(Locale.FRANCE).select(count)
//        val pluralEntry = strings[id.key] ?: return null
//
//        return when (keyword) {
//            PluralRules.KEYWORD_ZERO -> pluralEntry.many
//            PluralRules.KEYWORD_ONE -> pluralEntry.one
//            PluralRules.KEYWORD_TWO -> pluralEntry.two
//            PluralRules.KEYWORD_FEW -> pluralEntry.few
//            PluralRules.KEYWORD_MANY -> pluralEntry.many
//            PluralRules.KEYWORD_OTHER -> pluralEntry.other
//
//            else -> null
//        }
//    }
//
//
//    private fun Map<String, PluralEntry>.getPluralString(
//        id: PluralId,
//        count: Int,
//        lang: String,
//    ): String {
//        val key = id.key
//        val formKey = getPluralFormKey(count, lang)
//        val pluralEntry = get(key) ?: return "[$key]"
//        val template = when (formKey) {
//            "zero" -> pluralEntry.zero
//            "one" -> pluralEntry.one
//            "two" -> pluralEntry.two
//            "few" -> pluralEntry.few
//            "many" -> pluralEntry.many
//            else -> pluralEntry.other
//        } ?: pluralEntry.other ?: "[$key]"
//
//        return template.format(count)
//    }
//}
//
//object LocalizationLoader {
//    fun loadFromJson(json: String): Map<String, String> {
//        return Json.decodeFromString(json)
//    }
//}
//
//val json = "{\n" +
//        "  \"share_count\": {\n" +
//        "    \"one\": \"%1\$d partage\",\n" +
//        "    \"many\": \"%1\$d partages\",\n" +
//        "    \"other\": \"%1\$d partages\"\n" +
//        "  }\n" +
//        "}\n"
//
//val strings: Map<String, PluralEntry> = Json.decodeFromString(json)
//
//
//@Serializable
//data class PluralEntry(
//    val one: String? = null,
//    val many: String? = null,
//    val other: String? = null,
//    val zero: String? = null,
//    val few: String? = null,
//    val two: String? = null
//)
//
//typealias StringResources = Map<String, PluralEntry>
