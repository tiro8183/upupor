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

package com.upupor.web.page.abstracts;

import joptsimple.internal.Strings;
import org.springframework.web.servlet.ModelAndView;

/**
 * 抽象视图
 * @author Yang Runkang (cruise)
 * @date 2022年01月28日 11:06
 * @email: yangrunkang53@gmail.com
 */
public abstract class AbstractView {

    protected ModelAndView modelAndView;
    protected Integer pageNum;
    protected Integer pageSize;

    /**
     * 视图名
     * @return
     */
    public abstract String viewName();

    /**
     * 前缀
     * @return
     */
    public String prefix(){
        return Strings.EMPTY;
    }

    /**
     * 适配ServletPath到View视图
     * @param pageUrl
     * @return
     * @note: pageUrl可能会根据运营需求来变更,但是视图名是不长变的,所以需要这个适配
     */
    public String adapterUrlToViewName(String pageUrl){
        // 默认 pageUrl 和视图命名是一致的,即不用适配
        return pageUrl;
    }

    /**
     * SEO信息
     */
    protected abstract void seoInfo();

    /**
     * 获取数据
     */
    protected void fetchData(){

    }

    /**
     * 做业务的方法
     * @return
     */
    public ModelAndView doBusiness(Integer pageNum, Integer pageSize) {
        // 数据初始化
        this.pageNum = pageNum;
        this.pageSize = pageSize;

        modelAndView = new ModelAndView();
        // 设置viewName
        modelAndView.setViewName(viewName());

        //获取信息
        fetchData();

        // 设置seo信息
        seoInfo();

        return modelAndView;
    }



}