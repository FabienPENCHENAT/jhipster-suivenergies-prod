package com.suivenergies.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.suivenergies.app.web.rest.TestUtil;

public class TD013InstalationECSTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TD013InstalationECS.class);
        TD013InstalationECS tD013InstalationECS1 = new TD013InstalationECS();
        tD013InstalationECS1.setId(1L);
        TD013InstalationECS tD013InstalationECS2 = new TD013InstalationECS();
        tD013InstalationECS2.setId(tD013InstalationECS1.getId());
        assertThat(tD013InstalationECS1).isEqualTo(tD013InstalationECS2);
        tD013InstalationECS2.setId(2L);
        assertThat(tD013InstalationECS1).isNotEqualTo(tD013InstalationECS2);
        tD013InstalationECS1.setId(null);
        assertThat(tD013InstalationECS1).isNotEqualTo(tD013InstalationECS2);
    }
}
