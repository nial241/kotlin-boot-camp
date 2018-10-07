package io.rybalkinsd.kotlinbootcamp.geometry

/**
 * Entity that can physically intersect, like flame and player
 */
interface Collider {
    fun isColliding(other: Collider): Boolean
}

/**
 * 2D point with integer coordinates
 */
class Point(val x: Int, val y: Int) : Collider {
    override fun isColliding(other: Collider): Boolean = when (other) {
        is Point -> this == other
        is Bar -> other.isColliding(this)
        else -> false
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Point
        return x == other.x && y == other.y
    }
}

/**
 * Bar is a rectangle, which borders are parallel to coordinate axis
 * Like selection bar in desktop, this bar is defined by two opposite corners
 * Bar is not oriented
 * (It does not matter, which opposite corners you choose to define bar)
 */
class Bar(firstCornerX: Int, firstCornerY: Int, secondCornerX: Int, secondCornerY: Int) : Collider {

    fun isPointInsideBar(pointX: Int, pointY: Int) = (pointX >= cornersInOrder[0] && pointX <= cornersInOrder[1] && pointY >= cornersInOrder[2] && pointY <= cornersInOrder[3])

    fun isBarCollidingBar(otherBar: Bar) = isPointInsideBar(otherBar.cornersInOrder[0], otherBar.cornersInOrder[2]) || isPointInsideBar(otherBar.cornersInOrder[0], otherBar.cornersInOrder[3]) || isPointInsideBar(otherBar.cornersInOrder[1], otherBar.cornersInOrder[2]) || isPointInsideBar(otherBar.cornersInOrder[1], otherBar.cornersInOrder[3])

    override fun isColliding(other: Collider) =
        when {
            other == this -> true
            other is Point -> isPointInsideBar(other.x, other.y)
            other is Bar -> isBarCollidingBar(other) || other.isBarCollidingBar(this)
            else -> false
        }

    var cornersInOrder = listOf(firstCornerX, secondCornerX).sorted() + listOf(firstCornerY, secondCornerY).sorted()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Bar
        if (cornersInOrder != other.cornersInOrder) return false
        return true
    }
}