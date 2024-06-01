package ru.alexkhrenov.pastebox;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.alexkhrenov.pastebox.exception.NotFoundEntityException;
import ru.alexkhrenov.pastebox.service.PasteBoxService;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PasteBoxServiceTest {

    @Autowired
    private PasteBoxService pasteBoxService;

    @Test
    public void notExistHash() {
        assertThrows(NotFoundEntityException.class, () -> pasteBoxService.getByHash("yasgbyudsubg"));
    }
}
