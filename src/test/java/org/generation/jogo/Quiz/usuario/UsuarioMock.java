package org.generation.jogo.Quiz.usuario;

import com.github.javafaker.Faker;
import java.util.Locale;

public class UsuarioMock {
    public static Usuario getUsuario() {
        Usuario usuarioFake = new Usuario();
        Faker faker = new Faker(new Locale("pt-BR"));

        usuarioFake.setUsername(faker.name().username());
        usuarioFake.setSenha(faker.internet().password());

        return usuarioFake;
    }
}
