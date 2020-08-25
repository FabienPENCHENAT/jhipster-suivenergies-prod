package com.suivenergies.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.suivenergies.app.web.rest.TestUtil;

public class TD006BatimentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TD006Batiment.class);
        TD006Batiment tD006Batiment1 = new TD006Batiment();
        tD006Batiment1.setId(1L);
        TD006Batiment tD006Batiment2 = new TD006Batiment();
        tD006Batiment2.setId(tD006Batiment1.getId());
        assertThat(tD006Batiment1).isEqualTo(tD006Batiment2);
        tD006Batiment2.setId(2L);
        assertThat(tD006Batiment1).isNotEqualTo(tD006Batiment2);
        tD006Batiment1.setId(null);
        assertThat(tD006Batiment1).isNotEqualTo(tD006Batiment2);
    }
}
