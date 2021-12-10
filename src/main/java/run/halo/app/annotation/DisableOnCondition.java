package run.halo.app.annotation;

import org.springframework.core.annotation.AliasFor;
import run.halo.app.model.enums.Mode;

import java.lang.annotation.*;

/**
 * This annotation can restrict access under certain conditions api
 *
 * @author ssatwa
 * @date 2020-02-14 13:48
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DisableOnCondition {
    @AliasFor("mode")
    Mode value() default Mode.DEMO;

    @AliasFor("value")
    Mode mode() default Mode.DEMO;
}
