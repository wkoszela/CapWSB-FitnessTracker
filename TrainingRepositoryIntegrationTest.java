@DataJpaTests
public class TrainingRepositoryIntegrationTest {

    @Autowired
    private TrainingRepository trainingRepository;

    @Test
    public void shouldFindTrainingByActivity() {
        Training training = new Training("Running", LocalDate.now(), 5.0, "user1");
        trainingRepository.save(training);

        List<Training> found = trainingRepository.findByActivity("Running");
        assertEquals(1, found.size());
    }
}
