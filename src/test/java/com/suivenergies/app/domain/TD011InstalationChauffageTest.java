package com.suivenergies.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.suivenergies.app.web.rest.TestUtil;

public class TD011InstalationChauffageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TD011InstalationChauffage.class);
        TD011InstalationChauffage tD011InstalationChauffage1 = new TD011InstalationChauffage();
        tD011InstalationChauffage1.setId(1L);
        TD011InstalationChauffage tD011InstalationChauffage2 = new TD011InstalationChauffage();
        tD011InstalationChauffage2.setId(tD011InstalationChauffage1.getId());
        assertThat(tD011InstalationChauffage1).isEqualTo(tD011InstalationChauffage2);
        tD011InstalationChauffage2.setId(2L);
        assertThat(tD011InstalationChauffage1).isNotEqualTo(tD011InstalationChauffage2);
        tD011InstalationChauffage1.setId(null);
        assertThat(tD011InstalationChauffage1).isNotEqualTo(tD011InstalationChauffage2);
    }
}
