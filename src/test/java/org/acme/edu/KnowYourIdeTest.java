package org.acme.edu;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
// import org.hamcrest.CoreMatchers;
// import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

@QuarkusTest
class KnowYourIdeTest {

    @Inject
    KnowYourIde knowYourIde;

    @Test
    void autocomplete() {
        final var autocomplete = knowYourIde.autocompelete();
        // assertThat
        // MatcherAssert.assertThat(autocomplete, is
        // MatcherAssert.assertThat(autocomplete, CoreMatchers.is("autocomplete"));
        // assertThat(autocomplete, is("autocomplete"));
    }

    @Test
    void methodThrowingException() {
        knowYourIde.methodThrowingException();
    }
}
