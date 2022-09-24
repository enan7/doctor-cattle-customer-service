package com.doctor.cattle.customerservice.entity.access.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.doctor.cattle.customerservice.entity.access.Access;

@Converter(autoApply=true)
public class AccessConverter implements AttributeConverter<Access, Long>{

	@Override
	public Long convertToDatabaseColumn(Access access) {
		if (null == access) {
			return null;
		}
		return access.getId();
	}

	@Override
	public Access convertToEntityAttribute(Long id) {
		if (null == id) {
			return null;
		}
		 return Stream.of(Access.values())
		          .filter(r -> r.getId() ==  id)
		          .findFirst()
		          .orElseThrow(IllegalArgumentException::new);
	}

}
