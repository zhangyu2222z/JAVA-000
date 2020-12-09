package com.zy;

import com.zy.config.TransactionConfiguration;
import com.zy.service.XAOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//import javax.annotation.PostConstruct;

@SpringBootApplication
@Import(TransactionConfiguration.class)
public class HomeworkApplication {

//	@Autowired
//	private XAOrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
	}

//	@PostConstruct
//	public void executeOrderService() {
//		orderService.init();
//		orderService.selectAll();
//		orderService.cleanup();
//	}

}
