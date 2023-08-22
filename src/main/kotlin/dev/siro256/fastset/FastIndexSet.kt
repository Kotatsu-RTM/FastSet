package dev.siro256.fastset

class FastIndexSet<E> : MutableSet<E> {
    private val list = mutableListOf<E>()
    private val map = hashMapOf<E, Int>()

    override val size: Int
        get() = list.size

    override fun add(element: E): Boolean {
        if (map.containsKey(element)) return false

        list.add(element)
        map[element] = list.size - 1

        return true
    }

    override fun addAll(elements: Collection<E>): Boolean {
        return elements.map(::add).contains(true)
    }

    override fun clear() {
        list.clear()
        map.clear()
    }

    override fun isEmpty(): Boolean {
        return map.isEmpty()
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        return !elements.map { contains(it) }.contains(false)
    }

    override fun contains(element: E): Boolean {
        return map.containsKey(element)
    }

    override fun iterator(): MutableIterator<E> {
        return map.keys.iterator()
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        val elementsForRemove = list.filterNot(elements::contains)
        list.removeAll(elementsForRemove)
        return map.keys.removeAll(elementsForRemove.toSet())
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        list.removeAll(elements)
        return map.keys.removeAll(elements.toSet())
    }

    override fun remove(element: E): Boolean {
        map.remove(element)
        return list.remove(element)
    }

    fun indexOf(element: E): Int {
        return map[element] ?: -1
    }
}
