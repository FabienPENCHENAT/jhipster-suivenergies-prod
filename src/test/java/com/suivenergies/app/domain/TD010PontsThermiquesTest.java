package com.suivenergies.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.suivenergies.app.web.rest.TestUtil;

public class TD010PontsThermiquesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TD010PontsThermiques.class);
        TD010PontsThermiques tD010PontsThermiques1 = new TD010PontsThermiques();
        tD010PontsThermiques1.setId(1L);
        TD010PontsThermiques tD010PontsThermiques2 = new TD010PontsThermiques();
        tD010PontsThermiques2.setId(tD010PontsThermiques1.getId());
        assertThat(tD010PontsThermiques1).isEqualTo(tD010PontsThermiques2);
        tD010PontsThermiques2.setId(2L);
        assertThat(tD010PontsThermiques1).isNotEqualTo(tD010PontsThermiques2);
        tD010PontsThermiques1.setId(null);
        assertThat(tD010PontsThermiques1).isNotEqualTo(tD010PontsThermiques2);
    }
}
