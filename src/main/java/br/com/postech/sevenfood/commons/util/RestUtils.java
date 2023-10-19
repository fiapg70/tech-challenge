package br.com.postech.sevenfood.commons.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RestUtils {

    public static URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }
}