package utils;

import dto.UserDto;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();

    public static UserDto positiveUser() {
        UserDto user = UserDto.builder()
                .username(faker.internet().emailAddress())
                .password("Qwerty123!")
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
        return user;
    }
}
