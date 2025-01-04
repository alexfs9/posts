package com.app.posts.service.interfaces;

import com.app.posts.presentation.dto.CommentDTO;
import com.app.posts.presentation.dto.request.comment.MakeCommentRequest;

public interface ICommentService {

    CommentDTO save(MakeCommentRequest makeCommentRequest);
}
