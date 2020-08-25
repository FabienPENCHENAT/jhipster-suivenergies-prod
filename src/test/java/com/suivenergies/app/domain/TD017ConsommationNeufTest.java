package com.suivenergies.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.suivenergies.app.web.rest.TestUtil;

public class TD017ConsommationNeufTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TD017ConsommationNeuf.class);
        TD017ConsommationNeuf tD017ConsommationNeuf1 = new TD017ConsommationNeuf();
        tD017ConsommationNeuf1.setId(1L);
        TD017ConsommationNeuf tD017ConsommationNeuf2 = new TD017ConsommationNeuf();
        tD017ConsommationNeuf2.setId(tD017ConsommationNeuf1.getId());
        assertThat(tD017ConsommationNeuf1).isEqualTo(tD017ConsommationNeuf2);
        tD017ConsommationNeuf2.setId(2L);
        assertThat(tD017ConsommationNeuf1).isNotEqualTo(tD017ConsommationNeuf2);
        tD017ConsommationNeuf1.setId(null);
        assertThat(tD017ConsommationNeuf1).isNotEqualTo(tD017ConsommationNeuf2);
    }
}
