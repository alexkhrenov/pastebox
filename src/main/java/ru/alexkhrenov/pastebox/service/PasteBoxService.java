package ru.alexkhrenov.pastebox.service;

import ru.alexkhrenov.pastebox.api.request.PasteBoxRequest;
import ru.alexkhrenov.pastebox.api.response.PasteBoxResponse;
import ru.alexkhrenov.pastebox.api.response.PasteBoxURLResponse;

import java.util.List;

public interface PasteBoxService {

    PasteBoxResponse getByHash(String hash);
    List<PasteBoxResponse> getFirstPublicPasteBoxes();
    PasteBoxURLResponse create(PasteBoxRequest request);
}
