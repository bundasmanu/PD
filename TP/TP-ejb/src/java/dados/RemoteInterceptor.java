/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author gustavo
 */
public class RemoteInterceptor {
    
    @PostConstruct
    public void init(InvocationContext context)  {
        System.out.println("SampleInterceptor > PostConstruct > init");
    }
 
    @AroundConstruct
    public Object construct(InvocationContext context) throws Exception {
        System.out.println("SampleInterceptor > construct");
        return context.proceed();
    }

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        System.out.println("SampleInterceptor > invoke");
        System.out.println("\nEntrei no Select List\t"+context.getMethod().getName());
        return context.proceed();
    }
 
    @PreDestroy
    public void destroy(InvocationContext context) throws Exception {
        System.out.println("SampleInterceptor > PreDestroy > destroy");
        context.proceed();
        
    }
    
}
