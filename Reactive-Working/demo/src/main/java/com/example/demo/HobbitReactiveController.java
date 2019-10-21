package com.example.demo;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class HobbitReactiveController {

	@Autowired
	private HobbitFlux flux;

	@GetMapping(value = "read/{from}/{until}/{delay}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Line> read(@PathVariable int from, @PathVariable int until, @PathVariable int delay) {
		return flux.getHobbit().skipWhile(l -> l.getmLineNumber() < from).takeUntil(l -> l.getmLineNumber() >= until)
				.delayElements(Duration.ofSeconds(delay));
	}

	@GetMapping(value = "readAll", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Line> readAll() {
		return flux.getHobbit();
	}

	@GetMapping(value = "tolken", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Line> tolken() {
		return flux.getTolkin();
	}

	@GetMapping(value = "autoConnect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Line> autoConnect() {
		return flux.getAutoConnect();
	}

}
