package www.ru.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties("voron")
public class VoronProperties {
    List<String> address;
    boolean on;
}
