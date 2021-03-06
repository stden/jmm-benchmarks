/*
 * Copyright (c) 2005, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package org.sample;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ForIncrement {

    @State(Scope.Benchmark)
    public static class PlainShared {
        private int v = 42;
    }

    @State(Scope.Thread)
    public static class PlainLocal {
        private int v = 42;
    }

    @State(Scope.Benchmark)
    public static class VolatileShared {
        private volatile int v = 42;
    }

    @State(Scope.Thread)
    public static class VolatileLocal {
        private volatile int v = 42;
    }

    @GenerateMicroBenchmark
    public int plain_shared(PlainShared s) {
        int sum = 0;
        for (int c = 0; c < s.v; c++) {
            sum += s.v;
        }
        return sum;
    }

    @GenerateMicroBenchmark
    public int plain_local(PlainLocal s) {
        int sum = 0;
        for (int c = 0; c < s.v; c++) {
            sum += s.v;
        }
        return sum;
    }

    @GenerateMicroBenchmark
    public int volatile_shared(VolatileShared s) {
        int sum = 0;
        for (int c = 0; c < s.v; c++) {
            sum += s.v;
        }
        return sum;
    }

    @GenerateMicroBenchmark
    public int volatile_local(VolatileLocal s) {
        int sum = 0;
        for (int c = 0; c < s.v; c++) {
            sum += s.v;
        }
        return sum;
    }

}
