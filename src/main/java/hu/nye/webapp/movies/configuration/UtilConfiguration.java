package hu.nye.webapp.movies.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Itt definiáltuk, hogy a modelMapper Bean hogy jöjjön létre; és itt modelMapper() kézzel lepéldányosítottuk
 *
 * A MovieServiceImpl.java fájlban kívülről húztunk be egy függőséget, de a külső függőségi osztályokból nem tudok Bean-t készíteni úgy, hogy ráállok az osztályra, és a Class felé beírom az Annotációt,
 * így a Beant így configuration segítségével készítem el, mert ez a másik módja a Bean-ek létrehozásának
 *
 * this.modelMapper = modelMapper;  // mivel ezt kívülről kaptuk, a dependency-ből, így valahol léteznie kellene belőle egy Bean-nek, hogy a Spring le tudja példányosítani ezt az osztályt
 *                                             // ehhez a modelMapper oszályhoz nem tudunk odamenni, és beleírni az Annotációt, mert ezt kívűlről kaptuk, hanem van egy másik lehetőség is, hogy Bean-eket definiáljunk
 *                                             // például java config által -> így ehhez egy configuration packaget kell csinálni
 */

@Configuration                                      // 2. itt definiálom, hogy ez egy configuration osztály azaz Bean-t lehet belőle készíteni; azaz utasítom ezzel a Springet, hogy nézd át, mikor indul az alkalmazás
public class UtilConfiguration {

    // 1. itt csak létre kell hozni egy modelmapper metódust, mely egy új modelmapper-t ad vissza
    // 3. és ide a metódusra rakok egy Bean-t
    @Bean                                       // ez jelzi, hogy amit ez a metódus visszaad, abbol csináljon a Spring egy Bean-t
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
