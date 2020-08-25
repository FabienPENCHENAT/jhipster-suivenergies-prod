package com.suivenergies.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.suivenergies.app.web.rest.TestUtil;

public class TD001DPETest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TD001DPE.class);
        TD001DPE tD001DPE1 = new TD001DPE();
        tD001DPE1.setId(1L);
        TD001DPE tD001DPE2 = new TD001DPE();
        tD001DPE2.setId(tD001DPE1.getId());
        assertThat(tD001DPE1).isEqualTo(tD001DPE2);
        tD001DPE2.setId(2L);
        assertThat(tD001DPE1).isNotEqualTo(tD001DPE2);
        tD001DPE1.setId(null);
        assertThat(tD001DPE1).isNotEqualTo(tD001DPE2);
    }
}
