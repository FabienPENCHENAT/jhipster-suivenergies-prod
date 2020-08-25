package com.suivenergies.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.suivenergies.app.web.rest.TestUtil;

public class TD002ConsommationsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TD002Consommations.class);
        TD002Consommations tD002Consommations1 = new TD002Consommations();
        tD002Consommations1.setId(1L);
        TD002Consommations tD002Consommations2 = new TD002Consommations();
        tD002Consommations2.setId(tD002Consommations1.getId());
        assertThat(tD002Consommations1).isEqualTo(tD002Consommations2);
        tD002Consommations2.setId(2L);
        assertThat(tD002Consommations1).isNotEqualTo(tD002Consommations2);
        tD002Consommations1.setId(null);
        assertThat(tD002Consommations1).isNotEqualTo(tD002Consommations2);
    }
}
