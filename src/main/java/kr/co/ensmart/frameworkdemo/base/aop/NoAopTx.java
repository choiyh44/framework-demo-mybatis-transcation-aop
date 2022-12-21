/**
 * 
 */
package kr.co.ensmart.frameworkdemo.base.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author choiyh44
 * @version 1.0
 * @since 2022. 12. 19.
 *
 */
@Target({ ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoAopTx {
}
