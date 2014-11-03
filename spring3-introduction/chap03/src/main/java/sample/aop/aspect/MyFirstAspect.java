package sample.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import sample.di.business.entity.Product;

@Aspect
@Component
public class MyFirstAspect {

    @Before("execution(* findProduct())")
    public void before() {
        System.out.println("Hello Before! *** メソッドが呼ばれる前に出てくるよ！");
    }

    @After("execution(* findProduct())")
    public void after() {
        System.out.println("Hello After! *** メソッドを呼んだ後に出てくるよ！");
    }

    @AfterReturning(value = "execution(* findProduct())", returning = "product")
    public void afterReturning(Product product) {
        System.out.println("Hello AfterReturning! *** メソッドを呼んだ後に出てくるよ！");
    }

    @Around("execution(* findProduct())")
    public Product around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Hello Around! before *** メソッドを呼ぶ前に出てくるよ！");
        Product p = (Product)pjp.proceed();
        System.out.println("Hello Around! after *** メソッドを呼んだ後に出てくるよ！");
        return p;
    }

    @AfterThrowing(value = "execution(* findProduct())", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        System.out.println("Hello Throwing! *** 例外になったら出てくるよ！");
    }
}
