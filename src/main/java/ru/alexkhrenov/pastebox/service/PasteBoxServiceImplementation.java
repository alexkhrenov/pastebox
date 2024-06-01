package ru.alexkhrenov.pastebox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import ru.alexkhrenov.pastebox.api.request.PasteBoxRequest;
import ru.alexkhrenov.pastebox.api.request.PublicStatus;
import ru.alexkhrenov.pastebox.api.response.PasteBoxResponse;
import ru.alexkhrenov.pastebox.api.response.PasteBoxURLResponse;
import ru.alexkhrenov.pastebox.repository.PasteBoxEntity;
import ru.alexkhrenov.pastebox.repository.PasteBoxRepository;
import ru.alexkhrenov.pastebox.repository.PasteBoxRepositoryMap;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PasteBoxServiceImplementation implements PasteBoxService {

    private final PasteBoxRepositoryMap pasteBoxRepositoryMap;
    private String host = "http://pastebox.project.ru";
    private int publicListSize = 10;
    private final PasteBoxRepository repository;
    private int idGenerator;

    public PasteBoxServiceImplementation(PasteBoxRepositoryMap pasteBoxRepositoryMap, PasteBoxRepository repository) {
        this.pasteBoxRepositoryMap = pasteBoxRepositoryMap;
        this.repository = repository;
    }

    @Override
    public PasteBoxResponse getByHash(String hash) {
        PasteBoxEntity pasteBoxEntity = repository.getByHash(hash);
        return new PasteBoxResponse(
                pasteBoxEntity.getData(),
                pasteBoxEntity.isPublic()
        );
    }

    @Override
    public List<PasteBoxResponse> getFirstPublicPasteBoxes() {
        List<PasteBoxEntity> list = repository.getListOfPublicAndAlive(publicListSize);
        return list.stream()
                .map(pasteBoxEntity -> new PasteBoxResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public PasteBoxURLResponse create(PasteBoxRequest request) {
        int hash = generateID();
        PasteBoxEntity pasteBoxEntity = new PasteBoxEntity();
        pasteBoxEntity.setData(request.getData());
        pasteBoxEntity.setId(hash);
        pasteBoxEntity.setHash(Integer.toHexString(hash));
        pasteBoxEntity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
        pasteBoxEntity.setLifeTime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));

        repository.add(pasteBoxEntity);
        return new PasteBoxURLResponse(host + "/" + pasteBoxEntity.getHash());
    }

    private int generateID() {
        return idGenerator++;
    }
}
