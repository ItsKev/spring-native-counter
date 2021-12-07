package net.itskev.springnativecounter.controller;

import lombok.RequiredArgsConstructor;
import net.itskev.springnativecounter.data.CounterEntity;
import net.itskev.springnativecounter.data.CounterRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CounterController {

  private static final String ID = "Test";

  private final CounterRepository counterRepository;

  @PostMapping(value = "/increase/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Counter increaseCounter(@PathVariable int amount) {
    CounterEntity counterEntity = counterRepository.findById(ID).orElseGet(() -> new CounterEntity(ID, 0));
    counterEntity.inc(amount);

    CounterEntity savedEntity = counterRepository.save(counterEntity);

    return new Counter(savedEntity.getValue());
  }
}
