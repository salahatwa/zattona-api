package run.halo.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import run.halo.app.exception.NotFoundException;
import run.halo.app.model.entity.PostMeta;
import run.halo.app.repository.PostRepository;
import run.halo.app.repository.base.BaseMetaRepository;
import run.halo.app.service.PostMetaService;

/**
 * Post meta service implementation class.
 *
 * @author ssatwa
 * @date 2019-08-04
 */
@Slf4j
@Service
public class PostMetaServiceImpl extends BaseMetaServiceImpl<PostMeta> implements PostMetaService {

    private final PostRepository postRepository;

    public PostMetaServiceImpl(BaseMetaRepository<PostMeta> baseMetaRepository, PostRepository postRepository) {
        super(baseMetaRepository);
        this.postRepository = postRepository;
    }

    @Override
    public void validateTarget(@NotNull Integer postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Can't find information about this article").setErrorData(postId));
    }
}
