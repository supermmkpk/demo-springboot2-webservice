package com.demo.springboot.webservice.service.posts;

import com.demo.springboot.webservice.web.dto.PostsListResponseDto;
import com.demo.springboot.webservice.domain.posts.Posts;
import com.demo.springboot.webservice.domain.posts.PostsRepository;
import com.demo.springboot.webservice.web.dto.PostsResponseDto;
import com.demo.springboot.webservice.web.dto.PostsSaveRequestDto;
import com.demo.springboot.webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    public final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
        );

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
        );

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) //조회 기능만: 조회 속도 개선
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .stream()
                .map(PostsListResponseDto::new) //.map(posts -> new PostsListResponseDto(posts)) 와 동일
                .collect(Collectors.toList());
    }
}
