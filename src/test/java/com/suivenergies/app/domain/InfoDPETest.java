package com.suivenergies.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.suivenergies.app.web.rest.TestUtil;

public class InfoDPETest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InfoDPE.class);
        InfoDPE infoDPE1 = new InfoDPE();
        infoDPE1.setId(1L);
        InfoDPE infoDPE2 = new InfoDPE();
        infoDPE2.setId(infoDPE1.getId());
        assertThat(infoDPE1).isEqualTo(infoDPE2);
        infoDPE2.setId(2L);
        assertThat(infoDPE1).isNotEqualTo(infoDPE2);
        infoDPE1.setId(null);
        assertThat(infoDPE1).isNotEqualTo(infoDPE2);
    }
}
