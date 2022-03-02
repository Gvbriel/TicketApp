package com.gabrielpolak.ticket.Unit;

import com.gabrielpolak.ticket.Validators.PatternValidator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PatternValidationTest {

    @Test
    void ValidateNameShouldReturnTrue(){
        assertThat(PatternValidator.validateName("Gabriel")).isTrue();
    }

    @Test
    void ValidateNameShouldReturnFalse(){
        assertThat(PatternValidator.validateName("Ga")).isFalse();
    }

    @Test
    void ValidateNameShouldReturnFalse2(){
        assertThat(PatternValidator.validateName("Ga-Da")).isFalse();
    }

    @Test
    void ValidateSurnameShouldReturnTrue(){
        assertThat(PatternValidator.validateSurname("Polak")).isTrue();
    }

    @Test
    void ValidateSurnameShouldReturnFalse(){
        assertThat(PatternValidator.validateSurname("Polak-Jablonski.Es")).isFalse();
    }

    @Test
    void ValidateSurnameShouldReturnFalse2(){
        assertThat(PatternValidator.validateSurname("Polak-Jablonski-Es")).isFalse();
    }

    @Test
    void ValidateEmailShouldReturnTrue(){
        assertThat(PatternValidator.validateEmail("gabriel@polak.pl")).isTrue();
    }

    @Test
    void ValidateEmailShouldReturnFalse(){
        assertThat(PatternValidator.validateEmail("ga@briel@polak.pl")).isFalse();
    }
}
