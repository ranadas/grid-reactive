package com.rdas.service;

import com.rdas.model.TwitterResponse;

import java.util.List;
import java.util.Optional;

public interface TwitterService {
    Optional<List<TwitterResponse>> searchResponse(String searchString);
}
