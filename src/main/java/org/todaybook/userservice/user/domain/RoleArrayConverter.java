package org.todaybook.userservice.user.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter(autoApply = false)
public class RoleArrayConverter implements AttributeConverter<List<Role>, String> {

  @Override
  public String convertToDatabaseColumn(List<Role> roles) {
    if (roles == null || roles.isEmpty()) {
      return "{}";
    }

    String joined = roles.stream().map(Enum::name).collect(Collectors.joining("\",\""));

    return "{\"" + joined + "\"}";
  }

  @Override
  public List<Role> convertToEntityAttribute(String s) {
    if (s == null || s.equals("{}")) {
      return List.of();
    }

    return Arrays.stream(s.replace("{", "").replace("}", "").replace("\"", "").split(","))
        .map(Role::fromString)
        .collect(Collectors.toList());
  }
}
