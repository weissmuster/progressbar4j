package io.github.weissmuster;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ProgressBar4jTest {

  @Test
  void testBuilder() {
    ProgressBar4j progressBar = ProgressBar4j.Builder.builder()
            .withItemName("files")
            .withChunkSize(10)
            .withAmountOfItemsToProcess(100)
            .build();

    assertNotNull(progressBar);
  }

  @Test
  void testInvalidChunkSize() {
    assertThrows(IllegalArgumentException.class, () -> ProgressBar4j.Builder.builder().withChunkSize(-1).build());
  }

  @Test
  void testInvalidAmountOfItems() {
    assertThrows(IllegalArgumentException.class, () -> ProgressBar4j.Builder.builder().withAmountOfItemsToProcess(-10).build());
  }
}
