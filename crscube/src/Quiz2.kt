fun main() {
    val codeGroups = listOf(
        CodeGroup(
            codeId = "COUNTRY",
            codes = listOf(
                CodeGroup.Code(
                    value = "KR",
                    labels = listOf(
                        CodeGroup.CodeLabel(language = "ko", label = "한국"),
                        CodeGroup.CodeLabel(language = "en", label = "Republic of korea")
                    )
                ),
                CodeGroup.Code(
                    value = "CN",
                    labels = listOf(
                        CodeGroup.CodeLabel(language = "ko", label = "한국"),
                        CodeGroup.CodeLabel(language = "en", label = "China")
                    )
                ),
                CodeGroup.Code(
                    value = "DK",
                    labels = listOf(
                        CodeGroup.CodeLabel(language = "ko", label = "덴마크"),
                        CodeGroup.CodeLabel(language = "en", label = "Denmark")
                    )
                ),
                CodeGroup.Code(
                    value = "US",
                    labels = listOf(
                        CodeGroup.CodeLabel(language = "ko", label = "미"),
                        CodeGroup.CodeLabel(language = "en", label = "United States")
                    )
                )
            )
        )
    )

    val target = codeGroups.find { it.codeId === "COUNTRY" }?.codes?.find { it.value === "KR" }?.labels?.associate { it.language to it.label }

    println(target)
}

data class CodeGroup(
    val codeId: String,
    val codes: List<Code>
) {
    data class Code(
        val value: String,
        val labels: List<CodeLabel>
    )

    data class CodeLabel(
        val language: String,
        val label: String
    )
}

