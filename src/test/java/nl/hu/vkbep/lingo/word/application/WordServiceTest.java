package nl.hu.vkbep.lingo.word.application;

import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.word.data.WordRepository;
import nl.hu.vkbep.lingo.word.domain.Word;
import nl.hu.vkbep.lingo.word.presentation.exception.WordDoesNotExists;
import nl.hu.vkbep.lingo.word.presentation.exception.WordLengthNotCorrect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@State(Scope.Benchmark)
public class WordServiceTest {

    private static final Word word = new Word(1L, "baard");

    private static Stream<Arguments> provideWordLengthAndResult() {
        return Stream.of(
                Arguments.of("Test", 5, false),
                Arguments.of("isTeLang", 5, false),
                Arguments.of("qwerty", 6, true),
                Arguments.of("qwertyu", 7, true),
                Arguments.of("qwertyu", 5, false)
        );
    }

    private static Stream<Arguments> provideWordGuessAndResult() {
        return Stream.of(
                Arguments.of("Tests", "Tests", true),
                Arguments.of("isTeLang", "isTeLang", true),
                Arguments.of("qwerty", "zxcvbv", false),
                Arguments.of("qwertyu", "adsfgg", false),
                Arguments.of("qwertyu", "qwertyu", true)
        );
    }

    private static Stream<Arguments> provideIdWordAndResult() {
        return Stream.of(
                Arguments.of("aagje", true),
                Arguments.of("aaibaar", true),
                Arguments.of("zxcvbv", false),
                Arguments.of("adsfgg", false),
                Arguments.of("qwertyu", false)
        );
    }

