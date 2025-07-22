package guru.qa.owner.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@DisplayName("Параметризированные тесты на проверку поиска Wildberries")
public class WildTests extends TestBase{

    @ValueSource(strings = {
     "Перфоратор",
     "Светильник",
     "Видеокарта"
    })
    @ParameterizedTest(name = "Поиск на запрос {0} должен выдавать 10 результатов")
    @Tag("SMOKE")
    void searchShouldReturn10ResultsTest(String searchQuery){
    $("#searchInput").setValue(searchQuery).shouldBe(visible).pressEnter();
    $$(".product-card__wrapper").shouldBe(sizeGreaterThan(10));
    }

    @CsvSource(value = {
            "Корзина, Перейти на главную"
    })
    @Tag("SMOKE")
    @ParameterizedTest(name = "Появление кнопки при нажатии на корзину")
    void buttunForClickOnBasketTest(String chapter, String buttonText){
        $$(".navbar-pc__item").findBy(text(chapter)).click();
        $(".basket-empty__btn").shouldHave(text(buttonText));
    }

    static Stream<Arguments> titlsOnClickBattonAddress() {
        return Stream.of(
                Arguments.of(" Адреса ",List.of(
                        "Частые вопросы",
                        "Доставка",
                        "Как вернуть товар и деньги",
                        "Для бизнеса"))
        );
    }
    @MethodSource
    @ParameterizedTest(name = "Заголовки при нажитии на кнопку \"Адреса")
    void titlsOnClickBattonAddress(String chapter, List<String> expectedLinks){
        $$(".navbar-pc__item").findBy(text(chapter)).click();
        $$(".service-menu__list li").filter(visible).shouldHave(texts(expectedLinks));
    }

    @Tag("SMOKE")
    @Test
    @DisplayName("Появление всплывающего меню при клике на поиск по фото")
    void popUpPhotoSearch() {
        $(".search-catalog__btn-wrap").hover().click();
        $("#uploadImageForSearchByImagePopUpContainer").shouldBe(visible);
    }

}
