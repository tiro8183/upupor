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

package com.upupor.web.aop.view;

import com.upupor.framework.CcConstant;
import com.upupor.service.utils.RedisUtil;
import com.upupor.service.utils.ServletUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import static com.upupor.framework.CcConstant.CONTENT_IS_DONE;

/**
 * @author cruise
 * @createTime 2022-01-19 18:01
 */
@Service
@RequiredArgsConstructor
@Order(7)
public class CheckContentIsDone implements PrepareData{
    @Override
    public void prepare(ViewData viewData) {
        ModelAndView modelAndView = viewData.getModelAndView();
        try {
            String cacheContentKey = CcConstant.CvCache.CONTENT_CACHE_KEY + ServletUtils.getUserId();
            String content = RedisUtil.get(cacheContentKey);
            if (!StringUtils.isEmpty(content)) {
                modelAndView.addObject(CONTENT_IS_DONE, Boolean.TRUE);
            }
        } catch (Exception e) {
            modelAndView.addObject(CONTENT_IS_DONE, Boolean.FALSE);
        }
    }
}
