package day07

import TreeNode
import readInput

class FileSystemItem(val name: String, val type: Char, var dimension: Int) {
    fun isDirectory() = type == 'D'
    fun isFile() = type == 'F'
}

fun main() {

    fun calculateDimension(node: TreeNode<FileSystemItem>): Int {
        return node.getChildren().sumOf { child -> if (child.value.isFile()) child.value.dimension else calculateDimension(child) }
    }

    fun createTree(input: List<String>): TreeNode<FileSystemItem> {
        val homeNode: TreeNode<FileSystemItem> = TreeNode(FileSystemItem("/", 'D', 0))
        var actualNode = homeNode
        input.subList(2, input.size).forEach { command ->
            if (command.startsWith("dir ")) {
                val directoryName = command.split(" ")[1]
                actualNode.addChild(TreeNode(FileSystemItem(directoryName, 'D', 0)));
            } else if (command.matches(Regex("[0-9]+ (.+)"))) {
                val filename = command.split(" ")[1]
                val filesize = command.split(" ")[0].toInt()
                actualNode.addChild(TreeNode(FileSystemItem(filename, 'F', filesize)))
            } else if (command == "\$ cd ..") {
                actualNode = actualNode.getParent()!!
            } else if (command.startsWith("\$ cd")) {
                val parent = actualNode
                actualNode = actualNode.getChild { child -> child.value.name == command.split(" ")[2] }!!
                actualNode.setParent(parent);
            }
        }
        return homeNode
    }

    fun getDirectoryDimensions(node: TreeNode<FileSystemItem>, folderList: MutableList<Pair<String, Int>>): MutableList<Pair<String, Int>> {
        folderList.add(Pair(node.value.name, calculateDimension(node)))
        node.getChildren().filter { child -> child.value.isDirectory() }.forEach { child -> getDirectoryDimensions(child, folderList) }
        return folderList;
    }

    fun part1(input: List<String>): Int {
        val homeNode: TreeNode<FileSystemItem> = createTree(input)
        return getDirectoryDimensions(homeNode, ArrayList())
                .map { directoryDimension -> directoryDimension.second }
                .filter { directoryDimension -> directoryDimension < 100000 }
                .sum()
    }

    fun part2(input: List<String>): Int {
        val homeNode: TreeNode<FileSystemItem> = createTree(input)
        val spaceToFree = 30000000 - (70000000 - calculateDimension(homeNode))
        return getDirectoryDimensions(homeNode, ArrayList())
                .map { directoryDimension -> directoryDimension.second }
                .filter { directoryDimension -> (directoryDimension - spaceToFree) > 0 }
                .minBy { directoryDimension -> directoryDimension - spaceToFree }
    }

    check(part1(readInput("files/Day07_test")) == 95437)
    check(part2(readInput("files/Day07_test")) == 24933642)

    val input = readInput("files/Day07")
    println(part1(input))
    println(part2(input))
}
