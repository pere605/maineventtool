package pere.maineventtool.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import pere.maineventtool.domain.seasonalevent.mapper.SeasonalEventMapper;
import pere.maineventtool.domain.seasonalevent.model.SeasonalEvent;
import pere.maineventtool.domain.seasonalevent.repository.SeasonalEventRepository;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@WebMvcTest(SeasonalEventController.class)
public class SeasonalEventControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private SeasonalEventRepository repository;

    @MockBean
    private SeasonalEventMapper mapper;

    @Test
    public void testGetSeasonalEventList() throws Exception {
        this.mvc
            .perform(get("/seasonal_event"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void testGetSeasonalEvent() throws Exception {
        UUID id = UUID.randomUUID();
        SeasonalEvent seasonalEvent = new SeasonalEvent(
                id,
                1,
                "Name",
                "Type",
                "SubType",
                Date.from(Instant.now()),
                Date.from(Instant.now())
        );

        given(repository.findById(id)).willReturn(Optional.of(seasonalEvent));
        this.mvc
                .perform(get("/seasonal_event/" + id.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id.toString())))
                .andExpect(jsonPath("$.name", is("Name")))
                .andExpect(jsonPath("$.type", is("Type")))
                .andExpect(jsonPath("$.subType", is("SubType")));
    }

    @Test
    public void testDeleteSeasonalEvent() throws Exception {
        UUID id = UUID.randomUUID();
        SeasonalEvent seasonalEvent = new SeasonalEvent(
                id,
                1,
                "Name",
                "Type",
                "SubType",
                Date.from(Instant.now()),
                Date.from(Instant.now())
        );

        given(repository.findById(id)).willReturn(Optional.of(seasonalEvent));
        this.mvc
                .perform(delete("/seasonal_event/" + id.toString()))
                .andExpect(status().isNoContent());
    }
}
