package lib.std.re

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Measurement
import kotlinx.benchmark.Scope
import kotlinx.benchmark.State
import kotlinx.benchmark.Warmup

@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@State(Scope.Benchmark)
open class RegexBenchmarks {
    val re70750 = Regex("((?<=\\s)|(?=\\s))")
    val re67731 = Regex("""(?<!\\\\)\\"""")
    val re53538 = Regex("""(\\*)@\{(([^'}]|('([^'\\]|\\.)*'))*)\}""")
    val re53538_2 = Regex("""(^\w+)|(\.\w+)|(\[\d+\])""")

    @Benchmark
    fun kt70750() = re70750.split(TextCorpus.LoremIpsum)

    @Benchmark
    fun kt67731() = TextCorpus.LoremIpsum.replace(re67731, "\"")

    @Benchmark
    fun kt53538_1() = re53538.findAll(TextCorpus.Kt53538).count()

    @Benchmark
    fun kt53538_2() = re53538_2.findAll(TextCorpus.Kt53538).count()
}
