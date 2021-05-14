package com.reactive.ejemploreactivo.controller;


import javax.xml.transform.Result;
@RestController
public class Controller {
    @RequestMapping("/parallel")
    public Mono<Result> parallel() {
        return Flux.range(1, 10)
                .log()
                .flatMap(this::fetch, 4)
                .collect(Result::new, Result::add)
                .doOnSuccess(Result::stop);
    }

    private WebClient client = new WebClient();

    private Mono<HttpStatus> fetch(int value) {
        return this.client.perform(HttpRequestBuilders.get("http://example.com"))
                .extract(WebResponseExtractors.response(String.class))
                .map(response -> response.getStatusCode());
    }




}
