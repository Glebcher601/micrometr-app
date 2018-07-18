package com.nixsolutions.micrometr.utils;

import com.nixsolutions.micrometr.service.external.alphavintage.enums.Endpoints;

public interface EnumWithValue<E extends Enum<E>, T>
{

  E getByValue(T value);

  T getValue();

  default E iterateThroughValuesForMatch(T value, E[] values)
  {
    for (E enumWithValue : values)
    {
      if (((EnumWithValue) enumWithValue).getValue().equals(value))
      {
        return enumWithValue;
      }
    }
    throw new IllegalStateException(value + " is not present in enum" + values.getClass().getComponentType());
  }
}
