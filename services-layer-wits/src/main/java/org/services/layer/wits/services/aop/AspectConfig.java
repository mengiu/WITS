package org.services.layer.wits.services.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ImportResource;

@Aspect
public class AspectConfig
{
    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void serviceAnnotatedClass() {}
}