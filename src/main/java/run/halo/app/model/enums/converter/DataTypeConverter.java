package run.halo.app.model.enums.converter;

import run.halo.app.model.enums.DataType;

import javax.persistence.Converter;

/**
 * Data type converter.
 *
 * @author ssatwa
 * @date 4/10/19
 */
@Converter(autoApply = true)
public class DataTypeConverter extends AbstractConverter<DataType, Integer> {

}
