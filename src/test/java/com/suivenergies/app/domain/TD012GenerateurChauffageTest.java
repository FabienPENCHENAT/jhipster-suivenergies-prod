package com.suivenergies.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.suivenergies.app.web.rest.TestUtil;

public class TD012GenerateurChauffageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TD012GenerateurChauffage.class);
        TD012GenerateurChauffage tD012GenerateurChauffage1 = new TD012GenerateurChauffage();
        tD012GenerateurChauffage1.setId(1L);
        TD012GenerateurChauffage tD012GenerateurChauffage2 = new TD012GenerateurChauffage();
        tD012GenerateurChauffage2.setId(tD012GenerateurChauffage1.getId());
        assertThat(tD012GenerateurChauffage1).isEqualTo(tD012GenerateurChauffage2);
        tD012GenerateurChauffage2.setId(2L);
        assertThat(tD012GenerateurChauffage1).isNotEqualTo(tD012GenerateurChauffage2);
        tD012GenerateurChauffage1.setId(null);
        assertThat(tD012GenerateurChauffage1).isNotEqualTo(tD012GenerateurChauffage2);
    }
}
