package com.in.read.boot.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luyun on 2019/1/17.
 */
public class WXMappingFastJsonHttpMessageConverter extends FastJsonHttpMessageConverter {
    public WXMappingFastJsonHttpMessageConverter(){
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        mediaTypes.add(MediaType.TEXT_HTML);  //加入text/html类型的支持
        setSupportedMediaTypes(mediaTypes);// tag6
    }
}
