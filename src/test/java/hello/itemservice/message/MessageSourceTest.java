package hello.itemservice.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {
    @Autowired
    private MessageSource messageSource;

    @Test
    void helloMessageTest() {
        //given

        //when
        String message = messageSource.getMessage("hello", null, null);
        //then
        assertThat(message).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageTest() {
        //given

        //then
        assertThatThrownBy(() -> {
            //when
            messageSource.getMessage("no_code", null, null);
        }).isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundReplaceDefaultMessageTest() {
        //given

        //when
        String message = messageSource.getMessage("no_code", null, "기본 메시지", null);
        //then
        assertThat(message).isEqualTo("기본 메시지");
    }

    @Test
    void argumentMessageTest() {
        //given

        //when
        String message = messageSource.getMessage("hello.name", new Object[]{"Spring"}, null);
        //then
        assertThat(message).isEqualTo("안녕 Spring");
    }

    @Test
    void defaultLangTest() {
        assertThat(messageSource.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(messageSource.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
        assertThat(messageSource.getMessage("hello", null, Locale.getDefault())).isEqualTo("안녕");
    }

    @Test
    void enLangTest() {
        assertThat(messageSource.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }
}
