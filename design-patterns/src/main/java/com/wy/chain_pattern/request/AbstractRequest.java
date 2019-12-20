package com.wy.chain_pattern.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 抽象请求类
 * @author weiyin
 * @date 2019-03-20 10:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AbstractRequest {
    //消息级别
    private int level;
    //请求信息
    private Object requestData;
}
