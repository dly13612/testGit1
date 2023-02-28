package com.example.demo.controller;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DlTest {
	public static void main(String[] args) throws UnknownHostException  {
		InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8080);
		System.out.println(inetSocketAddress.getPort());
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 
																	Runtime.getRuntime().availableProcessors(), 
																	2, 
																	TimeUnit.SECONDS, 
																	new ArrayBlockingQueue(20),
																	Executors.defaultThreadFactory(),
																	new ThreadPoolExecutor.AbortPolicy());
	}
}


