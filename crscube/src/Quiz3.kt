fun main() {
    val directoryArchitect = listOf(
            Node(
                1L, "프로젝트 관리", false, false,
                children = listOf(
                    Node(
                        2L, "프로젝트-2023", false, false,
                        children = listOf(
                            Node(3L, "상반기 보고서.pdf", true, false),
                            Node(
                                4L, "첨부파일", false, false,
                                children = listOf(
                                    Node(5L, "이미지1.png", true, false),
                                    Node(7L, "프로젝트 로고.png", true, false),
                                )
                            ),
                        )
                    )
                )
            ),
            Node(
                6L, "사용자 관리", false, false,
                children = listOf(
                    Node(
                        8L, "사용자-2023", false, false,
                        children = listOf(
                            Node(9L, "사용자 프로젝트 등록 현황.pdf", true, true),
                            Node(
                                10L, "첨부파일", false, true,
                                children = listOf(
                                    Node(11L, "이미지1.png", true, false),
                                    Node(12L, "프로젝트 로고.png", true, false),
                                )
                            ),
                        )
                    )
                )
            )
    )

    println("검색 키워드를 입력해주세요.")
    val keyword = readLine()!!

    solution(directoryArchitect, keyword)
}

fun solution(nodes: List<Node>, keyword: String) {
    println("keyword: $keyword\n")

    for (node in nodes) {
        if (!node.hidden && !node.isFile) {
            travers(node, keyword)
        }
    }
}

fun travers(node: Node, keyword: String) {
    if (node.hidden || node.isFile) return

    val count = countMatchingDescendants(node, keyword)

    println("[${node.key}-${node.name}]: $count")

    for (child in node.children) {
        if (!child.hidden && !child.isFile) {
            travers(child, keyword)
        }
    }
}

fun countMatchingDescendants(node: Node, keyword: String): Int {
    var count = 0;

    fun dfs(current: Node) {
        for (child in current.children) {
            if (!child.hidden) {
                if (keyword in child.name) {
                    count ++
                }

                if (!child.isFile) {
                    dfs(child)
                }
            } else if (!child.isFile && child.hidden) {
                continue
            }
        }
    }

    dfs(node)
    return count
}
data class Node(
    val key: Long,
    val name: String,
    val isFile: Boolean = false,
    val hidden: Boolean = false,
    val children: List<Node> = emptyList(),
)