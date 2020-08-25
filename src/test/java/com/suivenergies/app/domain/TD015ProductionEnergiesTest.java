package com.suivenergies.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.suivenergies.app.web.rest.TestUtil;

public class TD015ProductionEnergiesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TD015ProductionEnergies.class);
        TD015ProductionEnergies tD015ProductionEnergies1 = new TD015ProductionEnergies();
        tD015ProductionEnergies1.setId(1L);
        TD015ProductionEnergies tD015ProductionEnergies2 = new TD015ProductionEnergies();
        tD015ProductionEnergies2.setId(tD015ProductionEnergies1.getId());
        assertThat(tD015ProductionEnergies1).isEqualTo(tD015ProductionEnergies2);
        tD015ProductionEnergies2.setId(2L);
        assertThat(tD015ProductionEnergies1).isNotEqualTo(tD015ProductionEnergies2);
        tD015ProductionEnergies1.setId(null);
        assertThat(tD015ProductionEnergies1).isNotEqualTo(tD015ProductionEnergies2);
    }
}
