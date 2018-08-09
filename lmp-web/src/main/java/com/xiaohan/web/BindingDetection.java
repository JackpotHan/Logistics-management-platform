package com.xiaohan.web;

import java.lang.annotation.*;

/**
 * Reason: 参数绑定检测注解
 *
 * @author Chen Lingang
 * @version $Id: BindingDetection, v 0.1 16/8/8 上午11:20
 */
//这个注解是方法的注解
@Target(ElementType.METHOD)
//定义的这个注解是注解会在class字节码文件中存在，在运行时可以通过反射获取到。
@Retention(RetentionPolicy.RUNTIME)
//说明该注解将被包含在javadoc中
@Documented
//子类可以继承父类中的该注解
@Inherited
public @interface BindingDetection {

    /**
     * 描述
     */
    String name() default "";

    /**
     * 绑定验证参数类型
     */
    Class<?> javaType() default void.class;

}
