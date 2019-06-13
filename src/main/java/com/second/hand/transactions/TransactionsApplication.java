package com.second.hand.transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/5/27 0027
 * Time:9:22
 */
@SpringBootApplication
@EnableTransactionManagement
public class TransactionsApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionsApplication.class, args);
    }
}
