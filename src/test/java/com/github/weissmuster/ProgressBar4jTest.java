package com.github.weissmuster;

import org.junit.jupiter.api.Test;

class ProgressBar4jTest {

  @Test
  void trigger() {

    int itemsToProcess = 83;
    ProgressBar4j progressbar = ProgressBar4j.Builder.builder().withChunkSize(25).withItemName("FooBar").withAmountOfItemsToProcess(itemsToProcess).build();

    for(int i = 0; i < itemsToProcess; i++) {
      progressbar.trigger();
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
