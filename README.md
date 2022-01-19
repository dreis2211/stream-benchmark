# Results

## JDK 11 (11.0.13-tem)
### `OtherType` at beginning of collection
```
java -jar target/benchmarks.jar -i 5 -wi 5 -r 2 -w 2 -f 2

Benchmark                            Mode  Cnt      Score     Error  Units
MyBenchmark.testChainedFilter        avgt   10   2242,782 ±  91,749  ns/op
MyBenchmark.testIntermediateCollect  avgt   10  24615,184 ± 282,459  ns/op
```
### `OtherType` at end of collection
```
java -jar target/benchmarks.jar -i 5 -wi 5 -r 2 -w 2 -f 2

Benchmark                            Mode  Cnt      Score      Error  Units
MyBenchmark.testChainedFilter        avgt   10  44101,588 ± 5720,545  ns/op
MyBenchmark.testIntermediateCollect  avgt   10  24631,645 ±  362,311  ns/op
```

### `OtherType` at beginning of collection - `-XX:-TieredCompilation`
```
java -XX:-TieredCompilation -jar target/benchmarks.jar -i 5 -wi 5 -r 2 -w 2 -f 2

Benchmark                            Mode  Cnt      Score      Error  Units
MyBenchmark.testChainedFilter        avgt   10   2388,364 ±  551,281  ns/op
MyBenchmark.testIntermediateCollect  avgt   10  25725,572 ± 2363,355  ns/op
```

### `OtherType` at end of collection - `-XX:-TieredCompilation`
```
java -XX:-TieredCompilation -jar target/benchmarks.jar -i 5 -wi 5 -r 2 -w 2 -f 2

Benchmark                            Mode  Cnt      Score     Error  Units
MyBenchmark.testChainedFilter        avgt   10  51934,057 ± 475,458  ns/op
MyBenchmark.testIntermediateCollect  avgt   10  25840,926 ± 429,695  ns/op
```

## JDK 17 (17.0.1-tem)
### `OtherType` at end of collection
```
Benchmark                            Mode  Cnt      Score      Error  Units
MyBenchmark.testChainedFilter        avgt   10  36288,711 ±  307,482  ns/op
MyBenchmark.testIntermediateCollect  avgt   10  35968,964 ± 1483,464  ns/op
```