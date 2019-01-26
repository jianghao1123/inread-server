package com.in.read.article.service;

import com.in.read.article.entity.NoteInteraction;
import com.in.read.framework.base.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Generator
 * @since 2019-01-16
 */
public interface NoteInteractionService extends BaseService<NoteInteraction> {
    void incShare(int nodeId);
}
