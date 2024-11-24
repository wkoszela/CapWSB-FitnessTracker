@SpringBootTest
@AutoConfigureMockMv
public class TrainingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllTrainings() throws Exception {
        mockMvc.perform(get("/trainings"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()").isGreaterThan(0));
    }
}
