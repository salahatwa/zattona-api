package run.halo.app.cache.lock;

import java.lang.annotation.*;

/**
 * Cache parameter annotation.
 *
 * @author ssatwa
 * @date 3/28/19
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {

}
