package net.itskev.springnativecounter.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("counter")
public class CounterEntity {

  private String id;
  private int value;

  public void inc(int amount) {
    this.value += amount;
  }
}
