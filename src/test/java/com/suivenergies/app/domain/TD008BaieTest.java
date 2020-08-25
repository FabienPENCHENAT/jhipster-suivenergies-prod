package com.suivenergies.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.suivenergies.app.web.rest.TestUtil;

public class TD008BaieTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TD008Baie.class);
        TD008Baie tD008Baie1 = new TD008Baie();
        tD008Baie1.setId(1L);
        TD008Baie tD008Baie2 = new TD008Baie();
        tD008Baie2.setId(tD008Baie1.getId());
        assertThat(tD008Baie1).isEqualTo(tD008Baie2);
        tD008Baie2.setId(2L);
        assertThat(tD008Baie1).isNotEqualTo(tD008Baie2);
        tD008Baie1.setId(null);
        assertThat(tD008Baie1).isNotEqualTo(tD008Baie2);
    }
}
