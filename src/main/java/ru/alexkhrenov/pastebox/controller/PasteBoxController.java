package ru.alexkhrenov.pastebox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.alexkhrenov.pastebox.api.request.PasteBoxRequest;
import ru.alexkhrenov.pastebox.api.response.PasteBoxResponse;
import ru.alexkhrenov.pastebox.api.response.PasteBoxURLResponse;
import ru.alexkhrenov.pastebox.service.PasteBoxService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PasteBoxController {

    private final PasteBoxService pasteBoxService;

    @GetMapping("/")
    public List<PasteBoxResponse> getPublicPasteList() {
        return pasteBoxService.getFirstPublicPasteBoxes();
    }

    @GetMapping("/{hash}")
    public PasteBoxResponse getByHash(@PathVariable String hash) {
        return pasteBoxService.getByHash(hash);
    }

    @PostMapping("/")
    public PasteBoxURLResponse add(@RequestBody PasteBoxRequest request) {
        return pasteBoxService.create(request);
    }
}
