package ru.alexkhrenov.pastebox.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PasteBoxURLResponse {

    private final String url;
}
