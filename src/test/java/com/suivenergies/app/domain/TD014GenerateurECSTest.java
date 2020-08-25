package com.suivenergies.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.suivenergies.app.web.rest.TestUtil;

public class TD014GenerateurECSTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TD014GenerateurECS.class);
        TD014GenerateurECS tD014GenerateurECS1 = new TD014GenerateurECS();
        tD014GenerateurECS1.setId(1L);
        TD014GenerateurECS tD014GenerateurECS2 = new TD014GenerateurECS();
        tD014GenerateurECS2.setId(tD014GenerateurECS1.getId());
        assertThat(tD014GenerateurECS1).isEqualTo(tD014GenerateurECS2);
        tD014GenerateurECS2.setId(2L);
        assertThat(tD014GenerateurECS1).isNotEqualTo(tD014GenerateurECS2);
        tD014GenerateurECS1.setId(null);
        assertThat(tD014GenerateurECS1).isNotEqualTo(tD014GenerateurECS2);
    }
}
