package ru.alexkhrenov.pastebox.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.alexkhrenov.pastebox.api.request.PublicStatus;

@Data
@RequiredArgsConstructor
public class PasteBoxResponse {

    private final String data;
    private final boolean isPublic;
}
