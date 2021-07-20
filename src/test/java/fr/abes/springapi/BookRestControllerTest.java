package fr.abes.springapi;

import fr.abes.springapi.data.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = SpringApiApplication.class)
@AutoConfigureMockMvc
class BookRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookDAO bookDAO;

}
