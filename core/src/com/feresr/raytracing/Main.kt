package com.feresr.raytracing

import com.badlogic.gdx.ApplicationAdapter
import kotlin.math.sqrt
import kotlin.random.Random

class Main : ApplicationAdapter() {

    private val imageWidth = 400
    private val imageHeight = 200
    private val ns = 500

    private val spheres = HittableList().also {
        it.add(Sphere(Vector(0f, 0f, -1f), .5f, Lambertian(Vector(0.8f, 0.3f, 0.3f))))
        it.add(Sphere(Vector(0f, -100.5f, -1f), 100f, Lambertian(Vector(0.8f, 0.8f, 0.0f))))
        it.add(Sphere(Vector(1f, 0.0f, -1f), .5f, Metal(Vector(0.8f, 0.6f, 0.2f), 0f)))
        it.add(Sphere(Vector(-1f, 0.0f, -1f), .5f, Metal(Vector(0.8f, 0.8f, 0.8f), .3f)))
    }

    private val graphics: Graphics by lazy { Graphics(imageWidth, imageHeight) }

    private val camera = Camera()
    private val random = Random(0)


    override fun create() {
        graphics.init()
    }

    override fun render() {
        for (hi in imageHeight downTo 0) {
            for (wi in 0 until imageWidth) {
                var r = 0f
                var g = 0f
                var b = 0f
                for (i in 0 until ns) {
                    val u = (wi.toFloat() + random.nextFloat()) / imageWidth
                    val v = (hi.toFloat() + random.nextFloat()) / imageHeight
                    val ray = camera.getRay(u, v)
                    val (dr, dg, db) = color(ray)
                    r += dr
                    g += dg
                    b += db
                }
                graphics.drawPixel(wi, imageHeight - 1 - hi, sqrt(r / ns), sqrt(g / ns), sqrt(b / ns))
            }
        }
        graphics.render()
    }

    private fun color(ray: Ray, c: Int = 0): Vector {
        val hit = spheres.hit(ray, 0.001f, Float.MAX_VALUE)
        if (hit != null && c < 50) {
            val (attenuation, scattered) = hit.material.scatter(ray, hit)
            return color(scattered, c + 1).multiply(attenuation)
        }
        val t = 0.5f * (ray.direction.unit().y + 1f)
        return Vector(1f, 1f, 1f) * (1f - t) + Vector(.5f, .7f, 1.0f) * t
    }

    override fun dispose() {
        graphics.dispose()
    }
}