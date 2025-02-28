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

package com.upupor.service.types;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 内容是否初始化
 *
 * @author cruise
 * @createTime 2021-12-31 18:03
 */

@Getter
public enum ContentIsInitialStatus {
    /**
     * 一次都未发布
     */
    NOT_FIRST_PUBLISH_EVER(0, "一次都未发布"),

    /**
     * 已发布
     */
    FIRST_PUBLISHED(1, "第一次发布"),

    ;
    @EnumValue
    private final Integer status;
    private final String name;

    ContentIsInitialStatus(Integer status, String name) {
        this.status = status;
        this.name = name;
    }
}