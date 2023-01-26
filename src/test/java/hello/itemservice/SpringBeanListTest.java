package hello.itemservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SpringBeanListTest {
    @Autowired
    private ApplicationContext context;
    @Test
    public void messageSourceTest() {
        MessageSource messageSource = context.getBean("messageSource", MessageSource.class);
        assertThat(messageSource).isNotNull();
        assertThat(messageSource).isInstanceOf(ResourceBundleMessageSource.class);//messages.properties 등록되기 전에는 DelegatingMessageSource.class 로 MessageSource bean 등록됨.
    }
}
