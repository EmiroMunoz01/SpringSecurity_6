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
import org.springframework.security.web.SecurityFilterChain;

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
        //4 este es el proveedor que nos permitira conectarnos con una base de datos y traer los usuarios con sus respectivas credenciales, a su vez este proveedor necesita el passwordencoder que es el componente que encripta las claves y las valida y el userDetailService, que practicamente es el componente que hace el llamado a la base de datos, de momento seran null los espacios el password y el userDetailsService
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(null);
        daoAuthenticationProvider.setUserDetailsService(null);
        return daoAuthenticationProvider;

    }

}
