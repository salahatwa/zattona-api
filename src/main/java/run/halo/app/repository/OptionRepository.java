package run.halo.app.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import run.halo.app.model.entity.Option;
import run.halo.app.repository.base.BaseRepository;

import java.util.Optional;

/**
 * Option repository.
 *
 * @author ssatwa
 * @author ssatwa
 * @date 2019-03-20
 */
public interface OptionRepository extends BaseRepository<Option, Integer>, JpaSpecificationExecutor<Option> {

    /**
     * Query option by key
     *
     * @param key key
     * @return Option
     */
    Optional<Option> findByKey(String key);

    /**
     * Delete option by key
     *
     * @param key key
     */
    void deleteByKey(String key);
}
