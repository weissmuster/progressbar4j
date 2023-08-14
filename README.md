# progressbar4j
A simple-to-use Java toolkit for progress bars.

## Introduction
progressbar4j is a library designed to enable Java developers to easily integrate progress bars into their applications.

## Prerequisites
Java JDK 17 or higher

## Setup
```
git clone https://github.com/weissmuster/progressbar4j.git
cd progressbar4j
mvn install
```

## Usage
Here's a simple example on how to use progressbar4j:
```
package io.github.weissmuster;

import java.util.ArrayList;
import java.util.List;

public class Demo {

  public static void main(String[] args) {
    
    // for demo we create a list with some items
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      list.add(i);
    }

    ProgressBar4j progressBar4j =
        ProgressBar4j.Builder.builder()
            .withChunkSize(5)
            .withAmountOfItemsToProcess(list.size())
            .withItemName("RandomItemName")
            .build();

    list.forEach(item -> progressBar4j.trigger());
  }
}
```

### console output
```
INFO: 5 of 1000 RandomItemName done. | 0.50% processed | ElapsedTime: 0 minutes | RemainingTime: 1 minutes left
Aug 14, 2023 4:40:04 PM io.github.weissmuster.ProgressBar4j logMessage
INFO: 10 of 1000 RandomItemName done. | 1.00% processed | ElapsedTime: 0 minutes | RemainingTime: 1 minutes left
Aug 14, 2023 4:40:05 PM io.github.weissmuster.ProgressBar4j logMessage
INFO: 15 of 1000 RandomItemName done. | 1.50% processed | ElapsedTime: 0 minutes | RemainingTime: 1 minutes left
Aug 14, 2023 4:40:05 PM io.github.weissmuster.ProgressBar4j logMessage
INFO: 20 of 1000 RandomItemName done. | 2.00% processed | ElapsedTime: 0 minutes | RemainingTime: 1 minutes left
Aug 14, 2023 4:40:06 PM io.github.weissmuster.ProgressBar4j logMessage
INFO: 25 of 1000 RandomItemName done. | 2.50% processed | ElapsedTime: 0 minutes | RemainingTime: 1 minutes left
```

## Features
- Easy integration
- Customizable design

## Contributing
Contributions from the community are welcomed! Please create an issue or a pull request if you've found a bug or wish to suggest a new feature.

## License
This project is licensed under the Apache License, Version 2.0. See the [LICENSE](https://www.apache.org/licenses/LICENSE-2.0.txt) file for more details.

