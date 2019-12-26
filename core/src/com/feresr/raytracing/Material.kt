package com.feresr.raytracing

abstract class Material {
    abstract fun scatter(
            rayIn: Ray, hitRecord: Hittable.HitRecord
    ): Pair<Vector, Ray>
}

class Lambertian(private val albedo: Vector) : Material() {
    //Returns <Attenuation, Scattered>
    override fun scatter(rayIn: Ray, hitRecord: Hittable.HitRecord): Pair<Vector, Ray> {
        val target = hitRecord.p + hitRecord.normal + Vector.randomInUnitSphere()
        return albedo to Ray(hitRecord.p, target - hitRecord.p)
    }
}

class Metal(private val albedo: Vector, private val fuzz : Float = 0f) : Material() {

    private fun reflect(v: Vector, n: Vector): Vector {
        return v - n * (v * n) * 2f
    }

    //Returns <Attenuation, Scattered>
    override fun scatter(rayIn: Ray, hitRecord: Hittable.HitRecord): Pair<Vector, Ray> {
        val reflected = reflect(rayIn.direction, hitRecord.normal)
        return albedo to Ray(hitRecord.p, reflected + Vector.randomInUnitSphere() * fuzz )
    }
}