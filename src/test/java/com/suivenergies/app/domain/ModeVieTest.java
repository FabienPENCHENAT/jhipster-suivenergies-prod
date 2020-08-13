package com.suivenergies.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.suivenergies.app.web.rest.TestUtil;

public class ModeVieTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ModeVie.class);
        ModeVie modeVie1 = new ModeVie();
        modeVie1.setId(1L);
        ModeVie modeVie2 = new ModeVie();
        modeVie2.setId(modeVie1.getId());
        assertThat(modeVie1).isEqualTo(modeVie2);
        modeVie2.setId(2L);
        assertThat(modeVie1).isNotEqualTo(modeVie2);
        modeVie1.setId(null);
        assertThat(modeVie1).isNotEqualTo(modeVie2);
    }
}
