package gecko;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class Settings {
   @Value("${volume}")
   private int volume;
   @Value("${prefix}")
   private String prefix;
}
