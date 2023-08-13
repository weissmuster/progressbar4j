package io.github.weissmuster;

import java.text.DecimalFormat;

public class ProgressBar4j {

  private static final DecimalFormat df = new DecimalFormat("0.00");
  private static final System.Logger LOG = System.getLogger(ProgressBar4j.class.getName());
  private final long startTime = System.currentTimeMillis();
  private String itemName = "items";
  private int chunkSize = 100;
  private int amountOfItemsToProcess = 0;
  private int processedItems = 1;

  private ProgressBar4j(Builder builder) {
    itemName = builder.itemName;
    chunkSize = builder.chunkSize;
    amountOfItemsToProcess = builder.amountOfItemsToProcess;
  }

  public void trigger() {
    process(processedItems++);
  }

  private void process(int processedItems) {

    if (processedItems == 0) {
      return;
    }

    long progressTime = System.currentTimeMillis();
    long elapsedTime = startTime - progressTime;
    long allTimeForProcessing = (elapsedTime * amountOfItemsToProcess / processedItems);
    long remainingTime = (allTimeForProcessing - elapsedTime) * -1;

    double processedInPercent = (double) processedItems * 100.0 / (double) amountOfItemsToProcess;

    if (processedItems % chunkSize == 0) {
      logMessage(processedItems, processedInPercent, progressTime, remainingTime);
    }

    if (processedItems == amountOfItemsToProcess) {
      logMessage(processedItems, processedInPercent, progressTime, remainingTime);
    }
  }

  private void logMessage(
      int processedItems, double processedInPercent, long progressTime, long remainingTime) {
    LOG.log(
        System.Logger.Level.INFO,
        String.format(
            "%d of %d %s done. | %s%% processed | ElapsedTime: %s minutes | RemainingTime: %s minutes left",
            processedItems,
            amountOfItemsToProcess,
            itemName,
            df.format(processedInPercent),
            ((progressTime - startTime) / 1000 / 60),
            remainingTime / 1000 / 60));
  }

  public static final class Builder {
    private String itemName;
    private int chunkSize;
    private int amountOfItemsToProcess;

    private Builder() {}

    public static Builder builder() {
      return new Builder();
    }

    public Builder withItemName(String val) {
      itemName = val;
      return this;
    }

    public Builder withChunkSize(int val) {
      chunkSize = val;
      return this;
    }

    public Builder withAmountOfItemsToProcess(int val) {
      amountOfItemsToProcess = val;
      return this;
    }

    public ProgressBar4j build() {
      return new ProgressBar4j(this);
    }
  }
}
