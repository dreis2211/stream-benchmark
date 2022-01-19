package org.dreis;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class MyBenchmark {

    interface SomeType {
        String getId();
    }

    static class DefaultType implements SomeType {
        private final String id;
        public DefaultType(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }
    }

    static class OtherType implements SomeType {
        private final String id;
        public OtherType(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }
    }

    @State(Scope.Thread)
    public static class BenchmarkState {
        public String id = "id300";
        public List<SomeType> types;

        public BenchmarkState() {
            types = new ArrayList<>(10000);
            for (int i = 0; i < 9400; i++) {
                types.add(new DefaultType("id" + i));
            }
            for (int i = 0; i < 600; i++) {
                types.add(new OtherType("id" + i));
            }
        }
    }

    @Benchmark
    public Optional<SomeType> testIntermediateCollect(BenchmarkState state) {
        List<SomeType> types = state.types.stream()
                .filter(type -> type instanceof OtherType)
                .collect(Collectors.toList());

        return types.stream()
                .filter(type -> type.getId().equals(state.id))
                .findFirst();
    }

    @Benchmark
    public Optional<SomeType> testChainedFilter(BenchmarkState state) {
        return state.types.stream()
                .filter(type -> type instanceof OtherType)
                .filter(type -> type.getId().equals(state.id))
                .findFirst();
    }

/*
	@Benchmark
	public Optional<SomeType> testCombinedFilter(BenchmarkState state) {
		return state.types.stream()
				.filter(type -> type instanceof OtherType && type.getId().equals(state.id))
				.findFirst();
	}

	@Benchmark
	public Optional<SomeType> testVanilla(BenchmarkState state) {
		for (SomeType type : state.types) {
			if (type instanceof OtherType) {
				if (type.getId().equals(state.id)) {
					return Optional.of(type);
				}
			}
		}
		return Optional.empty();
	}
*/

}
