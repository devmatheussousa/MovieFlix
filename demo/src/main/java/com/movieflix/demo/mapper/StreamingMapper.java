package com.movieflix.demo.mapper;

import com.movieflix.demo.controllers.request.StreamingRequest;
import com.movieflix.demo.controllers.response.StreamingResponse;
import com.movieflix.demo.entities.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest streamingRequest) {
        return Streaming
                .builder()
                .name(streamingRequest.name())
                .build();
    }

    public static StreamingResponse toCategoryResponse(Streaming streaming) {
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
