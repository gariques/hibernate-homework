package com.iddev.filters;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ClientFilter {

    String firstName;
    String lastName;
}
