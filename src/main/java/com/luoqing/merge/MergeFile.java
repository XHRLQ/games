package com.luoqing.merge;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * @Author: SMA
 * @Date: 2017-08-27 01:36
 * @Explain:
 */
public class MergeFile {

    public void merge(String input,String output){
        SparkConf conf = new SparkConf()
                .setMaster("")
                .setAppName("MergeFile");

        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD hdfsRDD=sc.textFile(input);
        hdfsRDD.repartition(1).saveAsTextFile(output);


    }
}
