package io.github.weissmuster;

import java.text.DecimalFormat;

/**
 * ProgressBar4j provides a progress bar utility for Java applications. It helps
 * in monitoring the progress of long-running tasks.
 *
 * Example usage:
 * <pre>
 *     ProgressBar4j progressBar = ProgressBar4j.Builder.builder()
 *                 .withItemName("files")
 *                 .withChunkSize(10)
 *                 .withAmountOfItemsToProcess(100)
 *                 .build();
 *     progressBar.trigger();
 * </pre>
 */
public class ProgressBar4j {

  private static final DecimalFormat df = new DecimalFormat("0.00");
  private static final System.Logger LOG = System.getLogger(ProgressBar4j.class.getName());
  private final long startTime = System.currentTimeMillis();
  private String itemName = "items";
  private int chunkSize = 100;
  private int amountOfItemsToProcess = 0;
  private int processedItems = 1;

  /**
   * Private constructor to initialize a ProgressBar4j instance via its builder.
   *
   * @param builder The builder used to configure and create the ProgressBar4j instance.
   */
  private ProgressBar4j(Builder builder) {
    itemName = builder.itemName;
    chunkSize = builder.chunkSize;
    amountOfItemsToProcess = builder.amountOfItemsToProcess;
  }

  /**
   * Triggers the processing of an item and updates the progress.
   */
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

  /**
   * The builder class for ProgressBar4j.
   */
  public static final class Builder {
    private String itemName;
    private int chunkSize;
    private int amountOfItemsToProcess;

    private Builder() {}

    /**
     * Returns a new instance of the Builder.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
      return new Builder();
    }

    /**
     * Sets the name of the item being processed.
     *
     * @param val Name of the item.
     * @return The Builder instance.
     */
    public Builder withItemName(String val) {
      itemName = val;
      return this;
    }

    /**
     * Sets the chunk size for logging.
     * For example, if chunkSize is set to 10, a log will be printed every 10 items processed.
     *
     * @param val The chunk size value.
     * @return The Builder instance.
     */
    public Builder withChunkSize(int val) {
      chunkSize = val;
      return this;
    }

    /**
     * Sets the total number of items to be processed.
     *
     * @param val The total number of items.
     * @return The Builder instance.
     */
    public Builder withAmountOfItemsToProcess(int val) {
      amountOfItemsToProcess = val;
      return this;
    }

    /**
     * Constructs and returns a new instance of ProgressBar4j using the configured values.
     *
     * @return A new ProgressBar4j instance.
     */
    public ProgressBar4j build() {
      return new ProgressBar4j(this);
    }
  }
}
