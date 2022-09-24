package com.doctor.cattle.customerservice.entity.role.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.doctor.cattle.customerservice.entity.role.Role;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class RoleConvertor implements AttributeConverter<Role,Long> {

	@Override
	public Long convertToDatabaseColumn(Role role) {
		// TODO Auto-generated method stub
	       if (role == null) {
	            return null;
	        }
	        return role.getId();

	}

	@Override
	public Role convertToEntityAttribute(Long id) {
	       if (id == null) {
	            return null;
	        }
	        return Stream.of(Role.values())
	          .filter(r -> r.getId().equals(id))
	          .findFirst()
	          .orElseThrow(IllegalArgumentException::new);
	    }

	}
	
