package run.halo.app.model.enums.converter;

import run.halo.app.model.enums.PostType;

import javax.persistence.Converter;

/**
 * PostType converter.
 *
 * @author ssatwa
 * @date 3/27/19
 */
@Converter(autoApply = true)
@Deprecated
public class PostTypeConverter extends AbstractConverter<PostType, Integer> {

}
