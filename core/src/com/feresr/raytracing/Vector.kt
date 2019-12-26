package com.feresr.raytracing

import kotlin.math.sqrt
import kotlin.random.Random

class Vector(val x: Float = 0f, val y: Float = 0f, val z: Float = 0f) {


    operator fun plus(other: Vector): Vector {
        val (x1, y1, z1) = this
        val (x2, y2, z2) = other
        return Vector(x1 + x2, y1 + y2, z1 + z2)
    }

    operator fun times(scalar: Float): Vector {
        val (x1, y1, z1) = this
        return Vector(x1 * scalar, y1 * scalar, z1 * scalar)
    }

    fun unit(): Vector {
        val (x, y, z) = this
        val k = 1f / sqrt(x * x + y * y + z * z)
        return times(k)
    }

    operator fun div(scalar: Float): Vector {
        return Vector(this.x / scalar, this.y / scalar, this.z / scalar)

    }

    fun multiply(other: Vector): Vector {
        return Vector(this.x * other.x,
                this.y * other.y,
                this.z * other.z
        )
    }

    operator fun times(other: Vector): Float {
        return this.x * other.x +
                this.y * other.y +
                this.z * other.z
    }

    operator fun minus(other: Vector): Vector {
        return Vector(
                this.x - other.x,
                this.y - other.y,
                this.z - other.z
        )
    }

    operator fun component1(): Float = x
    operator fun component2(): Float = y
    operator fun component3(): Float = z
    fun squaredLength(): Float {
        return this * this
    }


    companion object {
        private val random = Random(0)
        fun randomInUnitSphere(): Vector {
            var p: Vector = Vector(
                    random.nextFloat(),
                    random.nextFloat(),
                    random.nextFloat()
            ) * 2f - Vector(1f, 1f, 1f)
            return p.unit()
        }
    }

}