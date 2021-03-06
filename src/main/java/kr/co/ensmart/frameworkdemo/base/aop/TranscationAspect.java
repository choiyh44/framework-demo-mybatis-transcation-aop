/**
 * 
 */
package kr.co.ensmart.frameworkdemo.base.aop;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * @author choiyh44
 * @version 1.0
 * @since 2021. 11. 25.
 *
 */
//@Aspect
//@Configuration
public class TranscationAspect {

    @Resource(name="orderRwdbTxManager")
    private TransactionManager transactionManager;

    private static final String EXPRESSION 
            = "execution(* kr.co.ensmart.frameworkdemo.app.service.*ServiceImpl.register*(..)) "
            + " || execution(* kr.co.ensmart.frameworkdemo.app..service..*ServiceImpl.modify*(..))"
            + " || execution(* kr.co.ensmart.frameworkdemo.app..service..*ServiceImpl.delete*(..))"
            ;

    @Bean
    public TransactionInterceptor transactionAdvice() {

        List<RollbackRuleAttribute> rollbackRules = Collections.singletonList(new RollbackRuleAttribute(Exception.class));

        RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
        transactionAttribute.setRollbackRules(rollbackRules);
        transactionAttribute.setName("*");

        MatchAlwaysTransactionAttributeSource attributeSource = new MatchAlwaysTransactionAttributeSource();
        attributeSource.setTransactionAttribute(transactionAttribute);

        return new TransactionInterceptor(transactionManager, attributeSource);
    }

    @Bean
    public Advisor transactionAdvisor() {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(EXPRESSION);

        return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
    }

}
