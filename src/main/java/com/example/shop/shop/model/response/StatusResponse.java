package com.example.shop.shop.model.response;

import com.example.shop.shop.type.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StatusResponse {
    private StatusCode statusCode;
}
