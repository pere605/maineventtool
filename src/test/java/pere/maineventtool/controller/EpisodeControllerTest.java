package pere.maineventtool.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pere.maineventtool.domain.seasonalevent.mapper.EpisodeMapper;
import pere.maineventtool.domain.seasonalevent.model.Episode;
import pere.maineventtool.domain.seasonalevent.repository.EpisodeRepository;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EpisodeController.class)
public class EpisodeControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EpisodeRepository repository;

    @MockBean
    private EpisodeMapper mapper;

    @Test
    public void testGetEpisodeList() throws Exception{
        UUID id = UUID.randomUUID();
        List<Episode> episodes = List.of();
        given(repository.findAllBySeasonalEventId(id)).willReturn(episodes);

        this.mvc
                .perform(get("/episode/" + id.toString()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
