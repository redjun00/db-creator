package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan
@SpringBootApplication  //@ComponentScan 이 기재되어 있는 폴더 밑으로 컴포턴트들을 찾아 등록한다.
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
