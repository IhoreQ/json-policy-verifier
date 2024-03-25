package org.ibobek;

import org.ibobek.exception.MissingJSONFieldsException;
import org.ibobek.model.Policy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PolicyValidatorTest {

    private final JSONTestReader jsonTestReader = new JSONTestReader();
    private final PolicyValidator policyValidator = new PolicyValidator();


    static Stream<String> validPolicyFiles() {
        return Stream.of(
                "single-statement-valid-resource-string.json",
                "single-statement-valid-resource-list.json",
                "single-statement-valid-resource-list-with-astrix.json",
                "multiple-statements-valid-resource-list.json",
                "multiple-statements-valid-resource-list-with-astrix.json",
                "multiple-statements-valid-resource-string.json"
        );
    }

    static Stream<String> invalidPolicyFiles() {
        return Stream.of(
                "single-statement-invalid-resource-string.json",
                "single-statement-invalid-resource-list.json",
                "multiple-statements-invalid-resource-string.json",
                "multiple-statements-invalid-resource-list.json"
        );
    }

    static Stream<String> incorrectFormatFiles() {
        return Stream.of(
                "dummy-file.json"
        );
    }

    static Stream<String> missingImportantFieldsFiles() {
        return Stream.of(
                "empty-file.json",
                "no-policy-document-field.json",
                "no-statement-field.json",
                "no-resource-field.json",
                "invalid-issue.json"
        );
    }

    @ParameterizedTest
    @MethodSource("validPolicyFiles")
    @DisplayName("Should return true for all valid policies")
    void validateValidPolicyFile(String fileName) throws IOException, MissingJSONFieldsException {
        Policy policy = jsonTestReader.getPolicyFromFile(fileName);
        boolean result = policyValidator.validate(policy);

        assertTrue(result, "The result for file " + fileName + " should be true");
    }

    @ParameterizedTest
    @MethodSource("invalidPolicyFiles")
    @DisplayName("Should return false for all invalid policies")
    void validateInvalidPolicyFile(String fileName) throws IOException, MissingJSONFieldsException {
        Policy policy = jsonTestReader.getPolicyFromFile(fileName);
        boolean result = policyValidator.validate(policy);

        assertFalse(result, "The result for file " + fileName + " should be false");
    }

    @ParameterizedTest
    @MethodSource("incorrectFormatFiles")
    @DisplayName("Should throw IOException for files in an invalid format")
    void shouldThrowIOExceptionWhenParsing(String fileName) {
        assertThrows(IOException.class,
                () -> jsonTestReader.getPolicyFromFile(fileName),
                "Parsing the file should throw an IOException for file: " + fileName);
    }

    @ParameterizedTest
    @MethodSource("missingImportantFieldsFiles")
    @DisplayName("Should throw IOException for files in an invalid format")
    void shouldThrowMissingJSONFieldsExceptionWhenParsing(String fileName) {
        assertThrows(MissingJSONFieldsException.class,
                () -> jsonTestReader.getPolicyFromFile(fileName),
                "Parsing the file should throw an MissingJSONFieldsException for file: " + fileName);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for non-existent file")
    void shouldThrowExceptionIfFileNotExists() {
        String fileName = "this-file-does-not-exist.json";
        assertThrows(IllegalArgumentException.class,
                () -> jsonTestReader.getPolicyFromFile(fileName),
                "Non-existent file should throw an exception");
    }

}
