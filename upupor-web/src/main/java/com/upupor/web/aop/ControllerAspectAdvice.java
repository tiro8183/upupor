/*
 * MIT License
 *
 * Copyright (c) 2021-2022 yangrunkang
 *
 * Author: yangrunkang
 * Email: yangrunkang53@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.upupor.web.aop;


import com.upupor.service.common.BusinessException;
import com.upupor.service.common.CcResponse;
import com.upupor.service.common.ErrorCode;
import com.upupor.service.listener.event.BuriedPointDataEvent;
import com.upupor.service.types.PointType;
import com.upupor.service.utils.ServletUtils;
import com.upupor.web.aop.checker.controller.ControllerAspectChecker;
import com.upupor.web.aop.checker.controller.dto.ControllerCheckerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: YangRunkang(cruise)
 * @created: 2019/12/23 02:29
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ControllerAspectAdvice {
    private final ApplicationEventPublisher publisher;
    private final List<ControllerAspectChecker> controllerAspectCheckerList;

    /**
     * 以 controller 包下定义的所有请求为切入点
     */
    @Pointcut("execution(public * com.upupor.web.controller..*.*(..))")
    public void controllerLog() {
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("controllerLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        CcResponse ccResponse = new CcResponse();
        HttpServletRequest request = ServletUtils.getRequest();
        long startTime = System.currentTimeMillis();

        // 页面请求埋点
        BuriedPointDataEvent pointEvent = BuriedPointDataEvent.builder().request(request).pointType(PointType.DATA_REQUEST).build();
        publisher.publishEvent(pointEvent);

        try {
            for (ControllerAspectChecker checker : controllerAspectCheckerList) {
                checker.check(new ControllerCheckerDto(request, proceedingJoinPoint));
            }
            ccResponse.setData(proceedingJoinPoint.proceed());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            if (throwable instanceof BusinessException) {
                BusinessException businessException = (BusinessException) throwable;
                ccResponse.setCode(businessException.getCode());
                ccResponse.setData(businessException.getMessage());
            } else {
                ccResponse.setCode(ErrorCode.SYSTEM_ERROR.getCode());
                ccResponse.setData(throwable.getMessage());
            }
        }
        // 执行业务后
        String format = String.format("URL:%s\nconsume time:%s ms", request.getRequestURL().toString(), System.currentTimeMillis() - startTime);
        // 日志打印
        log.info(format);

        return ccResponse;
    }


}
