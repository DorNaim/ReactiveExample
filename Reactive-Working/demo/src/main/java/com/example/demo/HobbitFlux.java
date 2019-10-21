package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

@Component
public class HobbitFlux {

	private Flux<Line> hobbit;
	private ConnectableFlux<Line> tolkin;
	private Flux<Line> autoConnect;

	public HobbitFlux() {

		List<Line> lines = new ArrayList<Line>();
		int counter = 0;

		try (BufferedReader stream = new BufferedReader(new FileReader("Hobbit.txt"))) {
			String lineContent = stream.readLine();
			Line line = null;
			while (lineContent != null) {
				line = new Line(lineContent, ++counter);
				lines.add(line);
				lineContent = stream.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		hobbit = Flux.fromIterable(lines);
		tolkin = Flux.fromIterable(lines).delayElements(Duration.ofSeconds(1)).replay(3);
		tolkin.connect();
		autoConnect = Flux.fromIterable(lines).delayElements(Duration.ofSeconds(1)).publish().autoConnect(2);

	}

	public Flux<Line> getHobbit() {
		return hobbit;
	}

	public ConnectableFlux<Line> getTolkin() {
		return tolkin;
	}

	public Flux<Line> getAutoConnect() {
		return autoConnect;
	}

}
