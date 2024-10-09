package vis.rhynia.monotrix.extensions

import kotlin.random.Random

fun MutableList<Int>.fillZero(fill: Int) = this.apply { while (size < fill) this.add(0, 0) }

fun MutableList<Int>.fillRand(
    fill: Int,
    randMax: Int,
) = this.apply { while (size < fill) this.add(0, Random.nextInt(randMax)) }
