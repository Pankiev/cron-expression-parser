package org.shift4.cronexpressionparser


fun String.replaceIgnoreCase(replacements: List<Pair<String, String>>): String {
    var result = this
    replacements.forEach { (text, replacement) -> result = result.replace(text, replacement, ignoreCase = true) }
    return result
}
