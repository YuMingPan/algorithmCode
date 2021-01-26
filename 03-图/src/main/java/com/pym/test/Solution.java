package com.pym.test;

import java.util.*;

public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 课程，前驱
        HashMap<Integer,Integer> map = new HashMap();
        // 存放输出
        List<Integer> list = new ArrayList();
        // 存放入度为0的节点
        Queue<Integer> queue = new LinkedList();

        // 构建节点和入度的关系
        for (int i = 0; i < prerequisites.length; i++) {
            for (int j = 0; j < prerequisites.length; j++) {
                int postCourse = prerequisites[i][j];
                if (map.get(postCourse) == null){
                    map.put(postCourse,0);
                } else{
                    map.put(postCourse,map.get(postCourse)+1);
                }
            }
        }

        int origiVal = map.size();

        // 入度为0的进队
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            if(integerIntegerEntry.getValue() == 0){
                queue.offer(integerIntegerEntry.getKey());
            }
        }

        if (!queue.isEmpty()){
            Integer course = queue.poll();
            list.add(course);
            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][0] == course){
                    int p = prerequisites[i][1];
                    int count = map.get(p) - 1;
                    if (count == 0){
                        queue.add(p);
                    }else{
                        map.put(p,count);
                    }
                }
            }

        }

        if (origiVal != list.size()){return new int[]{};}

        int arr[] = new int[list.size()];

        for (int i = 0; i <list.size() ; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }
}
