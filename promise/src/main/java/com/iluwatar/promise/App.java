package com.iluwatar.promise;

import com.iluwatar.async.method.invocation.ThreadAsyncExecutor;

/**
 * 
 * Application that uses promise pattern.
 */
public class App {

  /**
   * Program entry point
   * @param args arguments
   * @throws InterruptedException if main thread is interruped.
   */
  public static void main(String[] args) throws InterruptedException {
    ThreadAsyncExecutor executor = new ThreadAsyncExecutor();
    
    Promise<Integer> consumedPromise = new Promise<>();
    consumedPromise.fulfillInAsync(() -> {
      Thread.sleep(1000);
      return 10;
    }, executor).then(value -> {
      System.out.println("Consumed int value: " + value);
    });
    
    Promise<String> transformedPromise = new Promise<>();
    transformedPromise.fulfillInAsync(() -> {
      Thread.sleep(1000);
      return "10";
    }, executor).then(value -> { return Integer.parseInt(value); }).then(value -> {
      System.out.println(value);
    });
    
    consumedPromise.await();
    transformedPromise.await();
  }
}
