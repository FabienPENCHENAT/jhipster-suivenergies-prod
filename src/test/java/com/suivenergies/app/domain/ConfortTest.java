package com.suivenergies.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.suivenergies.app.web.rest.TestUtil;

public class ConfortTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Confort.class);
        Confort confort1 = new Confort();
        confort1.setId(1L);
        Confort confort2 = new Confort();
        confort2.setId(confort1.getId());
        assertThat(confort1).isEqualTo(confort2);
        confort2.setId(2L);
        assertThat(confort1).isNotEqualTo(confort2);
        confort1.setId(null);
        assertThat(confort1).isNotEqualTo(confort2);
    }
}
