package com.wy.springcloudstream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@DirtiesContext
public class SpringCloudStreamApplicationTests {

	@Autowired
	private Sink sink;

	@Test
	public void contextLoads() {
		System.out.println(this.sink.input());
	}
}
