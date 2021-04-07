package com.example.ratelimiter.service;

import org.assertj.core.groups.Tuple;


import java.io.*;
import java.util.*;
class Solution {
    public static void main(String[] args) {
        //Return false
        System.out.println(rateLimit("device_info", 30, 3));
        System.out.println(rateLimit("device_info", 30, 3));
        System.out.println(rateLimit("device_info", 30, 3));
        //Return true
        System.out.println(rateLimit("device_info", 30, 3));
    }
    static HashMap<String, long> cache = new HashMap<String, long>();

    public static boolean rateLimit(String key, int intervalInSecs, int maxLimit) {
        // Return "true" if no more requests are allowed, and "false" otherwise
        private Queue<String> requests;

        long timeStamp = System.currentTimeMillis()/1000;

        while(!this.requests.isEmpty()) {
            if(!this.cache.containsKey(key)) {
                this.cache.put(key, timeStamp);
            }
            //check the limit
            if(this.requests.size() > maxLimit) {
                return false;
            }
            int diff = timeStamp - this.requests.peek();
            //whatever threshhold we keep 30 seconds
            if(diff >= intervalInSecs) {
                return false;
            } else {
                this.cache.put(key, timeStamp);
                return true;
            }
        }
    }
    public static boolean rateLimit(String key, int intervalInSecs, int maxLimit) {
         if(cache.containsKey(key)){
             int timeStamp = cache.getValue(key);
         }
         while(!this.requests.isEmpty()) {
             if(requests.size() > maxLimit) {
                 return false;
             }
             requests.add(new Request);
             return true;
         }
    }
}





