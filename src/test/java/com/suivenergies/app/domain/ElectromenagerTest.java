package com.suivenergies.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.suivenergies.app.web.rest.TestUtil;

public class ElectromenagerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Electromenager.class);
        Electromenager electromenager1 = new Electromenager();
        electromenager1.setId(1L);
        Electromenager electromenager2 = new Electromenager();
        electromenager2.setId(electromenager1.getId());
        assertThat(electromenager1).isEqualTo(electromenager2);
        electromenager2.setId(2L);
        assertThat(electromenager1).isNotEqualTo(electromenager2);
        electromenager1.setId(null);
        assertThat(electromenager1).isNotEqualTo(electromenager2);
    }
}
