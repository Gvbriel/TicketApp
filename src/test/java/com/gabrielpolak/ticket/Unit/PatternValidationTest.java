package com.gabrielpolak.ticket.Unit;

import com.gabrielpolak.ticket.validators.PatternValidator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PatternValidationTest {

    @Test
    void ValidateNameShouldReturnTrue() {
        assertThat(PatternValidator.validateName("Gabriel")).isTrue();
    }

    @Test
    void ValidateNameToShortShouldReturnFalse() {
        assertThat(PatternValidator.validateName("Ga")).isFalse();
    }

    @Test
    void ValidateNameWithDashShouldReturnFalse2() {
        assertThat(PatternValidator.validateName("Ga-Da")).isFalse();
    }

    @Test
    void ValidateSurnameShouldReturnTrue() {
        assertThat(PatternValidator.validateSurname("Polak")).isTrue();
    }

    @Test
    void ValidateSurnameWithDotShouldReturnFalse() {
        assertThat(PatternValidator.validateSurname("Polak-Jablonski.Es")).isFalse();
    }

    @Test
    void ValidateSurnameWithTwoDashesShouldReturnFalse2() {
        assertThat(PatternValidator.validateSurname("Polak-Jablonski-Es")).isFalse();
    }

    @Test
    void ValidateEmailShouldReturnTrue() {
        assertThat(PatternValidator.validateEmail("gabriel@polak.pl")).isTrue();
    }

    @Test
    void ValidateEmailWithTwoAtShouldReturnFalse() {
        assertThat(PatternValidator.validateEmail("ga@briel@polak.pl")).isFalse();
    }
}
