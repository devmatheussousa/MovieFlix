package com.movieflix.demo.controllers.request;

import lombok.Builder;

@Builder
public record StreamingRequest(String name) {
}
