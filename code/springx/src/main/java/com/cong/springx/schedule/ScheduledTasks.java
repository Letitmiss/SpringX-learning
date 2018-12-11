package com.cong.springx.schedule;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class ScheduledTasks {

  public static   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

  @Scheduled(fixedRate = 5000)
  public void currentTime() {
      System.out.println();
  }

}
