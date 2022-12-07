package day07

import TreeNode
import readInput

class FileSystemItem(val name: String, val type: Char, var dimension: Int) {
    fun isDirectory() = type == 'D'
    fun isFile() = type == 'F'
}

fun main() {

    fun calculateDimension(node: TreeNode<FileSystemItem>): Int {
        var totalSize = 0;
        node.getChildren().forEach { child ->
            if (child.value.isFile()) {
                totalSize += child.value.dimension;
            }
            if (child.value.isDirectory()) {
                totalSize += calculateDimension(child);
            }
        }

        return totalSize;
    }

    fun navigateAndCalculate(node: TreeNode<FileSystemItem>): Int {
        var sizeSum = 0;
        val calculatedDimension = calculateDimension(node);
        if (calculatedDimension < 100000) {
            sizeSum += calculatedDimension;
        }
        node.getChildren().forEach { child -> sizeSum += navigateAndCalculate(child) }
        return sizeSum;
    }

    fun createTree(input: List<String>): TreeNode<FileSystemItem> {
        val subInput = input.subList(2, input.size)
        val homedir = FileSystemItem("/", 'D', 0)
        val homeNode: TreeNode<FileSystemItem> = TreeNode(homedir)
        var actualNode = homeNode
        subInput.forEach { command ->
            if (command.startsWith("dir ")) {
                actualNode.addChild(TreeNode(FileSystemItem(command.split(" ")[1], 'D', 0)));
            } else if (command.matches(Regex("[0-9]+ (.+)"))) {
                actualNode.addChild(TreeNode(FileSystemItem(command.split(" ")[1], 'F', command.split(" ")[0].toInt())))
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
        return navigateAndCalculate(homeNode);
    }

    fun part2(input: List<String>): Int {
        val homeNode: TreeNode<FileSystemItem> = createTree(input)
        val usedSpace = calculateDimension(homeNode)
        val spaceToFree = 30000000 - (70000000 - usedSpace);

        val directoryDimensions = ArrayList<Pair<String, Int>>()
        getDirectoryDimensions(homeNode, directoryDimensions);

        val minOf = directoryDimensions.filter { directoryDimension -> (directoryDimension.second - spaceToFree) > 0 }.minBy { directoryDimension -> directoryDimension.second - spaceToFree }

        return minOf.second;
    }

    println(part1(readInput("files/Day07_test")))
    check(part1(readInput("files/Day07_test")) == 95437)
    println(part2(readInput("files/Day07_test")))
    check(part2(readInput("files/Day07_test")) == 24933642)
    println()
    
    val input = readInput("files/Day07")
    println(part1(input))
    println(part2(input))
}
