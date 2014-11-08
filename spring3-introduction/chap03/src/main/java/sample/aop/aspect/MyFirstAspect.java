package sample.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import sample.di.business.entity.Product;

//@Aspect
//@Component
public class MyFirstAspect {

//    @Before("execution(* findProduct())")
    public void before(JoinPoint jp) {
        System.out.println("Hello Before! *** メソッドが呼ばれる前に出てくるよ！");
        Signature sig = jp.getSignature();
        System.out.println("-----> メソッド名を取得するよ：" + sig.getName());
//        Object[] objs = jp.getArgs();
//        System.out.println("-----> 引数の値を取得するよ：" + objs[0]);
    }

//    @After("execution(* findProduct())")
    public void after() {
        System.out.println("Hello After! *** メソッドを呼んだ後に出てくるよ！");
    }

//    @AfterReturning(value = "execution(* findProduct())", returning = "product")
    public void afterReturning(Product product) {
        System.out.println("Hello AfterReturning! *** メソッドを呼んだ後に出てくるよ！");
        System.out.println("-----> 戻り値を取得するよ：" + product.getPrice());
    }

//    @Around("execution(* findProduct())")
    public Product around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Hello Around! before *** メソッドを呼ぶ前に出てくるよ！");
        Signature sig = pjp.getSignature();
        System.out.println("-----> aop:around メソッド名を取得するよ：" + sig.getName());
        Product p = (Product)pjp.proceed();
        System.out.println("Hello Around! after *** メソッドを呼んだ後に出てくるよ！");
        return p;
    }

//    @AfterThrowing(value = "execution(* findProduct())", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        System.out.println("Hello Throwing! *** 例外になったら出てくるよ！");
    }
}
