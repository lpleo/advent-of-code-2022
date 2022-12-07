class TreeNode<T>(val value: T) {
    private val children: MutableList<TreeNode<T>> = mutableListOf()
    private var parent: TreeNode<T>? = null

    fun addChild(child: TreeNode<T>) = children.add(child)
    fun getChild(findFunction: (child: TreeNode<T>) -> Boolean) = children.find { child -> findFunction(child) }
    fun getChildren() = children
    fun hasChildren() = children.size > 0
    fun setParent(parent: TreeNode<T>) = run { this.parent = parent }
    fun getParent() = parent
}
