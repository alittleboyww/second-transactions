package com.second.hand.transactions.config;

import com.second.hand.transactions.commands.validate.BeanValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/5/28 0028
 * Time:8:05
 */

@Configuration
public class ValidatorConfig {
    //生产一个Validator对象
    @Bean("validator")
    public Validator createValidator(){
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        return validatorFactoryBean;
    }

    @Bean
    @Qualifier("validator")
    public BeanValidator createBeanValidator(Validator validator){
        BeanValidator beanValidator = new BeanValidator();
        beanValidator.setValidator(validator);
        return beanValidator;
    }
}
