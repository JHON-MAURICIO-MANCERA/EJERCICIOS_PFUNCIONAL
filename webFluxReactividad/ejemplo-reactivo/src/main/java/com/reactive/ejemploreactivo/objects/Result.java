package com.reactive.ejemploreactivo.objects;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class Result {

    private ConcurrentMap<HttpStatus, AtomicLong> counts = new ConcurrentHashMap<>();

    private long timestamp = System.currentTimeMillis();

    private long duration;

    public long add(HttpStatus status) {
        AtomicLong value = this.counts.getOrDefault(status, new AtomicLong());
        this.counts.putIfAbsent(status, value);
        return value.incrementAndGet();
    }

    public void stop() {
        this.duration = System.currentTimeMillis() - this.timestamp;
    }

    public long getDuration() {
        return this.duration;
    }

    public Map<HttpStatus, AtomicLong> getCounts() {
        return this.counts;
    }

}