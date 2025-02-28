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

import com.upupor.framework.CcConstant;
import com.upupor.framework.config.UpuporConfig;
import com.upupor.service.business.aggregation.dao.entity.Radio;
import com.upupor.service.business.aggregation.service.RadioService;
import com.upupor.service.dto.page.common.ListRadioDto;
import com.upupor.service.dto.seo.GoogleSeoDto;
import com.upupor.service.types.RadioStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang Runkang (cruise)
 * @date 2022年03月13日 21:30
 * @email: yangrunkang53@gmail.com
 */
@Component
@RequiredArgsConstructor
public class RadioSiteMap extends AbstractSiteMap<Radio> {

    private final RadioService radioService;
    private final UpuporConfig upuporConfig;

    @Override
    protected Boolean dataCheck() {
        return radioService.total() > 0;
    }

    @Override
    protected List<Radio> getSiteMapData() {
        Integer total = radioService.total();
        int pageNum = total % CcConstant.Page.SIZE == 0 ? total / CcConstant.Page.SIZE : total / CcConstant.Page.SIZE + 1;
        List<Radio> radioAllList = new ArrayList<>();
        for (int i = 0; i < pageNum; i++) {
            ListRadioDto listRadioDto = radioService.list(i + 1, CcConstant.Page.SIZE);
            if (CollectionUtils.isEmpty(listRadioDto.getRadioList())) {
                break;
            }
            radioAllList.addAll(listRadioDto.getRadioList());
        }
        return radioAllList;
    }

    @Override
    protected void renderSiteMap(List<Radio> radioList) {
        radioList.forEach(radio -> {
            if (radio.getStatus().equals(RadioStatus.NORMAL)) {
                GoogleSeoDto googleSeoDto = new GoogleSeoDto();
                // 按照参数顺序依次是 来源、内容Id
                String contentUrl = upuporConfig.getWebsite() + "/r/%s";
                contentUrl = String.format(contentUrl, radio.getRadioId());

                googleSeoDto.setLoc(contentUrl);
                googleSeoDto.setChangeFreq("hourly");
                googleSeoDto.setLastmod(sdf.format(radio.getSysUpdateTime()));
                googleSeoDto.setPriority("0.5");// 默认值
                googleSeoDtoList.add(googleSeoDto);
            }
        });
    }
}
