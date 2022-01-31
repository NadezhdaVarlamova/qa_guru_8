package gmail.com.varlamvanadia1996;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ParameterizedWebTest {

    @ValueSource(strings = {"Selenide", "Allure"})
    @ParameterizedTest(name = "Тестирование поиска Github с тестовыми данными: {0}")
    void searchInGithubTitleValueSource(String testData) {
        open("https://github.com/");
        $(".header-search-input").click();
        $(".header-search-input").setValue(testData).pressEnter();
        $("a.v-align-middle").shouldHave(text(testData));
    }

    @CsvSource(value = {
            "Selenide; Concise UI Tests with Java!",
            "Allure; Report is a flexible, lightweight multi-language test reporting tool"},
            delimiter = ';')
    @ParameterizedTest(name = "Тестирование поиска Github с тестовыми данными: {0}, {1}")
    void searchInGithubDescriptionCsvSource(String testData, String resultDescription) {
        open("https://github.com/");
        $(".header-search-input").click();
        $(".header-search-input").setValue(testData).pressEnter();
        $("p.mb-1").shouldHave(text(resultDescription));
    }

    static Stream<Arguments> searchInGithubDescriptionMethodSourceData() {
        return Stream.of(
                Arguments.of("Selenide", "Concise UI Tests with Java!"),
                Arguments.of("Allure", "Report is a flexible, lightweight multi-language test reporting tool")
        );
    }

    @MethodSource("searchInGithubDescriptionMethodSourceData")
    @ParameterizedTest(name = "Тестирование поиска Github с тестовыми данными: {0}, {1}")
    void searchInGithubDescriptionMethodSource(String testData, String resultDescription) {

        open("https://github.com/");
        $(".header-search-input").click();
        $(".header-search-input").setValue(testData).pressEnter();
        $("p.mb-1").shouldHave(text(resultDescription));
    }
}
