package com.rdas.service;

import com.rdas.model.TwitterResponse;

import java.util.List;

public interface TwitterService {
    List<TwitterResponse> searchResponse(String searchString);
}
