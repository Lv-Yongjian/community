package com.hciot.community.mapper;

import com.hciot.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}