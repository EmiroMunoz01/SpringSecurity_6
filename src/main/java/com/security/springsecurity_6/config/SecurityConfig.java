package com.security.springsecurity_6.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    //in this place I create the configuration, include the filters and more things

    //2 inyectamos el componente que vamos a utilizar
    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.build();
    }


    //1 este componente lo creamos a partir de un objeto que ya existe en spring security, que es Auth
    public AuthenticationManager authenticationManager() throws Exception {

        //3 con esto estamos configurando el componente encargado de la autenticacion
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() throws Exception {
        //4 este es el proveedor que nos permitira conectarnos con una base de datos y traer los usuarios con sus respectivas credenciales, a su vez este proveedor necesita el passwordencoder que es el componente que encripta las claves y las valida y el userDetailService, que practicamente es el componente que hace el llamado a la base de datos, de momento seran null los espacios el password y el userDetailsService. Se ha configurado el provider con sus dos componentes, el passwordEncoder y el userDetailsService

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(null);
        daoAuthenticationProvider.setUserDetailsService(null);
        return daoAuthenticationProvider;
    }


    //6 Desde aquí simularemos que los datos llegan desde la una base de datos, posteriormente se agregara la base de datos, el usuario que estamos creando en este momento está en memoria
    @Bean
    public UserDetailsService userDetailsService() throws Exception {

        List<UserDetails> userDetails = new ArrayList<UserDetails>();

        userDetails.add(User.withUsername("emiro").password("1234").roles("ADMIN").authorities("READ", "CREATE").build());
        userDetails.add(User.withUsername("valentin").password("4321").roles("User").authorities("READ").build());

        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //5 estamos en pruebas, por end solo lo usaremos como manera de ejemplo, porque no nos retornara las claves encriptadas, en production solo se maneja encriptado, en ese caso se maneja con BCrypt.
        return NoOpPasswordEncoder.getInstance();
    }


}
