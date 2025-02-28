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

package com.upupor.service.scheduled.sitemap;

import com.alibaba.fastjson.JSON;
import com.upupor.framework.config.UpuporConfig;
import com.upupor.service.business.aggregation.dao.entity.Content;
import com.upupor.service.business.aggregation.dao.entity.Tag;
import com.upupor.service.dto.page.common.CountTagDto;
import com.upupor.service.dto.seo.GoogleSeoDto;
import com.upupor.service.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

import static com.upupor.framework.CcConstant.CvCache.TAG_COUNT;

/**
 * @author Yang Runkang (cruise)
 * @date 2022年03月13日 21:30
 * @email: yangrunkang53@gmail.com
 */
@Component
@RequiredArgsConstructor
public class TagSiteMap extends AbstractSiteMap<CountTagDto> {

    private final UpuporConfig upuporConfig;

    @Override
    protected Boolean dataCheck() {
        return !StringUtils.isEmpty(RedisUtil.get(TAG_COUNT));
    }

    @Override
    protected List<CountTagDto> getSiteMapData() {
        String s = RedisUtil.get(TAG_COUNT);
        return JSON.parseArray(s, CountTagDto.class);
    }

    @Override
    protected void renderSiteMap(List<CountTagDto> tagList) {
        for (CountTagDto countTagDto : tagList) {
            GoogleSeoDto googleSeoDto = new GoogleSeoDto();
            googleSeoDto.setLoc(upuporConfig.getWebsite() + "/tag/" + countTagDto.getTagName());
            googleSeoDto.setChangeFreq("hourly");
            googleSeoDto.setLastmod(sdf.format(new Date()));
            googleSeoDto.setPriority("0.5");// 默认值
            googleSeoDtoList.add(googleSeoDto);
        }
    }
}