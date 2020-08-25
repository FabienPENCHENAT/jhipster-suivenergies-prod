package com.suivenergies.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.suivenergies.app.web.rest.TestUtil;

public class TD007ParoiOpaqueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TD007ParoiOpaque.class);
        TD007ParoiOpaque tD007ParoiOpaque1 = new TD007ParoiOpaque();
        tD007ParoiOpaque1.setId(1L);
        TD007ParoiOpaque tD007ParoiOpaque2 = new TD007ParoiOpaque();
        tD007ParoiOpaque2.setId(tD007ParoiOpaque1.getId());
        assertThat(tD007ParoiOpaque1).isEqualTo(tD007ParoiOpaque2);
        tD007ParoiOpaque2.setId(2L);
        assertThat(tD007ParoiOpaque1).isNotEqualTo(tD007ParoiOpaque2);
        tD007ParoiOpaque1.setId(null);
        assertThat(tD007ParoiOpaque1).isNotEqualTo(tD007ParoiOpaque2);
    }
}