    private static Stream<Arguments> provideGametypeLengthAndResult() {
        return Stream.of(
                Arguments.of(GameType.LETTEROF5, 5, true),
                Arguments.of(GameType.LETTEROF5, 6, false),
                Arguments.of(GameType.LETTEROF5, 7, false),

                Arguments.of(GameType.LETTEROF6, 5, false),
                Arguments.of(GameType.LETTEROF6, 6, true),
                Arguments.of(GameType.LETTEROF6, 7, false),

                Arguments.of(GameType.LETTEROF7, 5, false),
                Arguments.of(GameType.LETTEROF7, 6, false),
                Arguments.of(GameType.LETTEROF7, 7, true)
        );
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Test
    @DisplayName("CHECK IF WORD IS SAVED")
    public void save(){
        Word word = new Word(1L, "tests");
        WordRepository wordRepository = Mockito.mock(WordRepository.class);
        WordService wordService = new WordService(wordRepository);

        when(wordRepository.save(word)).thenReturn(word);
        Word result = wordService.save(word);

        assertEquals(word, result);

    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Test
    @DisplayName("GIVES TRUE WHEN WORD DOES EXISTS")
    public void wordExists(){
        Word word = new Word(1L, "tests");

        WordRepository wordRepository = Mockito.mock(WordRepository.class);
        WordService wordService = new WordService(wordRepository);

        when(wordRepository.existsByWord(word.toString())).thenReturn(true);

        Boolean result = wordService.wordExits(word.toString());

        assertTrue(result);
    }

//    @Benchmark
//    @BenchmarkMode(Mode.SingleShotTime)
    @ParameterizedTest
    @MethodSource("provideGametypeLengthAndResult")
    public void getLengthByGameType(GameType gameType, int length, boolean expectedresult){
        WordRepository wordRepository = Mockito.mock(WordRepository.class);
        WordService wordService = new WordService(wordRepository);

        boolean result = wordService.getLengthByGameType(gameType) == length;

        Assertions.assertEquals(result, expectedresult);
    }

    private static Stream<Arguments> provideWordsForFeedback() {
        return Stream.of(
                Arguments.of(word, "bonje", List.of("CORRECT", "ABSENT", "ABSENT", "ABSENT", "ABSENT")),
                Arguments.of(word, "barst", List.of("CORRECT", "CORRECT", "CONTAINS", "ABSENT", "ABSENT")),
                Arguments.of(word, "draad", List.of("ABSENT", "CONTAINS", "CORRECT", "CONTAINS", "CORRECT")),
                Arguments.of(word, "baard", List.of("CORRECT", "CORRECT", "CORRECT", "CORRECT", "CORRECT"))
        );
    }

//    @Benchmark
//    @BenchmarkMode(Mode.SingleShotTime)
    @ParameterizedTest
    @MethodSource("provideWordsForFeedback")
    @DisplayName("GIVES FEEDBACK FOR GUESSES")
    public void checkOfLetters(Word word, String guess, List<String> expectedFeedback) {

        WordRepository wordRepository = Mockito.mock(WordRepository.class);
        WordService wordService = new WordService(wordRepository);

        when(wordRepository.getById(word.getId())).thenReturn(word);

        Map<String, List<String>> result = wordService.checkOfLetters(guess, word.getId());

        Assertions.assertEquals(expectedFeedback, result.get("feedback"));
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Test
    @DisplayName("GIVES FEEDBACK WHEN RANDOM PICKED WORD LENGTH = GAMETYPE LENGTH")
    public void getRandomWord() {
        Word word = new Word(1L, "tests");
        GameType gameType = GameType.LETTEROF5;

        WordRepository wordRepository = Mockito.mock(WordRepository.class);
        WordService wordService = new WordService(wordRepository);

        when(wordRepository.getRandomWord()).thenReturn(word);
        when(wordService.getRandomWord(gameType)).thenReturn(word);

        String result = wordService.getRandomWord(gameType).getWord();

        Assertions.assertEquals(result, word.getWord());
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Test
    @DisplayName("GIVES TRUE WHEN GUESS EQUALS WORD")
    public void attemptChecker() {
        Word guess = new Word(1L, "baard");
        WordRepository wordRepository = Mockito.mock(WordRepository.class);

        when(wordRepository.getById(word.getId())).thenReturn(word);

        WordService wordService = new WordService(wordRepository);

        boolean result = wordService.attemptChecker(word.getId(), guess.getWord());

        assertTrue(result);
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Test
    @DisplayName("RETURNS WORD WHEN FOUND")
    public void getWordById() {
        WordRepository wordRepository = Mockito.mock(WordRepository.class);

        when(wordRepository.getById(word.getId())).thenReturn(word);

        WordService wordService = new WordService(wordRepository);
        Word result = wordService.getWordbyId(word.getId());

        assertEquals(word, result);
    }


    @Benchmark
    @BenchmarkMode(Mode.All)
    @Test
    @DisplayName("RETURNS MAP OF FEEDBACK WHEN LENGTH AND EXISTS IS TRUE")
    public void wordCheckerCorrect() {
        Word guess = new Word(1L, "tests");
        WordRepository wordRepository = Mockito.mock(WordRepository.class);

        when(wordRepository.getById(word.getId())).thenReturn(word);

        WordService wordService = new WordService(wordRepository);
        when(wordService.wordExits(guess.toString())).thenReturn(true);

        Map result = wordService.wordChecker(guess.getWord(), word.getId());

        assertTrue(result.containsKey("feedback"));
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Test
    @DisplayName("RETURNS WORDLENGTHNOTCORRECT WHEN LENGTH IS FALSE")
    public void wordCheckerWrongLength() {
        Word guess = new Word(1L, "123456789");
        WordRepository wordRepository = Mockito.mock(WordRepository.class);

        when(wordRepository.getById(word.getId())).thenReturn(word);

        WordService wordService = new WordService(wordRepository);
        when(wordService.wordExits(guess.toString())).thenReturn(true);

        Map result = wordService.wordChecker(guess.getWord(), word.getId());

        assertTrue(result.containsValue(new WordLengthNotCorrect(guess.toString()).getMessage()));
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Test
    @DisplayName("RETURNS WORDDOESNOTEXISTS WHEN EXISTS IS FALSE")
    public void wordCheckerGuessNotExisting() {
        Word guess = new Word(1L, "12345");
        WordRepository wordRepository = Mockito.mock(WordRepository.class);

        when(wordRepository.getById(word.getId())).thenReturn(word);

        WordService wordService = new WordService(wordRepository);
        when(wordService.wordExits(guess.toString())).thenReturn(false);

        Map result = wordService.wordChecker(guess.getWord(), word.getId());

        assertTrue(result.containsValue(new WordDoesNotExists(guess.toString()).getMessage()));
    }
}
