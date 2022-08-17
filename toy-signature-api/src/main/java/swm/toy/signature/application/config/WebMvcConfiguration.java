package swm.toy.signature.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
class WebMvcConfiguration {

    @Bean
    PageableHandlerMethodArgumentResolverCustomizer
            pageableHandlerMethodArgumentResolverCustomizer() {
        return pageableResolver -> {
            pageableResolver.setSizeParameterName("size");
            pageableResolver.setPageParameterName("page");
        };
    }
}
