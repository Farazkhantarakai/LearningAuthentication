package learnJwt.app.Config;


import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectConfig {


    @Bean
public Cloudinary getCloudinary() {


    Map config=new HashMap();

    config.put("cloud_name","dxkyl6ym0");
    config.put("api_key","172149751376219");
    config.put("api_secret","A9-9vPgrTQJtHAdOtz-NdpqeaJI");
    config.put("secure",true);

    return new Cloudinary(config);
}

}
