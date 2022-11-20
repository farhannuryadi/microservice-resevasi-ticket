package com.farhan.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class MySchedulerTask {

    @Scheduled(cron = "0 50 23 ? * * *")
    public void myFirstTask(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Task exceuted at "+ fmt.format(System.currentTimeMillis()));
    }
}
