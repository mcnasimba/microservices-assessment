package com.mcnasimba.msvc_accounts.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessagingDTO<T> {

    UUID uuid = UUID.randomUUID();

    @JsonProperty("request-id")
    String requestId;

    Date timestamp = new Date();

    String source;

    @JsonProperty("detail-type")
    String detailType;

    T payload;

}
