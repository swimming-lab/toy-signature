package swm.toy.signature.infrastructure.converter;

import java.util.EnumSet;
import java.util.NoSuchElementException;
import javax.persistence.AttributeConverter;

public class CodeValueConverter<E extends Enum<E> & CodeValue>
        implements AttributeConverter<E, String> {

    private Class<E> clz;

    public CodeValueConverter(Class<E> enumClass) {
        this.clz = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(E attribute) {
        return attribute.getCode();
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(clz).stream()
                .filter(e -> e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }
}
